

public class Main {
    public static void main(String[] args){
        System.out.println("Start of program");

        //pass the csv file of both cities and countries to be read in the DAO
        DAO myDAO = new DAO("countries.csv","cities.csv");
        System.out.println(myDAO);

        // map of the countries
        myDAO.printMap();

        //get cities sorted for this specific country code
        myDAO.getSortedCities();

        // get the highest populated city by a specific country
        Country country = new Country("Egypt",1);
        myDAO.maxPopCityByCountry(country);

        // get the highest populated city by a specific Continent
        myDAO.maxPopCityByContinent();
        System.out.println("End of program");

        //Get the median , mean , quartiles
        myDAO.getStats();
    }
}