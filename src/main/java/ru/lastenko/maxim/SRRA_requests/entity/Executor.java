package ru.lastenko.maxim.SRRA_requests.entity;

import javax.persistence.*;

@Entity
@Table(name = "executors", schema = "requests", catalog = "")
public class Executor {

    @Id
    @GeneratedValue
    @Column(name = "executor_id")
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
