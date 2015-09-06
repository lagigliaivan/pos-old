package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.dto.Item;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ivan on 05/09/15.
 */
public class ControllerTest {

    @Test
    public void return_an_item_if_it_exits(){

        String id = "7798133540489";

        Controller controller = new Controller();
        Item item = controller.getItem(id);

        assertThat(item, is(notNullValue()));
        assertThat(item.getId(), is(id));

    }
}
