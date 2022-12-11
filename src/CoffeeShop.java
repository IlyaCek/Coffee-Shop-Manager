public class CoffeeShop {

    int id;
    String brand, type;
    double pricePerKg, quantityInStock;

    public CoffeeShop(int id, String brand, String type, double pricePerKg, double quantityInStock) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.pricePerKg = pricePerKg;
        this.quantityInStock = quantityInStock;
    }


    public CoffeeShop( String brand, String type, double pricePerKg, double quantityInStock) {

        this.brand = brand;
        this.type = type;
        this.pricePerKg = pricePerKg;
        this.quantityInStock = quantityInStock;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public double getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(double quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public String toString(){
       return this.id+", "+this.brand+", "+this.type+", "+this.pricePerKg+", "+this.quantityInStock;
    }
}

