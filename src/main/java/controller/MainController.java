package controller;

import java.util.Scanner;

public class MainController {
    public static void start() {
        boolean running = true;

        while (running) {
            System.out.println("print souvenir or manufacture");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.nextLine().trim();

            switch (next) {
                case "souvenir", "s" -> new SouvenirsController().start();
                case "manufacture", "m" -> new ManufactureController().start();
                case "0", "exit" -> running = false;
            }
        }
    }
}
