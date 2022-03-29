package ru.gb.springbootdemoapp.dto;


import lombok.Data;

import java.util.List;

@Data
public class Cart {
  private List<CartItem> items;
  private double price;

    private Cart(Builder builder) {
        setItems(builder.items);
        setPrice(builder.price);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public void addItem(CartItem cartItem) {
    items.stream().filter(items -> items.getProductId().equals(cartItem.getProductId())).findFirst()
        .ifPresentOrElse(CartItem::incrementCount, () -> items.add(cartItem));
    recalculate();
  }

  public void removeItem(Long id) {
    items.stream().filter(items -> items.getProductId().equals(id)).findFirst()
        .ifPresent(
            item -> {
              item.decrementCount();
              if (item.getCount() == 0) {
                items.remove(item);
              }
            }
        );
    recalculate();
  }

  private void recalculate() {
    price = items.stream().mapToDouble(CartItem::getPrice).sum();
  }

    public static final class Builder {
        private List<CartItem> items;
        private double price;

        private Builder() {
        }

        public Builder setItems(List<CartItem> val) {
            items = val;
            return this;
        }

        public Builder setPrice(double val) {
            price = val;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
