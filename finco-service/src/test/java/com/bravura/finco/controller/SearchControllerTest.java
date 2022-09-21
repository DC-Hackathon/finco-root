package com.bravura.finco.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.bravura.finco.model.FincoResponse;
import com.bravura.finco.service.NLPService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SearchController.class})
@ExtendWith(SpringExtension.class)
class SearchControllerTest {
    @MockBean
    private NLPService nLPService;

    @Autowired
    private SearchController searchController;

    /**
     * Method under test: {@link SearchController#mapToString(String)}
     */
    @Test
    void testMapToString() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/flatMapToJson")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"root\":\"\"}"));
    }

    /**
     * Method under test: {@link SearchController#mapToString(String)}
     */
    @Test
    void testMapToString2() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/api/flatMapToJson", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"root\":\"\"}"));
    }

    /**
     * Method under test: {@link SearchController#postFromFlask(String)}
     */
    @Test
    void testPostFromFlask() throws Exception {
        when(nLPService.getNlp((String) any())).thenReturn(new FincoResponse());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/search")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"nlpResponse\":null,\"data\":null,\"queryResponse\":null}"));
    }
}

