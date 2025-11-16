package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Manufacture {
    private int manufactureID;
    private String surname;
    private String firstName;
    private int age;
    private String country;

}
