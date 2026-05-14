package com.splitinam.api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionCode;
    private String title;
    private BigDecimal total;
    private String paidBy;
    private BigDecimal perPerson;
    private LocalDateTime createdAt;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> people;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Boolean> payments;

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public BigDecimal getPerPerson() {
        return perPerson;
    }

    public void setPerPerson(BigDecimal perPerson) {
        this.perPerson = perPerson;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public Map<String, Boolean> getPayments() {
        return payments;
    }

    public void setPayments(Map<String, Boolean> payments) {
        this.payments = payments;
    }
}