package com.aqlu.graphql.demo.sku.fetcher;

import com.aqlu.graphql.demo.sku.domain.Sku;
import com.aqlu.graphql.demo.sku.domain.Stock;
import com.aqlu.graphql.demo.sku.repository.StockRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * StocksDataFetcher
 * Created by aqlu on 2018/1/31.
 */
@Component
public class StocksDataFetcher implements DataFetcher<List<Stock>> {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> get(DataFetchingEnvironment env) {
        Sku sku = env.getSource();
        return stockRepository.queryStocksBySkuId(sku.getId());
    }

    @PostConstruct
    private void init() {

        // 添加初始化数据
        Stock stock_1 = new Stock(1, "华东", 10);
        Stock stock_2 = new Stock(1, "华南", 20);
        Stock stock_3 = new Stock(1, "华北", 30);
        Stock stock_4 = new Stock(2, "华东", 40);
        Stock stock_5 = new Stock(2, "华南", 50);
        Stock stock_6 = new Stock(3, "华东", 60);
        Stock stock_7 = new Stock(3, "华北", 70);
        Stock stock_8 = new Stock(4, "华东", 80);

        stockRepository.save(Arrays.asList(stock_1, stock_2, stock_3, stock_4, stock_5, stock_6, stock_7, stock_8));
    }
}
