package ru.gosarhro.SRRA_requests.controller;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gosarhro.SRRA_requests.entity.personal_data.ComputerWithAccess;
import ru.gosarhro.SRRA_requests.entity.personal_data.PersonalData;
import ru.gosarhro.SRRA_requests.service.*;
import ru.gosarhro.SRRA_requests.dto.RequestDto;
import ru.gosarhro.SRRA_requests.entity.requests.Executor;
import ru.gosarhro.SRRA_requests.entity.requests.Payment;
import ru.gosarhro.SRRA_requests.entity.requests.Request;
import ru.gosarhro.SRRA_requests.entity.requests.Rubric;
import ru.gosarhro.SRRA_requests.entity.requests.Source;
import ru.gosarhro.SRRA_requests.entity.requests.Theme;
import ru.gosarhro.SRRA_requests.util.Pager;
import ru.gosarhro.SRRA_requests.util.RequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class RequestController {
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 15;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    static List<String> whitelistOfIps;

    private final RequestService requestService;
    private final RubricService rubricService;
    private final ThemeService themeService;
    private final SourceService sourceService;
    private final ExecutorService executorService;
    private final PaymentService paymentService;
    private final PersonalDataService personalDataService;
    private final RequestWithPersonalService requestWithPersonalService;
    private final ComputerWithAccessService computerWithAccessService;

    private final ModelMapper modelMapper;

    public RequestController(
            RequestService requestService,
            RubricService rubricService,
            ThemeService themeService,
            SourceService sourceService,
            ExecutorService executorService,
            PaymentService paymentService,
            PersonalDataService personalDataService,
            RequestWithPersonalService requestWithPersonalService,
            ComputerWithAccessService computerWithAccessService,
            ModelMapper modelMapper) {
        this.requestService = requestService;
        this.rubricService = rubricService;
        this.themeService = themeService;
        this.sourceService = sourceService;
        this.executorService = executorService;
        this.paymentService = paymentService;
        this.personalDataService = personalDataService;
        this.requestWithPersonalService = requestWithPersonalService;
        this.computerWithAccessService = computerWithAccessService;
        this.modelMapper = modelMapper;
        whitelistOfIps = computerWithAccessService.getAll().stream().map(ComputerWithAccess::getIp).collect(Collectors.toList());
    }

    @GetMapping({"/requests", "/"})
    public ModelAndView requests(
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
            @RequestParam(required = false) Boolean caseIns,
            @RequestParam(required = false) String initiator,
            @RequestParam(required = false) String shipment,
            HttpServletRequest servletRequest) {

        int evalPageSize = INITIAL_PAGE_SIZE;
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Pageable pageable = PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending());
        RequestFilter filter = new RequestFilter(id, outNumber, smav, subject, answer, executor, executeDateFrom, executeDateTo, inNumFromOrg, caseIns, initiator, shipment);
        Page<Request> requests;
        if (whitelistOfIps.contains(servletRequest.getRemoteAddr()) && initiator != null && shipment != null) {
            if (!initiator.equals("") || !shipment.equals("")) {
                requests = requestWithPersonalService.getByFilterAndPersonal(filter, pageable);
            } else {
                requests = requestService.getByFilter(filter, pageable);
            }
        } else {
            requests = requestService.getByFilter(filter, pageable);
        }
        Page<RequestDto> requestsDto = new PageImpl<>(requests.get().map(this::convertToDto).collect(Collectors.toList()), pageable, requests.getTotalElements());
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

    @GetMapping("/request")
    public ModelAndView request(
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
            @RequestParam(required = false) Boolean caseIns,
            @RequestParam(required = false) String initiator,
            @RequestParam(required = false) String shipment,
            HttpServletRequest servletRequest) {
        int evalPageSize = 1;
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Pageable pageable = PageRequest.of(evalPage, evalPageSize, Sort.by("id").descending());
        RequestFilter filter = new RequestFilter(id, outNumber, smav, subject, answer, executor, executeDateFrom, executeDateTo, inNumFromOrg, caseIns, initiator, shipment);
        Page<Request> requests;
        if (whitelistOfIps.contains(servletRequest.getRemoteAddr()) && initiator != null && shipment != null) {
            if (!initiator.equals("") || !shipment.equals("")) {
                requests = requestWithPersonalService.getByFilterAndPersonal(filter, pageable);
            } else {
                requests = requestService.getByFilter(filter, pageable);
            }
        } else {
            requests = requestService.getByFilter(filter, pageable);
        }
        Page<RequestDto> requestsDto = new PageImpl<>(requests.get().map(this::convertToDto).collect(Collectors.toList()), pageable, requests.getTotalElements());
        Pager pager = new Pager(requests.getTotalPages(), requests.getNumber(), BUTTONS_TO_SHOW);
        ModelAndView modelAndView = new ModelAndView("request");
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
        System.out.println(servletRequest.getRemoteAddr());
        if (whitelistOfIps.contains(servletRequest.getRemoteAddr())) {
            int requestId = requestsDto.getContent().get(0).getId();
            PersonalData personalData = personalDataService.getById(requestId) == null ?
                    new PersonalData()
                    :
                    personalDataService.getById(requestId);
            modelAndView.addObject("personalData", personalData);
        }
        return modelAndView;
    }

    @GetMapping("/new")
    public String addNew() {
        requestService.save(new Request());
        return "redirect:/request";
    }

    @RequestMapping(value = "/request/update", params = "save", method = RequestMethod.POST)
    public String saveRequest(@ModelAttribute("request") Request request,
                              @ModelAttribute("personalData") PersonalData personalData,
                              HttpServletRequest servletRequest,
                              RedirectAttributes redirectAttributes) {
        requestService.save(request);
        if (whitelistOfIps.contains(servletRequest.getRemoteAddr())) {
            personalDataService.save(new PersonalData(request.getId(), personalData.getRequestInitiator(), personalData.getShipment()));
        }
        redirectAttributes.addFlashAttribute("action", "Изменения сохранены");
        return "redirect:" + servletRequest.getHeader("Referer");
    }

    @RequestMapping(value = "/request/update", params = "printInquiry", method = RequestMethod.POST)
    public String printInquiry(@ModelAttribute("request") Request request,
                               @ModelAttribute("personalData") PersonalData personalData,
                               Model model,
                               HttpServletRequest servletRequest,
                               RedirectAttributes redirectAttributes) {
        LocalDate endDate = requestService.getById(request.getId()).getEndDate();
        if (endDate == null || !LocalDate.now().isAfter(endDate.plusDays(10))) {
            requestService.save(request);
            if (whitelistOfIps.contains(servletRequest.getRemoteAddr())) {
                personalDataService.save(new PersonalData(request.getId(), personalData.getRequestInitiator(), personalData.getShipment()));
            }
        }
        if (request.getEndDate() == null) {
            redirectAttributes.addFlashAttribute("action", "Не задана дата исполнения");
            return "redirect:" + servletRequest.getHeader("Referer");
        }
        if (personalData.getRequestInitiator().equals("")) {
            redirectAttributes.addFlashAttribute("action", "Не задан инициатор");
            return "redirect:" + servletRequest.getHeader("Referer");

        }
        model.addAttribute("request", request);
        model.addAttribute("personalData", personalDataService.getById(request.getId()).getRequestInitiator());
        model.addAttribute("isInquiry", true);
        return "print_page";
    }

    @RequestMapping(value = "/request/update", params = "printLetter", method = RequestMethod.POST)
    public String printLetter(@ModelAttribute("request") Request request,
                              @ModelAttribute("personalData") PersonalData personalData,
                              Model model,
                              HttpServletRequest servletRequest,
                              RedirectAttributes redirectAttributes) {
        LocalDate endDate = requestService.getById(request.getId()).getEndDate();
        if (endDate == null || !LocalDate.now().isAfter(endDate.plusDays(10))) {
            requestService.save(request);
            if (whitelistOfIps.contains(servletRequest.getRemoteAddr())) {
                personalDataService.save(new PersonalData(request.getId(), personalData.getRequestInitiator(), personalData.getShipment()));
            }
        }
        if (request.getEndDate() == null) {
            redirectAttributes.addFlashAttribute("action", "Не задана дата исполнения");
            return "redirect:" + servletRequest.getHeader("Referer");
        }
        if (personalData.getShipment().equals("")) {
            redirectAttributes.addFlashAttribute("action", "Не задана пересылка");
            return "redirect:" + servletRequest.getHeader("Referer");
        }
        model.addAttribute("request", request);
        model.addAttribute("personalData", personalDataService.getById(request.getId()).getShipment());
        return "print_page";
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
        requestDto.setChangeable(true);
        if (request.getEndDate() != null
                && LocalDate.now().isAfter(request.getEndDate().plusDays(10))) {
            requestDto.setChangeable(false);
        }
        if (request.getReceiptDate() != null) {
            Long daysLeft = DAYS.between(LocalDate.now(), request.getReceiptDate()) + 30;
            requestDto.setDaysLeft(daysLeft);
            requestDto.setExpired(daysLeft <= 0);
        }
        return requestDto;
    }
}
