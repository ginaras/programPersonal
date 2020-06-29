package JavaFX.controller;

import java.time.LocalDate;

public interface DateLimit {
    //  dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
    void updateItem( LocalDate item, boolean empty );
}
