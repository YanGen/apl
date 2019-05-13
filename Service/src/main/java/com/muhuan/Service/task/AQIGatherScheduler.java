package com.muhuan.Service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class AQIGatherScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AQIGatherScheduler.class);


    @Scheduled(cron = "0 0/60 * * * ?")
    public void executeUpdateCityAQI(){

    }
}
