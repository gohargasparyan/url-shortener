package gg.projects.urlshortener;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author gohar.gasparyan
 */
@Slf4j
@SpringBootTest(classes = {UrlShortenerApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UrlShorteningControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void request_shortenUrl_expect_response_ok() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/url")
                        .contentType("application/json")
                        .content("{" +
                                "\"value\":\"https://github.com/gohargasparyan/url-shortener\"" +
                                "}");

        this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
