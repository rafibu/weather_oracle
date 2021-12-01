package ch.rafi.weather_oracle.ui;

import ch.rafi.weather_oracle.server.WeatherOracle;
import ch.rafi.weather_oracle.server.WeatherXML;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class WeatherService extends VLayout{

    @GetMapping(value = "/weather")
    @ResponseBody
    public ResponseEntity<WeatherXML> getWeatherXML(@RequestParam String plz, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/xml");//FIXME: is actually JSON
            return ResponseEntity.ok(WeatherOracle.get().createXML(plz));
        } catch(Exception e) {
            response.sendError(510, e.getMessage());
        }
        return null;
    }
}
