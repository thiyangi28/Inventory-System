package model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerDto {

    private String customerId;
    private String title;
    private String name;
    private LocalDate dateOfBirth;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
