package model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerDto {

    private String CustomerID;

    private String Title;

    private String Name;

    private LocalDate Date_of_birth;

    private double Salary;

    private String Address;

    private  String City;

    private String Province;

    private String PostalCode;
}
