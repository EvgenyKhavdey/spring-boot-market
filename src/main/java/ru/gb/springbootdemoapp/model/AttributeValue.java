package ru.gb.springbootdemoapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="attributes_values")
@Data
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attributes_id")
    private Attribute attributesId;

    @ManyToMany
    @JoinTable(
            name = "product_attributes",
            joinColumns = @JoinColumn(name = "attributes_value_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;
}
