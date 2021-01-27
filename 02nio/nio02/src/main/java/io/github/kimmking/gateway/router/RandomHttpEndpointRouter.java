package io.github.kimmking.gateway.router;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        System.out.println("urls After = "+ JSONObject.toJSONString(urls));
        return urls.get(random.nextInt(size));

    }
}
