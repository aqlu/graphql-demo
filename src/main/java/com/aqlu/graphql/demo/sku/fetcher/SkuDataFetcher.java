package com.aqlu.graphql.demo.sku.fetcher;

import com.aqlu.graphql.demo.sku.domain.Sku;
import com.aqlu.graphql.demo.sku.repository.SkuRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * `sku`的数据提取器
 * Created by aqlu on 2018/1/31.
 */
@Component
@Slf4j
public class SkuDataFetcher implements DataFetcher<Sku> {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public Sku get(DataFetchingEnvironment env) {
        Integer id = env.getArgument("id");
        try {
            return skuRepository.findOne(id);
        } catch (Exception e) {
            log.error("load sku failed.errMsg:{}", e.getMessage());
            return null;
        }
    }
}
