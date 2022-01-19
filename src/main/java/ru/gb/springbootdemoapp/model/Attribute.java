package ru.gb.springbootdemoapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="attributes")
@Data
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne(mappedBy = "attributesId",
            cascade = CascadeType.ALL)
    private AttributeValue attributeValue;
}
