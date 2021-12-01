package ch.rafi.weather_oracle.ui;

import ch.rafi.weather_oracle.server.AbstractWeatherChecker;
import ch.rafi.weather_oracle.server.WeatherOracle;
import ch.rafi.weather_oracle.util.ILayout;
import ch.rafi.weather_oracle.util.ObjUtil;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;

public class OraclePanel extends VLayout{

    private final TextField update;

    public OraclePanel(ILayout parent){
        super(parent);
        WeatherOracle oracle = WeatherOracle.get();
        update = addTextfield("Last Updated", null, ObjUtil.toString(oracle.getLastUpdate()));
        update.setEnabled(false);
        Grid<AbstractWeatherChecker> grid = new Grid<>(AbstractWeatherChecker.class);
        grid.setWidth("300px");
        grid.setHeight("250px");
        grid.setItems(oracle.getWeatherCheckers());
        grid.asSingleSelect().addValueChangeListener(e -> {
            fireStateChanged();
        });
        add(grid);
    }

    @Override
    public void fireStateChanged() {
        super.fireStateChanged();
        update.setValue(ObjUtil.toString(WeatherOracle.get().getLastUpdate()));
    }
}
