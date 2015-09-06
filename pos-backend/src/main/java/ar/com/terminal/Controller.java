package ar.com.terminal;

import ar.com.terminal.dto.Item;

/**
 * Created by ivan on 05/09/15.
 */
public class Controller {

    public Item getItem(String id) {

        Item itemDto = new Item();
        itemDto.setDescription("Item for testing purposes");
        itemDto.setId("7798133540489");

        return itemDto;
    }
}
