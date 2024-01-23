package com.nespresso.heating;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Implementation of the ExternalInformationRetriever using Http.
 */
public class ExternalInformationRetrieverImpl implements ExternalInformationRetriever {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Execute a GET request and return the result of it as an integer.
     * @param url the URL where the wanted information is.
     * @return the result of the request as an integer.
     * @throws URISyntaxException if the URL is malformed.
     * @throws IOException if there is a problem with the request.
     * @throws InterruptedException if there is a problem with the request.
     */
    private int doGet(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        return Integer.parseInt(httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body());
    }

    @Override
    public int getEndHour() {
        try {
            return doGet("http://probe.home:9990/end");
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getStartHour() {
        try {
            return doGet("http://probe.home:9990/start");
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getTemperature() {
        try {
            return doGet("http://probe.home:9990/temp");
        } catch (Exception e) {
            return 0;
        }
    }
}
