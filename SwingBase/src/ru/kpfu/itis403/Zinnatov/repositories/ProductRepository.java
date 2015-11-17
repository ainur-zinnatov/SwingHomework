package ru.kpfu.itis403.Zinnatov.repositories;

import ru.kpfu.itis403.Zinnatov.entities.Product;
import ru.kpfu.itis403.Zinnatov.exceptions.DBException;
import ru.kpfu.itis403.Zinnatov.exceptions.ProductException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static ru.kpfu.itis403.Zinnatov.repositories.MySQLConnect.getDbCon;
import static ru.kpfu.itis403.Zinnatov.repositories.MySQLConnect.insert;
import static ru.kpfu.itis403.Zinnatov.repositories.MySQLConnect.query;

/**
 * Created by hp on 15.11.2015.
 */
public class ProductRepository {

    public static void add(Product product) throws DBException {
        try {
            getDbCon();
        }catch (Exception sqle){
            sqle.getMessage();
        }
        try {

            insert("INSERT INTO product(price,name,weight,manufacturer,category) VALUE("+product.getPrice()+",'"+product.getName()+"',"+product.getWeight()+",'"+ product.getManufacturer().getCountry() +"','"+product.getCategory()+"')");

        }  catch (SQLException e) {
           throw new DBException(e.getMessage());

        }
    }
    public static void checkProduct(Product product) throws ProductException {



        if (product == null){
            throw new NullPointerException("Product must not be null!");
        }
        if (product.getName() == null || "".equals(product.getName())){
            throw new ProductException("Product name must not be empty");
        }

        if (product.getPrice() <= 0|| product.getWeight() <= 0){
            throw new ProductException("Product weight and price must be more than zero");
        }
    }
    public static List<Product> getAllProd() throws SQLException {
            getDbCon();
        ResultSet resultSet = null;
        try {
            resultSet = query("SELECT * FROM product");
        } catch (SQLException e) {
           e.printStackTrace();
        }
        List<Product> products = new LinkedList<>();
        while (resultSet.next()){


            Product product = new Product(resultSet.getInt("id"),resultSet.getInt("price"),resultSet.getString("name"),resultSet.getInt("weight"),getManufacturer(resultSet.getString("manufacturer")),resultSet.getString("category"));


                products.add(product);

        }
        return products;
    }
    private static Product.Manufacturer getManufacturer(String country){
        for (Product.Manufacturer m : Product.Manufacturer.values()){
            if (m.getCountry().equals(country)){
                return m;
            }
        }
        return null;
    }


    public static String[][] getTable(){
        try {
            List<Product> products = getAllProd();
            String [][] data = new String[products.size()][5];
            int i = 0;
            for (Product product : products){
                data[i][0] = product.getName();
                data[i][1] = String.valueOf(product.getPrice());
                data[i][2] = String.valueOf(product.getWeight());
                data[i][3] = product.getManufacturer().getCountry();
                data[i][4] = product.getCategory();
                i++;
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[][]{};
    }
}
