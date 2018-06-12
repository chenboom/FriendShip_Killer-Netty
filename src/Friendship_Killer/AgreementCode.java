package Friendship_Killer;

public class AgreementCode {
	public static final String login = "0001";					//登陆
	public static final String matching = "0002";				//匹配
	public static final String end = "0003";					//结束当前局游戏
	public static final String register = "0004";				//注册
	public static final String quitFromRoom = "0005";			//玩家退出房间
	public static final String quitFromGame = "0006";			//玩家退出游戏
	public static final String winnerone = "0007";				//玩家胜利
	public static final String gamedata = "0000";				//游戏数据
	public static final String newPlayer = "1000";				//新玩家加入房间
	public static final String loginStatForFail = "2000";		//登陆失败
	public static final String loginStatForSuccess = "3000";	//登陆成功
	public static final String newMap = "4000";					//地图参数
	public static final String weapon = "5000";					//武器参数
	public static final String matchingStatForFail = "6000";	//匹配失败
	public static final String matchingStatForSuccess = "7000";	//匹配成功
	public static final String registerStatForFail = "8000";	//注册失败
	public static final String registerStatForSuccess = "9000";	//注册成功
	public static final String otherQuitFromRoom = "0100";		//其他玩家退出房间
	public static final String winnerOne = "0200";				//胜利者
	
	/*
	 * 各协议约束格式:
	 * Attention1:其中userid为玩家id，roomid为房间id，均由匹配时服务器返回；
	 * Attention2：其中0000为s
	 *
	 * login									String="0001"+"username"+"^"+"password";
	 * matching									String="0002"+"username";
	 * end										String="0003"+"roomid";
	 * register									String="0004"+"username"+"^"+"mail"+"^"+"passworld";
	 * quitFromRoom								String="0005"+"userid"+"^"+"roomid";
	 * quitFromGame								String="0006"+userid+^+roomid;
	 * winnerone								String="0007"+userid+^+roomid;
	 * gamedate									String="0000"+"userid"+"^"+"roomid"+"^"+"data";
	 * newplayer								String="1000"+"userid"+"^"+"roomid"+"^"+statinroom";
	 * loginStatForFail							String="2000";
	 * loginStatForSuccess						String="3000";
	 * newMap									String="4000"＋"mapid";
	 * weapon									String="5000"+"weaponid;
	 * matchingStatforFail						String="6000";
	 * natchingStatForSuccess					String="7000"+"userid"+"^"+"roomid"+"^"+"statinroom"+"^"+"user_1id"+"^"
	 * 													+"user_2id"+"^"+"user_3id"+"^"+"user_4id";其中若某user不存在则用-1标识，自身用0标识。
	 * registerStatForFail						String="8000";
	 * registerStatForSuccess					String="9000";
	 * otherQuitFromRoom						String="0100"+userid";
	 * winnerOne								String="0200"+"userid";
	 */
}
