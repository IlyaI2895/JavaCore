package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Souvenir {
    private int id;
    private String name;
    Manufacture manufacturer;
    private double price;
    int date;
}
