package ru.gb.springbootdemoapp.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getCartFroCurrentUser() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/", String.class);
        assertSame(entity.getStatusCode(), HttpStatus.OK);
    }

    @ParameterizedTest(name="{arguments}")
    @CsvSource({"1"})
    void getCartFroCurrentUserId(Long id) {
        ResponseEntity<String> entity = restTemplate.getForEntity("/info/" + id, String.class);
        assertSame(entity.getStatusCode(), HttpStatus.OK);
    }

}
