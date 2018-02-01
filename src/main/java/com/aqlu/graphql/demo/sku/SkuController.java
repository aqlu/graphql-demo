package com.aqlu.graphql.demo.sku;

import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SkuController
 * Created by aqlu on 2018/1/31.
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private GraphQLService graphQLService;

    @PostMapping("/query")
    public ResponseEntity query(@RequestBody String dsl) {
        ExecutionResult result = graphQLService.query(dsl);

        if (result.getErrors().isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}
