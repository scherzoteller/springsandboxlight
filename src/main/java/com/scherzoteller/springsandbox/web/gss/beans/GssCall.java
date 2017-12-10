package com.scherzoteller.springsandbox.web.gss.beans;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author vloret
 *
 */
public class GssCall {
    public static final String ID_FORMAT = "%1$2s@%2$2s@%3$2tY_%3$2tm_%3$2td";
    public static final String DATE_FORMAT_IN_ID = "'_''_'";
    private Long gssCallId;
    private String siebelId;
    private String entity;
    private Date executionDate;

    public GssCall() {
        // Empty constructor (mybatis)
    }

    public GssCall(String siebelId, String entity, Date executionDate) {
        this.siebelId = siebelId;
        this.entity = entity;
        this.executionDate = executionDate;
    }

    public Long getGssCallId() {
        return gssCallId;
    }

    public void setGssCallId(Long gssCallId) {
        this.gssCallId = gssCallId;
    }

    public String getSiebelId() {
        return siebelId;
    }

    public void setSiebelId(String siebelId) {
        this.siebelId = siebelId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GssCall)) {
            return false;
        }
        GssCall other = (GssCall) obj;
        return new EqualsBuilder().append(getSiebelId(), other.getSiebelId()).append(getEntity(), other.getEntity())
            .append(getExecutionDate(), other.getExecutionDate()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getSiebelId()).append(getEntity()).append(getExecutionDate()).toHashCode();
    }

    public String toGssId() {
        return String.format(ID_FORMAT, siebelId, entity, executionDate);
    }

}
