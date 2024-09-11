package com.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeolocationUtilTest {

    @Test
    public void testGeolocationByCityState() {
        String[] args = {"Madison, WI"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        GeolocationUtil.main(args);

        assertTrue(outContent.toString().contains("Madison"));
    }

    @Test
    public void testGeolocationByZipCode() {
        String[] args = {"10001"};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        GeolocationUtil.main(args);

        assertTrue(outContent.toString().contains("New York"));
    }
}
