package controller.orderManagmentController;

import db.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderManagmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderController implements OrderControllerService {


    @Override
    public void updateOrderDetails(OrderManagmentDto orderManagmentDto) {
        String SQL = "INSERT INTO orders(OrderID, OrderDate, CustID) VALUES(?,?,?)";
        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(2, orderManagmentDto.getOrderID());
            preparedStatement.setDate(1, java.sql.Date.valueOf(orderManagmentDto.getOrderDate()));
            preparedStatement.setString(3, orderManagmentDto.getCustID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addOrderDetails(OrderManagmentDto orderManagmentDto) {
        String SQL = "UPDATE orders SET OrderDate = ?, CustID = ? WHERE OrderID = ?";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(orderManagmentDto.getOrderDate()));
            preparedStatement.setString(2, orderManagmentDto.getCustID());
            preparedStatement.setString(3, orderManagmentDto.getOrderID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderDetails(OrderManagmentDto orderManagmentDto) {
        String SQL = "DELETE FROM orders WHERE OrderID = ?";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, orderManagmentDto.getOrderID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OrderManagmentDto> getAllOrderDetails() {
        ObservableList<OrderManagmentDto> orderList = FXCollections.observableArrayList();

        String SQL = "SELECT OrderID, OrderDate, CustID FROM orders";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                orderList.add(new OrderManagmentDto(
                        resultSet.getString("OrderID"),
                        resultSet.getDate("OrderDate").toLocalDate(),
                        resultSet.getString("CustID")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }
}
