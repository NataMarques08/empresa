package com.nata.empresa.data.dto;

import com.nata.empresa.model.types.Gender;
import com.nata.empresa.model.types.Occupation;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

public class EmployeeDTO extends RepresentationModel<EmployeeDTO>{
        private String name;
        private String lastname;
        private Occupation occupation;
        private Double salary;
        private Date admission;
        private Date termination;
        private Gender gender;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String lastname, Occupation occupation, Double salary, Date admission, Date termination, Gender gender) {
        this.name = name;
        this.lastname = lastname;
        this.occupation = occupation;
        this.salary = salary;
        this.admission = admission;
        this.termination = termination;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getAdmission() {
        return admission;
    }

    public void setAdmission(Date admission) {
        this.admission = admission;
    }

    public Date getTermination() {
        return termination;
    }

    public void setTermination(Date termination) {
        this.termination = termination;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}