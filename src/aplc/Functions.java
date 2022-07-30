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

    // Source: https://stackoverflow.com/questions/23699371/java-8-distinct-by-propert
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static List<Country> getSameCountries(List<Country> dataSet, String country) {
        if (dataSet == null) {
            return null;
        }
        return dataSet.stream()
                .filter(elem -> sameCountry.test(elem.getName_Region(), country))
                .collect(Collectors.toList());
    }

    public static List<Country> getDistinctCountryList(List<Country> dataSet) {
        if (dataSet == null) {
            return null;
        }
        return dataSet.stream()
                .filter(distinctByKey(elem -> elem.getName_Region()))
                .collect(Collectors.toList());
    }

    /**
     * Requirement 1: Find Total Confirmed Cases
     */
    public static int getTotalConfirmedCasesByCountry(List<Country> dataSet) {
        if (dataSet == null) {
            return 0;
        }
        return dataSet.stream().map(elem -> elem.getDataset())
                .mapToInt(elem -> elem.stream().mapToInt(a -> a.getData()).reduce(0, (x, y) -> x + y))
                .sum();
    }

    /**
     * Requirement 2: Find Weekly and Monthly Confirmed Cases
     */
    public static List<String> getWeeklyOrMonthlyFormattedDate(List<Country> dataSet, SimpleDateFormat dateFormat) {
        if (dataSet == null) {
            return null;
        }
        Country resultList = dataSet.stream().findFirst().orElse(null);
        if (resultList == null) {
            return null;
        }
        return resultList.getDataset().stream()
                .map(elem -> dateFormat.format(elem.getDate()))
                .filter(distinctByKey(elem -> elem))
                .collect(Collectors.toList());
    }

    public static Integer getWeeklyOrMonthlyConfirmedCasesByCountry(List<Country> dataSet, String monthweekYear, SimpleDateFormat dateFormat) {
        if (dataSet == null) {
            return null;
        }
        return dataSet.stream().map(elem -> elem.getDataset())
                .mapToInt(dataElements -> dataElements.stream().filter(elem
                -> dateFormat.format(elem.getDate()).equals(monthweekYear)).mapToInt(elem -> elem.getData()).sum())
                .sum();
    }

    public static String getStartDateForWeekly(List<Country> dataSet, String weekYear, SimpleDateFormat dateFormat, SimpleDateFormat dateFormat2) {
        Country resultList = dataSet.stream().findFirst().orElse(null);
        if (resultList == null) {
            return null;
        }
        return resultList.getDataset().stream()
                .filter(elem -> dateFormat.format(elem.getDate()).equals(weekYear))
                .sorted(Comparator.comparing(elem -> elem.getDate()))
                .map(elem -> dateFormat2.format(elem.getDate())).findFirst().orElse(null);
    }

    public static String getEndDateForWeekly(List<Country> dataSet, String weekYear, SimpleDateFormat dateFormat, SimpleDateFormat dateFormat2) {
        Country resultList = dataSet.stream().findFirst().orElse(null);
        if (resultList == null) {
            return null;
        }
        return resultList.getDataset().stream()
                .filter(elem -> dateFormat.format(elem.getDate()).equals(weekYear))
                .sorted(Comparator.comparing(elem -> elem.getDate()))
                .map(elem -> dateFormat2.format(elem.getDate())).reduce((a, b) -> b).orElse(null);
    }

    /*
    *Requirement 3
     */
    // Currying
    private static final Function<Integer, Function<Integer, Integer>> getMinValue = a -> b -> ((a > b) ? b : a);
    private static final Function<Integer, Function<Integer, Integer>> getMaxValue = a -> b -> ((a > b) ? a : b);

    public static int getHighestCountryData(List<Country> dataSet, BiFunction<int[], Integer, Integer> func) {
        if (dataSet == null) {
            return 0;
        }
        int[] resultArray = dataSet.stream()
                .map(c -> c.getDataset())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(CountryDataElement::getDate)).entrySet().stream()
                .mapToInt(p -> p.getValue().stream().mapToInt(elem -> elem.getData()).sum())
                .toArray();
        return func.apply(resultArray, resultArray.length);

    }

    public static int getLowestCountryData(List<Country> dataSet) {
        if (dataSet == null) {
            return 0;
        }
        int[] resultArray = dataSet.stream()
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
    public static List<Country> searchByCountryName(List<Country> dataSet, String country) {
        return dataSet.stream()
                .filter(distinctByKey(elem -> elem.getName_Region()))
                .filter(elem -> sameCountry.test(elem.getName_Region().toLowerCase(), country))
                .collect(Collectors.toList());
    }

}
