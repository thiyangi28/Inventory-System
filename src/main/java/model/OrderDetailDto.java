package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailDto {

    private String OrderId;

    private String ItemCode;

    private Integer OrderQTY;

    private Integer Discount;
}
