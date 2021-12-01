package ch.rafi.weather_oracle.server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherOracle implements Runnable{

    private static WeatherOracle INSTANCE;

    private final int threshold = 1;

    private final List<AbstractWeatherChecker> weatherCheckers;
    private Timestamp lastUpdate;

    private WeatherOracle(){
        weatherCheckers = createCheckers();
    }

    public static void initialize() {
        assert INSTANCE == null;
        INSTANCE = new WeatherOracle();
        new Thread(INSTANCE).start();
    }

    private List<AbstractWeatherChecker> createCheckers() {
        List<AbstractWeatherChecker> list = new ArrayList<>();
        list.add(new SRFWeatherChecker("SRF"));
        list.add(new GoogleWeatherChecker("Google"));
        list.add(new SearchMeteoWeatherChecker("Search.Meteo"));
        list.add(new LandiWeatherChecker("Landi Wetter"));
        return list;
    }

    public static WeatherOracle get(){return INSTANCE;}

    public List<AbstractWeatherChecker> getWeatherCheckers() {return weatherCheckers;}

    public void checkNodes(){
        for(AbstractWeatherChecker checker: getWeatherCheckers()){
            checker.setHasError(checker.findTemperature("4500").equals(Weather.ERROR));
        }
        this.lastUpdate = new Timestamp(Clock.systemDefaultZone().millis());
    }

    private double getTemperature(String ort){
        List<AbstractWeatherChecker> activeCheckers = getWeatherCheckers().stream().filter(c -> !c.hasError()).collect(Collectors.toList());
        if(activeCheckers.size() >= threshold) {
            double[] temperatures = new double[activeCheckers.size()];
            int i = 0;
            for (AbstractWeatherChecker checker : activeCheckers) {
                temperatures[i++] = checker.findTemperature(ort).getTemperature();
            }
            return Arrays.stream(temperatures).filter(t -> t != Integer.MAX_VALUE).average().getAsDouble();
        } else {
            throw new IllegalStateException("Not enough Active Weather Nodes, active nodes: " + activeCheckers.size());
        }
    }

    public Timestamp getLastUpdate() {return lastUpdate;}

    @Override
    public void run() {
        while(true){
            checkNodes();
            try {
                Thread.sleep(5*1000*60);
            } catch (InterruptedException e) {

            }
        }
    }

    public File createFile(String plz) throws Exception{
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        document.setXmlStandalone(true);
        // root element
        Element root = document.createElement("weather");
        document.appendChild(root);

        root.appendChild(append(document, "date", Instant.now().toString()));
        root.appendChild(append(document,"plz", plz));
        root.appendChild(append(document,"temperature", Double.toString(getTemperature(plz))));

        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        File res = new File("weather.xml");
        StreamResult streamResult = new StreamResult(res);
        transformer.transform(domSource, streamResult);
        return res;
    }

    private Element append(Document document, String name, String value) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        return element;
    }

    public WeatherXML createXML(String plz) {
        return new WeatherXML(Instant.now().toString(), plz, Double.toString(getTemperature(plz)));
    }
}
