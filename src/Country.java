import java.util.HashMap;

public class Country {
    int code;
    String countryName;

    public Country( String countryName, int code) {
        this.code = code;
        this.countryName = countryName;
    }
    public int getCode() { return code; }
    public String getCountryName() { return countryName; }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}