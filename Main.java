import java.util.Scanner;
import java.time.LocalTime;

public class Main {
    public static void main(String args[]) {
        int hours, minutes, seconds, option;
        final int EXIT_OPTION = 4;
        Scanner scanner = new Scanner(System.in);
        Clock clock = new Clock();

        do {
            do {
                System.out.println("\n1. Timer ");
                System.out.println("\n2. Current Time ");
                System.out.println("\n3. Chronometer ");
                System.out.println("\n4. Exit");
                option = scanner.nextInt();
            } while (option < 1 || option > EXIT_OPTION);

            switch (option) {
                case 1:
                    System.out.print("Enter hours: ");
                    hours = scanner.nextInt();
                    System.out.print("Enter minutes: ");
                    minutes = scanner.nextInt();
                    System.out.print("Enter seconds: ");
                    seconds = scanner.nextInt();

                    clock.setTimer(hours, minutes, seconds);
                    clock.timer();
                    break;
                case 2:
                    LocalTime currentTime = LocalTime.now();

                    // Extract hour, minute, and second from the current time
                    int currentHour = currentTime.getHour();
                    int currentMinute = currentTime.getMinute();
                    int currentSecond = currentTime.getSecond();

                    clock.setCurrentLocalTime(currentHour, currentMinute, currentSecond);
                    clock.sysClock();
                    break;
                case 3:
                    clock.setChronometer();
                    clock.chronometer();
                    break;
            }
        } while (option != EXIT_OPTION);

        System.out.println("Okay, see you then!");
        scanner.close();
    }
}
