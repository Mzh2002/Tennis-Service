package com.example.week3day5.dto;

import com.example.week3day5.entity.Gender;

import java.time.LocalDate;

public class PlayerResponse {

    private Integer playerId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private Integer age;
    private Double heightCm;
    private Double weightKg;
    private Integer careerTitle;
    private Integer careerWins;
    private String country;
    private Integer ranking;
    private Integer teamId;
    private String teamName;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getCareerTitle() {
        return careerTitle;
    }

    public void setCareerTitle(Integer careerTitle) {
        this.careerTitle = careerTitle;
    }

    public Integer getCareerWins() {
        return careerWins;
    }

    public void setCareerWins(Integer careerWins) {
        this.careerWins = careerWins;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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
}
