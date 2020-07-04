package edu.utn.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Bill {



    String line;
    Float priceTotal;
    LocalDate date;
    LocalDate dateExpiration;
    boolean isPaid;
    User user;
    Integer quantityOfCalls;

}
