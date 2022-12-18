package com.example.scalardb.model;

public class Contact {
    private int id;
    private String name;
    private String start_time;
    private String end_time;

    public Contact(String name, String start_time, String end_time){
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Contact(int id, String name, String start_time, String end_time){
        this.id = id;
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Contact(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
