package ru.gb.springbootdemoapp.controller.rest;


import org.springframework.web.bind.annotation.*;
import ru.gb.springbootdemoapp.dto.Cart;
import ru.gb.springbootdemoapp.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartRestController {

  private CartService cartService;

  public CartRestController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping// GET cart
  public Cart getCart() {
    return cartService.getCartForCurrnetUser();
  }

  @PostMapping("/product/{id}") // POST cart/product/1
  public Cart addProduct(@PathVariable Long id) {
    return cartService.addProductById(id);
  }

  @DeleteMapping("/product/{id}") // DELETE cart/product/1
  public Cart deleteProduct(@PathVariable Long id) {
    return cartService.removeProductById(id);
  }

  @GetMapping("/order")
  public void createOrder(){
    int i = 0;
    cartService.createOrder();
  }
}
