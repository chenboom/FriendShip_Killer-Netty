package Friendship_Killer;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
	public static ClientChannel_Save cC = new ClientChannel_Save();
	public static int counter = 0;
	public static Timer timer = new Timer();
	public static void Save_Channel(ClientChannel_Save c) {
		cC = c;
	}
	public static void timer() {
		timer.schedule(tk, 5000,5000);
	}
	public static void Cancel() {
		counter--;
		timer.cancel();
	}
	static TimerTask tk = new TimerTask(){
		public void run() {
			counter++;
			for(int i=0;i<4;i++) {
					if(cC.channel[i]!=null) {
					cC.channel[i].writeAndFlush(AgreementCode.weapon.toString()+"$_");
				}
			}
		}
	};
}
