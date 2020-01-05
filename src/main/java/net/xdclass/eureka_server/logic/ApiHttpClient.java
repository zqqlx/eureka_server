package net.xdclass.eureka_server.logic;

import com.baomidou.mybatisplus.toolkit.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ApiHttpClient {

    private CloseableHttpClient closeableHttpClient;

    private RequestConfig requestConfig;

    private static  final String UTF_8 = "encoding";

    public ApiHttpClient (CloseableHttpClient closeableHttpClient,RequestConfig requestConfig){
        this.closeableHttpClient = closeableHttpClient;
        this.requestConfig = requestConfig;
    }

    /**
     *
     * @param url
     * @param json
     * @param params
     * @return
     */
    public  String doPostJson(String url, String json, Map<String,String> params) {
        String result = "";
        CloseableHttpResponse response = null;
        try{
            StringEntity entity = new StringEntity(json,UTF_8);
            entity.setChunked(true);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            if (params != null && params.size() > 0) {
                for(Map.Entry<String,String> en : params.entrySet()) {
                    httpPost.setHeader(en.getKey(),en.getValue());
                }
            } else {
                httpPost.setHeader("Content-Type","application/json");
            }
            httpPost.setConfig(requestConfig);
            response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity);
            }
            return result;
        }catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }finally {
            IOUtils.closeQuietly(response);
        }
        return  null;
    }
}
