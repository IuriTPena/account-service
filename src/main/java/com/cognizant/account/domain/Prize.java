package com.cognizant.account.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Prize implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALUE")
    private Integer value;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "prize")
    @JsonBackReference
    private Account account;

    public Prize() {
    }

    public Prize(Integer value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", value=" + value +
                ", consumer=" + account +
                '}';
    }
}
