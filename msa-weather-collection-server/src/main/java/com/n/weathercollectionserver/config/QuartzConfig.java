package com.n.weathercollectionserver.config;

import com.n.weathercollectionserver.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Autowired
    private ConstConfig constConfig;

    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return  JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherSyncJob").storeDurably().build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(constConfig.getInterval()).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build();
    }
}
