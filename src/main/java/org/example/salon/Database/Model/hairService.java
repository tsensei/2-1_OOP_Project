package org.example.salon.Database.Model;

public class hairService extends Service {
    public hairService(String name, double price, int duration) {
        super(name, price, duration);
        this.setCategory("Hair");
    }
}
