package ru.kpfu.itis403.Zinnatov.entities;


public class Product {

    private int id;
    private String name;
    private int price;
    private int weight;
    private Manufacturer manufacturer;
    private String category;

    public Product(int id,  int price,String name, int weight, Manufacturer manufacturer, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    public Product(String name, int price, int weight, Manufacturer manufacturer,String category) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public enum Manufacturer{

        RUSSIA("Russia"),USA("USA"),CHINA("China"),USSR("USSR"),GERMANY("Germany"),JAPAN("Japan"),INDIA("India"),FRANCE("France");

        private String country;

        Manufacturer(String country){
            this.country = country;
        }

        public String getCountry() {
            return this.country;
        }}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }




}
