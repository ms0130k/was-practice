package org.shock;

import lombok.extern.slf4j.Slf4j;
import org.shock.was.CustomWebApplicationServer;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            new CustomWebApplicationServer(7080).start();
        } catch (IOException e) {
            log.error("처리하지 못 한 예외");
        }
    }
}