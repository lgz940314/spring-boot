package com.krupp.sources.config;

import com.mongodb.MongoClientURI;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * 定义公共抽象类，创建mongoDb工厂
 * @Author: liuguangzheng   rrefe@163.com
 * @Date: 2019/12/11
 */
public abstract class AbstractMongoConfig {

    /**
     *连接MongoDB地址
     */
    private String uri;

    /**
     * 获取mongoDBTtemplate对象
     */
    public abstract MongoTemplate getMongoTemplate() throws Exception;

    /**
     * 创建mongoDb工厂
     */
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoClientURI mongoclienturi = new MongoClientURI(uri);
        return new SimpleMongoDbFactory(mongoclienturi);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
