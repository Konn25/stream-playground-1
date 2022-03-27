package brickset;

import repository.Repository;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /***
     *  Visszaadja a legtöbb kockát tartalmazó szett kocka darabszámát
     *
     * @return Visszaadja a legtöbb kockát tartalmazó szett kocka darabszámát
     */

    public long maxLegoSetsPiece(){

        return getAll().stream().mapToLong(LegoSet::getPieces).max().getAsLong();
    }

    /**
     * Kiiratja az összes olyan témával rendelkező Lego szettet ABC sorrendben,amit a theme változóba megadnak
     *
     * @param theme egy Lego téma
     */


    public void printLegoSetsWithTheme(String theme){

        getAll().stream().filter(legoSet ->legoSet.getTheme() != null && legoSet.getTheme().contains(theme)).map(LegoSet::getName).sorted().forEach(System.out::println);

    }

    /**
     * Visszaadja az adott témában hány Lego készlet van
     *
     * @param theme egy Lego téma
     * @return Visszaadja az adott témában hány Lego készlet van
     */

    public long countLegoSetsWithTheme(String theme){
        return getAll().stream().filter(legoSet ->legoSet.getTheme() != null && legoSet.getTheme().contains(theme)).map(LegoSet::getName).count();
    }

    /**
     *  Visszaadja az összes Lego kocka darabszámát
     *
     * @return Visszaadja az összes Lego kocka darabszámát
     */

    public long sumLegoSetsAllPieces(){
        return getAll().stream().mapToLong(LegoSet::getPieces).sum();
    }

    /**
     * Kiírja a Lego szett dimenzióit
     *
     * @param name a Lego szett neve
     */
    public void printLegoSetDimensions(String name){
        getAll().stream().filter(legoSet -> legoSet.getName() != null && legoSet.getName().equals(name)).map(LegoSet::getDimensions).forEach(System.out::println);
    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();

        System.out.println("1. stream");
        System.out.println("Legtöbb kockát tartalmazó szett "+repository.maxLegoSetsPiece()+" darab kockát tartalmaz");

        System.out.println(" ");
        System.out.println("2. stream");
        System.out.println("Lego szett Star Wars téma szerint");
        repository.printLegoSetsWithTheme("Star Wars");

        System.out.println(" ");
        System.out.println("3. stream");
        System.out.println("Összes Star Wars témájú szett: ");
        System.out.println(repository.countLegoSetsWithTheme("Star Wars"));

        System.out.println(" ");
        System.out.println("4. stream");
        System.out.println("Összes Lego kocka: "+repository.sumLegoSetsAllPieces()+" darab");

        System.out.println(" ");
        System.out.println("5. stream");
        System.out.println("A megadott Lego szett dimenziója: ");
        repository.printLegoSetDimensions("Krusty Krab Adventures");

    }


}
