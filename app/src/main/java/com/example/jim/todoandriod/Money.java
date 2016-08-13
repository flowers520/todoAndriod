package com.example.jim.todoandriod;

public class Money {
    int id;
    String matter;
    String dtime;
    double price;
    String type;

    public Money(int id, String matter, double price, String dtime, String type){
        this.id = id;
        this.matter = matter;
        this.dtime = dtime;
        this.price = price;
        this.type = type;
    }

}
