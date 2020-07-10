package be.vdab.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

import static be.vdab.jdbc.ConnectionUtils.*;

public class BeerTransactions {

    public static void main(String[] args) throws SQLException {
        String sql1 = "UPDATE beers SET stock = 509999 where Name = 'Jupiler'";
        String sql2 = "UPDATE beers SET stock = 100 where Name = 'Julipters'";
        String sql3 = "SELECT * FROM beers where name like 'Ju%'";

        try (Connection con = DriverManager.getConnection(
                ADDRESS,
                USER,
                PASSWORD)) {
            try (Statement stmt = con.createStatement()) {
                con.setAutoCommit(false);

                stmt.executeUpdate(sql1);
                methodName(sql3);

                int i = stmt.executeUpdate(sql2);
                System.out.println(i);
                methodName(sql3);

                con.commit();

            } catch (SQLException throwables) {
                con.rollback();
                System.out.println("oeps, typo?");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void methodName(String sql3) {
        try (Connection con = DriverManager.getConnection(
                ADDRESS,
                USER,
                PASSWORD)) {
            try (Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                ResultSet rs = stmt.executeQuery(sql3);

                while (rs.next()) {
                    System.out.println(rs.getString("Name"));
                    System.out.println(rs.getInt("Stock"));
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}