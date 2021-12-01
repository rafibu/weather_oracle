package ch.rafi.weather_oracle.util;

public interface ILayout {

    /**
     * Should define which elements have to be rerendered if some value changes
     */
    public void fireStateChanged();

    public void add(com.vaadin.flow.component.Component... components);
}
