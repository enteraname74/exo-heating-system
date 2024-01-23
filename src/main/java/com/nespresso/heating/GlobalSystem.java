package com.nespresso.heating;

public class GlobalSystem {

    public static void main(String[] args) {
        ExternalInformationRetriever externalInformationRetriever = new ExternalInformationRetrieverImpl();
        HeatingManager heatingManager = new HeatingManager();

        ScheduleManager scheduleManager = new ScheduleManager(
                externalInformationRetriever,
                heatingManager
        );

        scheduleManager.launchScheduler();
    }
}
