package org.example.salon.Database.Model;

public class facialService extends Service {
    public facialService(String name, double price, int duration) {
        super(name, price, duration);
        this.setCategory("Facial");
    }
}
