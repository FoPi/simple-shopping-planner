package hu.unideb.inf.controller;

import hu.unideb.inf.util.HibernateUtil;
import javafx.fxml.Initializable;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingPlannerController implements Initializable {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LoggerFactory.getLogger(ShoppingPlannerController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
