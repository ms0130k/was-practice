package org.shock.was;

import lombok.extern.slf4j.Slf4j;
import org.shock.calculator.Calculator;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ClientRequestHandler implements Runnable {
    private Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try (InputStream in = clientSocket.getInputStream(); OutputStream os = clientSocket.getOutputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
             DataOutputStream dos = new DataOutputStream(os);) {
            log.info("[ClientRequestHandler] new client {} started", Thread.currentThread().getName());
            HttpRequest httpRequest = new HttpRequest(br);
            if (httpRequest.isGet() && httpRequest.matchPath("/calculate")) {
                QueryStrings queryStrings = httpRequest.getQeuryStrings();
                double operand1 = Double.parseDouble(queryStrings.getValue("operand1"));
                double operand2 = Double.parseDouble(queryStrings.getValue("operand2"));
                String operator = queryStrings.getValue("operator");
                double result = Calculator.calculate(operand1, operand2, operator);
                byte[] body = String.valueOf(result).getBytes(StandardCharsets.UTF_8);

                HttpResponse httpResponse = new HttpResponse(dos);
                httpResponse.response200Header("application/json", body.length);
                httpResponse.responseBody(body);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
