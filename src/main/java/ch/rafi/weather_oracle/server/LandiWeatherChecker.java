package ch.rafi.weather_oracle.server;

import elemental.json.Json;
import elemental.json.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

public class LandiWeatherChecker extends AbstractWeatherChecker{

    LandiWeatherChecker(String name) {super(name);}

    @Override
    public Weather findTemperature(String ort){
        try {
            String apiUrl = createUrl(ort);
            HttpHeaders requestHeaders = new HttpHeaders();
            addHeaders(requestHeaders);
            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            ResponseEntity<String> responseEntity = getRestTemplate().exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
            JsonObject object = Json.parse(responseEntity.getBody().split("\\[")[1].split(",\\{")[0]);
            String id = object.get("id").asString();
            String response = getRestTemplate().exchange(createResultUrl(id), HttpMethod.GET, requestEntity, String.class).getBody();
            return new Weather(findTemprature(response), "https://www.landi.ch/wetter", ort);
        } catch (Exception e){
            return Weather.ERROR;
        }
    }

    @Override
    protected double findTemprature(String response) {
        JsonObject abschnitt = Json.parse(response).getObject("abschnitt");
        String temp = abschnitt.getString("temp");
        return Double.parseDouble(temp.split("°")[0]);
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
        return "https://www.landi.ch/weather/api/geosuche/de/"+ ort;
    }

    private String createResultUrl(String ortId){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return "https://www.landi.ch/weather/api/lokalprognosekurz/de/"+ (1900+now.getYear()) + "-"+ (now.getMonth() + 1) + "-" + now.getDate() + "/" + now.getHours() + "/" + now.getMinutes() + "/" + ortId;
    }

}
