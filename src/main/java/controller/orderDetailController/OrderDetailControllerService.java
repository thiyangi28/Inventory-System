package controller.orderDetailController;

import javafx.collections.ObservableList;
import model.OrderDetailDto;

public interface OrderDetailControllerService {


    void addOrderDetails(OrderDetailDto orderDetailDto);

    void updateOrderDetails(OrderDetailDto orderDetailDto);

    ObservableList<OrderDetailDto> getAllCustomerDetails();

    void deleteOrderDetails(OrderDetailDto dto);


}
