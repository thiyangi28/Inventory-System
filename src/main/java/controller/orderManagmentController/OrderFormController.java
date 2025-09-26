package controller.orderManagmentController;

import db.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderManagmentDto;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    ObservableList<OrderManagmentDto> orderDetailDto = FXCollections.observableArrayList();
    OrderController orderControllerService = new OrderController();


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<OrderManagmentDto, String> colCustomerId;

    @FXML
    private TableColumn<OrderManagmentDto, LocalDate> colDate;

    @FXML
    private TableColumn<OrderManagmentDto, String> colOrderId;

    @FXML
    private TableView<OrderManagmentDto> tableOrderDetail;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtOrderId;



    public  void loadOrderDetails(){

        orderDetailDto.clear();
        orderDetailDto = orderControllerService.getAllOrderDetails();
        tableOrderDetail.setItems(orderDetailDto);

    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
                txtOrderId.setText(null);
                datePicker.setValue(null);
                txtCustomerId.setText(null);
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        OrderManagmentDto dto = new OrderManagmentDto();
        dto.setOrderID(txtOrderId.getText());
        orderControllerService.deleteOrderDetails(dto);
        loadOrderDetails();
    }


    public void updateBtnOnAction(ActionEvent actionEvent) {
        OrderManagmentDto dto = new OrderManagmentDto(
                txtOrderId.getText(),
                datePicker.getValue(),
                txtCustomerId.getText()
        );
        orderControllerService.updateOrderDetails(dto);
        loadOrderDetails();
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
        OrderManagmentDto dto = new OrderManagmentDto(
                txtOrderId.getText(),
                datePicker.getValue(),
                txtCustomerId.getText()
        );
        orderControllerService.addOrderDetails(dto);
        loadOrderDetails();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustID"));

        loadOrderDetails();
    }
}
