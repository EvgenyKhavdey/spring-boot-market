package ru.gb.springbootdemoapp.service;

import org.springframework.stereotype.Component;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;

@Component
public class ProxyProductService {
    private final ProductRepository productRepository;

    private final HashMap<String,List<Product>> rates = new HashMap<>();

    public ProxyProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        if(rates.containsKey("1")){
            return rates.get("1");
        } else {
            List<Product> products = productRepository.findAll();
            return rates.put("1",products);
        }
    }

    public void save(Product product) {
        rates.clear();
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        rates.clear();
        productRepository.deleteById(id);
    }
}
