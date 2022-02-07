package ru.gb.springbootdemoapp.unit;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.ExampleMatcher;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.repository.ProductRepository;
import ru.gb.springbootdemoapp.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes  = ProductService.class)
public class ProductServesTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findAllSuccess() {
        Mockito.doReturn(Collections.emptyList()).when(productRepository).findAll();
        List<Product> actual = productService.getAll();
        Assertions.assertThat(actual).isEmpty();
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvSource({"1"})
    public void searchProductSuccess(Long id) {
        Product product = new Product();
        product.setId(id);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());
        Mockito.doReturn(Collections.emptyList()).when(productRepository).findAll();
        Optional<Product> actual = productService.findById(id);
        Assertions.assertThat(actual).isEmpty();
    }

}

