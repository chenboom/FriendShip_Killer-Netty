package FriendShip_Client;

import io.netty.channel.ChannelHandlerContext;

public class msgFromServer {
	public static ChannelHandlerContext ctx;
	private static String[] msg = new String[5];
	private static int count = 0;
	public static void putsMsg(String msg) {
		if(count<5) {
			msgFromServer.msg[count] = new String();
			msgFromServer.msg[count] = msg;
			count++;
		}
	}
	public static int getNumOfMsg() {
		return count;
	}
	public static char[] getMsg() {
		if(count>0) {
			count--;
			char[] charmsg= msg[count].toCharArray();
			return charmsg;
		}
		else {
			char[]charmsg = new char[1];
			charmsg[0] = '_';
			return charmsg;
		}
	}
	public static void Sendmsg(String msg) {
		ctx.writeAndFlush(msg+"$_");
	}
	public static void close() {
		ctx.writeAndFlush("0006"+"$_");
		ctx.close();
	}
}
