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
                double result = 0;
                log.info("[CustomWebApplicationServer] client connected");

                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */
                try (InputStream in = clientSocket.getInputStream(); OutputStream os = clientSocket.getOutputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                     DataOutputStream dos = new DataOutputStream(os);) {

                    HttpRequest httpRequest = new HttpRequest(br);
                    if (httpRequest.isGet() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQeuryStrings();
                        double operand1 = Double.parseDouble(queryStrings.getValue("operand1"));
                        double operand2 = Double.parseDouble(queryStrings.getValue("operand2"));
                        String operator = queryStrings.getValue("operator");
                        result = Calculator.calculate(operand1, operand2, operator);
                        byte[] body = String.valueOf(result).getBytes(StandardCharsets.UTF_8);

                        HttpResponse httpResponse = new HttpResponse(dos);
                        httpResponse.response200Header("application/json", body.length);
                        httpResponse.responseBody(body);
                    }
                }
                log.info("결과: {}", result);
            }
        }
    }
}
