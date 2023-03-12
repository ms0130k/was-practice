package org.shock.was;

import lombok.extern.slf4j.Slf4j;
import org.shock.calculator.Calculator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomWebApplicationServer {
    private final int port;

    public CustomWebApplicationServer(final int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("[CustomWebApplicationServer] started {} port", port);

            Socket clientSocket;
            log.info("[CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                log.info("[CustomWebApplicationServer] client connected");

                /**
                 * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 처리한다.
                 */
                new Thread(new ClientRequestHandler(clientSocket)).start();

            }
        }
    }
}
