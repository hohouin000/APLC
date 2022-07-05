/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplc;

import aplc.Pages.MainMenu;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

/**
 *
 * @author caesa
 */
public class Main {

    public static void main(String[] args) throws IOException, MalformedURLException, CsvValidationException, ParseException {
        ReadFile.initFile();
        MainMenu MainMenu = new MainMenu();
        MainMenu.setVisible(true);
    }
    
}
