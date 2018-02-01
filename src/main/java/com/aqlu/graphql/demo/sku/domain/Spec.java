package com.aqlu.graphql.demo.sku.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Spec
 * Created by aqlu on 2018/1/31.
 */
@Data
@Entity
@NoArgsConstructor
public class Spec {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "sku_id", nullable = false)
    private Sku sku;

    public Spec(Sku sku, String name, String value) {
        this.sku = sku;
        this.name = name;
        this.value = value;
    }
}
