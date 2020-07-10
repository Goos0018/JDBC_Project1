package be.vdab.jdbc;

import java.sql.*;

import static be.vdab.jdbc.ConnectionUtils.*;

public class GetBeerByAlcohol {
    public static void main(String[] args) {
        String sql =
                "SELECT * FROM beers WHERE Alcohol = ?";

        try (Connection con = DriverManager.getConnection(
                ADDRESS,
                USER,
                PASSWORD);
             PreparedStatement st = con.prepareStatement(sql);) {

            float i = 5F;
            st.setFloat(1, i);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString("Name"));
                System.out.println(rs.getFloat("Alcohol"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
