package mkuzdowicz.drawingapp;

import mkuzdowicz.drawingapp.features.app.DrawApplicationCTX;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        final DrawApplicationCTX drawApp = new DrawApplicationCTX();

        System.out.println("Welcome to drawing program\n");

        while (true) {

            System.out.print("enter command: ");

            final String request = scanner.nextLine();
            final String response = drawApp.controller().executeCommandAndGetResult(request);

            if (response.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println(response);
        }
    }

}
