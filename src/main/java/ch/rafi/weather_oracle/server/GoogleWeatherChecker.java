package ch.rafi.weather_oracle.server;

import org.springframework.http.HttpHeaders;

public class GoogleWeatherChecker extends AbstractWeatherChecker{

    GoogleWeatherChecker(String name) {super(name);}

    @Override
    protected double findTemprature(String response) {
        String before = "<div class=\"BNeawe iBp4i AP7Wnd\">";
        String after = "°";
        return findTemp(response, before, after);
    }

    private double findTemp(String response, String before, String after) {
        for(String s : response.split(before)){
            try{
                return Double.parseDouble(s.split(after)[0]);
            } catch (NumberFormatException ignored){}
        }
        throw new IllegalStateException("No Temperature found");
    }

    @Override
    protected int findWindSpeed(String response) {
        return Integer.MAX_VALUE;
    }

    @Override
    protected int findAirPressure(String response) {
        return Integer.MAX_VALUE;
    }

    @Override
    protected String createUrl(String plz) {
        return "https://www.google.com/search?q=wetter+"+plz;
    }

    @Override
    protected void addHeaders(HttpHeaders requestHeaders) {
        super.addHeaders(requestHeaders);
        requestHeaders.add("Cookie", "CONSENT=YES+shp.gws-20211110-0-RC1.de+FX+323");
    }
}
