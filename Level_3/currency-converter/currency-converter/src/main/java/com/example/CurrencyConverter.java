package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

// real time currency converter
public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input base currency and target currency
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter amount in base currency: ");
        double amount = scanner.nextDouble();

        // Fetch exchange rate and convert currency
        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
        } catch (IOException e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String url = API_URL + baseCurrency;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);

        try {
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            JSONObject json = new JSONObject(responseBody.toString());
            JSONObject rates = json.getJSONObject("rates");

            if (rates.has(targetCurrency)) {
                return rates.getDouble(targetCurrency);
            } else {
                throw new IOException("Target currency not found.");
            }
        } finally {
            response.close();
        }
    }
}
