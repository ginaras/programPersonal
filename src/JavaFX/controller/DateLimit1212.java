package JavaFX.controller;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class DateLimit1212<dayCellFactory> implements DateLimit {
    DatePicker myDatePicker = new DatePicker(); // This DatePicker is shown to user
    DatePicker maxDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date

    public DateLimit1212 ( Callback<DatePicker, DateCell> dayCellFactory ) {

        this.dayCellFactory = dayCellFactory;
    }

    public DateLimit1212 () {
        
    }

    public DatePicker getMaxDate () {
        return maxDate;
    }

    public void setMaxDate ( DatePicker maxDate ) {
        this.maxDate = maxDate;
    }


    // Max date available will be 2015-01-01
    Callback<DatePicker, DateCell> dayCellFactory;

    public void DateLimit12123 ( Callback<DatePicker, DateCell> dayCellFactory ) {
        this.dayCellFactory = dayCellFactory;
    }

    //  dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
        @Override
        public void updateItem ( LocalDate item, boolean empty ) {
            //super.updateItem(item, empty);
            if (item.isAfter(maxDate.getValue())) { //Disable all dates after required date
                maxDate.setDisable(true);
            getMessage();
        }


    }

    private void getMessage () {
        System.out.println("eroare");
    }}




