package com.xyxy.bean;

public class Phone {
    private String name;
    private int price;
    private int count;
    private String soc;

    public Phone() {
    }

    public Phone(String name, int price, int count, String soc) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.soc = soc;
    }

    @Override
    public String toString() {
        return "phone{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", soc='" + soc + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }
}
