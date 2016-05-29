package hu.unideb.inf;

import hu.unideb.inf.controller.ShoppingPlannerController;
import hu.unideb.inf.dao.ShoppingItemDAO;
import hu.unideb.inf.domain.ShoppingItem;
import hu.unideb.inf.util.HibernateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

public class MainApp extends Application {
    static {
        System.setProperty("logback.configurationFile", MainApp.class.getResource("/config/logback.xml").toString());
    }

    private ShoppingItemDAO shoppingItemDAO;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private ObservableList<ShoppingItem> shoppingList = FXCollections.observableArrayList();

    private ObservableList<ShoppingItem> getShoppingList() {
        return this.shoppingList;
    }

    private Stage primaryStage;
    private BorderPane rootView;

    public MainApp() {
        shoppingItemDAO = new ShoppingItemDAO(sessionFactory);
        List<ShoppingItem> items = shoppingItemDAO.findAll();

        for (ShoppingItem item : items) {
            shoppingList.add(item);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createRootView();
        createDefaultView();
    }

    private void createRootView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/RootView.fxml"));
        try {
            BorderPane rootView = loader.load();
            this.rootView = rootView;

            primaryStage.setScene(new Scene(rootView));
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(1);
                }
            });

            primaryStage.show();
            primaryStage.setTitle("Simple shopping planner");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private void createDefaultView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ShoppingPlannerView.fxml"));
        try {
            AnchorPane shoppingPlannerView = (AnchorPane) loader.load();
            rootView.setCenter(shoppingPlannerView);

            ShoppingPlannerController controller = loader.getController();
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
    public static void main(String[] args)
    {
        launch(args);
    }
}
