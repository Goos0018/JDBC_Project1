package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/beersdb?serverTimezone=UTC",
                "root",
                "Mqwuff16")) {
            System.out.println("Connection OK");
        } catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}