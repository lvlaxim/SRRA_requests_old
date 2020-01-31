package ru.lastenko.maxim.SRRA_requests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.lastenko.maxim.SRRA_requests.model.UnloadModel;
import ru.lastenko.maxim.SRRA_requests.service.ExecutorService;
import ru.lastenko.maxim.SRRA_requests.service.RubricService;
import ru.lastenko.maxim.SRRA_requests.service.SourceService;
import ru.lastenko.maxim.SRRA_requests.util.UnloadFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/unload")
public class UnloadController {
    private int countOfRequests;
    private List<UnloadModel> unloadModels;

    private final RubricService rubricService;
    private final SourceService sourceService;
    private final ExecutorService executorService;

    public UnloadController(RubricService rubricService, SourceService sourceService, ExecutorService executorService) {
        this.rubricService = rubricService;
        this.sourceService = sourceService;
        this.executorService = executorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String unloadFromDb(Model model) {
        model.addAttribute("unloadFilter", new UnloadFilter());
        model.addAttribute("rubrics", rubricService.getAllOrderById());
        model.addAttribute("sources", sourceService.getAll());
        model.addAttribute("executors", executorService.getAll());
        return "unload";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String unloadFromDbWithFilter(@ModelAttribute("unloadFilter") UnloadFilter unloadFilter, Model model) {
        if (unloadFilter.getRubrics() != null) {
            if (unloadFilter.getDateFrom().equals("") || unloadFilter.getDateTo().equals(""))
                model.addAttribute("errorMessage", "Задайте дату");
            else {
                countOfRequests = 0;
                unloadModels = new ArrayList<>();
                connectToDbAndCountUp(
                        unloadFilter.getRubrics(),
                        unloadFilter.getSourceId(),
                        unloadFilter.getExecutorId(),
                        unloadFilter.isEntity(),
                        unloadFilter.getDateFrom(),
                        unloadFilter.getDateTo());

                model.addAttribute("countOfRecords", countOfRequests);
                model.addAttribute("unloadModels",unloadModels);
            }
        } else {
            model.addAttribute("errorMessage", "Не заданы рубрики");
        }
        model.addAttribute("rubrics", rubricService.getAllOrderById());
        model.addAttribute("sources", sourceService.getAll());
        model.addAttribute("executors", executorService.getAll());
        return "unload";
    }

    private void connectToDbAndCountUp(String[] rubricCodes, int sourceId, int executorId, boolean isEntity, String dateFrom, String dateTo) {
        String url = "jdbc:postgresql://server:5433/archive";
        try (Connection connection = DriverManager.getConnection(url, "admin", "adminus")) {
            StringBuilder rubricQuery = new StringBuilder();
            for (String rubric : rubricCodes) {
                rubricQuery.append(" OR rubric_code = ").append('\'').append(rubric).append('\'');
            }
            rubricQuery.delete(0, 4);

            String sql = String.format(
                    "SELECT rubric_code, SUM(copy_number), COUNT(requests.rubric_id) " +
                            "FROM requests.requests " +
                            "INNER JOIN requests.rubrics ON requests.rubric_id = rubrics.rubric_id " +
                            "WHERE requests.rubric_id IN (" +
                            "SELECT rubric_id " +
                            "FROM requests.rubrics WHERE %s" +
                            ") " +
                            "AND source_id = %s " +
                            "AND receipt_date > '%s' " +
                            "GROUP BY rubric_code",
                    rubricQuery.toString(),
                    sourceId,
                    dateFrom);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                unloadModels.add(new UnloadModel(
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