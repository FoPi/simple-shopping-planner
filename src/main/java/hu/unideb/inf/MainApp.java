package hu.unideb.inf;

import hu.unideb.inf.controller.SettingEditController;
import hu.unideb.inf.controller.ShoppingPlannerController;
import hu.unideb.inf.dao.*;
import hu.unideb.inf.domain.*;
import hu.unideb.inf.util.HibernateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MainApp extends Application {
    static {
        System.setProperty("logback.configurationFile", MainApp.class.getResource("/config/logback.xml").toString());
    }

    private ProductToShopDAO productToShopDAO;
    private ShopDAO shopDAO;
    private ProductDAO productDAO;
    private ShoppingItemDAO shoppingItemDAO;
    private SettingDAO settingDAO;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private ObservableList<ShoppingItem> shoppingList = FXCollections.observableArrayList();

    private Stage primaryStage;
    private BorderPane rootView;

    /**
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public ObservableList<ShoppingItem> getShoppingList() {
        return this.shoppingList;
    }

    private void initializeDatabase() {
        settingDAO.createOrUpdate(new Setting("fuelPrice", Float.toString(380)));
        settingDAO.createOrUpdate(new Setting("currentPosition", "nowhere"));

        Product milk1 = new Product("Tuti Tipp UHT Milk 1,5% 1l", "tuti-tipp-15", "5927349300975");
        Product milk2 = new Product("Milfina Milk 1,5% 1l", "milfina-01-15", "24067553");
        Product coffee = new Product("Bravos Classic Coffee 1kg", "bravos_coffee_classic_01", "5997349300975");
        Product bread = new Product("Aranykalász Toast Bread 0.5kg", "bread11", "2099005810071");

        productDAO.createOrUpdate(milk1);
        productDAO.createOrUpdate(milk2);
        productDAO.createOrUpdate(coffee);
        productDAO.createOrUpdate(bread);

        Shop auchanDebrecen = new Shop("Auchan áruház - Debrecen", 47.5403259, 21.58361979999995, "Debrecen, 4031 Hungary, Kishatár street 7.");
        Shop privateEbes = new Shop("Privát - Debrecen", 47.4752106, 21.5035383, "Ebes, 4211 Hungary, Forrás street 2.");
        Shop sparDebrecen = new Shop("Spar - Debrecen, Ötvenhatosok tere", 47.5475576, 21.6087569, "Debrecen, 4032 Hungary, Ötvenhatosok tere 6/a.");
        Shop aldiDebrecen = new Shop("Aldi - Debrecen, Ötvenhatosok tere", 47.5471966, 21.6084453, "Debrecen, 4032 Hungary, Ötvenhatosok tere 5.");

        shopDAO.createOrUpdate(auchanDebrecen);
        shopDAO.createOrUpdate(privateEbes);
        shopDAO.createOrUpdate(sparDebrecen);
        shopDAO.createOrUpdate(aldiDebrecen);

        productToShopDAO.createOrUpdate(new ProductToShop(milk2, auchanDebrecen, 118.0));
        productToShopDAO.createOrUpdate(new ProductToShop(bread, auchanDebrecen, 239.0));
        productToShopDAO.createOrUpdate(new ProductToShop(coffee, auchanDebrecen, 1190.0));
        productToShopDAO.createOrUpdate(new ProductToShop(coffee, privateEbes, 1190.0));
        productToShopDAO.createOrUpdate(new ProductToShop(milk2, privateEbes, 159.0));
        productToShopDAO.createOrUpdate(new ProductToShop(milk1, aldiDebrecen, 179.0));
        productToShopDAO.createOrUpdate(new ProductToShop(bread, aldiDebrecen, 200));

        shoppingItemDAO.createOrUpdate(new ShoppingItem("Milk", 4));
        shoppingItemDAO.createOrUpdate(new ShoppingItem("Bread", 1));
        shoppingItemDAO.createOrUpdate(new ShoppingItem("Coffee", 1));
    }

    public MainApp() {
        this.settingDAO = new SettingDAO(sessionFactory);
        this.shoppingItemDAO = new ShoppingItemDAO(sessionFactory);
        this.productDAO = new ProductDAO(sessionFactory);
        this.shopDAO = new ShopDAO(sessionFactory);
        this.productToShopDAO = new ProductToShopDAO(sessionFactory);

        this.initializeDatabase();

        List<ShoppingItem> items = this.shoppingItemDAO.findAll();
        System.out.println(items);

        this.shoppingList.addAll(items.stream().collect(Collectors.toList()));
    }

    /**
     * The default method that will be called when the FX application starts.
     * Initializes the main views
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createRootView();
        createShoppingPlannerView();
    }

    /**
     * Creates the basic view that will contains the views of the controllers
     */
    private void createRootView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/RootView.fxml"));
        try {
            BorderPane rootView = loader.load();
            this.rootView = rootView;

            Scene scene = new Scene(rootView);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(1);
                }
            });

            scene.getStylesheets().add(getClass().getResource("/style/application.css").toExternalForm());

            primaryStage.show();
            primaryStage.setTitle("Simple shopping planner");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private void createShoppingPlannerView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ShoppingPlannerView.fxml"));
        try {
            AnchorPane shoppingPlannerView = (AnchorPane) loader.load();
            rootView.setCenter(shoppingPlannerView);

            ShoppingPlannerController controller = loader.getController();

            controller.setApp(this);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void createManageShoppingListView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ShoppingPlannerView.fxml"));
        try {
            AnchorPane shoppingPlannerView = (AnchorPane) loader.load();
            rootView.setCenter(shoppingPlannerView);

            ShoppingPlannerController controller = loader.getController();

            controller.setApp(this);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void createSettingEditView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/SettingEditView.fxml"));
        try {
            AnchorPane settingEditView = loader.load();
            SettingEditController controller = loader.getController();

            Stage editorStage = new Stage();
            editorStage.initModality(Modality.WINDOW_MODAL);
            editorStage.initOwner(this.primaryStage);

            Scene scene = new Scene(settingEditView);
            editorStage.setScene(scene);
            controller.setEditorStage(editorStage);

            editorStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
