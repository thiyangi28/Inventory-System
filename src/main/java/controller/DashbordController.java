package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashbordController {

    Stage customerManagment = new Stage();
    Stage itemManagment = new Stage();
    Stage orderManagment = new Stage();
    Stage orderDetailManagment = new Stage();

    @FXML
    private void customerActionBtn(ActionEvent event) {
        try {
            customerManagment.setScene(
                    new Scene(FXMLLoader.load(getClass().getResource("/view/customer.fxml")))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        customerManagment.setResizable(false);
        customerManagment.show();
    }

    @FXML
    private void itemActionBtn(ActionEvent event) {
        try {
            itemManagment.setScene(
                    new Scene(FXMLLoader.load(getClass().getResource("/view/item.fxml")))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        itemManagment.setResizable(false);
        itemManagment.show();
    }

    @FXML
    private void orderManagmnetActionBtn(ActionEvent event) {
        try {
            orderManagment.setScene(
                    new Scene(FXMLLoader.load(getClass().getResource("/view/order.fxml")))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        orderManagment.setResizable(false);
        orderManagment.show();
    }

    @FXML
    private void orderDetailsActionBtn(ActionEvent event) {
        try {
            orderDetailManagment.setScene(
                    new Scene(FXMLLoader.load(getClass().getResource("/view/orderDetail.fxml")))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        orderDetailManagment.setResizable(false);
        orderDetailManagment.show();
    }
}
