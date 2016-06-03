package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.domain.ShoppingItem;
import hu.unideb.inf.util.HibernateUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingPlannerController {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(ShoppingPlannerController.class);

    /**
     *
     */
    private MainApp app;

    @FXML
    private TableView<ShoppingItem> shoppingListTable;

    @FXML
    private TableColumn<ShoppingItem, String> shoppingListProductNameColumn;

    @FXML
    private TableColumn<ShoppingItem, Float> shoppingListQuantityColumn;

    /**
     * Setter method to set the main app
     *
     * @param app
     */
    public void setApp(MainApp app) {
        this.app = app;
        this.shoppingListTable.setItems(this.app.getShoppingList());
    }

    @FXML
    private void initialize() {
        this.shoppingListProductNameColumn.setCellValueFactory(new PropertyValueFactory<ShoppingItem, String>("productName"));
        this.shoppingListQuantityColumn.setCellValueFactory(new PropertyValueFactory<ShoppingItem, Float>("quantity"));
    }

    @FXML
    public void onSettingsClick() {
        logger.info("Settings button click action occurred");
        app.createSettingEditView();
    }

    @FXML
    public void onManageListClick() {
        logger.info("Manage button click action occurred");
        app.createManageShoppingListView();
    }

    @FXML
    public void onPlanRouteClick() {
        logger.info("Plan route button click action occurred");
    }
}
