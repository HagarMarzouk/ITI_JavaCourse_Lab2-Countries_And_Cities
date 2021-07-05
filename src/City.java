public class City {

    private int code;
    private String name;
    private String continent;
    private double surfaceArea;
    private int population;

    public City(int code, String name, String continent, double surfaceArea, int population) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
    }

    public int getCode() { return code; }

    public String getName() { return name; }

    public String getContinent() { return continent; }

    public double getSurfaceArea() { return surfaceArea; }

    public int getPopulation() { return population; }

    @Override
    public String toString() {
        return "City{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", surfaceArea='" + surfaceArea + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
