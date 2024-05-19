package org.example.salon.Database.Model;

public class nailService extends Service {
    public nailService(String name, double price, int duration) {
        super(name, price, duration);
        this.setCategory("Nails");
    }
}
