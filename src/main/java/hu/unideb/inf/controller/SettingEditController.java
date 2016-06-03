package hu.unideb.inf.controller;

import hu.unideb.inf.dao.SettingDAO;
import hu.unideb.inf.domain.Setting;
import hu.unideb.inf.util.HibernateUtil;
import hu.unideb.inf.validator.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;

public class SettingEditController {
    private static final Logger logger = LoggerFactory.getLogger(SettingEditController.class);

    private SettingDAO settingDao;

    private Stage editorStage;

    @FXML
    private TextField fuelPrice;

    @FXML
    private TextField currentPosition;

    /**
     * Constructor
     */
    public SettingEditController() {
        this.settingDao = new SettingDAO(HibernateUtil.getSessionFactory());
    }

    @FXML
    private void initialize() {
        List<Setting> settings = this.settingDao.findAll();

        for (Setting setting : settings) {
            if (setting.getName().equals("fuelPrice")) {
                this.fuelPrice.setText(setting.getValue());
            }

            if (setting.getName().equals("currentPosition")) {
                this.currentPosition.setText(setting.getValue());
            }
        }
    }

    /**
     * Sets the editor stage, which can be used to close it.
     *
     * @param editorStage The stage that is contains the controller
     */
    public void setEditorStage(Stage editorStage) {
        this.editorStage = editorStage;
    }

    @FXML
    private void onSaveClick() {
        logger.info("Setting editor save button clicked");
        if (!this.validate()) {
            return;
        }

        List<Setting> settings = this.settingDao.findAll();

        for (Setting setting : settings) {
            if (
                setting.getName().equals("fuelPrice")
                && !setting.getValue().equals(this.fuelPrice.getText())
            ) {
                Float value = Float.parseFloat(this.fuelPrice.getText());
                setting.setValue(value.toString());
                saveSetting(setting);
                logger.debug("Fuel price field saved.");
            }

            if (
                setting.getName().equals("currentPosition")
                && !setting.getValue().equals(this.currentPosition.getText())
            ) {
                setting.setValue(this.currentPosition.getText());
                saveSetting(setting);
                logger.debug("Current position field saved.");
            }
        }

        this.editorStage.close();
    }

    private void saveSetting(Setting setting) {
        this.settingDao.createOrUpdate(setting);
    }

    private boolean validate() {
        logger.info("Validation started.");
        Validator validator = new Validator();

        HashSet<String> fuelPriceValidations = new HashSet<>();
        fuelPriceValidations.add("required");
        fuelPriceValidations.add("isFloat");
        validator.addValidation(this.fuelPrice, fuelPriceValidations);

        HashSet<String> currentPositionValidations = new HashSet<>();
        currentPositionValidations.add("required");
        validator.addValidation(this.currentPosition, currentPositionValidations);

        boolean isValid = false;
        try {
            isValid = validator.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    @FXML
    private void onCancelClick() {
        logger.info("Setting editor cancel button clicked");
        this.editorStage.close();
        logger.info("Setting editor stage closed!");
    }
}
