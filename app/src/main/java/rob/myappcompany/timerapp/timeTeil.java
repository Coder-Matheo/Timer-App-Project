package rob.myappcompany.timerapp;

public class timeTeil {
    private String hours;
    private String minutes;
    private String seconds;
    private byte[] image;

    public timeTeil(String hours, String minutes, String seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    //with image parameter for insert image to database
    public timeTeil(String hours, String minutes, String seconds, byte[] images) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.image = images;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }





}
