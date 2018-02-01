package com.aqlu.graphql.demo.sku.repository;

import com.aqlu.graphql.demo.sku.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 库存信息仓库, JPA实现
 * Created by aqlu on 2018/1/31.
 */
public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * 根据sku id获取对应的库存信息
     */
    List<Stock> queryStocksBySkuId(Integer skuId);
}
