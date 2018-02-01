# graphql-demo

文档：[GraphQL入门介绍](http://zyouwei.com/%E6%8A%80%E6%9C%AF%E7%AC%94%E8%AE%B0/Java/GraphQL-Introduction-1.html)


测试：

1. 查询所有sku的id与name信息
```bash
$ curl -XPOST -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/sku/query' -d '{
    allSkus {
        id
        name
    }
}'
```

2. 查询所有sku的id、name、price信息
```bash
$ curl -XPOST -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/sku/query' -d '{
    allSkus {
        id
        name
        price
    }
}'
```

3. 查询所有sku的id、name、price、specs信息
```bash
$ curl -XPOST -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/sku/query' -d '{
    allSkus {
        id
        name
        price
        specs {
            name
            value
        }
    }
}'
```

4. 查询指定id的sku信息，包含id、name、price、specs、stocks字段
```bash
$ curl -XPOST -H 'Content-Type:application/json;charset=UTF-8' 'http://localhost:8080/sku/query' -d '{
    sku(id: 1) {
        id
        name
        price
        specs {
            name
            value
        }
        stocks {
            area
            stocks
        }
    }
}'
```