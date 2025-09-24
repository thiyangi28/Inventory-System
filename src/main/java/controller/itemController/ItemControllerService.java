package controller.itemController;

import javafx.collections.ObservableList;
import model.ItemDto;

public interface ItemControllerService {

    void addItemDetails(ItemDto itemDto);
    void updateItemDetails(ItemDto itemDto);

    void deleteitemDetails(ItemDto itemDto);

    ObservableList<ItemDto>getAllItemDetails();

}
