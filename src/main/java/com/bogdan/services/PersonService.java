package com.bogdan.services;

import com.bogdan.data.Person;
import com.bogdan.repositories.PersonRepository;
import com.google.common.collect.Lists;
import com.sun.management.OperatingSystemMXBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.List;

/**
 * Created by bitfoi on 5/17/2017.
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class);

    private OperatingSystemMXBean operatingSystemMXBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public List<Person> getPersons() {
        logger.info("-----------------------------------------");
        logger.info("DB FETCH: This process uses " + operatingSystemMXBean.getProcessCpuLoad() * 100 +  "% of CPU");
        long ramUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        logger.info("DB FETCH: RAM usage is " + ramUsage);
        long startTime = System.currentTimeMillis();
        List<Person> result = Lists.newArrayList(personRepository.findAll());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.info("DB FETCH: Time needed is " + totalTime + " (msec)");
        logger.info("-----------------------------------------");
        return result;

    }

    public void save(Person p) {
        logger.info("-----------------------------------------");
        logger.info("DB SAVE: This process uses " + operatingSystemMXBean.getProcessCpuLoad() * 100 +  "% of CPU");
        //long ramUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        logger.info("DB SAVE: RAM usage is " + heapMemoryUsage.getUsed());
        long startTime = System.currentTimeMillis();
        personRepository.save(p);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.info("DB SAVE: Time needed is " + totalTime + " (msec)");
        logger.info("-----------------------------------------");
    }
}
