package ch.rafi.weather_oracle.ui;

import ch.rafi.weather_oracle.util.IFilterElement;
import ch.rafi.weather_oracle.util.ILayout;
import ch.rafi.weather_oracle.util.LayoutUtil;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Set;
import java.util.function.Consumer;

public class VLayout extends VerticalLayout implements ILayout {
    private final ILayout parent;

    public VLayout(ILayout parent){
        this.parent = parent;
    }

    //TODO: rbu 04.11.2021, Maybe try to find another way
    public VLayout() {parent = null;}


    public Text addText(String text){
        return LayoutUtil.addText(text, this);
    }

    public TextField addTextfield(String title, Consumer<String> setter){
        return LayoutUtil.addTextfield(title, setter, this);
    }
    public TextField addTextfield(String title, Consumer<String> setter, String value){
        return LayoutUtil.addTextfield(title, setter, value,this);
    }

    public <T extends IFilterElement> ComboBox<T> addCombobox(String title, Consumer<T> setter, T[] choices){
        return LayoutUtil.addCombobox(title, setter, choices, this);
    }
    public <T extends IFilterElement> ComboBox<T> addCombobox(String title, Consumer<T> setter, T value, T[] choices){
        return LayoutUtil.addCombobox(title, setter, value, choices, this);
    }

    public <T extends IFilterElement> Select<T> addSelect(String title, Consumer<T> setter, T[] choices){
        return LayoutUtil.addSelect(title, setter, choices, this);
    }
    public <T extends IFilterElement> Select<T> addSelect(String title, Consumer<T> setter, T value, T[] choices){
        return LayoutUtil.addSelect(title, setter, value, choices, this);
    }

    public <T extends IFilterElement> Select<T> addSelect(String title, Consumer<T> setter, T[] choices, boolean includeNull){
        return LayoutUtil.addSelect(title, setter, choices, includeNull, this);
    }
    public <T extends IFilterElement> Select<T> addSelect(String title, Consumer<T> setter, T value, T[] choices, boolean includeNull){
        return LayoutUtil.addSelect(title, setter, value, choices, includeNull, this);
    }

    public Anchor addAnchor(String title, String link){
        return LayoutUtil.addAnchor(title, link, this);
    }

    public <T extends IFilterElement> RadioButtonGroup<T> addRadioButtons(String title, Consumer<T> setter, T[] choices){
        return LayoutUtil.addRadioButtons(title, setter, choices, this);
    }
    public <T extends IFilterElement> RadioButtonGroup<T> addRadioButtons(String title, Consumer<T> setter, T value, T[] choices){
        return LayoutUtil.addRadioButtons(title, setter, value, choices, this);
    }

    public <T extends IFilterElement> RadioButtonGroup<T>  addRadioButtons(String title, Consumer<T> setter, T[] choices, boolean vertical){
        return LayoutUtil.addRadioButtons(title, setter, choices, vertical,this);
    }
    public <T extends IFilterElement> RadioButtonGroup<T>  addRadioButtons(String title, Consumer<T> setter, T value, T[] choices, boolean vertical){
        return LayoutUtil.addRadioButtons(title, setter, value, choices, vertical,this);
    }

    public TextArea addTextArea(String title, Consumer<String> setter){
        return LayoutUtil.addTextArea(title, setter, this);
    }

    public TextArea addTextArea(String title, Consumer<String> setter, String value){
        return LayoutUtil.addTextArea(title, setter, value, this);
    }

    public TextArea addTextArea(String title, Consumer<String> setter, String value, String placeholder){
        return LayoutUtil.addTextArea(title, setter, value, placeholder, this);
    }

    public NumberField addNumberField(String title, Consumer<Double> setter){
        return LayoutUtil.addNumberField(title, setter, this);
    }
    public NumberField addNumberField(String title, Consumer<Double> setter, Double value){
        return LayoutUtil.addNumberField(title, setter, value, this);
    }

