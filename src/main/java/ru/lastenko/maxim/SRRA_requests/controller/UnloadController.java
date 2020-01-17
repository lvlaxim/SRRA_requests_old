package ru.lastenko.maxim.SRRA_requests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.lastenko.maxim.SRRA_requests.util.UnloadFilter;


import java.sql.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/unload")
public class UnloadController {
    private int countOfRecords;

    @RequestMapping(method = RequestMethod.GET)
    public String unloadFromDb(Model model) {
        model.addAttribute("unloadFilter", new UnloadFilter());
        return "unload";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String unloadFromDbWithFilter(Model model, @ModelAttribute("unloadFilter") UnloadFilter uf) {
        connectToDbAndCountUp(uf.getRubricCode(), uf.getSourceId(), uf.getDate());
        model.addAttribute("countOfRecords", countOfRecords);
        return "unload";
    }

    private void connectToDbAndCountUp(String rubricCode, int sourceId, String date) {
        String url = "jdbc:postgresql://server:5433/archive";
        try (Connection connection = DriverManager.getConnection(url, "admin", "adminus")) {
            StringBuilder rubricQuery = new StringBuilder();
            String[] rubricCodes = rubricCode.split("\n");
            for (String rubric:rubricCodes) {
                rubricQuery.append("or rubric_code = ").append('\'').append(rubric).append('\'');
            }
            rubricQuery.delete(0, 2);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT COUNT(*) FROM requests.requests " +
                    "WHERE rubric_id IN " +
                    "(SELECT rubric_id FROM requests.rubrics WHERE " + rubricQuery.toString() +
                    " AND source_id = " + sourceId +
                    " AND receipt_date > '" + date + "'");
            while (resultSet.next()) {
                countOfRecords = resultSet.getInt(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}