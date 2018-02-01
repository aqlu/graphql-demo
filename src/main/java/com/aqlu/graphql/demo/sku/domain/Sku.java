package com.aqlu.graphql.demo.sku.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * SKU实体
 * Created by aqlu on 2018/1/31.
 */
@Data
@Entity
public class Sku {
    @Id
    private int id;

    private String name;

    @OneToMany(targetEntity = Spec.class, mappedBy = "sku", cascade = CascadeType.ALL)
    private List<Spec> specs = new ArrayList<>();

    private BigDecimal price;

    public void addSpec(String name, String value) {
        specs.add(new Spec(this, name, value));
    }
}
