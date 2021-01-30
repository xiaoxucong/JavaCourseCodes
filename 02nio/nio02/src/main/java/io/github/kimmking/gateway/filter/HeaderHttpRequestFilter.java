package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        String ip = HeaderHttpRequestFilter.getIpAddr(ctx);
        System.out.println("ip="+ip+"/uri="+uri);
        fullRequest.headers().set("mao", "soul");
    }
    public static String getIpAddr(ChannelHandlerContext ctx) {
        String ip = "";
        try{
            InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
            ip = insocket.getAddress().getHostAddress();
            System.out.println(ip);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return ip;
    }

}
