package be.vdab.jdbc;

import java.sql.*;

public class BeerDaoImp implements BeerDAO {
    private String url;
    private String user;
    private String password;

    public BeerDaoImp() {
        this.url = ConnectionUtils.ADDRESS;
        this.user = ConnectionUtils.USER;
        this.password = ConnectionUtils.PASSWORD;
    }

    public Beer getBeerById(int id) throws BeerException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM beers WHERE ID = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Beer beer = new Beer();
                    beer.setId(id);
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    return beer;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new BeerException();
        }
    }

    public Beer getBeersByAlcohol(float alcohol) throws BeerException {
        return null;
    }

    public Beer getBeersByName(String name) throws BeerException {
        return null;
    }

    public void updateBeer(Beer beer) throws BeerException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(
                     "UPDATE Beers SET Name = ?, Price = ?, Alcohol = ?, Stock = ? WHERE Id = ?")) {
            stmt.setString(1, beer.getName());
            stmt.setFloat(2, beer.getPrice());
            stmt.setFloat(3, beer.getAlcohol());
            stmt.setInt(4, beer.getStock());
            stmt.setInt(5, beer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new BeerException();
        }
    }



    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConnectionUtils.ADDRESS, ConnectionUtils.USER, ConnectionUtils.PASSWORD);
    }


}
