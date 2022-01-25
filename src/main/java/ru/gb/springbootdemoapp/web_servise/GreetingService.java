package ru.gb.springbootdemoapp.web_servise;

import org.springframework.stereotype.Component;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.repository.ProductRepository;

import java.util.Optional;

@Component
public class GreetingService {

    private final ProductRepository productRepository;

    public GreetingService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
