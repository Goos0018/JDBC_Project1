package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchBeer {

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/beersdb?serverTimezone=UTC", "root", "Mqwuff16");
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = statement.executeQuery("SELECT Name, Alcohol, Price FROM beers;")) {
            rs.afterLast();
            while (rs.previous()) {
                String beername = rs.getString(1);
                double alcohol = rs.getDouble(2);
                double price = rs.getDouble(3);
                System.out.format("%s %s %s%n", beername, alcohol, price);
            }
            System.out.println("Connection OK");
        } catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}