    public NumberField addNumberField(String title, Consumer<Double> setter, boolean hasControls){
        return LayoutUtil.addNumberField(title, setter, hasControls, this);
    }

    public NumberField addNumberField(String title, Consumer<Double> setter, boolean hasControls, Double max){
        return LayoutUtil.addNumberField(title, setter, hasControls, max, this);
    }

    public NumberField addNumberField(String title, Consumer<Double> setter, boolean hasControls, Double min, Double max, Double step){
        return LayoutUtil.addNumberField(title, setter, hasControls, min, max, step, this);
    }
    public NumberField addNumberField(String title, Consumer<Double> setter, Double value, boolean hasControls, Double min, Double max, Double step){
        return LayoutUtil.addNumberField(title, setter, value, hasControls, min, max, step, this);
    }

    public IntegerField addIntegerField(String title, Consumer<Integer> setter){
        return LayoutUtil.addIntegerField(title, setter, this);
    }

    public IntegerField addIntegerField(String title, Consumer<Integer> setter, Integer value){
        return LayoutUtil.addIntegerField(title, setter, value,this);
    }

    public IntegerField addIntegerField(String title, Consumer<Integer> setter, boolean hasControls){
        return LayoutUtil.addIntegerField(title, setter, hasControls, this);
    }

    public IntegerField addIntegerField(String title, Consumer<Integer> setter, boolean hasControls, Integer max){
        return LayoutUtil.addIntegerField(title, setter, hasControls, max, this);
    }

    public IntegerField addIntegerField(String title, Consumer<Integer> setter, boolean hasControls, Integer min, Integer max, Integer step) {
        return LayoutUtil.addIntegerField(title, setter, hasControls, min, max, step, this);
    }
    public IntegerField addIntegerField(String title, Consumer<Integer> setter, Integer value, boolean hasControls, Integer min, Integer max, Integer step) {
        return LayoutUtil.addIntegerField(title, setter, value, hasControls, min, max, step, this);
    }

    public Checkbox addCheckbox(String title, Consumer<Boolean> setter){
        return LayoutUtil.addCheckbox(title, setter, this);
    }
    public Checkbox addCheckbox(String title, Consumer<Boolean> setter, Boolean value){
        return LayoutUtil.addCheckbox(title, setter, value, this);
    }

    public <T extends IFilterElement> CheckboxGroup<T> addCheckboxGroup(String title, Consumer<Set<T>> setter, T[] choices){
        return LayoutUtil.addCheckboxGroup(title, setter, choices, this);
    }
    public <T extends IFilterElement> CheckboxGroup<T> addCheckboxGroup(String title, Consumer<Set<T>> setter, Set<T> value, T[] choices){
        return LayoutUtil.addCheckboxGroup(title, setter, value, choices, this);
    }

    public <T extends IFilterElement> CheckboxGroup<T>  addCheckboxGroup(String title, Consumer<Set<T>> setter, T[] choices, boolean vertical){
        return LayoutUtil.addCheckboxGroup(title, setter, choices, vertical, this);
    }
    public <T extends IFilterElement> CheckboxGroup<T>  addCheckboxGroup(String title, Consumer<Set<T>> setter, Set<T> value, T[] choices, boolean vertical){
        return LayoutUtil.addCheckboxGroup(title, setter, value, choices, vertical, this);
    }

    public <T> MultiSelectListBox<T> addMultiSelect(Consumer<Set<T>> setter, Set<T> value, T[] choices){
        return LayoutUtil.addMultiSelect(setter, value, choices, this);
    }
    public <T> MultiSelectListBox<T> addMultiSelect(Consumer<Set<T>> setter, T[] choices){
        return LayoutUtil.addMultiSelect(setter, choices, this);
    }

    public void fireStateChanged(){if(parent != null) parent.fireStateChanged();}
    public void add(Component... components){ super.add(components); }
}
