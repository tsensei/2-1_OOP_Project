package org.example.salon.Database.Model;

public class LoyaltyProgram {
    private Customer customer;
    private int points;

    public LoyaltyProgram(Customer customer, int points) {
        this.customer = customer;
        this.points = points;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getPoints() {
        return points;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void awardPoints(int amountSpent) {
        int pointsAwarded = amountSpent / 10;
        points += pointsAwarded;
    }

    public void redeemPoints(int pointsToRedeem) {
        points -= pointsToRedeem;
    }

    public void resetPoints() {
        points = 0;
    }

    public boolean hasEnoughPoints(int pointsToRedeem) {
        return points >= pointsToRedeem;
    }

    public void updatePoints(int points) {
        this.points = points;
    }
}
