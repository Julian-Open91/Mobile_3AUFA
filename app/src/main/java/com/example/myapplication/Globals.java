package com.example.myapplication;

        import android.app.Application;

public class Globals extends Application {
    private String status1 = "";
    private String status2 = "";

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }
}
