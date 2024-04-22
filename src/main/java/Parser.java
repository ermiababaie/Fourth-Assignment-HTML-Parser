import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException; 
import java.util.*;

import static java.util.Collections.swap;
import static java.util.Collections.synchronizedSet;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public static List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        for (int i = 0; i < Parser.countries.size(); i++) {
            sortedByName.add(Parser.countries.get(i));
        }
        for (int i = 1; i < sortedByName.size(); i++) {
            int j = i - 1;
            while (j >= 1 && sortedByName.get(j).getName().compareTo(sortedByName.get(j - 1).getName()) < 0) {
                Country countryy1 = sortedByName.get(j);
                sortedByName.set(j, sortedByName.get(j - 1));
                sortedByName.set(j - 1, countryy1);
                j--;
            }
        }
        List<Country> list = new ArrayList<>();
        for (int i = 0; i < sortedByName.size(); i += 2) {
            list.add(sortedByName.get(i));
        }

        return  list;
    }

    public static List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        for (int i = 0; i < Parser.countries.size(); i++) {
            sortedByPopulation.add(Parser.countries.get(i));
        }
        for (int i = 1; i < sortedByPopulation.size(); i++) {
            int j = i - 1;
            while (j >= 1 && sortedByPopulation.get(j).getPopulation() > sortedByPopulation.get(j - 1).getPopulation()) {
                Country countryy1 = sortedByPopulation.get(j);
                sortedByPopulation.set(j, sortedByPopulation.get(j - 1));
                sortedByPopulation.set(j - 1, countryy1);
                j--;
            }
        }
        List<Country> list = new ArrayList<>();
        for (int i = 0; i < sortedByPopulation.size(); i += 2) {
            list.add(sortedByPopulation.get(i));
        }

        return  list;
    }

    public static List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        for (int i = 0; i < Parser.countries.size(); i++) {
            sortedByArea.add(Parser.countries.get(i));
        }
        for (int i = 1; i < sortedByArea.size(); i++) {
            int j = i - 1;
            while (j >= 1 && sortedByArea.get(j).getArea() > sortedByArea.get(j - 1).getArea()) {
                Country countryy1 = sortedByArea.get(j);
                sortedByArea.set(j, sortedByArea.get(j - 1));
                sortedByArea.set(j - 1, countryy1);
                j--;
            }
        }
        List<Country> list = new ArrayList<>();
        for (int i = 0; i < sortedByArea.size(); i += 2) {
            list.add(sortedByArea.get(i));
        }
        return  list;
    }

    public static void setUp() throws IOException {
        File file = new File("src/Resources/country-list.html");
        Document doc = Jsoup.parse(file, null);
        Element country = doc.select("section#countries").first();
        Elements div = country.select("div.col-md-4.country");
        for (Element country2 : div) {
            String countryName = country2.select(".country-name").text();
            String capitalCity = country2.select(".country-capital").text();
            int population = Integer.parseInt(country2.select(".country-population").text());
            double area = Double.parseDouble(country2.select(".country-area").text());
            Country country1 = new Country(countryName, capitalCity, population, area);
            Parser.countries.add(country1);
        }
    }

    public static void main(String[] args) throws IOException {
        setUp();
        while (true) {
            Scanner in = new Scanner(System.in);
            int input = -1;
            while (input < 0 || input > 3) {
                System.out.print("select one: 1)sortByArea 2)sortByName 3)sortByPopulation");
                input = in.nextInt();
            }
            List<Country> list = new ArrayList<>();
            if (input == 0)
                break;
            else if (input == 1) {
                list = sortByArea();
            }
            else if (input == 2) {
                list = sortByName();
            }
            else if (input == 3) {
                list = sortByPopulation();
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        }
    }
}
