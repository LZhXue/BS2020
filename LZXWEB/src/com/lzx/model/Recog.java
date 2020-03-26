package com.lzx.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Recog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// I type ;313.45/1745 searchNumberWithoutType
		String wait = "I313.45/1745";
		System.out.println(Recognize(wait));

	}

	// 获取书架
	public static List<Book_Shelf> getBookShelfListBySql(String sql) {

		List<Book_Shelf> list = new ArrayList<Book_Shelf>();
		Connection con;// 声明Connection对象
		String driver = "com.mysql.jdbc.Driver";// 驱动程序名
		String url = "jdbc:mysql://localhost:3306/library";// URL指向要访问的数据库名
		String user = "root";// MySQL配置时的用户名
		String password = "123456";// MySQL配置时的密码

		try {// 遍历查询结果集
			Class.forName(driver);// 加载驱动程序
			con = DriverManager.getConnection(url, user, password);// 1.getConnection()方法，连接MySQL数据库
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement statement = con.createStatement();// 2.创建statement类对象，用来执行SQL语句
			ResultSet rs = statement.executeQuery(sql);// 3.ResultSet类，用来存放获取的结果集
//			System.out.println("-----------------");
//			System.out.println("执行结果如下所示:");
//			System.out.println("-----------------");

			while (rs.next()) {
				Book_Shelf modelBookShelf = new Book_Shelf(Integer.valueOf(rs.getString("primary_key")),
						rs.getString("storey_number"), rs.getString("area_number"), rs.getString("shelf_number"),
						rs.getString("search_number_type"), rs.getString("search_number_start"),
						rs.getString("search_number_end"),Integer.valueOf(rs.getString("zhuzi")));
//				System.out.println(modelBookShelf);// 输出结果
				list.add(modelBookShelf);
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can't find the Driver!");// 数据库驱动类异常处理
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();// 数据库连接失败异常处理
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			System.out.println("数据库数据成功获取！！");
		}
		return list;
	}

	 public static Book_Shelf Recognize(String waitToSearch) {

		int length = waitToSearch.length();// 索书号的长度
		String type = null;// 索书号的类型
		String searchNumberWithoutType = null;// 索书号去掉类别号
		String searchNumberWithoutTypeArray[] = null;// 用斜杠分割索书号存放的数组
		String searchNumberWithoutTypePre = null;// 分割后的前半部分
		String searchNumberWithoutTypeFol = null;// 分割后的后半部分
		int searchNumberWithoutTypeRear = 0;// 把后半部分转化为数字
		Book_Shelf resultString = null;

		// 先将类别号、索书号进行切分
				if (Character.isLetter(waitToSearch.charAt(1))) {// 第二位是字母，索书号有两位符号位
					type = waitToSearch.substring(0, 2);// 索书号的类型
					searchNumberWithoutType = waitToSearch.substring(2, length);// 索书号去掉类别号
					searchNumberWithoutTypeArray = searchNumberWithoutType.split("/");// 用斜杠分割
					searchNumberWithoutTypePre = searchNumberWithoutTypeArray[0];// 前半部分
					searchNumberWithoutTypeFol = searchNumberWithoutTypeArray[1];// 后半部分
					StringBuilder sb=new StringBuilder();
					sb = getValidF(searchNumberWithoutTypeArray[1]);
					searchNumberWithoutTypeRear = Integer.valueOf(sb.toString());// 把后半部分转化为数字

				} else if (Character.isDigit(waitToSearch.charAt(1))) {// 第二位是数字，索书号有一位符号位
					type = String.valueOf(waitToSearch.charAt(0));// 索书号的类型
					searchNumberWithoutType = waitToSearch.substring(1, length);// 索书号去掉类别号
					searchNumberWithoutTypeArray = searchNumberWithoutType.split("/");// 用斜杠分割
					searchNumberWithoutTypePre = searchNumberWithoutTypeArray[0];// 前半部分
					searchNumberWithoutTypeFol = searchNumberWithoutTypeArray[1].split("-")[0];// 后半部分
					StringBuilder sb=new StringBuilder();
					sb = getValidF(searchNumberWithoutTypeArray[1]);
					searchNumberWithoutTypeRear = Integer.valueOf(sb.toString());// 把后半部分转化为数字

				} else {
					System.out.println("索书号符号匹配发生错误");
				}

				// 先按类别找出类别一样的书架
				String sql = "select * from book_shelf where search_number_type = '" + type + "'";// 要执行的SQL语句
				List<Book_Shelf> bookShelfList = getBookShelfListBySql(sql);
				
				if(bookShelfList.size()==0){//如果从列表中找不到此类型的书
					System.out.println("目前数据库中未录入"+type+"类型的书籍！");
				}
				
				bookShelfList.forEach(System.out::println);

				// 遍历对比
//				System.out.println(bookShelfList.size());
				for (int i = 0; i < bookShelfList.size(); i++) {
					Book_Shelf modelSingle=bookShelfList.get(i);
					
					if(modelSingle.getZhuzi()==1) {
						continue;
					}
					String str1[] = modelSingle.getSearch_number_start().split("/");// 开始号通过斜杆分割
					
					String startPre = str1[0];	
					StringBuilder sb = new StringBuilder();
					if(str1.length<2) {
						sb.append("0");
					}else
					sb = getValidF(str1[1]);
					
					int starRear = Integer.valueOf(sb.toString());
					
					String str2[] =modelSingle.getSearch_number_end().split("/");// 结束号通过斜杆分割
					String endPre = str2[0];
					
					sb=new StringBuilder();
					if(str2.length<2) {
						sb.append("0");
					}else
					sb = getValidF(str2[1]);

					int endRear = Integer.valueOf(sb.toString());
//					System.out.print(endRear);

					// 前半段的开始和结束相同，直接用后半段相比的情况
//					System.out.println(startPre);
//					System.out.println(endPre);
//					System.out.println(searchNumberWithoutTypePre);
					
					if ((startPre.compareTo(endPre) == 0) && (startPre.compareTo(searchNumberWithoutTypePre) == 0)) {
						if ((searchNumberWithoutTypeRear >= starRear) && (searchNumberWithoutTypeRear <= endRear)) {
							System.out.println("书架信息成功找到！");

							resultString = modelSingle;
							return resultString;
						} else {
							continue;// 没有找到，进入下一个书架进行查找
						}

					}
					// 前半段的开始不同，结束相同的情况,用结束书架的后段序号比较
					else if (startPre.compareTo(searchNumberWithoutTypePre) < 0
							&& endPre.compareTo(searchNumberWithoutTypePre) == 0) {
						if (searchNumberWithoutTypeRear <= endRear) {
							System.out.println("书架信息成功找到！");
							resultString = modelSingle;
							return resultString;
						} else {
							continue;// 没有找到，进入下一个书架进行查找
						}
					}
					// 前半段的开始相同或不同，结束不相同但是待查找小于结束的时候的情况
					else if (startPre.compareTo(searchNumberWithoutTypePre) <= 0
							&& endPre.compareTo(searchNumberWithoutTypePre) > 0) {
							System.out.println("书架信息成功找到！");
							resultString =modelSingle;
							return resultString;
						
					}
					
				}
				// System.out.println(searchNumberWithoutTypePre);
				return resultString;
			}

	 public static StringBuilder getValidF(String endPreLast) {
			StringBuilder sb=new StringBuilder();
			
			for(int ci=0;ci<endPreLast.length();ci++) {
				if(endPreLast.charAt(ci)>='0'&&endPreLast.charAt(ci)<='9')
				{
					sb.append(endPreLast.charAt(ci));
				}else {
					break;
				}
			}
			
			if(sb.length()==0)
			{
				sb.append('0');
			}
			return sb;
		}

	}

