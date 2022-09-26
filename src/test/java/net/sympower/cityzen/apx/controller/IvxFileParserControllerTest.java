package net.sympower.cityzen.apx.controller;

import net.sympower.cityzen.apxrefactored.controller.IvxFileParserController;
import net.sympower.cityzen.apxrefactored.model.HourlyResponse;
import net.sympower.cityzen.apxrefactored.service.IvxApxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class IvxFileParserControllerTest {

    @Autowired
    private IvxFileParserController controller;

    @MockBean
    private IvxApxService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void testHourlyQuotes() throws Exception {

        List<HourlyResponse> list = new ArrayList<>();
        HourlyResponse response = HourlyResponse.builder().hour("").date("").netVolume("").price("").build();
        list.add(response);

        //given
        given(service.getHourlyQuotes())
                .willReturn(list);

        // when
        MockHttpServletResponse webResponse = mvc.perform(
                        get("/ivx/hourlyQuotes"))
                .andReturn().getResponse();
        //then
        assertThat(webResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertTrue(webResponse.getContentAsString().length() > 0);

    }

}
