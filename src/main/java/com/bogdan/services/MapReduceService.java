package com.bogdan.services;

import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Bogdan on 01-Jun-17.
 */
@Service
public class MapReduceService {

    public void mapReduce(int listSize) throws IOException {
        try (
                Socket echoSocket = new Socket("192.168.99.100", 9875);
                DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream())
        ) {
            out.writeInt(listSize);
        }
    }
}
