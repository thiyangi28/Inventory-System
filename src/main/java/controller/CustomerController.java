package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerController {

    ObservableList<CustomerDto> customerDtos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCustAddress;

    @FXML
    private TableColumn<?, ?> colCustBirthDate;

    @FXML
    private TableColumn<?, ?> colCustCity;

    @FXML
    private TableColumn<?, ?> colCustProvince;

    @FXML
    private TableColumn<?, ?> colCustSalary;

    @FXML
    private TableColumn<?, ?> colCustTitle;

    @FXML
    private TableColumn<?, ?> colCustname;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustpostcod;

    @FXML
    private TableView<CustomerDto> tableCustView;

    @FXML
    private Tab tabCustomerView;

    @FXML
    private Tab tabItemView;


    @FXML
    void tabActionCustomerView(Event event) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_thogakde", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerDto customerDto = new CustomerDto(
                    // sql table columns name in green color
                        resultSet.getString("cust_id"),
                        resultSet.getString("title"),
                        resultSet.getString("name"),
                        resultSet.getDate("dob").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("post_code")
                        //name, dob salary address city province post_code
                );

                customerDtos.add(customerDto);
                System.out.println(customerDto);

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //ui table coloumn name in green color
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colCustname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustBirthDate.setCellValueFactory(new PropertyValueFactory<>("Date_of_birth"));
        colCustSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colCustpostcod.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        //Title Name Date of birth Salary Address City Province Postal Code


        //tableId.setItems(customerDtos);
        tableCustView.setItems(customerDtos);
    }

    @FXML
    void tabActionItemView(Event event) {

    }

}
