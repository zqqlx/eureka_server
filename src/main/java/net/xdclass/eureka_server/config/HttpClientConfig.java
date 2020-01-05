package net.xdclass.eureka_server.config;

import net.xdclass.eureka_server.logic.ApiHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
@ConfigurationProperties(prefix = "")
public class HttpClientConfig {
    private String maxTotal;


    private String defaultMaxPerRoute;


    private String connectTimeOut;


    private String socketTimeOut;

    @Bean(name="sslContext")
    public SSLContext createIngoreVerifySSL() {
        SSLContext ssl = null;
        try{
            ssl = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
            // 实现一个X509TrustManger接口，不用修改里面的方法
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            ssl.init(null,new TrustManager[]{trustManager},new java.security.SecureRandom());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ssl;
    }

    @Bean(name="httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(@Qualifier("sslContext")SSLContext sslContext) {
        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connManager.setMaxTotal(Integer.parseInt(maxTotal));
        connManager.setDefaultMaxPerRoute(Integer.parseInt(defaultMaxPerRoute));
       return connManager;
    }

    @Bean(name="httpClientBuilder")
    public HttpClientBuilder getHttpClientBuildre(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        httpClientBuilder.setConnectionManagerShared(true);
        return httpClientBuilder;
    }

    @Bean(name="closeableHttpClient")
    public CloseableHttpClient getCloseHttpClient(@Qualifier("httpClientBuilder")HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    @Bean(name="requestConfigBuilder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(Integer.parseInt(connectTimeOut))
                .setSocketTimeout(Integer.parseInt(socketTimeOut));
    }

    @Bean(name="requestConfig")
    public RequestConfig getRequestConfig(@Qualifier("requestConfigBuilder") RequestConfig.Builder requestBuilder) {
        return requestBuilder.build();
    }

    @Bean(name="apiHttpClient")
    ApiHttpClient apiHttpClient(@Qualifier("closeableHttpClient") CloseableHttpClient closeableHttpClient, @Qualifier("requestConfig") RequestConfig requestConfig) {
        ApiHttpClient apiHttpClient =  new ApiHttpClient(closeableHttpClient,requestConfig);
        return apiHttpClient;
    }

    public String getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(String maxTotal) {
        this.maxTotal = maxTotal;
    }

    public String getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(String defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public String getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(String connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public String getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(String socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }
}
