package controller.itemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDto;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    ObservableList<ItemDto> itemDtos = FXCollections.observableArrayList();
    ItemControllerService itemControllerService = new ItemController();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ItemDto, String> colDescription;

    @FXML
    private TableColumn<ItemDto, String> colItemCode;

    @FXML
    private TableColumn<ItemDto, String> colPackSize;

    @FXML
    private TableColumn<ItemDto, Integer> colStockQuantity;

    @FXML
    private TableColumn<ItemDto, Double> colUnitPrice;

    @FXML
    private ComboBox<String> comboPackSize;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtStockQuantity;

    @FXML
    private TableView<ItemDto> tableItemView;

    private void loadItemDetails(){
        itemDtos.clear();
        itemDtos = itemControllerService.getAllItemDetails();
        tableItemView.setItems(itemDtos);
    }

    @FXML
    void addOnBtnAction(ActionEvent event) {
        ItemDto dto = new ItemDto(
                txtItemCode.getText(),
                txtDescription.getText(),
                comboPackSize.getValue(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtStockQuantity.getText())
        );

            itemControllerService.addItemDetails(dto);
            loadItemDetails();

    }

    @FXML
    void clearOnBtnAction(ActionEvent event) {
        txtItemCode.clear();
        txtDescription.clear();
        txtPrice.clear();
        txtStockQuantity.clear();
        comboPackSize.getSelectionModel().clearSelection();

    }

    @FXML
    void deleteOnBtnAction(ActionEvent event) {
            ItemDto dto = new ItemDto();
            dto.setItemCode(txtItemCode.getText());
            itemControllerService.deleteitemDetails(dto);
            loadItemDetails();
    }

    @FXML
    void updateOnBtnAction(ActionEvent event) {
        ItemDto dto = new ItemDto(
                txtItemCode.getText(),
                txtDescription.getText(),
                comboPackSize.getValue(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtStockQuantity.getText())
        );

        itemControllerService.updateItemDetails(dto);
        loadItemDetails();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colStockQuantity.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));

        loadItemDetails();

        ObservableList<String> packSizes = FXCollections.observableArrayList(
                "100g","200g","300g","400g","500g","600g","700g","800g","900g","1Kg","2Kg","3Kg","4kg","5Kg","6Kg","7Kg"
        );
        comboPackSize.setItems(packSizes);
    }

}
