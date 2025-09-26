package controller.itemController;

import db.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemControllerService {

    @Override
    public void addItemDetails(ItemDto itemDto) {
        String SQL = "INSERT INTO item(ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES(?,?,?,?,?)";

        try (Connection connection = DBconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, itemDto.getItemCode());
            preparedStatement.setString(2, itemDto.getDescription());
            preparedStatement.setString(3, itemDto.getPackSize());
            preparedStatement.setObject(4, itemDto.getUnitPrice());
            preparedStatement.setDouble(5, itemDto.getQtyOnHand());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItemDetails(ItemDto itemDto) {
         String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, itemDto.getDescription()); // Description
            preparedStatement.setString(2, itemDto.getPackSize());     // PackSize
            preparedStatement.setDouble(3, itemDto.getUnitPrice());    // UnitPrice
            preparedStatement.setInt(4, itemDto.getQtyOnHand());       // QtyOnHand
            preparedStatement.setString(5, itemDto.getItemCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteitemDetails(ItemDto itemDto){
        String SQL = "DELETE FROM item WHERE ItemCode = ?";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, itemDto.getItemCode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ItemDto> getAllItemDetails() {
        ObservableList<ItemDto> itemList = FXCollections.observableArrayList();
        String SQL = "SELECT ItemCode, Description, PackSize, UnitPrice, QtyOnHand FROM item";

        try (Connection connection = DBconnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                itemList.add(new ItemDto(
                        resultSet.getString("itemCode"),
                        resultSet.getString("description"),
                        resultSet.getString("packSize"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getInt("qtyOnHand")

                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}
