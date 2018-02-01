package com.aqlu.graphql.demo.sku.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Stock
 * Created by aqlu on 2018/1/31.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer skuId;
    private String area;
    private Integer stocks;

    public Stock(Integer skuId, String area, Integer stocks) {
        this.skuId = skuId;
        this.area = area;
        this.stocks = stocks;
    }
}
