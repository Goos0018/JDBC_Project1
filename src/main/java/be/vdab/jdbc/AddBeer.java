package be.vdab.jdbc;

import java.sql.*;

import static be.vdab.jdbc.ConnectionUtils.*;

public class AddBeer {
    public static void main(String[] args) {
        String sql =
                "INSERT into beers (Name, Alcohol, Price, Stock, BrewerId, CategoryId) " +
                        "VALUES ('DAGERAED', 12, 4, 24, 51, 18);" +
                        "INSERT into beers (Name, Alcohol, Price, Stock, BrewerId, CategoryId) " +
                        "VALUES ('BIER2', 10, 5, 24, 51, 18)";
        try (Connection con = DriverManager.getConnection(
                ADDRESS,
                USER,
                PASSWORD);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}