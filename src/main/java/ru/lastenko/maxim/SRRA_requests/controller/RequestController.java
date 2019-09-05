package ru.lastenko.maxim.SRRA_requests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.lastenko.maxim.SRRA_requests.Service.*;
import ru.lastenko.maxim.SRRA_requests.entity.Request;
import ru.lastenko.maxim.SRRA_requests.util.Pager;
import ru.lastenko.maxim.SRRA_requests.util.RequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class RequestController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private RequestService requestService;
    @Autowired
    private RubricService rubricService;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    HttpServletRequest httpServletRequest;


    /**
     * Handles all requests
     *
     * @param pageSize
     * @param page
     * @return model and view
     */
    @GetMapping("/requests")
    public ModelAndView requests(@RequestParam("pageSize") Optional<Integer> pageSize,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam(required = false) Integer id,
                                 @RequestParam(required = false) Integer outNumber,
                                 @RequestParam(required = false) Integer smav,
                                 @RequestParam(required = false) String theme,
                                 @RequestParam(required = false) String answer,
                                 @RequestParam(required = false) String executor,
                                 @RequestParam(required = false) String executeDate,
                                 @RequestParam(required = false) Boolean caseIns) {

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = INITIAL_PAGE_SIZE; //pageSize.orElse(INITIAL_PAGE_SIZE);

        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        RequestFilter filter = new RequestFilter(id, outNumber, smav, theme, answer, executor, executeDate, caseIns);
        Page<Request> requests = requestService.getByFilter(filter, PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending()));
        Pager pager = new Pager(requests.getTotalPages(), requests.getNumber(), BUTTONS_TO_SHOW);

        ModelAndView modelAndView = new ModelAndView(requests.getTotalElements() == 1 ? "forward:/request" : "requests");

        modelAndView.addObject("filter", filter);
        modelAndView.addObject("requests", requests);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("themes", themeService.getAll());
        modelAndView.addObject("executors", executorService.getAll());

        return modelAndView;
    }

    @GetMapping("/request")
    public ModelAndView request(@RequestParam("pageSize") Optional<Integer> pageSize,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam(required = false) Integer id,
                                @RequestParam(required = false) Integer outNumber,
                                @RequestParam(required = false) Integer smav,
                                @RequestParam(required = false) String theme,
                                @RequestParam(required = false) String answer,
                                @RequestParam(required = false) String executor,
                                @RequestParam(required = false) String executeDate,
                                @RequestParam(required = false) Boolean caseIns) {

        ModelAndView modelAndView = new ModelAndView("request");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = 1; //pageSize.orElse(INITIAL_PAGE_SIZE);

        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        RequestFilter filter = new RequestFilter(id, outNumber, smav, theme, answer, executor, executeDate, caseIns);
        Page<Request> requests = requestService.getByFilter(filter, PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending()));
        Pager pager = new Pager(requests.getTotalPages(), requests.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("filter", filter);
        modelAndView.addObject("requests", requests);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("rubrics", rubricService.getAll());
        modelAndView.addObject("themes", themeService.getAll());
        modelAndView.addObject("sources", sourceService.getAll());
        modelAndView.addObject("executors", executorService.getAll());
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("payments", paymentService.getAll());

        return modelAndView;
    }
}
