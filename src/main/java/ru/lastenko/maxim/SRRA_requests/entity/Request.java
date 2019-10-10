package ru.lastenko.maxim.SRRA_requests.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "requests")
public class Request {

    @Id
//    @SequenceGenerator(name = "requests_request_id_seq", sequenceName = "requests.requests_request_id_seq")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requests_request_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rubric_id")
    private Rubric rubric;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Column(name = "subject")
    private String subject;

    @Column(name = "short_request")
    private String shortRequest;

    @Column(name = "short_answer")
    private String shortAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id")
    private Source source;

    @Column(name = "is_urgent")
    private boolean urgent;

    @Column(name = "is_gpw")
    private boolean GPW;

    @Column(name = "is_entity")
    private boolean entity;

    @Column(name = "is_consular")
    private boolean consular;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver")
    private Executor receiver;

    @Column(name = "receipt_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate receiptDate;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "department_id")
//    private Department department;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "working_by")
//    private Executor workingBy;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor_id")
    private Executor executor;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "smav")
    private Integer smav;

    @Column(name = "out_number")
    private Integer outNumber;

//    @Column(name = "reg_number")
//    private String regNumber;

    @Column(name = "in_num_from_org")
    private String inNumFromOrg;

    @Column(name = "in_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inDate;

    @Column(name = "copy_number")
    private Integer copyNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public Request() {
    }

    public Request(Integer id, Rubric rubric, Theme theme, String subject, String shortRequest, String shortAnswer, Source source, boolean urgent, boolean GPW, boolean entity, boolean consular, Executor receiver, LocalDate receiptDate, Executor workingBy, LocalDate startDate, Executor executor, LocalDate endDate, Integer smav, Integer outNumber, String regNumber, String inNumFromOrg, LocalDate inDate, Integer copyNumber, Payment payment) {
        this.id = id;
        this.rubric = rubric;
        this.theme = theme;
        this.subject = subject;
        this.shortRequest = shortRequest;
        this.shortAnswer = shortAnswer;
        this.source = source;
        this.urgent = urgent;
        this.GPW = GPW;
        this.entity = entity;
        this.consular = consular;
        this.receiver = receiver;
        this.receiptDate = receiptDate;
//        this.department = department;
//        this.workingBy = workingBy;
        this.startDate = startDate;
        this.executor = executor;
        this.endDate = endDate;
        this.smav = smav;
        this.outNumber = outNumber;
//        this.regNumber = regNumber;
        this.inNumFromOrg = inNumFromOrg;
        this.inDate = inDate;
        this.copyNumber = copyNumber;
        this.payment = payment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getShortRequest() {
        return shortRequest;
    }

    public void setShortRequest(String shortRequest) {
        this.shortRequest = shortRequest;
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isGPW() {
        return GPW;
    }

    public void setGPW(boolean GPW) {
        this.GPW = GPW;
    }

    public boolean isEntity() {
        return entity;
    }

    public void setEntity(boolean entity) {
        this.entity = entity;
    }

    public boolean isConsular() {
        return consular;
    }

    public void setConsular(boolean consular) {
        this.consular = consular;
    }

    public Executor getReceiver() {
        return receiver!=null ? receiver : Executor.EMPTY_EXECUTOR;
    }

    public void setReceiver(Executor receiver) {
        this.receiver = receiver;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
//
//    public Executor getWorkingBy() {
//        return workingBy;
//    }
//
//    public void setWorkingBy(Executor workingBy) {
//        this.workingBy = workingBy;
//    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Executor getExecutor() {
        return executor != null ? executor : Executor.EMPTY_EXECUTOR;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getSmav() {
        return smav;
    }

    public void setSmav(Integer smav) {
        this.smav = smav;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

//    public String getRegNumber() {
//        return regNumber;
//    }
//
//    public void setRegNumber(String regNumber) {
//        this.regNumber = regNumber;
//    }

    public String getInNumFromOrg() {
        return inNumFromOrg;
    }

    public void setInNumFromOrg(String inNumFromOrg) {
        this.inNumFromOrg = inNumFromOrg;
    }

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public Integer getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(Integer copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Payment getPayment() {
        return payment!=null ? payment : Payment.EMPTY_PAYMENT;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return urgent == request.urgent &&
                GPW == request.GPW &&
                entity == request.entity &&
                consular == request.consular &&
                id.equals(request.id) &&
                Objects.equals(rubric, request.rubric) &&
                Objects.equals(theme, request.theme) &&
                Objects.equals(subject, request.subject) &&
                Objects.equals(shortRequest, request.shortRequest) &&
                Objects.equals(shortAnswer, request.shortAnswer) &&
                Objects.equals(source, request.source) &&
                Objects.equals(receiver, request.receiver) &&
                Objects.equals(receiptDate, request.receiptDate) &&
//                Objects.equals(department, request.department) &&
//                Objects.equals(workingBy, request.workingBy) &&
                Objects.equals(startDate, request.startDate) &&
                Objects.equals(executor, request.executor) &&
                Objects.equals(endDate, request.endDate) &&
                Objects.equals(smav, request.smav) &&
                Objects.equals(outNumber, request.outNumber) &&
//                Objects.equals(regNumber, request.regNumber) &&
                Objects.equals(inNumFromOrg, request.inNumFromOrg) &&
                Objects.equals(inDate, request.inDate) &&
                Objects.equals(copyNumber, request.copyNumber) &&
                Objects.equals(payment, request.payment);
    }

    @Override
    public int hashCode() {
//        return Objects.hash(id, rubric, theme, subject, shortRequest, shortAnswer, source, urgent, GPW, entity, consular, receiver, receiptDate, department, workingBy, startDate, executor, endDate, smav, outNumber, regNumber, inNumFromOrg, inDate, copyNumber, payment);
        return Objects.hash(id, rubric, theme, subject, shortRequest, shortAnswer, source, urgent, GPW, entity, consular, receiver, receiptDate, startDate, executor, endDate, smav, outNumber, inNumFromOrg, inDate, copyNumber, payment);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", rubric=" + rubric +
                ", theme=" + theme +
                ", subject='" + subject + '\'' +
                ", shortRequest='" + shortRequest + '\'' +
                ", shortAnswer='" + shortAnswer + '\'' +
                ", source=" + source +
                ", urgent=" + urgent +
                ", GPW=" + GPW +
                ", entity=" + entity +
                ", consular=" + consular +
                ", receiver=" + receiver +
                ", receiptDate=" + receiptDate +
//                ", department=" + department +
//                ", workingBy=" + workingBy +
                ", startDate=" + startDate +
                ", executor=" + executor +
                ", endDate=" + endDate +
                ", smav=" + smav +
                ", outNumber=" + outNumber +
//                ", regNumber='" + regNumber + '\'' +
                ", inNumFromOrg='" + inNumFromOrg + '\'' +
                ", inDate=" + inDate +
                ", copyNumber=" + copyNumber +
                ", payment=" + payment +
                '}';
    }
}
