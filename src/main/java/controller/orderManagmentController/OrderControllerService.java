package controller.orderManagmentController;

import javafx.collections.ObservableList;
import model.OrderManagmentDto;

public interface OrderControllerService {


      void updateOrderDetails(OrderManagmentDto dto);

     void addOrderDetails(OrderManagmentDto dto);

    void deleteOrderDetails(OrderManagmentDto dto);

    ObservableList<OrderManagmentDto> getAllOrderDetails();
}
