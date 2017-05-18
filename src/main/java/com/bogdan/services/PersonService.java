package com.bogdan.services;

import com.bogdan.data.Person;
import com.bogdan.repositories.PersonRepository;
import com.google.common.collect.Lists;
import com.sun.management.OperatingSystemMXBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
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
        logger.info("This process uses " + operatingSystemMXBean.getProcessCpuLoad() * 100 +  "% of CPU");
        long ramUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        logger.info("RAM usage is " + ramUsage);
        long startTime = System.currentTimeMillis();
        List<Person> result = Lists.newArrayList(personRepository.findAll());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.info("Time needed for DB fetch: " + totalTime);
        return result;

    }

    public void save(Person p) {
        logger.info("This process uses " + operatingSystemMXBean.getProcessCpuLoad() * 100 +  "% of CPU");
        long ramUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        logger.info("RAM usage is " + ramUsage);
        personRepository.save(p);
    }
}
