package test.java.com.bigode.controller;

import main.java.com.bigode.action.BigodeActions;
import main.java.com.bigode.controller.BigodeApi;
import main.java.com.bigode.model.Mesa;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BigodeApiTest extends AbstractApiTest {

    @Mock
    private BigodeActions bigodeActions;

    @InjectMocks
    private BigodeApi bigodeApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(bigodeApi);
    }


    @Test
    public void testMesas() throws Exception {

        List<Mesa> mesaList = new ArrayList<>();

        //when(customerAction.loadMoney(anyLong(), (Transaction.LoadMoneyInput) any())).thenReturn(transactionResponse);

        mvc.perform(
                get("/api/v1/mesas")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(123)))
        ;
    }

}

