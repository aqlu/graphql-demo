package com.aqlu.graphql.demo.sku;

import com.aqlu.graphql.demo.sku.fetcher.AllSkusDataFetcher;
import com.aqlu.graphql.demo.sku.fetcher.SkuDataFetcher;
import com.aqlu.graphql.demo.sku.fetcher.StocksDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * SkuService
 * Created by aqlu on 2018/1/31.
 */
@Service
public class GraphQLService {

    @Value("classpath:sku.graphqls")
    private Resource schemaResource;

    @Autowired
    private SkuDataFetcher skuFetcher;

    @Autowired
    private StocksDataFetcher stocksFetcher;

    @Autowired
    private AllSkusDataFetcher allSkusFetcher;

    private GraphQL graphQL;

    public ExecutionResult query(String dsl) {
        return graphQL.execute(dsl);
    }

    @PostConstruct
    private void loadSchema() throws IOException {

        // 类型定义注册
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaResource.getFile());

        // 运行时接线
        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        // 生成Schema
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, runtimeWiring);

        // 生成graphQL对象
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",
                        runtimeWiring -> runtimeWiring.dataFetcher("allSkus", allSkusFetcher)
                                .dataFetcher("sku", skuFetcher)
                )
                .type("Sku",
                        runtimeWiring -> runtimeWiring.dataFetcher("stocks", stocksFetcher)
                )
                .build();
    }
}
