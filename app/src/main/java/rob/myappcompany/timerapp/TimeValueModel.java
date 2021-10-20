package rob.myappcompany.timerapp;

public class TimeValueModel {
    private String TIMER_TIME;
    private String TIMER_DESC;
    private byte[] TIMER_IMG;



    public TimeValueModel(String TIMER_TIME, String TIMER_DESC, byte[] TIMER_IMG) {
        this.TIMER_TIME = TIMER_TIME;
        this.TIMER_DESC = TIMER_DESC;
        this.TIMER_IMG = TIMER_IMG;
    }

    public TimeValueModel() {

    }

    public String getTIMER_TIME() {
        return TIMER_TIME;
    }

    public void setTIMER_TIME(String TIMER_TIME) {
        this.TIMER_TIME = TIMER_TIME;
    }

    public String getTIMER_DESC() {
        return TIMER_DESC;
    }

    public void setTIMER_DESC(String TIMER_DESC) {
        this.TIMER_DESC = TIMER_DESC;
    }

    public byte[] getTIMER_IMG() {
        return TIMER_IMG;
    }

    public void setTIMER_IMG(byte[] TIMER_IMG) {
        this.TIMER_IMG = TIMER_IMG;
    }

}
