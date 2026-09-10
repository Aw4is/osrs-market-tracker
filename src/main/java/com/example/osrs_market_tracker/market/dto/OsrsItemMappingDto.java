package com.example.osrs_market_tracker.market.dto;

import lombok.NoArgsConstructor;

// Jackson uses JSON to create new OsrsItemMapping Dto
// But it uses setters not constructor hence we need no args constructor
    // In this case, it can use constructors with args if you use configuration
@NoArgsConstructor
public class OsrsItemMappingDto {

    private Long id;

    private String name;

    private Boolean members;

    private Integer highalch;

    private Integer lowalch;

    private Integer limit;


    public OsrsItemMappingDto(Long id, String name, Boolean members, Integer highalch, Integer lowalch, Integer limit) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.highalch = highalch;
        this.lowalch = lowalch;
        this.limit = limit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(Boolean members) {
        this.members = members;
    }

    public void setHighalch(Integer highalch) {
        this.highalch = highalch;
    }

    public void setLowalch(Integer lowalch) {
        this.lowalch = lowalch;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getMembers() {
        return members;
    }

    public Integer getHighalch() {
        return highalch;
    }

    public Integer getLowalch() {
        return lowalch;
    }

    public Integer getLimit() {
        return limit;
    }
}



