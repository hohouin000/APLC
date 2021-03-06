/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author caesa
 */
public class ReadFile {

    private static List<Country> confirmedCases = new ArrayList<>();
    private static List<Country> deathCases = new ArrayList<>();
    private static List<Country> recoveredCases = new ArrayList<>();

    public static List<Country> readCSV(String Path) throws MalformedURLException, IOException,
            CsvValidationException, ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yy");
        FileInputStream file = new FileInputStream(Path);
        InputStreamReader InputStreamReader = new InputStreamReader(file);
        CSVReader reader = new CSVReader(InputStreamReader);
        List<Country> Country_DataSet = new ArrayList<>();

        String[] row;
        List<String> headers = new ArrayList<>();
        boolean isHeader = true;

        while ((row = reader.readNext()) != null) {
            Country country = new Country();
            if (isHeader) {
                headers.addAll(Arrays.asList(row));
                isHeader = false;
                continue;
            }
            for (int column = 0; column < row.length; column++) {
                String header = headers.get(column);

                String data = row[column];

                switch (column) {

                    case 0 -> {
                        if (!data.isEmpty() && !data.equals("")) {
                            country.setState_Province(data);
                        }
                    }
                    case 1 ->
                        country.setName_Region(data);
                    case 2 -> {
                        if (!data.isEmpty()) {
                            country.setLatitude(Float.valueOf(data));
                        }
                    }
                    case 3 -> {
                        if (!data.isEmpty()) {
                            country.setLongitude(Float.valueOf(data));
                        }
                    }
                    default -> {
                        int rowData = Integer.valueOf(data);
                        CountryDataElement CountryDataElement = new CountryDataElement(dateFormatter.parse(header), rowData);
                        if (column > 4 && rowData > 0) {
                            int existingRow = Integer.valueOf(row[column - 1]);
                            // get row differences ie: 11-11=0
                            int newRow = rowData - existingRow;
                            if (existingRow > 0) {
                                if (newRow < 0) {
                                    CountryDataElement.setData(newRow);
                                } else {
                                    CountryDataElement.setData(newRow);
                                }
                            }
                        }
                        country.getDataset().add(CountryDataElement);
                    }
                }
            }
            Country_DataSet.add(country);
        }
        return Country_DataSet;
    }

    public static void initFile() throws IOException, MalformedURLException, CsvValidationException, ParseException {
        String confirmedCasesCSV = "sample-datasets/time_series_covid19_confirmed_global.csv";
        String deathCasesCSV = "sample-datasets/time_series_covid19_deaths_global.csv";
        String recoveredCasesCSV = "sample-datasets/time_series_covid19_recovered_global.csv";

        confirmedCases = readCSV(confirmedCasesCSV);
        deathCases = readCSV(deathCasesCSV);
        recoveredCases = readCSV(recoveredCasesCSV);
    }

    public static List<Country> getConfirmedCases() {

        return confirmedCases;
    }

    public static List<Country> getDeathCases() {

        return deathCases;
    }

    public static List<Country> getRecoveredCases() {

        return recoveredCases;
    }
}
