package com.example.osrs_market_tracker.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean members;

    @Column
    private Integer highAlch;

    @Column
    private Integer lowAlch;

    @Column
    private Integer buyLimit;

    @Column(nullable = false, unique = true)
    private Long externalId;


    public Item() {
    }

    public Item(
            Long externalId,
            String name,
            Boolean members,
            Integer highAlch,
            Integer lowAlch,
            Integer buyLimit
    ) {
        this.externalId = externalId;
        this.name = name;
        this.members = members;
        this.highAlch = highAlch;
        this.lowAlch = lowAlch;
        this.buyLimit = buyLimit;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public Long getExternalId() {
        return externalId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMembers() {
        return members;
    }

    public void setMembers(Boolean members) {
        this.members = members;
    }

    public Integer getHighAlch() {
        return highAlch;
    }

    public void setHighAlch(Integer highAlch) {
        this.highAlch = highAlch;
    }

    public Integer getLowAlch() {
        return lowAlch;
    }

    public void setLowAlch(Integer lowAlch) {
        this.lowAlch = lowAlch;
    }

    public Integer getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(Integer buyLimit) {
        this.buyLimit = buyLimit;
    }
}