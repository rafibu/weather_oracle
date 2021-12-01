package ch.rafi.weather_oracle;

import ch.rafi.weather_oracle.server.AbstractWeatherChecker;
import ch.rafi.weather_oracle.server.WeatherOracle;
import ch.rafi.weather_oracle.ui.OraclePanel;
import ch.rafi.weather_oracle.ui.VLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VLayout {
	public static String plz = "4500";
	public MainView() {
		add(new OraclePanel(this));
		addTextfield("PLZ", MainView::setPlz, plz);
		Button searchWeatherBtn = new Button("Search Weather");
		add(searchWeatherBtn);
		searchWeatherBtn.addClickListener(event -> {
			VLayout panel = new VLayout(this);
			panel.add(new Html("<div>Result for PLZ: " + plz + "<br></div>"));
			for(AbstractWeatherChecker checker: WeatherOracle.get().getWeatherCheckers()) {
				panel.addText(checker.findTemperature(plz).toString());
				panel.add(new Html("<div><br></div>"));
			}
			add(panel);
		});
	}

	public static void setPlz(String plz) {MainView.plz = plz;}
}