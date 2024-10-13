package com.iths.pojo;

public class Ingredients {
    private String name;
    private double amount;
    private String unit;

    public Ingredients() {
    }

    public Ingredients(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        return "Ingredients{name = " + name + ", amount = " + amount + ", unit = " + unit + "}";
    }
}
