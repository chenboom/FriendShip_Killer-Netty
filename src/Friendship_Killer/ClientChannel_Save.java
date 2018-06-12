package Friendship_Killer;

import io.netty.channel.Channel;

public class ClientChannel_Save {
	int[] userid = {-1,-1,-1,-1};
	Channel[] channel = {null,null,null,null};
	
	boolean flag = false;
	
	public ClientChannel_Save() {}
	public Channel[] getChannel() {
		return channel;
	}
}
