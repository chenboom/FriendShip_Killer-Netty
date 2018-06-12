package FriendShip_Client;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String msg = input.nextLine();
		msgFromServer.Sendmsg(msg);
	}
}
