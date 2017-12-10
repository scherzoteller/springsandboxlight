package com.scherzoteller.springsandbox.web.utils.spring;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.Constants;

public class SysAndFilePropFactoryBean extends org.springframework.beans.factory.config.PropertiesFactoryBean {

    private int systemPropertiesMode = PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_FALLBACK;

    private static final Constants constants = new Constants(PropertyPlaceholderConfigurer.class);

    @Override
    protected Properties createProperties() throws IOException {
        Properties createProperties = super.createProperties();
        if(systemPropertiesMode != PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_NEVER){
            for(Map.Entry<Object, Object> ent : System.getProperties().entrySet()){
                if(!createProperties.containsKey(ent.getKey()) || systemPropertiesMode == PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE){
                    createProperties.put(ent.getKey(), ent.getValue());
                }
            }
        }
        return createProperties;

    }


    /**
     * Set the system property mode by the name of the corresponding constant,
     * e.g. "SYSTEM_PROPERTIES_MODE_OVERRIDE".
     * @param constantName name of the constant
     * @throws java.lang.IllegalArgumentException if an invalid constant was specified
     * @see #setSystemPropertiesMode
     */
    public void setSystemPropertiesModeName(String constantName) throws IllegalArgumentException {
        this.systemPropertiesMode = constants.asNumber(constantName).intValue();
    }

    /**
     * Set how to check system properties: as fallback, as override, or never.
     * For example, will resolve ${user.dir} to the "user.dir" system property.
     * <p>The default is "fallback": If not being able to resolve a placeholder
     * with the specified properties, a system property will be tried.
     * "override" will check for a system property first, before trying the
     * specified properties. "never" will not check system properties at all.
     * @see #SYSTEM_PROPERTIES_MODE_NEVER
     * @see #SYSTEM_PROPERTIES_MODE_FALLBACK
     * @see #SYSTEM_PROPERTIES_MODE_OVERRIDE
     * @see #setSystemPropertiesModeName
     */
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        this.systemPropertiesMode = systemPropertiesMode;
    }
}
