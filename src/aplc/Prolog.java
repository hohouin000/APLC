/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import aplc.Pages.KnowledgeBase;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author caesa
 */
public class Prolog {

// Prolog Task 2
    private static final String path = "prolog-facts/Facts.pl";

    public static void generatePrologText() throws Exception {
        List<Country> countryList = ReadFile.getConfirmedCases();
        List<String[]> CountryConfirmedCasesList = getCountryConfirmCases(countryList);

        try (
                //generate pl file
                 PrintWriter plFile = new PrintWriter(path)) {

            plFile.println("% Rules");
            plFile.println("asc(Facts) :- findall([Name_Region,Confirmed_Cases],confirmed_cases(Name_Region,Confirmed_Cases),Result), sort(2,@<,Result,Facts).");
            plFile.println("dec(Facts) :- findall([Name_Region,Confirmed_Cases],confirmed_cases(Name_Region,Confirmed_Cases),Result), sort(2,@>,Result,Facts).");

            plFile.println("% Facts");
            CountryConfirmedCasesList.stream().forEach(elem -> plFile.println("confirmed_cases(" + elem[0].toLowerCase().replace(" ", "_").replaceAll("[^A-Za-z0-9_]", "") + ", " + elem[1] + ")."));

        }
    }

    public static List<String[]> getCountryConfirmCases(List<Country> list) throws IOException, CsvException {
        String[] temp;
        List resultList = new ArrayList();
        for (Country countryObj : Functions.getDistinctCountryList(list)) {
            String totalCases = String.valueOf(Functions.getTotalConfirmedCasesByCountry(Functions.getSameCountries(list, countryObj.getName_Region())));
            temp = new String[2];
            temp[0] = countryObj.getName_Region();
            temp[1] = totalCases;
            resultList.add(temp);
        }
        return resultList;
    }

    public static String getCasesByAscendingOrder() {
        PL_FILE_CONN(path);
        String output = new String();
        KnowledgeBase knldbaseObj = new KnowledgeBase();
        output += "------------...[ Countries with Covid-19 Confirmed Cases in Ascending Order ]...------------";
        String qs1 = "asc(Result).";
        Query newqueryObj = new Query(qs1);
        Map<String, Term> solution = newqueryObj.oneSolution();

        Term[] temp = solution.get("Result").listToTermArray();
        for (Term result : temp) {
            output += "\n" + result.toString();
        }
        return output;
    }

    public static String getCasesByDecendingOrder() {
        PL_FILE_CONN(path);
        String output = new String();
        KnowledgeBase knldbaseObj = new KnowledgeBase();
        output += "------------...[ Countries with Covid-19 Confirmed Cases in Decending Order ]...------------";
        String qs1 = "dec(Result).";
        Query newqueryObj = new Query(qs1);
        Map<String, Term> solution = newqueryObj.oneSolution();

        Term[] temp = solution.get("Result").listToTermArray();
        for (Term result : temp) {
            output += "\n" + result.toString();
        }
        return output;
    }

    private static void PL_FILE_CONN(String path) {
        Query queryObj = new Query("consult('" + path + "').");
        boolean isConnected = queryObj.hasSolution();
        if (isConnected) {
            System.out.println(path + " is connected !");
        } else {
            System.out.println(path + " is not connected!");
            queryObj.close();
        }
    }
}
