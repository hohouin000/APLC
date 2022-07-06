/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author caesa
 */
public class Functions {

    private static final BiPredicate<String, String> sameCountry = (input, comparison) -> input.equals(comparison);
    private static final BiPredicate<String, String> countryNameExist = (input, comparison) -> input.contains(comparison);

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

    public static List<Country> getDistinctCountryList(List<Country> data) {
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

    /*
    *Requirement 3
     */
    // Currying
    private static final Function<Integer, Function<Integer, Integer>> getMinValue = a -> b -> ((a > b) ? b : a);
    private static final Function<Integer, Function<Integer, Integer>> getMaxValue = a -> b -> ((a > b) ? a : b);

    public static int getHighestCountryData(List<Country> dataset, String country, BiFunction<int[], Integer, Integer> func) {
        int[] resultArray = dataset.stream()
                .map(c -> c.getDataset())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(CountryDataElement::getDate)).entrySet().stream()
                .mapToInt(p -> p.getValue().stream().mapToInt(elem -> elem.getData()).sum())
                .toArray();
        return func.apply(resultArray, resultArray.length);

    }

    public static int getLowestCountryData(List<Country> dataset, String country) {
        int[] resultArray = dataset.stream()
                .map(c -> c.getDataset())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(CountryDataElement::getDate)).entrySet().stream()
                .mapToInt(p -> p.getValue().stream().mapToInt(elem -> elem.getData()).sum())
                .toArray();

        return getLowestValue(resultArray, resultArray.length, getMinValue);
    }

    public static int getLowestValue(int[] resultArray, int count, Function<Integer, Function<Integer, Integer>> func) {
        if (count == 1) {
            return resultArray[0];
        }
        return func.apply(resultArray[count - 1]).apply(getLowestValue(resultArray, count - 1, func));
    }

    public static final BiFunction<int[], Integer, Integer> getHighestValue = (resultArray, count) -> count == 1
            ? resultArray[0]
            : getMaxValue.apply(resultArray[count - 1]).apply(Functions.getHighestValue.apply(resultArray, count - 1));

    /*
    * Requrement 4: Search  by Country
     */
    public static List<Country> searchByCountryName(List<Country> dataset, String country) {
        return dataset.stream()
                .filter(distinctByKey(p -> p.getName_Region()))
                .filter(p -> countryNameExist.test(p.getName_Region(), country))
                .collect(Collectors.toList());
    }

}
