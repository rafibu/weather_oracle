package ch.rafi.weather_oracle.server;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weather")
public class WeatherXML {

    public WeatherXML(String timestamp, String plz, String temperature) {
        this.timestamp = timestamp;
        this.plz = plz;
        this.temperature = temperature;
    }

    public WeatherXML(){}

    @XmlElement
    private String timestamp;
    @XmlElement
    private String plz;
    @XmlElement
    private String temperature;

    public String getTimestamp() {return timestamp;}
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}

    public String getPlz() {return plz;}
    public void setPlz(String plz) {this.plz = plz;}

    public String getTemperature() {return temperature;}
    public void setTemperature(String temperature) {this.temperature = temperature;}

}
