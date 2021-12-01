package ch.rafi.weather_oracle.server;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class AbstractWeatherChecker {
    private final RestTemplate restTemplate;
    private final String name;
    private boolean hasError;

    AbstractWeatherChecker(String name){
        this.name = name;
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(factory);
    }

    public Weather findWeather(String ort) {
        String url = createUrl(ort);
        String response = restTemplate.getForObject(url, String.class);
        return new Weather(findTemprature(response), findWindSpeed(response), findAirPressure(response), url, ort);
    }

    public Weather findTemperature(String ort){
        try {
            String url = createUrl(ort);
            HttpHeaders requestHeaders = new HttpHeaders();
            addHeaders(requestHeaders);
            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            String response = responseEntity.getBody();
            return new Weather(findTemprature(response), url, ort);
        } catch (Exception e){
            return Weather.ERROR;
        }
    }

    protected void addHeaders(HttpHeaders requestHeaders){}

    protected abstract double findTemprature(String response);
    protected abstract int findWindSpeed(String response);
    protected abstract int findAirPressure(String response);

    protected String find(String response, String before, String after) {
        String[] cutBefore = response.split(before);
        return cutBefore[1].split(after)[0].trim();
    }

    protected abstract String createUrl(String ort);

    public void setHasError(boolean hasError){this.hasError = hasError;}
    public boolean hasError() {return hasError;}

    public String getActive() {return hasError() ? "❌" : "✔";}
    public String getName() {return name;}

    protected RestTemplate getRestTemplate() {return restTemplate;}
}
