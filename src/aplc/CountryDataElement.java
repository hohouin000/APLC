/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import java.util.Date;

/**
 *
 * @author caesa
 */
public class CountryDataElement {
     private Date date;
    private int data;

    public CountryDataElement(Date date, int data) {
        this.date = date;
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    } 
}
