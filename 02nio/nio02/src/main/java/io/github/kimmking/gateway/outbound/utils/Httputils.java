package io.github.kimmking.gateway.outbound.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Httputils {
    public static String doPostJson(String url, JSONObject json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            if(json == null ) {
                json = new JSONObject();
            }
             HttpEntity postEntity = new StringEntity(json.toString(), "UTF-8");
            httpPost.setEntity(postEntity);
            httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            httpPost.setHeader("mao", "mao");
            // 执行http请求
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                resultString = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        System.out.println("返回的数据"+resultString);
        return resultString;
    }

    public static HttpResponse doGetJson(String url, JSONObject json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpGet httpPost = new HttpGet(url);
            // 创建请求内容
            if(json == null ) {
                json = new JSONObject();
            }

            httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            httpPost.setHeader("mao", "mao");
            // 执行http请求
            response = httpClient.execute(httpPost);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static void main(String[] args) {
        JSONObject json1=new JSONObject();
        String url="http://localhost:8801";
        System.out.println(doPostJson(url,json1));
    }

}
