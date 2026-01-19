package dto;

import lombok.Data;

@Data
public class SouvenirFromDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer date;

    private Manufacture manufacture;

    @Data
    public static class Manufacture {
        private int manufactureID;
        private String surname;
        private String firstName;
        private int age;
        private String country;

    }

}
