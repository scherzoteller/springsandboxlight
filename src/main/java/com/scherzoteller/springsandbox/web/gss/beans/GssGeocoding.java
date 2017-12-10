package com.scherzoteller.springsandbox.web.gss.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GssGeocoding {
    private GssCall gssCall;
    private String address;
    private boolean withTimezone;
    public GssGeocoding() {
        // Empty constructor
    }
    public GssGeocoding(GssCall gssCall, String address, boolean withTimezone) {
        this.gssCall = gssCall;
        this.address = address;
        this.withTimezone = withTimezone;
    }
    public GssGeocoding(GssCall gssCall, String address) {
        this(gssCall, address, false);
    }
    public String getExternalIdentifier() {
        return gssCall == null ? null : gssCall.toGssId();
    }
    public String getAddress() {
        return address;
    }
    public boolean isWithTimezone() {
        return withTimezone;
    }
    
    public void setGssCall(GssCall gssCall) {
        this.gssCall = gssCall;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setWithTimezone(boolean withTimezone) {
        this.withTimezone = withTimezone;
    }
    @JsonIgnore
    public GssCall getGssCall() {
        return gssCall;
    }
    
}
