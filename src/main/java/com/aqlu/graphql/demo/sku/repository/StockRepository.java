package com.aqlu.graphql.demo.sku.repository;

import com.aqlu.graphql.demo.sku.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * StockRepository
 * Created by aqlu on 2018/1/31.
 */
public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> queryStocksBySkuId(Integer skuId);
}
