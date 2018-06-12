package Friendship_Killer;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import com.mysql.jdbc.Statement;

@SuppressWarnings("unused")
public class SQL {
	/*
	 * 连接数据库
	 */
	private static final String URL="jdbc:mysql://********:3306/test?";
	//private static final String URL="jdbc:mysql://localhost:3306/test?";
	private static final String UserName = "******";
	private static final String PassWord = "******";
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("驱动加载成功!");
		}catch (ClassNotFoundException e) {  
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");  
            //添加一个println，如果加载驱动异常，检查是否添加驱动，或者添加驱动字符串是否错误  
            e.printStackTrace();  
		}  
		Connection conn = null;
		try {  
            conn = DriverManager.getConnection(URL, UserName, PassWord);  
                System.out.println("获取数据库连接成功！");  
        } catch (SQLException e) {  
            System.out.println("获取数据库连接失败！");  
                        //添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误  
            e.printStackTrace();  
        } 
		return conn;
	}
	/*
	 * 关闭数据库
	 */
	public static void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				System.out.println("数据库关闭成功");
			}
		}
		catch(SQLException e){
			System.out.println("数据库关闭失败！");
			e.printStackTrace();
		}
	}
	/*
	 * 管理接口，用于输入指令
	 */
	public static String getCommend() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("输入用户信息：");
		
		String Com = input.nextLine();
		return Com;
	}
	/*
	 * 管理接口，执行sql命令
	 */
	public static void ExcuteSQL(List<String> options) throws SQLException{
		
	}
	/*
	 * 执行对用户表进行查询的工作
	 */
	public static void excuteQueryFromUsers() throws SQLException{
		
	}
	/*
	 * 用户注册
	 */
	public static int UsersRegister(Connection conn, String username, String mail, String password) {
		try {
			
			java.sql.Statement stat = conn.createStatement();
			String commendForName = "SELECT count(id) FROM users WHERE name_key = '"+username+"';";
			ResultSet re = stat.executeQuery(commendForName);
			re.next();
			System.out.println(re.getInt(1));
			if(re.getInt(1)>0) {
				return -1;
			}
		}catch(Exception e) {	
			return -1;
		}try {
			java.sql.Statement stat = conn.createStatement();
			String commendForInsert = "INSERT INTO users(name_key,mail,password)"+
										"VALUES('"+username+"','"+mail+"','"+password+"');";
			stat.executeUpdate(commendForInsert);
			System.out.println("数据插入成功！");
			return 1;
		}catch(Exception e1) {
			System.out.println("数据插入失败！");
			return -1;
			//e.printStackTrace();
		}
	}
	/*
	 * 执行对room表的查询工作
	 */
	public static int excuteQueryFromRooms(Connection conn) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SelectFromRooms = "SELECT id_room FROM rooms WHERE stats = 1";
			ResultSet re = stat.executeQuery(SelectFromRooms);
			int room_id = re.getInt(0);
			System.out.println("房间信息查询成功！");
			return room_id;
		}catch(Exception e) {
			System.out.println("房间信息查询失败！");
			e.printStackTrace();
			return -1;
		}
	}
	/*
	 * 执行更改用户的工作
	 */
	public static void changeUserMail(Connection conn, String UserName,String mail) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String ChangeUser = "UPDATE users set mail '"+mail+"' WHERE name_key = '"+UserName+"';";
			stat.execute(ChangeUser);
			System.out.println("更新用户信息成功！");
		}catch(Exception e) {
			System.out.println("更新用户信息失败！");
			e.printStackTrace();
		}
	}
	public static void changeUserSex(Connection conn, String UserName,String sex) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String ChangeUser = "UPDATE users set mail '"+sex+"' WHERE name_key = '"+UserName+"';";
			stat.execute(ChangeUser);
			System.out.println("更新用户信息成功！");
		}catch(Exception e) {
			System.out.println("更新用户信息失败！");
			e.printStackTrace();
		}
	}
	/*
	 * 执行删除用户的工作
	 */
	public static void DropUsers(Connection conn,String UserName){
		try {
			java.sql.Statement stat= conn.createStatement();
			String DeletUser = "DELETE FROM users WHERE name_key = '"+UserName+"';";
			stat.executeUpdate(DeletUser);
			System.out.println("用户删除成功；");
		}catch(Exception e) {
			System.out.println("用户删除失败；");
			e.printStackTrace();
		}
	}
	/*
	 * 执行更改房间参数的工作
	 */
	public static void ChangeRoom(Connection conn, long key){
		try {
			java.sql.Statement stat = conn.createStatement();
			String ChangeRoom = "UPDATE rooms SET  WHERE id = "+key+";";
			stat.executeUpdate(ChangeRoom);
		}catch(Exception e) {
			System.out.println("房间更改失败！");
			e.printStackTrace();
		}
	}
	/*
	 * 执行删除房间的工作
	 */
	public static void DeleteRoom(Connection conn, long key) {
		try {
			java.sql.Statement stat= conn.createStatement();
			String DeleteRoom = "DELETE FROM rooms WHERE id = "+key+";";
			stat.executeUpdate(DeleteRoom);
		}catch(Exception e) {
			System.out.println("房间删除失败!");
			e.printStackTrace();
		}
	}
	/*
	 * 执行添加房间的工作
	 */
	public static void AddRoom(Connection conn,int userid){
		try {
			java.sql.Statement stat = conn.createStatement();
			String AddRoom = "INSERT INTO rooms(user_1)VALUES("+userid+");";
			stat.executeUpdate(AddRoom);
			System.out.println("房间加入成功！");
		}catch(Exception e) {
			System.out.println("房间添加失败！");
			e.printStackTrace();
		}
	}
	
	public static int Login(Connection conn,String username,String password) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SELECTCONUT = "SELECT count(password) FROM users WHERE name_key = '" + username+"';";
			ResultSet rscount = stat.executeQuery(SELECTCONUT);
			rscount.next();
			System.out.println(rscount.getInt(1));
			if(rscount.getInt(1)==1) {
				String SELECT = "SELECT password FROM users WHERE name_key = '" + username+"';";
				ResultSet rs = stat.executeQuery(SELECT);
				rs.next();
				String passwordFromData = rs.getString(1);
				if(password.equals(passwordFromData)) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				return -1;
			}
			
		}catch(Exception e){
			return -1;
		}
	}
	
	public static int SelectRoomId_user1(Connection conn, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SelectRoomId = "SELECT id_room FROM rooms WHERE user_1 = "+userid+";";
			ResultSet rs = stat.executeQuery(SelectRoomId);
			rs.next();
			return rs.getInt(1);

		}catch(Exception e) {
			return -1;
		}
	}
	
	public static int SelectRoomId_user2(Connection conn, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SelectRoomId = "SELECT id_room FROM rooms WHERE user_2 = "+userid+";";
			ResultSet rs = stat.executeQuery(SelectRoomId);
			rs.next();
			return rs.getInt(1);

		}catch(Exception e) {
			return -1;
		}
	}
	public static int SelectRoomId_user3(Connection conn, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SelectRoomId = "SELECT id_room FROM rooms WHERE user_3 = "+userid+";";
			ResultSet rs = stat.executeQuery(SelectRoomId);
			rs.next();
			return rs.getInt(1);

		}catch(Exception e) {
			return -1;
		}
	}
	public static int SelectRoomId_user4(Connection conn, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SelectRoomId = "SELECT id_room FROM rooms WHERE user_4 = "+userid+";";
			ResultSet rs = stat.executeQuery(SelectRoomId);
			rs.next();
			return rs.getInt(1);

		}catch(Exception e) {
			return -1;
		}
	}
	
	
	public static int SelectUserId(Connection conn, String username) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String SelectUserId = "SELECT id FROM users WHERE name_key = '"+username+"';";
			ResultSet rsid = stat.executeQuery(SelectUserId);
			rsid.next();
			return rsid.getInt(1);
		}catch(Exception e) {
			return -1;
		}
	}
	
	public static boolean SelectUserInRoom_1(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT count(user_1) FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			if(rs.getInt(1)==0) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean SelectUserInRoom_2(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT count(user_2) FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			if(rs.getInt(1)==0) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public static boolean SelectUserInRoom_3(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT count(user_3) FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			if(rs.getInt(1)==0) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	public static boolean SelectUserInRoom_4(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT count(user_4) FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			if(rs.getInt(1)==0) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public static int SelectUserIdInRoom_1(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT user_1 FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			return rs.getInt(1);
		}catch(Exception e){
			return -1;
		}
	}
	public static int SelectUserIdInRoom_2(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT user_2 FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			return rs.getInt(1);
		}catch(Exception e){
			return -1;
		}
	}
	public static int SelectUserIdInRoom_3(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT user_3 FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			return rs.getInt(1);
		}catch(Exception e){
			return -1;
		}
	}
	public static int SelectUserIdInRoom_4(Connection conn, int room_id) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Select = "SELECT user_4 FROM rooms WHERE id_room = "+room_id + ";";
			ResultSet rs = stat.executeQuery(Select);
			rs.next();
			return rs.getInt(1);
		}catch(Exception e){
			return -1;
		}
	}
	public static int Upateuser1Toroom(Connection conn, int room_id, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Update = "UPDATE rooms SET user_1 = "+userid+" WHERE id_room = "+room_id+";";
			stat.executeUpdate(Update);
			System.out.println("mcnvzdkjghj");
			return 1;
		}catch(Exception e) {
			System.out.println("www");
			return -1;
		}
	}
	public static int Upateuser2Toroom(Connection conn, int room_id, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Update = "UPDATE rooms SET user_2 = "+userid+" WHERE id_room = "+room_id+";";
			stat.executeUpdate(Update);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}
	public static int Upateuser3Toroom(Connection conn, int room_id, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Update = "UPDATE rooms SET user_3 = "+userid+" WHERE id_room = "+room_id+";";
			stat.executeUpdate(Update);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}
	public static int Upateuser4Toroom(Connection conn, int room_id, int userid) {
		try {
			java.sql.Statement stat = conn.createStatement();
			String Update = "UPDATE rooms SET user_4 = "+userid+" WHERE id_room = "+room_id+";";
			stat.executeUpdate(Update);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}
	public static int matching(Connection conn,int user_id) {
		int result;
		try {
			int userid = user_id;
			Integer useridi = new Integer(userid); 
			java.sql.Statement stat = conn.createStatement();
			String n = new String(useridi.toString());
			String SelectRoomCount = "SELECT count(id_room) FROM rooms WHERE stats BETWEEN 1 AND 3";
			ResultSet rscount = stat.executeQuery(SelectRoomCount);
			rscount.next();
			if(rscount.getInt(1) == 0) {
				SQL.AddRoom(conn,userid);
				int roomid = SQL.SelectRoomId_user1(conn, userid);
				if(SQL.Upateuser1Toroom(conn, roomid, userid)==1) {
					return roomid;
				}else {
					return -1;
				}
			}
			else {
				String SelectRoom = "SELECT id_room FROM rooms WHERE stats BETWEEN 1 AND 3";
				ResultSet rs = stat.executeQuery(SelectRoom);
				rs.next();
				int roomid = rs.getInt(1);
				
				int[] userinroom = new int[4];
				int flag = -1;
				userinroom[0] = SQL.SelectUserIdInRoom_1(conn, roomid);
				userinroom[1] = SQL.SelectUserIdInRoom_2(conn, userid);
				userinroom[2] = SQL.SelectUserIdInRoom_3(conn, userid);
				userinroom[3] = SQL.SelectUserIdInRoom_4(conn, userid);
				
				for(int i=0; i<4;i++) {
					if(userinroom[i]==-1) {
						flag = i+1;
						break;
					}
				}
				if(flag == 1) {
					int ree = SQL.Upateuser1Toroom(conn, roomid, userid);
					if(ree==-1) {
						roomid = -1;
					}
				}else if(flag == 2) {
					int ree = SQL.Upateuser2Toroom(conn, roomid, userid);
					if(ree==-1) {
						roomid = -1;
					}
				}else if(flag == 3) {
					int ree = SQL.Upateuser3Toroom(conn, roomid, userid);
					if(ree==-1) {
						roomid = -1;
					}
				}else {
					int ree = SQL.Upateuser4Toroom(conn, roomid, userid);
					if(ree==-1) {
						roomid = -1;
					}
				}
				return roomid;
			}
		}catch(Exception e) {
			return -1;
		}
	}
}
