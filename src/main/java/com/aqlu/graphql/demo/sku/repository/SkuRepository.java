package com.aqlu.graphql.demo.sku.repository;

import com.aqlu.graphql.demo.sku.domain.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SkuRepository
 * Created by aqlu on 2018/1/31.
 */
public interface SkuRepository extends JpaRepository<Sku, Integer> {
}
