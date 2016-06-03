package hu.unideb.inf.validator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * Validator for input validation
 */
public class Validator {
    private static final Logger logger = LoggerFactory.getLogger(Validator.class);
    private HashMap<TextInputControl, HashSet<String>> validationFields = new HashMap<>();

    /**
     * Add input for validation
     *
     * @param input
     * @param validations
     */
    public void addValidation(TextInputControl input, HashSet<String> validations) {
        HashSet<String> existingValidations = this.validationFields.get(input);

        if (existingValidations == null) {
            existingValidations = new HashSet<>();
        }

        existingValidations.addAll(validations);

        this.validationFields.put(input, existingValidations);
    }

    public boolean validate() {
        boolean isValid = true;

        for (Entry<TextInputControl, HashSet<String>> entry : this.validationFields.entrySet()) {
            TextInputControl input = entry.getKey();
            input.getStyleClass().remove("input-error");
            HashSet<String> validations = entry.getValue();

            for (String validation : validations) {
                isValid = isValid && validateInput(input, validation);
                System.out.println(isValid);
            }

            if (!isValid) {
                input.getStyleClass().add("input-error");
            }
        }

        return isValid;
    }

    private boolean validateInput(TextInputControl input, String validation) {
        logger.info("Validate " + input.getClass() + ", validation method: " + validation);
        try {
            Method method = getClass().getDeclaredMethod(validation, input.getClass());
            return (boolean) method.invoke(this, input);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    private boolean required(TextInputControl input) {
        return !input.getText().isEmpty();
    }

    private boolean isFloat(TextField input) {
        return input.getText().matches("\\d+(\\.(\\d)+)?");
    }
}
