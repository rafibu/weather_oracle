package ch.rafi.weather_oracle.server;

import java.sql.Timestamp;
import java.time.Clock;

/**
 * Represents a single weather request
 */
public class Weather {

    public static final Weather ERROR = new Weather(Double.MAX_VALUE, null, null){
        @Override
        public String toString() {
            return "Not Available";
        }
    };
    private final double temperature; //°C
    private int windSpeed; //km/h
    private int airPressure; //hPa
    private final Timestamp timestamp;
    private final String url;
    private final String ort;

    public Weather(double temperature, int windSpeed, int airPressure, String url, String ort) {
        this(temperature, url, ort);
        this.windSpeed = windSpeed;
        this.airPressure = airPressure;
    }

    public Weather(double temperature, String url, String ort){
        this.temperature = temperature;
        this.url = url;
        this.ort = ort;
        this.timestamp = new Timestamp(Clock.systemDefaultZone().millis());
    }

    public double getTemperature() {return temperature;}
    public int getWindSpeed() {return windSpeed;}
    public int getAirPressure() {return airPressure;}
    public Timestamp getTimestamp() {return timestamp;}
    public String getOrt() {return ort;}

    @Override
    public String toString() {
        return "Weather at " + ort + " {" +
                "temperature=" + temperature +
                ", timestamp=" + timestamp +
                ", url='" + url + '\'' +
                '}';
    }
}
