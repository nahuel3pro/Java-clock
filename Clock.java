
public class Clock {
    private int hours, minutes, seconds;
    private static final int MAX_TIME_VALUE = 59;
    private static final int MIN_TIME_VALUE = -1;

    // Setters
    public void setTimer(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void setCurrentLocalTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void setChronometer() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    // Methods
    public synchronized void chronometer() {
        validateTime();
        while (true) {
            showTime();
            updateChronometerValues();
            waitaSecond();
        }
    }

    public synchronized void timer() {
        convertExcessTime();
        while (hours != 0 || minutes != 0 || seconds != -1) {
            showTime();
            updateTimerValues();
            waitaSecond();
        }
    }

    public synchronized void sysClock() {
        while (true) {
            showTime();
            waitaSecond();
            updateChronometerValues();
        }
    }

    private void waitaSecond() {
        try {
            Thread.sleep(1000); // Wait for one second (1000 milliseconds)
        } catch (InterruptedException e) {
        }
    }

    private void updateChronometerValues() {
        seconds++;
        if (seconds == MAX_TIME_VALUE) {
            minutes++;
            seconds = 0;
        }
        if (minutes == MAX_TIME_VALUE && seconds == MAX_TIME_VALUE) {
            hours++;
            minutes = 0;
        }
    }

    private void validateTime() {
        if (hours < 0 || minutes < 0 && seconds <= 0) {
            throw new IllegalArgumentException(
                    "Invalid time values. Hours, minutes, and seconds must be within valid ranges.");
        }
    }

    private void showTime() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.printf("%s || %s || %s", hours, minutes, seconds);
    }

    private void convertExcessTime() {
        if (seconds >= MAX_TIME_VALUE + 1) {
            minutes += seconds / MAX_TIME_VALUE + 1;
            seconds %= MAX_TIME_VALUE + 1;
        }

        if (minutes >= MAX_TIME_VALUE + 1) {
            hours += minutes / MAX_TIME_VALUE + 1;
            minutes %= MAX_TIME_VALUE + 1;
        }
    }

    private void updateTimerValues() {
        seconds--;
        if (seconds == MIN_TIME_VALUE && minutes != 0) {
            minutes--;
            seconds = MAX_TIME_VALUE;
        }
        if (minutes == MIN_TIME_VALUE && hours != 0) {
            hours--;
            minutes = MAX_TIME_VALUE;
        }
        if (seconds == MIN_TIME_VALUE && minutes == 0 && hours != 0) {
            minutes = MAX_TIME_VALUE;
            seconds = MAX_TIME_VALUE;
            hours--;
        }
    }
}
