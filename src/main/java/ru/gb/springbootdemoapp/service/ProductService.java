package ru.gb.springbootdemoapp.service;


import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.model.Product;
import ru.gb.springbootdemoapp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProxyProductService proxyProductService;

  public ProductService(ProductRepository productRepository, ProxyProductService proxyProductService) {
    this.productRepository = productRepository;
    this.proxyProductService = proxyProductService;
  }

  public List<Product> getAll() {
    return proxyProductService.getAll();
  }

  public void save(Product product) {
    proxyProductService.save(product);
  }

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public void deleteById(Long id) {
    proxyProductService.deleteById(id);
  }
}
