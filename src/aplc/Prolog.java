/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

import com.opencsv.exceptions.CsvException;

/**
 *
 * @author caesa
 */
public class Prolog {

// Prolog Task 2
    private static final String PATH = "prolog-facts/Facts.pl";

    public static void generatePrologText() throws Exception {
        List<Country> countryList = ReadFile.getConfirmedCases();
        List<String[]> CountryConfirmedCasesList = getCountryConfirmCases(countryList);
        try {
            try ( PrintWriter plFile = new PrintWriter(PATH)) {

                plFile.println("% Rules");
                plFile.println(
                        "asc(Total_Cases) :- findall([Name_Region,Confirmed_Cases],confirmed_cases(Name_Region,Confirmed_Cases),Result), "
                        + "sort(2,@<,Result,Total_Cases).");
                plFile.println(
                        "dec(Total_Cases) :- findall([Name_Region,Confirmed_Cases],confirmed_cases(Name_Region,Confirmed_Cases),Result), "
                        + "sort(2,@>,Result,Total_Cases).");

                plFile.println("% Facts");
                CountryConfirmedCasesList.stream()
                        .forEach(elem -> plFile.println("confirmed_cases("
                        + elem[0].toLowerCase().replace(" ", "_").replaceAll("[^A-Za-z0-9_]", "") + ", "
                        + elem[1] + ")."));

            }
        } catch (Exception e) {
        }

    }

    public static List<String[]> getCountryConfirmCases(List<Country> list) throws IOException, CsvException {
        try {
            String[] temp;
            List resultList = new ArrayList();
            for (Country countryObj : Functions.getDistinctCountryList(list)) {
                String totalCases = String.valueOf(Functions.getTotalConfirmedCasesByCountry(
                        Functions.getSameCountries(list, countryObj.getName_Region())));
                temp = new String[2];
                temp[0] = countryObj.getName_Region();
                temp[1] = totalCases;
                resultList.add(temp);
            }
            return resultList;
        } catch (Exception e) {
            return null;
        }

    }

    public static ArrayList<String> getCasesByAscendingOrder() {
        try {
            ArrayList<String> temp = new ArrayList<>();
            Query queryObj = new Query("consult('" + PATH + "').");
            boolean isConnected = queryObj.hasSolution();
            if (isConnected) {
                System.out.println(PATH + " is connected !");
                String qs1 = "asc(Result).";
                queryObj = new Query(qs1);
                Map<String, Term> solution = queryObj.oneSolution();
                Arrays.asList(solution.get("Result").listToTermArray()).stream().forEach(p -> temp.add(p.toString()));
                return temp;
            } else {
                System.out.println(PATH + " is not connected!");
                queryObj.close();
                return null;
            }

        } catch (Exception e) {
            return null;
        }

    }

    public static ArrayList<String> getCasesByDecendingOrder() {
        try {
            ArrayList<String> temp = new ArrayList<>();
            Query queryObj = new Query("consult('" + PATH + "').");
            boolean isConnected = queryObj.hasSolution();
            if (isConnected) {
                System.out.println(PATH + " is connected !");
                String qs1 = "dec(Result).";
                queryObj = new Query(qs1);
                Map<String, Term> solution = queryObj.oneSolution();
                Arrays.asList(solution.get("Result").listToTermArray()).stream().forEach(p -> temp.add(p.toString()));
                return temp;
            } else {
                System.out.println(PATH + " is not connected!");
                queryObj.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }
}
