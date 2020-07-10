package be.vdab.jdbc;

import java.util.List;

public interface BeerDAO {
    public Beer getBeerById(int id) throws BeerException;
    public void updateBeer(Beer beer) throws BeerException;
    public Beer getBeersByAlcohol (float alcohol) throws BeerException;
    public Beer getBeersByName (String name) throws BeerException
}
