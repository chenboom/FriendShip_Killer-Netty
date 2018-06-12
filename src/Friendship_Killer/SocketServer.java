package Friendship_Killer;

import java.util.Properties;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class SocketServer {
	public static SocketServer inst;
	public static Properties p;
	public static int port = 8700;
	private static NioEventLoopGroup bossGroup = new NioEventLoopGroup();
	private static NioEventLoopGroup workGroup = new NioEventLoopGroup();
	private SocketServer() {}
	
	
	public void start() {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 128)
		.option(ChannelOption.SO_REUSEADDR, true)
		.childOption(ChannelOption.TCP_NODELAY, true)
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.childHandler(new ChannelInitializer<SocketChannel>(){
			protected void initChannel(SocketChannel ch)throws Exception{
				ChannelPipeline pipeline = ch.pipeline();
				ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
				pipeline.addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
				pipeline.addLast(new StringDecoder());
				pipeline.addLast(new StringEncoder());
				pipeline.addLast(new SocketHandleServer());
			}
		});
		
		ChannelFuture future;
		try {
			future = b.bind(port).sync();
			future.channel().closeFuture().sync();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void shut() {
		workGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}
	public static void main(String[] args) {
		new SocketServer().start();
	}
}
