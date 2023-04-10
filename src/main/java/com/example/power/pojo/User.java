package com.example.power.pojo;



public class User {

    private String PWID;
    private String username;
    private String password;
    private int benchPress;
    private int pullHard;
    private int deepSquat;
    private int total;

    public String getPWID() {
        return PWID;
    }

    public void setPWID(String PWID) {
        this.PWID = PWID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBenchPress() {
        return benchPress;
    }

    public void setBenchPress(int benchPress) {
        this.benchPress = benchPress;
    }

    public int getPullHard() {
        return pullHard;
    }

    public void setPullHard(int pullHard) {
        this.pullHard = pullHard;
    }

    public int getDeepSquat() {
        return deepSquat;
    }

    public void setDeepSquat(int deepSquat) {
        this.deepSquat = deepSquat;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "User{" +
                "PWID='" + PWID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", benchPress=" + benchPress +
                ", pullHard=" + pullHard +
                ", deepSquat=" + deepSquat +
                ", total=" + total +
                '}';
    }
}
