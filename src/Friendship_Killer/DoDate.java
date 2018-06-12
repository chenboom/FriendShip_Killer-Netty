package Friendship_Killer;

import java.util.Random;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class DoDate extends Thread{
	static ClientChannel_Save[] chan = new ClientChannel_Save[101];
	static String devide = "$_";
	static java.sql.Connection con = SQL.getConnection();
	public static void register(ChannelHandlerContext ctx,String msg) {
		String m = msg;
		String username;
		String mail;
		String password;
		int flag = m.indexOf('^');
		username = m.substring(4,flag);
		int flag1 = m.indexOf('^', flag+1);
		mail= m.substring(flag+1,flag1);
		password = m.substring(flag1+1,m.length());
		int re = SQL.UsersRegister(con, username, mail, password);
		if(re == 1) {
			ctx.writeAndFlush(AgreementCode.registerStatForSuccess+devide);
		}
		else {
			ctx.writeAndFlush(AgreementCode.registerStatForFail+devide);
		}
	}
	public static void login(ChannelHandlerContext ctx, String msg) {
		String m = msg;
		String username;
		String password;
		int flag = m.indexOf('^');
		username = m.substring(4, flag);
		password = m.substring(flag+1,m.length());
		int re = SQL.Login(con, username, password);
		if(re ==1) {
			ctx.writeAndFlush(AgreementCode.loginStatForSuccess+devide);
		}
		else {
			ctx.writeAndFlush(AgreementCode.loginStatForFail+devide);
		}
	}
	public static void matching(ChannelHandlerContext ctx,String msg) {
		String m = msg;
		String name = m.substring(4);
		int userid = SQL.SelectUserId(con, name);
		int roomid = SQL.matching(con, userid);
		System.out.println(roomid+"^"+userid);
		if(roomid == -1||userid==-1) {
			ctx.writeAndFlush(AgreementCode.matchingStatForFail+devide);
		}else {
			if(chan[roomid] == null) {
				chan[roomid] = new ClientChannel_Save();
			}
			int[] user = {-1,-1,-1,-1};
			int stat = -1;
			if(!SQL.SelectUserInRoom_1(con, roomid)) {
				user[0] = SQL.SelectUserIdInRoom_1(con, roomid);
			}
			if(!SQL.SelectUserInRoom_2(con, roomid)) {
				user[1] = SQL.SelectUserIdInRoom_2(con, roomid);
			}
			if(!SQL.SelectUserInRoom_3(con, roomid)) {
				user[2] = SQL.SelectUserIdInRoom_3(con, roomid);
			}
			if(!SQL.SelectUserInRoom_4(con, roomid)) {
				user[3] = SQL.SelectUserIdInRoom_4(con, roomid);
			}
			for(int i=0; i<4; i++) {
				System.out.println(user[i]);
				if(user[i] == userid) {
					stat = i+1;
					chan[roomid].channel[i] = ctx.channel();
					chan[roomid].userid[i] = userid;
					user[i] = 0;
				}
			}
			System.out.println(roomid+" "+userid+" "+stat);
			if(roomid == -1 || userid == -1 || stat == -1) {
				ctx.writeAndFlush(AgreementCode.matchingStatForFail+devide);
			}
			else {
				ctx.writeAndFlush(AgreementCode.matchingStatForSuccess+"^"+userid+"^"+roomid+
									"^"+stat+"^"+user[0]+"^"+user[1]+"^"+user[2]+"^"+user[3]+devide);
			}
		}
	}
	public static void end(String msg) {
		String m = msg;
		String room_id = m.substring(4);
		Random ran = new Random();
		int map = ran.nextInt(10);
		int roomid = Integer.parseInt(room_id);
		for(int i=0;i<4;i++) {
			if(chan[roomid].channel[i]!=null) {
				chan[roomid].channel[i].writeAndFlush(AgreementCode.newMap.toString()+map+devide);
				Time.Save_Channel(chan[roomid]);
				Time.timer();
			}
		}
	}
	public static void gData(String msg) {
		String m = msg;
		int flag1 = msg.indexOf('^');
		int flag2 = msg.indexOf('^',flag1+1);
		
		System.out.println(flag1+" "+flag2+" ");
		String room_id = msg.substring(flag1+1,flag2);
		int roomid = Integer.parseInt(room_id);
		for(int i=0;i<4;i++) {
			if(chan[roomid].channel[i]!=null) {
				System.out.print(chan[roomid].channel[i]);
				chan[roomid].channel[i].writeAndFlush(m+devide);
			}
		}
	}
	public static void quitFromroom(ChannelHandlerContext ctx,String msg) {
		String m = msg;
		int flag = m.indexOf('^');
		String user_id = m.substring(4,flag);
		String room_id = m.substring(flag+1);
		int userid = Integer.parseInt(user_id);
		int roomid = Integer.parseInt(room_id);
		for(int i=0;i<4;i++) {
			if(chan[roomid].userid[i]==userid) {
				chan[roomid].userid[i] = -1;
				chan[roomid].channel[i] = null;
			}
			if(chan[roomid].userid[i]!=userid&&chan[roomid].channel[i]!=null) {
				chan[roomid].channel[i].writeAndFlush(AgreementCode.otherQuitFromRoom.toString()+userid+devide);
			}
		}
	}
	public static void winnerOne(ChannelHandlerContext ctx, String msg){
		String m = msg;
		int flag = msg.indexOf('^');
		String room_id = m.substring(4,flag);
		String user_id = m.substring(flag+1);
		int userid = Integer.parseInt(user_id);
		int roomid = Integer.parseInt(room_id);
		for(int i=0;i<3;i++) {
			if(chan[roomid].userid[i]!=userid&&chan[roomid].channel[i]!=null) {
				chan[roomid].channel[i].writeAndFlush(AgreementCode.winnerOne.toString()+userid+devide);
			}
		}
	}
}
