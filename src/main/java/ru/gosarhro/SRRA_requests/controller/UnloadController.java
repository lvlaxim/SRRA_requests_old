package ru.gosarhro.SRRA_requests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gosarhro.SRRA_requests.form.UnloadForm;
import ru.gosarhro.SRRA_requests.service.ExecutorService;
import ru.gosarhro.SRRA_requests.service.RubricService;
import ru.gosarhro.SRRA_requests.service.SourceService;
import ru.gosarhro.SRRA_requests.util.UnloadFilter;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/unload")
public class UnloadController {
    private int countOfRequests;
    private List<UnloadForm> unloadModels;

    private final RubricService rubricService;
    private final SourceService sourceService;
    private final ExecutorService executorService;
    public UnloadController(RubricService rubricService, SourceService sourceService, ExecutorService executorService) {
        this.rubricService = rubricService;
        this.sourceService = sourceService;
        this.executorService = executorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String unloadFromDb(Model model, HttpServletRequest servletRequest) {
        model.addAttribute("unloadFilter", new UnloadFilter());
        model.addAttribute("rubrics", rubricService.getAllOrderById());
        model.addAttribute("sources", sourceService.getAll());
        model.addAttribute("executors", executorService.getAll());
        if (RequestController.whitelistOfIps.contains(servletRequest.getRemoteAddr()))
            model.addAttribute("isPersonal", true);
        return "unload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String unloadFromDbWithFilter(@ModelAttribute("unloadFilter") UnloadFilter unloadFilter, Model model, HttpServletRequest servletRequest) {
        if (unloadFilter.getRubrics() != null) {
            countOfRequests = 0;
            unloadModels = new ArrayList<>();
            connectToDbAndCountUp(
                    unloadFilter.getRubrics(),
                    unloadFilter.getSourceIds(),
                    unloadFilter.getExecutorId(),
                    unloadFilter.getIsEntity(),
                    unloadFilter.getDateFrom(),
                    unloadFilter.getDateTo()
            );

            model.addAttribute("countOfRecords", countOfRequests);
            model.addAttribute("unloadModels", unloadModels);

        } else {
            model.addAttribute("errorMessage", "Не заданы рубрики");
        }
        model.addAttribute("rubrics", rubricService.getAllOrderById());
        model.addAttribute("sources", sourceService.getAll());
        model.addAttribute("executors", executorService.getAll());
        if (RequestController.whitelistOfIps.contains(servletRequest.getRemoteAddr()))
            model.addAttribute("isPersonal", true);
        return "unload";
    }

    private void connectToDbAndCountUp(
            String[] rubricCodes,
            int[] sourceIds,
            int executorId,
            String isEntity,
            String dateFrom,
            String dateTo) {
        String url = "jdbc:postgresql://server:5433/archive";
        try (Connection connection = DriverManager.getConnection(url, "admin", "adminus")) {
            StringBuilder sqlQuery = new StringBuilder("SELECT rubric_code, SUM(copy_number), COUNT(requests.rubric_id) " +
                    "FROM requests.requests " +
                    "INNER JOIN requests.rubrics ON requests.rubric_id = rubrics.rubric_id " +
                    "WHERE requests.rubric_id IN (" +
                    "SELECT rubric_id " +
                    "FROM requests.rubrics WHERE ");
            for (String rubric : rubricCodes) {
                sqlQuery.append("rubric_code = ").append('\'').append(rubric).append('\'').append(" OR ");
            }
            sqlQuery.delete(sqlQuery.length() - 4, sqlQuery.length()).append(')');

            if (sourceIds != null) {
                sqlQuery.append(" AND (");
                for (int sourceId : sourceIds)
                    sqlQuery.append("source_id = ").append(sourceId).append(" OR ");
                sqlQuery.delete(sqlQuery.length() - 4, sqlQuery.length()).append(')');
            }
            if (executorId != 0) {
                sqlQuery.append(" AND executor_id = ").append(executorId);
            }
            if (!isEntity.equals("0")) {
                sqlQuery.append(" AND is_entity = ").append(isEntity);
            }
            if (!dateFrom.equals("")) {
                sqlQuery.append(" AND end_date >= '").append(dateFrom).append('\'');
            }
            if (!dateTo.equals("")) {
                sqlQuery.append(" AND end_date <= '").append(dateTo).append('\'');
            }
            sqlQuery.append(" GROUP BY rubric_code");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery.toString());
            while (resultSet.next()) {
                unloadModels.add(new UnloadForm(
                        resultSet.getString("rubric_code"),
                        resultSet.getInt("sum"),
                        resultSet.getInt("count")));
                countOfRequests += resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}