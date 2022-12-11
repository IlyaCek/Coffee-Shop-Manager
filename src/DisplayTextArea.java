import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayTextArea extends JTextArea {

    public void displayAllCoffee() throws SQLException, IOException {

        QueryingDB db = new QueryingDB();
        this.setText("");

        ArrayList<CoffeeShop> allProducts = db.selectAllQuery();
        for (int i = 0; i<allProducts.size(); i++)  {
            CoffeeShop coffee = allProducts.get(i);
            this.append("ID: " + coffee.getId() +"\n");
            this.append("       Brand: " + coffee.getBrand() +"\n");
            this.append("       Type: " + coffee.getType() +"\n");
            this.append("       Quantity: " + coffee.getQuantityInStock() +"\n");
            this.append("       Price: " + coffee.getPricePerKg() +"\n");
        }



    }
    public void displaySearchedProduct(int id) throws Exception {
        QueryingDB db = new QueryingDB();
        this.setText("");

        CoffeeShop coffee  = db.searchBrandById(id);
        this.append("ID: " + coffee.getId() +"\n");
        this.append("       Brand: " + coffee.getBrand() +"\n");
        this.append("       Type: " + coffee.getType() +"\n");
        this.append("       Quantity: " + coffee.getQuantityInStock() +"\n");
        this.append("       Price: " + coffee.getPricePerKg() +"\n");
    }

}



