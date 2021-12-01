package ch.rafi.weather_oracle.server;

import elemental.json.Json;
import elemental.json.JsonObject;
import elemental.json.impl.JreJsonArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

public class SRFWeatherChecker extends AbstractWeatherChecker{

    SRFWeatherChecker(String name) {super(name);}

    @Override
    public Weather findTemperature(String ort){
        try {
            String apiUrl = createUrl(ort);
            HttpHeaders requestHeaders = new HttpHeaders();
            addHeaders(requestHeaders);
            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            ResponseEntity<String> responseEntity = getRestTemplate().exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
            JsonObject geolocation = Json.parse(responseEntity.getBody().substring(1, responseEntity.getBody().length()-1)).get("geolocation");
            String id = geolocation.get("id").asString();
//            String defaultName = geolocation.get("default_name").asString();
            ResponseEntity<String> forecastEntity = getRestTemplate().exchange(createForecastUrl(id), HttpMethod.GET, requestEntity, String.class);
            JreJsonArray hourlyForecast = ((JsonObject)Json.parse(forecastEntity.getBody()).get("forecast")).get("60minutes");
            String temperature = ((JsonObject)((JsonObject)hourlyForecast.get(new Timestamp(System.currentTimeMillis()).getHours())).get("cur_color")).get("temperature").asString();
            return new Weather(findTemprature(temperature), apiUrl, ort);
        } catch (Exception e){
            return Weather.ERROR;
        }
    }

    @Override
    protected double findTemprature(String temperature) {
        return Double.parseDouble(temperature);
    }

    @Override
    protected int findWindSpeed(String response) {
        String before = "Mittelwind: <span>";
        String after = "km/h</span>";
        return Integer.parseInt(find(response, before, after));
    }

    @Override
    protected int findAirPressure(String response) {
        String before = "<td class=\"caption\">Luftdruck</td><td title=\"Luftdruck\">";
        String after = " hPa</td>";
        return Integer.parseInt(find(response, before, after));
    }

    protected String createUrl(String ort) {
        return "https://www.srf.ch/meteoapi/geolocationNames?query="+ ort + "&limit=50";
    }

    private String createResultUrl(String ort, String coordinates, String geolocation_id){
        return "https://www.srf.ch/meteo/wetter/"+ort+"/"+coordinates + "?geolocationNameId=" + geolocation_id;
    }

    private String createForecastUrl(String coordinates){
        return "https://www.srf.ch/meteoapi/forecast/" + coordinates;
    }

}
