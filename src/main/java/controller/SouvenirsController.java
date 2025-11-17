package controller;

import Service.SouvenirsServiceImpl;
import model.Manufacture;
import model.Souvenir;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SouvenirsController {
    private final SouvenirsServiceImpl souvenirsService;
    private final Scanner scanner;
    public SouvenirsController() {
        this.souvenirsService = new SouvenirsServiceImpl();
        this.scanner = new Scanner(System.in);
    }
    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1 -> getAllSouvenirs() ;
                case 2 -> addSouvenir();
                case 3 -> getSouvenirsByManufacture() ;
                case 4 -> getSouvenirByCountry();
                case 5 -> manufacturedFromSouvenirsPrice();
                case 6 -> getManufactureFromYearsProduct();
                case 7 -> deleteManufacture() ;
                case 0 -> {
                    running = false;
                    System.out.println("Выход из программы...");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }

            if (running) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
    }


    private void printMenu() {
        System.out.println("\n=== МЕНЮ CУВЕНИРОВ ===");
        System.out.println("1. Посмотреть все сувениры"); //+
        System.out.println("2. Добавить сувенир"); //+
        System.out.println("3. Вывести информацию о сувенирах заданного производителя"); //+
        System.out.println("4. Вывести информацию о сувенирах, произведенных в заданной стране"); //+
        System.out.println("5. Вывести информацию о производителях, чьи цены на сувениры меньше заданной.");
        System.out.println("6. Вывести информацию о производителях заданного сувенира,` произведенного в заданном году.");//+
        System.out.println("7. Удалить заданного производителя и его сувениры.");
        System.out.println("0. Выход");
        System.out.println("=======================");
    }

    public void deleteManufacture() {
        System.out.println("\n=== УДАЛЕНИЕ ПРОИЗВОДИТЕЛЯ И ЕГО СУВЕНИРОВ ===");
        String manufactureName = getStringInput("Введите имя  производителя");
        String manufactureFirstName =getStringInput("Введите Фамилию производителя");
        Manufacture manufacture = new Manufacture().builder()
                .surname(manufactureName)
                .firstName(manufactureFirstName)
                .build();
        souvenirsService.deleteManufacture(manufacture);
        System.out.println("Удаление завершено (Если данный производитель существует)");

    }
    public void getManufactureFromYearsProduct() {
        System.out.println("\n=== ИНФОРМАЦИЯ О ПРОИЗВОДИТЕЛЯХ СУВЕНИРА , ПРОИЗВЕДЕННОГО В ДАННОМ ГОДУ");
        String name = getStringInput("Введите имя сувенира");
        int year = getIntInput("Введите год выпука");
        List<Souvenir> manufactureFromYearsProduct = souvenirsService.getManufactureFromYearsProduct(name, year);
        displaySouvenirs(manufactureFromYearsProduct);
         }

    public void manufacturedFromSouvenirsPrice() {
        System.out.println("\n=== ВЫВОД ИНФОРМАЦИИ О ПРОИЗВОДИТЕЛЯХ, У КОТОРЫХ ЦЕНЫ НИЖЕ ЗАДАННОЙ");
        int price =getIntInput("Введите цену");
        List<Manufacture> manufactures = souvenirsService.manufacturedFromSouvenirsPrice(price);
        displayManufactured(manufactures);

    }

    public void getSouvenirByCountry() {
        System.out.println("\n=== ИНФОРМАЦИЯ О СУВЕНИРАХ, ПРОИЗВЕДЕННЫХ В ДАННОЙ СТРАНЕ");
        String country = getStringInput("Введите страну");
        List<Souvenir> souvenirList = souvenirsService.getSouvenirByCountry(country);
        displaySouvenirs(souvenirList);
    }


     private void getSouvenirsByManufacture(){
        System.out.println("\n=== ИНФОРМАЦИЯ О СУВЕНИРАХ ЗАДАННОГО ПРОИЗВОДИТЕЛЯ ===");
        String manufacture = getStringInput("Введите имя Производителя");
        List<Souvenir> souvenirList = souvenirsService.getSouvenirsByManufacture(manufacture);
        displaySouvenirs(souvenirList);
    };

    private void addSouvenir() {
        System.out.println("\n=== ДОБАВЛЕНИЕ СУВЕНИРА ===");

        int id = getIntInput("ID сувенира: ");
        String name = getStringInput("Название: ");
        double price = getDoubleInput("Цена");

        System.out.println("Производитель:");
        int manufactureID = getIntInput("ID производителя: ");
        String firstName = getStringInput("Имя производителя: ");
        String surname = getStringInput("Фамилия производителя: ");
        String country = getStringInput("Страна");
        int age = getIntInput("Возраст");
        Manufacture manufacture = new Manufacture(manufactureID,surname,firstName,age,country);

        int year = getIntInput("Год производства: ");

        Souvenir souvenir = new Souvenir(id,name,manufacture,price,year);
        souvenirsService.addSouvenir(souvenir);
        System.out.println("Сувенир успешно добавлен!");
    }
    private void displaySouvenirs(List<Souvenir> souvenirs) {
        if (souvenirs.isEmpty()) {
            System.out.println("Сувениры не найдены");
            return;
        }

        System.out.println("Найдено сувениров: " + souvenirs.size());
        IntStream.range(0, souvenirs.size())
                .forEach(i -> System.out.printf("%d. %s\n", i + 1, souvenirs.get(i)));
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число");
            }
        }
    }
    private void displayManufactured(List<Manufacture> manufactures) {
        if (manufactures.isEmpty()) {
            System.out.println("Производители  не найдены");
            return;
        }
        System.out.println("Найдено ппроизводителей " + manufactures.size());

        IntStream.range(0, manufactures.size())
                .forEach(i -> System.out.printf("%d. %s\n", i + 1, manufactures.get(i)));
    }
    void getAllSouvenirs(){
        List<Souvenir> souvenirs = souvenirsService.getSouvenirs();
        displaySouvenirs(souvenirs);
    }

}
