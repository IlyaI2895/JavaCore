package controller;

import Service.ManufactureService;
import Service.ManufactureServiceImpl;
import model.Manufacture;

import java.util.List;
import java.util.Scanner;

public class ManufactureController {
    private final ManufactureService manufactureService;
    private final Scanner scanner;

    public ManufactureController() {
        manufactureService = new ManufactureServiceImpl();
        scanner = new Scanner(System.in);
    }

    public void start(){
        List<Manufacture> manufactures = manufactureService.getManufactures();
        System.out.println(manufactures);
    }

}
