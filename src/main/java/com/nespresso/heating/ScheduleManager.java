package com.nespresso.heating;

import java.util.Calendar;

/**
 * The system obtains temperature data from a remote source,
 * compares it with a given threshold and controls a remote heating
 * unit by switching it on and off. It does so only within a time
 * period configured on a remote service (or other source)
 * <p>
 * This is purpose-built crap
 *
 * The ScheduleManager now is only specialized in the scheduling task.
 * Retrieving external information or managing the heating system is done by other classes,
 * specialized in their respective field.
 */
public class ScheduleManager {
    private final ExternalInformationRetriever externalInformationRetriever;
    private final HeatingManager heatingManager;

    public ScheduleManager(
            ExternalInformationRetriever externalInformationRetriever,
            HeatingManager heatingManager
    ) {
        this.externalInformationRetriever = externalInformationRetriever;
        this.heatingManager = heatingManager;
    }

    /**
     * Launch the scheduler.
     * It will update periodically the heating system.
     */
    public void launchScheduler() {
        try {
            while(true) {
                Thread.sleep(1000);
                updateHeatingInformation();
            }
        } catch (Exception e) {
            System.err.println("Error from the scheduler!");
        }
    }

    /**
     * Update information for the heating system.
     */
    private void updateHeatingInformation() {
        boolean canSystemBeWorking = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > externalInformationRetriever.getStartHour()
                && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < externalInformationRetriever.getEndHour();

        int currentTemp = externalInformationRetriever.getTemperature();

        /*
         * The previous condition was useless, so it was removed.
         */
        heatingManager.sendHeatingInformation(currentTemp, canSystemBeWorking);
    }
}
