package controller.orderDetailController;


import db.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetailDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailController implements OrderDetailControllerService {

    @Override
    public void addOrderDetails(OrderDetailDto orderDetailDto) {
        String SQL = "INSERT INTO orderdetail(OrderID, ItemCode, OrderQTY, Discount) VALUES(?,?,?,?)";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, orderDetailDto.getOrderId());
            preparedStatement.setString(2, orderDetailDto.getItemCode());
            preparedStatement.setObject(3, orderDetailDto.getOrderQTY());
            preparedStatement.setObject(4, orderDetailDto.getDiscount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void deleteOrderDetails(OrderDetailDto orderDetailDto) {
        String SQL = "DELETE FROM orderdetail WHERE OrderID = ?";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, orderDetailDto.getOrderId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void updateOrderDetails(OrderDetailDto orderDetailDto) {
        String SQL = "UPDATE orderdetail SET ItemCode = ?, OrderQTY = ?, Discount = ? WHERE OrderID = ?";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)){

            preparedStatement.setString(1, orderDetailDto.getItemCode());
            preparedStatement.setObject(2, orderDetailDto.getOrderQTY());
            preparedStatement.setObject(3, orderDetailDto.getDiscount());
            preparedStatement.setString(4, orderDetailDto.getOrderId());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ObservableList<OrderDetailDto> getAllCustomerDetails() {

        ObservableList<OrderDetailDto> orderDetailList = FXCollections.observableArrayList();
        String SQL = "SELECT OrderID, ItemCode, OrderQTY, Discount FROM orderdetail";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                orderDetailList.add(new OrderDetailDto(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQTY"),
                        resultSet.getInt("Discount")

                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailList;
    }
}
