package nl.novi.vinylshop.dtos;

import jakarta.validation.constraints.Positive;

public class StockRequestDTO {

    private String condition;

    @Positive
    private double price;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}