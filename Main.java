import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        int hours, minutes, seconds, option;
        final int EXIT_OPTION = 4;
        Scanner scanner = new Scanner(System.in);

        do {
            do {
                System.out.println("\n1. Timer ");
                System.out.println("\n2. System Clock ");
                System.out.println("\n3. Chronometer ");
                System.out.println("\n4. Exit");
                option = scanner.nextInt();
            } while (option <= 0 || option > EXIT_OPTION);

            switch (option) {
                case 1:
                    System.out.println("Enter hours: ");
                    hours = scanner.nextInt();
                    System.out.println("Enter minutes: ");
                    minutes = scanner.nextInt();
                    System.out.println("Enter seconds: ");
                    seconds = scanner.nextInt();

                    Clock clock = new Clock(hours, minutes, seconds);
                    clock.timer();
                    break;
                case 2:
                    System.out.println("Sys clock ");
                    break;
                case 3:
                    System.out.println("Cronhometer ");
                    Clock clock2 = new Clock();
                    clock2.chronometer();
                    break;
            }
        } while (option != EXIT_OPTION);

        System.out.println("Okay, see you then!");
        scanner.close();
    }
}
