/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caesa
 */
public class Country {
    
    private String name_region;
    private String state_province;
    private float latitude;
    private float longitude;
    private List<CountryDataElement> dataset;

    public Country() {
        this.dataset = new ArrayList<>();
    }
    
    public Country(String name_region, String state_province, float latitude, float longitude) {
        this.name_region = name_region;
        this.state_province = state_province;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataset = new ArrayList<>();
    }

    public String getName_Region() {
        return name_region;
    }

    public void setName_Region(String name_region) {
        this.name_region = name_region;
    }

    public String getState_Province() {
        return state_province;
    }

    public void setState_Province(String state_province) {
        this.state_province = state_province;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public List<CountryDataElement> getDataset() {
        return dataset;
    }

    public void setDataset(List<CountryDataElement> dataset) {
        this.dataset = dataset;
    }
}
