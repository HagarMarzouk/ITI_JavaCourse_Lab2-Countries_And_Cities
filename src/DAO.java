import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DAO {
    static Map<String,List<City>> citiesMap = new HashMap<>();
    String countryFile;
    String cityFile;

    public DAO(String countryFile, String cityFile){
        this.countryFile = countryFile;
        this.cityFile = cityFile;
        List<City> cities = readCity(cityFile);
        List<Country> countries = readCountry(countryFile);
        showMap(countries , cities );
    }
    public  List<Country> readCountry(String countryFile){
        List<Country> countryArray = new ArrayList<Country>();
        List<String[]> linesInCsv = readFromCsv(countryFile);
        for(String[] s: linesInCsv){
            countryArray.add(new Country(s[0],Integer.parseInt(s[1])));
        }
        return countryArray;
    }
    //asetile sestayen
    public List<City> readCity(String cityFile){
        List<City> cityArray = new ArrayList<City>();
        //read the city's file and get its lines seperatly
        List<String[]> linesInCsv = readFromCsv(cityFile);
        //made iterator that reads each line to seperate the line
        Iterator<String[]> stringIterator = linesInCsv.iterator();
        while (stringIterator.hasNext()){
            String [] line = stringIterator.next();
            cityArray.add(new City(Integer.parseInt(line[0]),line[1],line[2],Double.parseDouble(line[3]),Integer.parseInt(line[4])));
        }
        return cityArray;
    }

    public List<String[]> readFromCsv(String filePath){
        List<String []> linesInCsv = new ArrayList<String[]>();
        try{
            FileReader f = new FileReader(filePath);
            BufferedReader b = new BufferedReader(f);
            String line;
            String splitBy = ",";
            line = b.readLine();
            while((line = b.readLine()) != null) {
                String [] data = line.split(splitBy);
                linesInCsv.add(data);
            }
        } catch (IOException e) { e.printStackTrace(); }

        return  linesInCsv;
    }

    public void showMap(List<Country> countryList , List<City> cityList ){
        int noOfCities = cityList.size();
        int noOfCountries = countryList.size();
        for (int i= 0; i<=noOfCities; i++){
            List<City> citiesofACountry = new ArrayList<City>();
            for (int j = 0; j < cityList.size(); j++) {
                if(countryList.get(i).getCode() == cityList.get(j).getCode());
                    citiesofACountry.add(cityList.get(j));
                citiesMap.put(countryList.get(i).getCountryName(),citiesofACountry);
            }
        }
    }

    public void printMap(){
        //print the countries with its cities
        for(Map.Entry<String,List<City>> me: citiesMap.entrySet()) {
            System.out.println("\n Country of "+ me.getKey() + "  has the following cities:");
            for (int i = 0; i < me.getValue().size(); i++)
                System.out.println("\t"+me.getValue().get(i));
        }
    }


    public void getSortedCities(){
        citiesMap.forEach((key,value)->value.sort(Comparator.comparing(City::getPopulation)));
    }

    //get highest populated City by Continent
    public void maxPopCityByContinent(){
        Map<String,List<City>> continentMap = citiesMap.values().stream().flatMap(Collection::stream).collect(Collectors.groupingBy(City::getContinent));
        Map<String,City> continentToCity = new HashMap<>();
        for(Map.Entry<String,List<City>> me: continentMap.entrySet()) {
            String Continent = me.getKey();
            City maxCity = me.getValue().stream().max(Comparator.comparing(City::getPopulation)).get();
            continentToCity.put(Continent,maxCity);
        }
        for(Map.Entry<String,City> me: continentToCity.entrySet()) {
            System.out.println("\nThe continent is "+ me.getKey() + ":");
            System.out.println("\tThe city is:");
            System.out.println("\t"+me.getValue());
        }
    }


    //get highest populated City by Country
    public void maxPopCityByCountry(Country countryName){
        System.out.println("MAximum city given country: " +citiesMap.get(countryName.getCountryName()).stream().max(Comparator.comparing(City::getPopulation)).get());
    }


    public void getStats() {
        citiesMap.forEach((k, v) -> {
            v.sort(Comparator.comparing(City::getPopulation));
            System.out.println("Median = " + v.size() / 2);
            if (v.size() > 5) {
                int q1 = (v.get(v.size() / 4).getPopulation()
                        - v.get(v.size() / 4 - 1).getPopulation()) / 2;
                System.out.println("Q1 \t" + q1);

                int q2 = (v.get(v.size() / 2).getPopulation()
                        - v.get(v.size() / 2 - 1).getPopulation()) / 2;
                System.out.println("Q2 \t" + q2);

                int q3 = (v.get(3 * v.size() / 4).getPopulation()
                        - v.get(1 + 3 * v.size() / 4).getPopulation()) / 2;
                System.out.println("Q3 \t" + q3);
            }

            System.out.println("AVG" + v.stream().mapToDouble((x) -> x.getPopulation())
                    .summaryStatistics().getAverage() + '\n');
        });
    }
}