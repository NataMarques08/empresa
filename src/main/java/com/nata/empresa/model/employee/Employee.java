package com.nata.empresa.model.employee;


import com.nata.empresa.model.types.Gender;
import com.nata.empresa.model.types.Occupation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Table(name = "employee")
@Entity(name = "Employee")
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Occupation occupation;
    @NotNull
    private Double salary;
    @NotNull
    private Date admission;
    private Date termination;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Employee() {
    }

    public Employee(Long id, String name, String lastname, Occupation occupation, Double salary,
                    Date admission, Date termination, Gender gender) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.occupation = occupation;
        this.salary = salary;
        this.admission = admission;
        this.termination = termination;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(lastname, employee.lastname) && occupation == employee.occupation && Objects.equals(salary, employee.salary) && Objects.equals(admission, employee.admission) && Objects.equals(termination, employee.termination) && gender == employee.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, occupation, salary, admission, termination, gender);
    }


}
