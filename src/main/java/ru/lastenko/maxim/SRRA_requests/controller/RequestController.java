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
    private static final int INITIAL_PAGE_SIZE = 15;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    private final RequestService requestService;
    private final RubricService rubricService;
    private final ThemeService themeService;
    private final SourceService sourceService;
    private final ExecutorService executorService;
    private final PaymentService paymentService;
    private final HttpServletRequest httpServletRequest;
    private final ModelMapper modelMapper;

    public RequestController(
            RequestService requestService,
            RubricService rubricService,
            ThemeService themeService,
            SourceService sourceService,
            ExecutorService executorService,
            PaymentService paymentService,
            HttpServletRequest httpServletRequest,
            ModelMapper modelMapper) {
        this.requestService = requestService;
        this.rubricService = rubricService;
        this.themeService = themeService;
        this.sourceService = sourceService;
        this.executorService = executorService;
        this.paymentService = paymentService;
        this.httpServletRequest = httpServletRequest;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/requests")
    public ModelAndView requests(
            @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer outNumber,
            @RequestParam(required = false) Integer smav,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String answer,
            @RequestParam(required = false) String executor,
            @RequestParam(required = false) String executeDateFrom,
            @RequestParam(required = false) String executeDateTo,
            @RequestParam(required = false) String inNumFromOrg,
            @RequestParam(required = false) Boolean caseIns) {

        int evalPageSize = INITIAL_PAGE_SIZE;

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        RequestFilter filter = new RequestFilter(id, outNumber, smav, subject, answer, executor, executeDateFrom, executeDateTo, inNumFromOrg, caseIns);
        Pageable pageable = PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending());
        Page<Request> requests = requestService.getByFilter(filter, pageable);
        Page<RequestDto> requestsDto = new PageImpl<>(
                requests.get().map(this::convertToDto).collect(Collectors.toList()),
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

    @PostMapping("/request/update")
    public ModelAndView saveRequest(@ModelAttribute("request") Request request) {
        requestService.save(request);
        return new ModelAndView("redirect:/requests");
    }

    @GetMapping("/request")
    public ModelAndView request(
            @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer outNumber,
            @RequestParam(required = false) Integer smav,
            @RequestParam(required = false) String theme,
            @RequestParam(required = false) String answer,
            @RequestParam(required = false) String executor,
            @RequestParam(required = false) String executeDateFrom,
            @RequestParam(required = false) String executeDateTo,
            @RequestParam(required = false) String inNumFromOrg,
            @RequestParam(required = false) Boolean caseIns) {

        ModelAndView modelAndView = new ModelAndView("request");

        int evalPageSize = 1;
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        RequestFilter filter = new RequestFilter(id, outNumber, smav, theme, answer, executor, executeDateFrom, executeDateTo, inNumFromOrg, caseIns);
        Pageable pageable = PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending());
        Page<Request> requests = requestService.getByFilter(filter, pageable);
        Page<RequestDto> requestsDto = new PageImpl<>(
                requests.get()
                        .map(this::convertToDto)
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

    @GetMapping("/new")
    public ModelAndView addNew() {
        ModelAndView modelAndView = new ModelAndView("newRequest");
        RequestDto requestDto = convertToDto(new Request());
        modelAndView.addObject("filter", new RequestFilter());
        modelAndView.addObject("requests", requestDto);
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
            requestDto.setExpired(daysLeft <= 0);
        }

        return requestDto;
    }
}
