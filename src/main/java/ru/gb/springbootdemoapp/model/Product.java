package ru.gb.springbootdemoapp.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="products")
@Data
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Float price;

  @Column(name = "image_url")
  private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToMany
  @JoinTable(
          name = "product_attributes",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "attributes_value_id")
  )
  private Set<AttributeValue> attributeValues;

}
