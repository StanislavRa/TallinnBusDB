package util;

import javafx.application.Platform;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * @author StanislavR
 */
public class Validator {
    public static boolean stringMatcherValidation(String checkText, String regExPattern, String message) {

        if (!checkText.matches(regExPattern)) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean startDateIsBeforeEndDate(LocalDate startDate, LocalDate endDate, String message) {
        if (startDate.isAfter(endDate)) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean dateIsNotPast(LocalDate date, String message) {
        // need only end date check, consider updating
        if (LocalDate.now().isAfter((date))) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean isChoiceBoxHasValue(ChoiceBox<String> choiceBox, String message) {
        if (choiceBox.getValue() == null) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean isTextFieldEmpty(TextField textField, String message) {
        if (textField.getText().isEmpty()) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean isDatePickerNotEmpty(DatePicker datePicker, String message) {
        if (datePicker.getValue() == null) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean checkTextLength(String text, int length, String message) {
        if (text.length() > length) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }
}
