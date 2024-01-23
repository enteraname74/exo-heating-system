package com.nespresso.heating;

/**
 * Retrieve external information for the heating system.
 */
public interface ExternalInformationRetriever {

    /**
     * Retrieve the end hour when the heating system can be operational.
     * @return the end hour when the heating system can be operational.
     */
    int getEndHour();

    /**
     * Retrieve the start hour when the heating system can be operational.
     * @return the start hour when the heating system can be operational.
     */
    int getStartHour();

    /**
     * Retrieve the current temperature.
     * @return the current temperature.
     */
    int getTemperature();
}
