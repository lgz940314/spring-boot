package com.krupp.rest.config.elasticSearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    private static final Logger log = LoggerFactory.getLogger(ESConfig.class);

    @Value("${elasticsearch.user}")
    private String userName;
    @Value("${elasticsearch.password}")
    private String password;
    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private Integer port;

    @Bean(name = "myClient")
    public RestClient getRestClient() {
        //基本的连接
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(host, port));
        //配置身份验证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        clientBuilder.setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        //设置连接超时和套接字超时
        clientBuilder.setRequestConfigCallback(builder -> builder.setConnectTimeout(10000).setSocketTimeout(60000));
        //设置节点选择器
        clientBuilder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
        //配置HTTP异步请求ES的线程数
        clientBuilder.setHttpClientConfigCallback(
                httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultIOReactorConfig(
                        IOReactorConfig.custom().setIoThreadCount(1).build()));
        //设置监听器，每次节点失败都可以监听到，可以作额外处理
        clientBuilder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
                log.error(node.getHost() + "--->该节点失败了");
            }
        });

        return clientBuilder.build();
    }


}
