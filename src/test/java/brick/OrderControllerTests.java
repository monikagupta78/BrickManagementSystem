package brick;

import brick.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static brick.utils.BrickType.CHEMICALLY_SET;
import static brick.utils.BrickType.FIRED;
import static brick.utils.OrderStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void newOrderCreatedSuccessfullyShouldReturnOrderReferenceId() throws Exception {
        addOrder(FIRED.toString(), 10);
    }

    @Test
    public void getTheOrderSuccessfullyUsingOrderReferenceId() throws Exception {
        int quantity = 20;
        long orderReferenceId = addOrder(FIRED.toString(), quantity);

        String urlTemplate = "/order/" + orderReferenceId;
        String status = IN_PROGRESS.toString();

        getOrder(urlTemplate, status, quantity);

    }

    private void getOrder(String urlTemplate, String status, int quantity) throws Exception {
        this.mockMvc.perform(get(urlTemplate)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.orderReferenceId").isNumber())
                //.andExpect(jsonPath("$.brickType").value(FIRED.toString()))
                .andExpect(jsonPath("$.status").value(status))
                .andExpect(jsonPath("$.quantity").value(quantity));
    }

    @Test
    public void updateTheOrderSuccessfullyUsingOrderReferenceId() throws Exception {
        int quantity = 20;
        long orderReferenceId = addOrder(FIRED.toString(), quantity);

        String urlTemplate = "/order/" + orderReferenceId + "/" + 30;

        this.mockMvc.perform(put(urlTemplate)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderReferenceId").isNumber())
                //.andExpect(jsonPath("$.brickType").value(FIRED.toString()))
                .andExpect(jsonPath("$.status").value(IN_PROGRESS.toString()))
                .andExpect(jsonPath("$.quantity").value(30));

        urlTemplate = "/order/" + orderReferenceId;
        String status = CANCELLED.toString();

        getOrder(urlTemplate, status, quantity);

    }

    @Test
    public void dispatchTheOrderSuccessfullyUsingOrderReferenceId() throws Exception {
        int quantity = 20;
        long orderReferenceId = addOrder(CHEMICALLY_SET.toString(), quantity);

        String urlTemplate = "/order/" + orderReferenceId + "/dispatch";

        this.mockMvc.perform(put(urlTemplate)).andDo(print()).andExpect(status().isOk());

        urlTemplate = "/order/" + orderReferenceId;
        String status = DISPATCHED.toString();

        getOrder(urlTemplate, status, quantity);

    }

    @Test
    public void alreadyDispatchedOrderCannotBeUpdated() throws Exception {
        int quantity = 20;
        long orderReferenceId = addOrder(CHEMICALLY_SET.toString(), quantity);

        String urlTemplate = "/order/" + orderReferenceId + "/dispatch";

        this.mockMvc.perform(put(urlTemplate)).andDo(print()).andExpect(status().isOk());

        urlTemplate = "/order/" + orderReferenceId + "/" + 30;

        this.mockMvc.perform(put(urlTemplate)).andDo(print()).andExpect(status().isBadRequest());

    }

    @Test
    public void getTheOrderUsingInvalidOrderReferenceIdReturnsError() throws Exception {
        String urlTemplate = "/order/111111";
        this.mockMvc.perform(get(urlTemplate)).andDo(print()).andExpect(status().isBadRequest());
    }

    private long addOrder(String brickType, long brickQuantity) throws Exception {
        String urlTemplate = "/order/" + brickType + "/" + brickQuantity;
        MvcResult mvcResult = this.mockMvc.perform(post(urlTemplate)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderReferenceId").isNumber())
                //.andExpect(jsonPath("$.brickType").value(brickType))
                .andExpect(jsonPath("$.status").value(IN_PROGRESS.toString()))
                .andExpect(jsonPath("$.quantity").value(brickQuantity))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Order order = new ObjectMapper().readValue(content, Order.class);
        return order.getOrderReferenceId();
    }

    @Test
    public void listAllOrdersThatWereSuccessfullyCreated() throws Exception {
        addOrder(FIRED.toString(), 20);
        String urlTemplate = "/order";
        this.mockMvc.perform(get(urlTemplate))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

}
