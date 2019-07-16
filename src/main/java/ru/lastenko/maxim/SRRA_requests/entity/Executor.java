package ru.lastenko.maxim.SRRA_requests.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "executors", schema = "requests", catalog = "")
public class Executor {

    @Id
    @GeneratedValue
    @Column(name = "executor_id")
    private Integer id;

    @Column(name = "executor")
    private String executor;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "job")
    private String job;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    public Executor() {
    }

    public Executor(Integer id, String executor, boolean isActive, String job, String phoneNumber, String email) {
        this.id = id;
        this.executor = executor;
        this.isActive = isActive;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Executor{" +
                "id=" + id +
                ", executor='" + executor + '\'' +
                ", isActive=" + isActive +
                ", job='" + job + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Executor executor1 = (Executor) o;
        return id == executor1.id &&
                isActive == executor1.isActive &&
                executor.equals(executor1.executor) &&
                job.equals(executor1.job) &&
                Objects.equals(phoneNumber, executor1.phoneNumber) &&
                Objects.equals(email, executor1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, executor, isActive, job, phoneNumber, email);
    }
}
