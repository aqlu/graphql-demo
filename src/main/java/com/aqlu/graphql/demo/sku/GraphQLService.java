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
 * GrahpQL服务
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

    /**
     * GrahpQL dsl 执行入口
     * @param dsl GraphQL dsl查询语言
     */
    public ExecutionResult query(String dsl) {
        return graphQL.execute(dsl);
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        // 类型定义注册，加载“sku.graphqls”文件定义的schema
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
                ) // 分别指定Query类型中allSkus字段与sku字段对应的Fetcher
                .type("Sku",
                        runtimeWiring -> runtimeWiring.dataFetcher("stocks", stocksFetcher)
                ) // 指定schemaSku类型的stocks字段对应的Fetcher
                .build();
    }
}
