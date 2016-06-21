package com.example.admin.myapplication.mobel;

/**
 * Created by Admin on 10/6/2559.
 */
public class vocabulary {
    private int id;
    private String engliesh;
    private String thai;
//    public int id;
//    public String engliesh;
//    public String thai;
    public vocabulary(int id, String engliesh, String thai) {
        this.id = id;
        this.engliesh = engliesh;
        this.thai = thai;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getEngliesh() {
        return engliesh;
    }

    public void setEngliesh(String engliesh) {
        this.engliesh = engliesh;
    }

    public String getThai() {
        return thai;
    }

    public void setThai(String thai) {
        this.thai = thai;
    }

}
