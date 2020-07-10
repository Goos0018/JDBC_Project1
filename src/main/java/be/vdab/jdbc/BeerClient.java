package be.vdab.jdbc;

public class BeerClient {
    public static void main(String[] args) throws BeerException {
        BeerDAO beerDAO = new BeerDaoImp();

        Beer beer1 = beerDAO.getBeerById(500);

        System.out.println(beer1.getName());
        System.out.println(beer1.getPrice());
        System.out.println(beer1.getAlcohol());
        System.out.println(beer1.getStock());

        beer1.setStock(beer1.getStock()-5);
        beerDAO.updateBeer(beer1);

        System.out.println(beer1.getStock());
    }
}
