package com.susu.inventory_management_susu;

public class transaction_item {
    private int image;
    private String header;
    private String description;
    private String type;


    public transaction_item(int image, String header, String description, String type) {
        this.image = image;
        this.header = header;
        this.description = description;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public String getType(){
        return type;
    }
}
