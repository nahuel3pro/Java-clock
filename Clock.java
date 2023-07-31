public class Clock {
    int hours, minutes, seconds;

    Clock() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    };

    Clock(int hours, int minutes, int seconds) {
        validateTime(hours, minutes, seconds);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public synchronized void chronometer() {
        while (true) {
            showTime();
            updateChronometerValues();
            waitaSecond();
        }
    }

    public synchronized void timer() {
        convertExcessTime();
        while (this.hours != 0 || this.minutes != 0 || this.seconds != -1) {
            showTime();
            updateTimerValues();
            waitaSecond();
        }
    }

    private void waitaSecond() {
        try {
            Thread.sleep(1000); // Wait for one second (1000 milliseconds)
        } catch (InterruptedException e) {
        }
    }

    private void updateChronometerValues() {
        this.seconds++;
        if (this.seconds == 59) {
            this.minutes++;
            this.seconds = 0;
        }
        if (this.minutes == 59) {
            this.hours++;
            this.minutes = 0;
        }
    }

    private void validateTime(int hours, int minutes, int seconds) {
        if (hours < 0 || minutes < 0 || seconds <= 0) {
            throw new IllegalArgumentException(
                    "Invalid time values. Hours, minutes, and seconds must be within valid ranges.");
        }
    }

    private void showTime() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.printf("%s || %s || %s", this.hours, this.minutes, this.seconds);
    }

    private void convertExcessTime() {
        if (this.seconds >= 60) {
            this.minutes += this.seconds / 60;
            this.seconds %= 60;
        }

        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes %= 60;
        }
    }

    private void updateTimerValues() {
        this.seconds--;
        if (this.seconds == -1 && this.minutes != 0) {
            this.minutes--;
            this.seconds = 59;
        }
        if (this.minutes == -1 && this.hours != 0) {
            this.hours--;
            this.minutes = 59;
        }
        if (this.seconds == -1 && this.minutes == 0 && this.hours != 0) {
            this.minutes = 59;
            this.seconds = 59;
            this.hours--;
        }
    }
}
