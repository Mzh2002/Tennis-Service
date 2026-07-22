package com.example.week3day5.dto;

public class TeamResponse {

    private Integer teamId;
    private String teamName;
    private String country;

    public TeamResponse() {
    }

    public TeamResponse(Integer teamId, String teamName, String country) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.country = country;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
