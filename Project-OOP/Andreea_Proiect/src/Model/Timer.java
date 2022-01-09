package Model;


public class Timer extends Thread {
    private int minutes;
    private int seconds;
    private boolean runnable;

    /**
     * Constructor
     */
    public Timer() {
        this.minutes = 30;
        this.seconds = 0;
        this.runnable = true;
    }

    /**
     * Returneaza minutele si secundele
     *
     * @return timpul ca un string
     */
    public String getTime() {
        String minutes = (this.minutes > 9) ? String.valueOf(this.minutes) : "0" + this.minutes;
        String seconds = (this.seconds > 9) ? String.valueOf(this.seconds) : "0" + this.seconds;
        return minutes + ":" + seconds;
    }

    @Override
    public void run() {

        while (minutes + seconds > 0 && runnable) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (seconds > 0)
                seconds--;
            else {
                minutes--;
                seconds = 59;
            }
        }
    }

    /**
     * stop
     */
    public void stopExecution() {
        this.runnable = false;
    }
}
