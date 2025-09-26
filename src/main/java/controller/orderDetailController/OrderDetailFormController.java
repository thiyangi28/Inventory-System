
package controller.orderDetailController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDto;
import model.OrderDetailDto;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailFormController implements Initializable {
    ObservableList<OrderDetailDto>  orderDetailDtos = FXCollections.observableArrayList();
    OrderDetailControllerService orderDetailControllerService = new OrderDetailController();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<OrderDetailDto, Integer> colDiscount;

    @FXML
    private TableColumn<OrderDetailDto, String> colItemCode;

    @FXML
    private TableColumn<OrderDetailDto, String> colOrderId;

    @FXML
    private TableColumn<OrderDetailDto, Integer> colOrderQty;

    @FXML
    private TableView<OrderDetailDto> tableOrderDetails;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtorderQty;


    public void loadOrderDetails() {
        orderDetailDtos.clear();
        orderDetailDtos = orderDetailControllerService.getAllCustomerDetails();
        tableOrderDetails.setItems(orderDetailDtos);
    }


    @FXML
    void addBtnOnAction(ActionEvent event) {
        OrderDetailDto orderDetailDto = new OrderDetailDto(
                txtOrderId.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtorderQty.getText()),
                Integer.parseInt(txtDiscount.getText())
        );

        orderDetailControllerService.addOrderDetails(orderDetailDto);
        loadOrderDetails();
    }

    @FXML
    void clearBtnOnAction(ActionEvent event) {
            txtOrderId.setText(null);
            txtItemCode.setText(null);
            txtorderQty.clear();
            txtDiscount.clear();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setOrderId(txtOrderId.getText());
        orderDetailDto.setItemCode(txtItemCode.getText());

        orderDetailControllerService.deleteOrderDetails(orderDetailDto);
        loadOrderDetails();
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        OrderDetailDto orderDetailDto = new OrderDetailDto(
                txtOrderId.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtorderQty.getText()),
                Integer.parseInt(txtDiscount.getText())
        );

        orderDetailControllerService.updateOrderDetails(orderDetailDto);
        loadOrderDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("OrderQTY"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        loadOrderDetails();
    }
}
