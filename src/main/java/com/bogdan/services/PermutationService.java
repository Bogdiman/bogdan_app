package com.bogdan.services;

import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Bogdan on 01-Jun-17.
 */
@Service
public class PermutationService {
    public void permute(int wordSize) throws IOException {
        try (
                Socket echoSocket = new Socket("127.0.0.1", 9876);
                DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream())
        ) {
            out.writeInt(wordSize);
        }
    }
}
