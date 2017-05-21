package com.bogdan.services;

import com.sun.management.OperatingSystemMXBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.Socket;

/**
 * Created by Bogdan on 21-May-17.
 */
@Service
public class UploadService {
    private final int CHUNK_SIZE = 5;

    private Logger logger = Logger.getLogger(UploadService.class);

    private OperatingSystemMXBean operatingSystemMXBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public void uploadFile(MultipartFile file) throws IOException {
        try (
            Socket echoSocket = new Socket("192.168.99.100", 9874);
            DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream());
            DataInputStream in = new DataInputStream(echoSocket.getInputStream())
        ) {
            long startTime = System.currentTimeMillis();
            byte[] bytes = file.getBytes();
            int noOfPackages = bytes.length % CHUNK_SIZE == 0 ? bytes.length / CHUNK_SIZE : bytes.length / CHUNK_SIZE + 1;
            out.writeInt(bytes.length);
            byte[] rec = new byte[CHUNK_SIZE];
            for (int i = 0; i < noOfPackages; i++) {
                int bytesToUseThisRound = i == noOfPackages - 1 ? bytes.length % CHUNK_SIZE : CHUNK_SIZE;
                out.write(bytes, i * CHUNK_SIZE, bytesToUseThisRound);
                in.read(rec, 0, bytesToUseThisRound);
            }
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            logger.info("UPLOAD FILE: Time needed is " + totalTime + " (msec)");
            logger.info("-----------------------------------------");
        }
    }
}
