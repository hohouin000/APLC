/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author caesa
 */
public class Functions {

    private static final BiPredicate<String, String> sameCountry = (input, comparison) -> input.toLowerCase().equals(comparison.toLowerCase());

    // Source: https://stackoverflow.com/questions/23699371/java-8-distinct-by-propert
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static List<Country> getSameCountries(List<Country> dataset, String country) {
        return dataset.stream()
                .filter(elem -> sameCountry.test(elem.getName_Region(), country))
                .collect(Collectors.toList());
    }

    public static List<Country> getCountryList(List<Country> data) {
        return data.stream()
                .filter(distinctByKey(elem -> elem.getName_Region()))
                .collect(Collectors.toList());
    }

    /**
     * Requirement 1: Find Total Confirmed Cases
     */
    public static Integer getTotalConfirmedCasesByCountry(List<Country> dataset, String country) {
        return dataset.stream().map(elem -> elem.getDataset())
                .mapToInt(elem -> elem.stream().mapToInt(a -> a.getData()).reduce(0, (x, y) -> x + y))
                .sum();
    }

    /**
     * Requirement 2: Find Weekly and Monthly Confirmed Cases
     */
    public static List<String> getWeeklyOrMonthlyFormattedDate(List<Country> dataset, SimpleDateFormat dateFormat) {
        Country resultList = dataset.get(0);
        return resultList.getDataset().stream()
                .map(elem -> dateFormat.format(elem.getDate()))
                .filter(distinctByKey(elem -> elem))
                .collect(Collectors.toList());
    }

    public static Integer getWeeklyOrMonthlyConfirmedCasesByCountry(List<Country> dataset, String country, String monthweekYear, SimpleDateFormat dateFormat) {
        return dataset.stream().map(p -> p.getDataset())
                .mapToInt(dataElements -> dataElements.stream().filter(p
                -> dateFormat.format(p.getDate()).equals(monthweekYear)).mapToInt(p -> p.getData()).sum())
                .sum();
    }

    public static String getStartDateForWeekly(List<Country> dataset, String weekYear, SimpleDateFormat dateFormat, SimpleDateFormat dateFormat2) {
        Country resultList = dataset.get(0);
        return resultList.getDataset().stream()
                .filter(p -> dateFormat.format(p.getDate()).equals(weekYear))
                .sorted(Comparator.comparing(p -> p.getDate()))
                .map(p -> dateFormat2.format(p.getDate())).findFirst().orElse(null);
    }

    public static String getEndDateForWeekly(List<Country> dataset, String weekYear, SimpleDateFormat dateFormat, SimpleDateFormat dateFormat2) {
        Country resultList = dataset.get(0);
        return resultList.getDataset().stream()
                .filter(p -> dateFormat.format(p.getDate()).equals(weekYear))
                .sorted(Comparator.comparing(p -> p.getDate()))
                .map(p -> dateFormat2.format(p.getDate())).reduce((a, b) -> b).orElse(null);
    }

}
