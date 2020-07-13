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

            float valueToFillInQuestioMark = 5F;
            st.setFloat(1, valueToFillInQuestioMark);
            ResultSet rs = st.executeQuery();
            System.out.println("These are all 5° beers");
            while (rs.next()) {
                System.out.print(rs.getString("Name"));
                System.out.println(rs.getFloat("Alcohol"));
            }
            //new alcohol level query
            valueToFillInQuestioMark = 10F;
            st.setFloat(1,valueToFillInQuestioMark);
            ResultSet rs2 = st.executeQuery();
            System.out.println("These are all 10° beers");
            rs2.beforeFirst();
            while (rs2.next()){
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getString("Alcohol"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
