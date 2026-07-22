package com.example.week3day5.dto;

import com.example.week3day5.entity.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PlayerRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @Min(1)
    private Double heightCm;

    @NotNull
    @Min(1)
    private Double weightKg;

    @NotNull
    @Min(0)
    private Integer careerTitle;

    @NotNull
    @Min(0)
    private Integer careerWins;

    @NotBlank
    private String country;

    @NotNull
    @Min(0)
    private Integer ranking;

    private Integer teamId;

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
}
