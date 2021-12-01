package ch.rafi.weather_oracle.server;

public class SearchMeteoWeatherChecker extends AbstractWeatherChecker{

    SearchMeteoWeatherChecker(String name) {super(name);}

    @Override
    protected double findTemprature(String response) {
        String before = "<td title=\"Lufttemperatur 2 m über Boden\">";
        String after = "°C</td>";
        return Double.parseDouble(find(response, before, after));
    }

    @Override
    protected int findWindSpeed(String response) {
        String before = "<td title=\"Zehnminutenmittel\">";
        String after = "km/h</td>";
        return Integer.parseInt(find(response, before, after));
    }

    @Override
    protected int findAirPressure(String response) {
        String before = "<td title=\"Luftdruck auf Stationshöhe \\(QFE\\)\">";
        String after = "hPa</td>";
        return Integer.parseInt(find(response, before, after));
    }

    @Override
    protected String createUrl(String plz) {
        return "https://meteo.search.ch/"+plz;
    }

}
