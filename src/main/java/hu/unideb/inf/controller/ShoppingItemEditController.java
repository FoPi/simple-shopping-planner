package hu.unideb.inf.controller;

import hu.unideb.inf.dao.ShoppingItemDAO;
import hu.unideb.inf.domain.ShoppingItem;
import hu.unideb.inf.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that responsible for Shopping Item editing view
 */
public class ShoppingItemEditController {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(ShoppingItemEditController.class);

    private ShoppingItemDAO shoppingItemDAO;

    private ShoppingEditController controller;

    @FXML
    private javafx.scene.control.TextField shoppingItemProductNameField;

    @FXML
    private TextField shoppingItemQuantityField;

    private ShoppingItem shoppingItem;

    /**
     * Constructor
     */
    public ShoppingItemEditController() {
        this.shoppingItemDAO = new ShoppingItemDAO(sessionFactory);
    }

    /**
     *
     * @param controller
     */
    public void setController(ShoppingEditController controller) {
        this.controller = controller;
    }

    @FXML
    private void initialize() {
        setShoppingItemData(null);
    }

    public void setShoppingItemData(ShoppingItem item) {
        this.shoppingItem = item;
        this.shoppingItemProductNameField.setText(item.getProductName());
        this.shoppingItemQuantityField.setText(Float.toString(item.getQuantity()));
    }

    private void onSaveClick() {
        if (!this.validate()) {
            return;
        }

        this.shoppingItem.setProductName(this.shoppingItemProductNameField.getText());
        this.shoppingItem.setQuantity(Float.parseFloat(this.shoppingItemQuantityField.getText()));
    }

    private boolean validate() {
        boolean isValid = true;
        this.shoppingItemProductNameField.getStyleClass().remove("input-error");
        this.shoppingItemQuantityField.getStyleClass().remove("input-error");

        if (this.shoppingItemProductNameField.getText().isEmpty()) {
            isValid = false;
            this.shoppingItemProductNameField.getStyleClass().add("input-error");
        }

        if (
                this.shoppingItemQuantityField.getText().isEmpty()
                || !this.shoppingItemQuantityField.getText().matches("\\d+(\\.(\\d)+)?")
        ) {
            isValid = false;
            this.shoppingItemQuantityField.getStyleClass().add("input-error");
        }

        return isValid;
    }

    private void onCancelClick() {

    }

    private void showShoppingItemData(ShoppingItem item) {
        if (item == null) {
            this.shoppingItemProductNameField.setText("");
            this.shoppingItemQuantityField.setText("");
        } else {
            this.shoppingItemProductNameField.setText(item.getProductName());
            this.shoppingItemQuantityField.setText(Float.toString(item.getQuantity()));
        }
    }
}
