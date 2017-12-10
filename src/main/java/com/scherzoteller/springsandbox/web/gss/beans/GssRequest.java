package com.scherzoteller.springsandbox.web.gss.beans;

import java.util.List;

public class GssRequest {
    private List<? extends GssGeocoding> geocodings;
    
    public GssRequest() {
        // Empty constructor
    }
    
    public GssRequest(List<? extends GssGeocoding> geocodings) {
        this.geocodings = geocodings;
    }

    public List<? extends GssGeocoding> getGeocodings() {
        return geocodings;
    }

    public void setGeocodings(List<? extends GssGeocoding> geocodings) {
        this.geocodings = geocodings;
    }
}
