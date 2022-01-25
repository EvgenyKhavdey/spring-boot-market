package ru.gb.springbootdemoapp.web_servise;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.springbootdemoapp.api.products.GetGreetingRequest;
import ru.gb.springbootdemoapp.api.products.GetGreetingResponse;
import ru.gb.springbootdemoapp.api.products.Products;
import ru.gb.springbootdemoapp.converter.ProductMapper;
import ru.gb.springbootdemoapp.dto.ProductDto;

@Endpoint
public class GreetingEndpoint {

    private final GreetingService greetingService;
    private final ProductMapper productMapper;

    public GreetingEndpoint(GreetingService greetingService, ProductMapper productMapper) {
        this.greetingService = greetingService;
        this.productMapper = productMapper;
    }

    @PayloadRoot(namespace = "http://ru/gb/springbootdemoapp/api/products", localPart = "getGreetingRequest")
    @ResponsePayload
    public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest request){
        GetGreetingResponse response = new GetGreetingResponse();
        ProductDto productDto = productMapper.productToProductDto(greetingService.findById(request.getId()).orElse(null));
        Products products = new Products();
        products.setTitle(productDto.getTitle());
        products.setPrice(productDto.getPrice());
        products.setCategory(productDto.getCategory());
        response.setProducts(products);
        return response;
    }
}
