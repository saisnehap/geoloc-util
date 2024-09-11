package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeolocationUtil {

    private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide at least one location in the format: City, State or Zip code");
            return;
        }

        for (String location : args) {
            if (location.matches("\\d{5}")) {
                // Treat as zip code
                getGeolocationByZip(location);
            } else {
                // Treat as City, State
                getGeolocationByCityState(location);
            }
        }
    }

    private static void getGeolocationByCityState(String cityState) {
        try {
            // Encode the cityState to handle special characters (e.g., spaces, commas)
            String encodedCityState = URLEncoder.encode(cityState, StandardCharsets.UTF_8.toString());
            String apiUrl = "http://api.openweathermap.org/geo/1.0/direct?q=" + encodedCityState + ",US&limit=1&appid=" + API_KEY;

            HttpURLConnection conn = createConnection(apiUrl);
            String response = getApiResponse(conn);

            if (response.isEmpty()) {
                System.out.println("No data found for " + cityState);
                return;
            }

            JSONArray jsonArray = new JSONArray(response);
            if (jsonArray.length() > 0) {
                JSONObject location = jsonArray.getJSONObject(0);
                System.out.println("Location: " + location.getString("name") +
                        ", Lat: " + location.getDouble("lat") +
                        ", Lon: " + location.getDouble("lon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getGeolocationByZip(String zipCode) {
        try {
            String apiUrl = "http://api.openweathermap.org/geo/1.0/zip?zip=" + zipCode + ",US&appid=" + API_KEY;
            HttpURLConnection conn = createConnection(apiUrl);
            String response = getApiResponse(conn);

            if (response.isEmpty()) {
                System.out.println("No data found for ZIP code: " + zipCode);
                return;
            }

            JSONObject location = new JSONObject(response);
            System.out.println("Location: " + location.getString("name") +
                    ", Lat: " + location.getDouble("lat") +
                    ", Lon: " + location.getDouble("lon"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection createConnection(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        return conn;
    }

    private static String getApiResponse(HttpURLConnection conn) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }
}
