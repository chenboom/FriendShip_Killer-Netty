package FriendShip_Client;

import java.util.Properties;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Client {
	public static Properties p;
	public static int port = 8700;
	public static String host = "****";
	//public static String host = "localhost";
	private static NioEventLoopGroup workGroup = new NioEventLoopGroup();
	private Client() {}
	
	
	public void start() throws Exception {
		Bootstrap b = new Bootstrap();
		b.group(workGroup).channel(NioSocketChannel.class)
		.option(ChannelOption.SO_REUSEADDR, true)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>(){
			protected void initChannel(SocketChannel ch)throws Exception{
				ChannelPipeline pipeline = ch.pipeline();
				ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
				pipeline.addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
				pipeline.addLast(new StringDecoder()); 
				pipeline.addLast(new StringEncoder());
				pipeline.addLast(new ClientHandler());
			}
		});
		
		Channel ch = b.connect(host,port).sync().channel();
		ch.closeFuture().sync();
	}
	
	public void shut() {
		workGroup.shutdownGracefully();
	}
	public static void main(String[] args) throws Exception {
		new Client().start();
		/*while(true) {
			Scanner input = new Scanner(System.in);
			String msg = input.nextLine();
			ClientHandler.sendMsg(msg);
		}*/
	}
}
