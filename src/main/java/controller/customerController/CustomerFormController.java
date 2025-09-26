package controller.customerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDto;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    ObservableList<CustomerDto> customerDto = FXCollections.observableArrayList();

    CustomerControllerService customerControllerService = new CustomerController();


        @FXML
        private Button btnAdd;

        @FXML
        private Button btnClear;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnUpdate;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustAddress;

        @FXML
        private TableColumn<CustomerDto, LocalDate> colCustBirthDate;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustCity;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustProvince;
        //
        @FXML
        private TableColumn<CustomerDto, Double> colCustSalary;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustTitle;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustId;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustname;
        //
        @FXML
        private TableColumn<CustomerDto, String> colCustpostcod;

        @FXML
        private TextField txtAddress;

        @FXML
        private TextField txtCity;

        @FXML
        private TextField txtCustId;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtSalary;

        @FXML
        private ComboBox<String> comboPostalCode;

        @FXML
        private ComboBox<String> comboProvince;

        @FXML
        private ComboBox<String> comboTitle;

        @FXML
        private DatePicker datePickOfBirth;

        @FXML
        private Tab tabCustomerView;

        @FXML
        private Tab tabItemView;
        //
        @FXML
        private TableView<CustomerDto> tableCustView;



        private void loadCustomerDetails() {

            customerDto.clear();
            customerDto = customerControllerService.getAllCustomerDetails();
            tableCustView.setItems(customerDto);
        }

        @FXML
         void addOnBtn(ActionEvent event) {
            CustomerDto dto = new CustomerDto(
                    txtCustId.getText(),
                    comboTitle.getValue(),
                    txtName.getText(),
                    datePickOfBirth.getValue(),
                    Double.parseDouble(txtSalary.getText()),
                    txtAddress.getText(),
                    txtCity.getText(),
                    comboProvince.getValue(),
                    comboPostalCode.getValue()
            );

            customerControllerService.addCustomerDetails(dto);
            loadCustomerDetails();

        }

        @FXML
         void updateOnBtn(ActionEvent event) {
            CustomerDto dto = new CustomerDto(
                    txtCustId.getText(),
                    comboTitle.getValue(),
                    txtName.getText(),
                    datePickOfBirth.getValue(),
                    Double.parseDouble(txtSalary.getText()),
                    txtAddress.getText(),
                    txtCity.getText(),
                    comboProvince.getValue(),
                    comboPostalCode.getValue()
            );
            customerControllerService.updateCustomerDetails(dto);
            loadCustomerDetails();
        }

        @FXML
         void cleaerOnBtn(ActionEvent event) {
                    txtCustId.setText(null);
                    comboTitle.getSelectionModel().clearSelection();
                    txtName.setText(null);
                    datePickOfBirth.setValue(null);
                    txtSalary.setText(null);
                    txtAddress.setText(null);
                    txtCity.setText(null);
                    comboProvince.getSelectionModel().clearSelection();
                    comboPostalCode.getSelectionModel().clearSelection();
        }

        @FXML
        void deleteOnBtn(ActionEvent event) {
            CustomerDto dto = new CustomerDto();
            dto.setCustID(txtCustId.getText());
            customerControllerService.deleteCustomerDetails(dto);
            loadCustomerDetails();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<String> titleTypes = FXCollections.observableArrayList(
                    "Mr", "Mrs", "Ms");

            ObservableList<String> postalCodes = FXCollections.observableArrayList(
                    "10021", "10236", "10569", "10874", "10050", "10653", "12365", "10421");

            ObservableList<String> provincetypes = FXCollections.observableArrayList(
                    "Western", "Uva", "Southern", "North", "East");

            comboTitle.setItems(titleTypes);
            comboPostalCode.setItems(postalCodes);
            comboProvince.setItems(provincetypes);


            // Set table column bindings
            colCustTitle.setCellValueFactory(new PropertyValueFactory<>("CustTitle"));
            colCustId.setCellValueFactory(new PropertyValueFactory<>("CustID"));
            colCustname.setCellValueFactory(new PropertyValueFactory<>("CustName"));
            colCustBirthDate.setCellValueFactory(new PropertyValueFactory<>("DOB"));
            colCustSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
            colCustAddress.setCellValueFactory(new PropertyValueFactory<>("CustAddress"));
            colCustCity.setCellValueFactory(new PropertyValueFactory<>("City"));
            colCustProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
            colCustpostcod.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

            loadCustomerDetails();
        }

}



