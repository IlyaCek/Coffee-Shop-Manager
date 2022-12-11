import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QueryingDB {

    final private String SELECT_ALL = "select * from coffee";
    final private String ADD_BRAND_QUERRY = "INSERT INTO coffee (brand,type,pricePerKg,quantityInStock) VALUES ";
    final private String REMOVE_BRAND = "DELETE FROM coffee WHERE ID=";
    final private String UPDATE_QUANTITY = "UPDATE coffee SET quantityInStock=";
    final private String WHERE_ID = "WHERE ID=";
    final private String SEARCH_BY_ID = "select * from coffee where ID=";
    public ArrayList<CoffeeShop> selectAllQuery() throws SQLException, IOException {

        DaatabaseConnection dbconn = new DaatabaseConnection();
        Connection conn = dbconn.getConn();
        PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
        ResultSet rs = ps.executeQuery();
        ArrayList<CoffeeShop> coffeeShops = new ArrayList<>();
        while (rs.next()){

            CoffeeShop shop = new CoffeeShop(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
            coffeeShops.add(shop);
            System.out.println(shop);
        }
        return coffeeShops;


    }
    public void addBrandQuerry(CoffeeShop newBrand) throws Exception {

        DaatabaseConnection dbconn = new DaatabaseConnection();
        Connection conn = dbconn.getConn();
        String query = ADD_BRAND_QUERRY +
                "('"+newBrand.getBrand()+"', '"+newBrand.getType()+"', "+newBrand.getPricePerKg()+", "+newBrand.getQuantityInStock()+")";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
        dbconn.close();
    }
    public void removeBrandByIdQuerry(int id) throws Exception {

        DaatabaseConnection dbconn = new DaatabaseConnection();
        Connection conn = dbconn.getConn();
        String query = REMOVE_BRAND+id;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
        dbconn.close();
    }
    public void updateQuantityById(int id, double quantity) throws Exception {

        DaatabaseConnection dbconn = new DaatabaseConnection();
        Connection conn = dbconn.getConn();
        String query = UPDATE_QUANTITY + quantity + WHERE_ID + id;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate();
        dbconn.close();
    }
    public CoffeeShop searchBrandById(int id) throws Exception {

        DaatabaseConnection dbconn = new DaatabaseConnection();
        Connection conn = dbconn.getConn();
        PreparedStatement ps = conn.prepareStatement(SEARCH_BY_ID+id);
        ResultSet rs = ps.executeQuery();
        CoffeeShop coffeFoundById = null;
        if(rs.next()) {
            coffeFoundById = new CoffeeShop(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDouble(5));
            System.out.println(coffeFoundById);
        }
        dbconn.close();
        return coffeFoundById;


    }
    public QueryingDB() throws FileNotFoundException {
    }
}



