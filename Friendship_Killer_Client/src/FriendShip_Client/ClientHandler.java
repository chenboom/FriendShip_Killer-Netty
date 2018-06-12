package FriendShip_Client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter{
	//String cmsg = "oiniyhuiloiuho";
	//String cmsg = "0002cb";
	String cmsg = "0004cccc^asd@qwe^123";
	//String cmsg = "0001cccc^123456";
	//String cmsg = "0001cccc^123";
	public static ChannelHandlerContext ctx;
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ClientHandler.ctx = ctx;
		msgFromServer.ctx = ctx;
		ctx.writeAndFlush((cmsg+"$_")/*.getBytes()*/);
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		//ByteBuf sm = (ByteBuf)msg;
		String smsg = (String)msg;
		System.out.println(smsg);
		//msgFromServer.putsMsg(smsg);
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
	public static void sendMsg(String cmsg) {
		ctx.writeAndFlush(cmsg+"$_");
	}
}
