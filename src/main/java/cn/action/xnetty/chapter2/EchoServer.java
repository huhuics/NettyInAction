/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.action.xnetty.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 * @author HuHui
 * @version $Id: EchoServer.java, v 0.1 2017年6月5日 下午8:56:07 HuHui Exp $
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port = 8088;
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class) //指定所使用的NIO传输Channel
                .localAddress(new InetSocketAddress(port)) //设置指定端口
                .childHandler(new ChannelInitializer<SocketChannel>() { //添加一个EchoServerHandler到子Channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler); //由于EchoServerHandler被@Shareable标注,对于所有客户端来说都会使用同一个EchoServerHandler
                        }
                    });

            //异步绑定服务器,调用sync方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();

            //阻塞当前线程直到完成close
            f.channel().closeFuture().sync();

        } finally {
            //关闭EventLoopGroup并释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
