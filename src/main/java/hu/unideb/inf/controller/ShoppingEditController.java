package hu.unideb.inf.controller;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.dao.ShoppingItemDAO;
import hu.unideb.inf.domain.Shop;
import hu.unideb.inf.domain.ShoppingItem;
import hu.unideb.inf.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class that responsible for Shopping Item editing view
 */
public class ShoppingEditController {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(ShoppingEditController.class);

    private ShoppingItemDAO shoppingItemDAO;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label productQuantityLabel;

    @FXML
    private TableView<ShoppingItem> shoppingListTable;

    @FXML
    private TableColumn<ShoppingItem, String> shoppingListProductNameColumn;

    @FXML
    private TableColumn<ShoppingItem, Float> shoppingListQuantityColumn;

    private MainApp app;

    /**
     * Constructor
     */
    public ShoppingEditController() {
        this.shoppingItemDAO = new ShoppingItemDAO(sessionFactory);
    }

    /**
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

    /**
     * Handles the "New" item button click event
     *
     * @param event The event that is occurred
     */
    public void onNewItemClick() {

    }

    /**
     * Handles the "Modify" item button click event
     *
     * @param event The event that is occurred
     */
    public void onModifyItemClick() {

    }

    /**
     * Handles the "Delete" item button click event
     *
     * @param event The event that is occurred
     */
    public void onDeleteItemClick() {

    }

    /**
     * Handles the "Clear list" item button click event
     *
     * @param event The event that is occurred
     */
    public void onClearListClick() {
        logger.info("Shopping list clear request");
        List<ShoppingItem> shoppingItems = this.shoppingItemDAO.findAll();
        for (ShoppingItem shoppingItem : shoppingItems) {
            this.shoppingItemDAO.delete(shoppingItem);
        }
    }
}
