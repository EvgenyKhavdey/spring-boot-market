package ru.gb.springbootdemoapp.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "image_url")
  private String imageUrl;

  @OneToMany
  @JoinColumn(name = "id")
  private List<Category> parentId;

  @OneToMany(mappedBy = "category")
  private List<Product> products;
}
