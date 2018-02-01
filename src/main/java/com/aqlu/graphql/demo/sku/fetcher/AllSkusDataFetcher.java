package com.aqlu.graphql.demo.sku.fetcher;

import com.aqlu.graphql.demo.sku.domain.Sku;
import com.aqlu.graphql.demo.sku.repository.SkuRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * `allSkus`的数据提取器
 * Created by aqlu on 2018/1/31.
 */
@Component
public class AllSkusDataFetcher implements DataFetcher<List<Sku>> {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public List<Sku> get(DataFetchingEnvironment environment) {
        return skuRepository.findAll();
    }

    @PostConstruct
    public void init() {
        // 添加一些初始化数据
        Sku sku_1 = new Sku();
        sku_1.setId(1);
        sku_1.setName("iPhone X 银色 64G");
        sku_1.setPrice(new BigDecimal("8388.00"));
        sku_1.addSpec("容量", "64G");
        sku_1.addSpec("颜色", "银色");

        Sku sku_2 = new Sku();
        sku_2.setId(2);
        sku_2.setName("iPhone X 银色 256G");
        sku_2.setPrice(new BigDecimal("9688.00"));
        sku_2.addSpec("容量", "256G");
        sku_2.addSpec("颜色", "银色");

        Sku sku_3 = new Sku();
        sku_3.setId(3);
        sku_3.setName("iPhone X 深空灰色 64G");
        sku_3.setPrice(new BigDecimal("8388.00"));
        sku_3.addSpec("容量", "64G");
        sku_3.addSpec("颜色", "深空灰色");

        Sku sku_4 = new Sku();
        sku_4.setId(4);
        sku_4.setName("iPhone X 深空灰色 256G");
        sku_4.setPrice(new BigDecimal("9688.00"));
        sku_4.addSpec("容量", "256G");
        sku_4.addSpec("颜色", "深空灰色");


        Sku sku_5 = new Sku();
        sku_5.setId(5);
        sku_5.setName("iPhone 8 深空灰色 256G");
        sku_5.setPrice(new BigDecimal("6888.00"));
        sku_5.addSpec("容量", "256G");
        sku_5.addSpec("颜色", "深空灰色");
        skuRepository.save(Arrays.asList(sku_1, sku_2, sku_3, sku_4, sku_5));
    }
}
