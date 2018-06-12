package Friendship_Killer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class SocketHandleServer extends ChannelInboundHandlerAdapter{
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("客户端："+ctx.channel().toString()+"已连接；");
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
		//ByteBuf ms = (ByteBuf)msg;
		String thismsg = (String)msg;
		System.out.println(msg);
		String type = thismsg.substring(0, 4);
		System.out.println(type);
		switch(type) {
		case AgreementCode.register:
			DoDate.register(ctx, thismsg);break;
		case AgreementCode.login:
			DoDate.login(ctx, thismsg);break;
		case AgreementCode.matching:
			DoDate.matching(ctx, thismsg);break;
		case AgreementCode.end:
			DoDate.end(thismsg);break;
		case AgreementCode.gamedata:
			DoDate.gData(thismsg);break;
		case AgreementCode.quitFromRoom:
			DoDate.quitFromroom(ctx, thismsg);break;
		case AgreementCode.quitFromGame:
			ctx.close();break;
		case AgreementCode.winnerone:
			DoDate.winnerOne(ctx, thismsg);break;
		default:
			System.out.println(msg);
			ctx.writeAndFlush("9999$_");break;
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
		
	}
}
