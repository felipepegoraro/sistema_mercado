package model;

import java.util.List;
//import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Produto {
    private int id; // id sera utilizado como codigo de barras.
    private int stockQuantity;
    private String name;
    private String description;
    private String supplier;
    private String category;
    private float price;
    private float rating;
    private List<String> tags;
    private byte[] image;
    private boolean favorito;
    
    public Produto(
            int id, int stockQuantity, String name, String description, String supplier,
            String category, float price, float rating, List<String> tags, byte[] img
    ) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.tags = tags;
        this.image = img;
        this.favorito = false;
    }

    @Override
    public String toString() {
        return "Produto(" + id + ") -> " + name + "[" + price + "]";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    public byte[] getImage() {
        return image;
    }
    
    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public void setImageFromFile(String filePath) {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] imageBytes = new byte[(int) file.length()];
            fis.read(imageBytes);
            this.image = imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
