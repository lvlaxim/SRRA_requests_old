package ru.lastenko.maxim.SRRA_requests.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lastenko.maxim.SRRA_requests.dto.RequestDto;
import ru.lastenko.maxim.SRRA_requests.entity.*;
import ru.lastenko.maxim.SRRA_requests.service.*;
import ru.lastenko.maxim.SRRA_requests.util.Pager;
import ru.lastenko.maxim.SRRA_requests.util.RequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

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
    private PaymentService paymentService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    ModelMapper modelMapper;


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
        Pageable pageable = PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending());
        Page<Request> requests = requestService.getByFilter(filter, pageable);
        Page<RequestDto> requestsDto = new PageImpl<>(
                requests.get()
                        .map(request -> convertToDto(request))
                        .collect(Collectors.toList()),
                pageable,
                requests.getTotalElements());
        Pager pager = new Pager(requests.getTotalPages(), requests.getNumber(), BUTTONS_TO_SHOW);

        ModelAndView modelAndView = new ModelAndView(requests.getTotalElements() == 1 ? "forward:/request" : "requests");

        modelAndView.addObject("filter", filter);
        modelAndView.addObject("requests", requestsDto);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("themes", themeService.getAll());
        modelAndView.addObject("executors", executorService.getAll());

        return modelAndView;
    }

    //    @PostMapping("/request/update")
//    public ModelAndView saveRequest(@RequestParam Integer outNumber,
//                              @RequestParam String receiver, Model model) {
//        System.out.println(outNumber);
//        return new ModelAndView("redirect:/requests");
//    }
    @PostMapping("/request/update")
    public ModelAndView saveRequest(@ModelAttribute("request") Request request, Model model) {
//        System.out.println(request);
        requestService.save(request);
        return new ModelAndView("redirect:/requests");
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
        Pageable pageable = PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending());
        Page<Request> requests = requestService.getByFilter(filter, pageable);
        Page<RequestDto> requestsDto = new PageImpl<>(
                requests.get()
                        .map(request -> convertToDto(request))
                        .collect(Collectors.toList()),
                pageable,
                requests.getTotalElements());
        Pager pager = new Pager(requests.getTotalPages(), requests.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("filter", filter);
        modelAndView.addObject("requests", requestsDto);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);

        modelAndView.addObject("rubrics", rubricService.getAll());
        modelAndView.addObject("themes", themeService.getAll());
        modelAndView.addObject("sources", sourceService.getAll());
        modelAndView.addObject("executors", executorService.getAll());
        modelAndView.addObject("payments", paymentService.getAll());

        return modelAndView;
    }

    private RequestDto convertToDto(Request request) {

        RequestDto requestDto = modelMapper.map(request, RequestDto.class);

        if (request.getRubric() == null) {
            requestDto.setRubric(Rubric.EMPTY_RUBRIC);
        }
        if (request.getTheme() == null) {
            requestDto.setTheme(Theme.EMPTY_THEME);
        }
        if (request.getSource() == null) {
            requestDto.setSource(Source.EMPTY_SOURCE);
        }
        if (request.getExecutor() == null) {
            requestDto.setExecutor(Executor.EMPTY_EXECUTOR);
        }
        if (request.getPayment() == null) {
            requestDto.setPayment(Payment.EMPTY_PAYMENT);
        }

        requestDto.setChangeable(false);
        if (request.getEndDate() != null
                && LocalDate.now().isAfter(request.getEndDate().plusDays(10))) {
            requestDto.setChangeable(true);
        }

        if (request.getReceiptDate() != null) {
            Long daysLeft = DAYS.between(LocalDate.now(), request.getReceiptDate()) + 30;
            requestDto.setDaysLeft(daysLeft);
            requestDto.setExpired(daysLeft>0 ? false : true);
        }

        return requestDto;
    }
}
