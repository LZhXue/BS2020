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

	// ��ȡ���
	public static List<Book_Shelf> getBookShelfListBySql(String sql) {

		List<Book_Shelf> list = new ArrayList<Book_Shelf>();
		Connection con;// ����Connection����
		String driver = "com.mysql.jdbc.Driver";// ����������
		String url = "jdbc:mysql://localhost:3306/library";// URLָ��Ҫ���ʵ����ݿ���
		String user = "root";// MySQL����ʱ���û���
		String password = "123456";// MySQL����ʱ������

		try {// ������ѯ�����
			Class.forName(driver);// ������������
			con = DriverManager.getConnection(url, user, password);// 1.getConnection()����������MySQL���ݿ�
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement statement = con.createStatement();// 2.����statement���������ִ��SQL���
			ResultSet rs = statement.executeQuery(sql);// 3.ResultSet�࣬������Ż�ȡ�Ľ����
//			System.out.println("-----------------");
//			System.out.println("ִ�н��������ʾ:");
//			System.out.println("-----------------");

			while (rs.next()) {
				Book_Shelf modelBookShelf = new Book_Shelf(Integer.valueOf(rs.getString("primary_key")),
						rs.getString("storey_number"), rs.getString("area_number"), rs.getString("shelf_number"),
						rs.getString("search_number_type"), rs.getString("search_number_start"),
						rs.getString("search_number_end"),Integer.valueOf(rs.getString("zhuzi")));
//				System.out.println(modelBookShelf);// ������
				list.add(modelBookShelf);
			}
			rs.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can't find the Driver!");// ���ݿ��������쳣����
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();// ���ݿ�����ʧ���쳣����
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			System.out.println("���ݿ����ݳɹ���ȡ����");
		}
		return list;
	}

	 public static Book_Shelf Recognize(String waitToSearch) {

		int length = waitToSearch.length();// ����ŵĳ���
		String type = null;// ����ŵ�����
		String searchNumberWithoutType = null;// �����ȥ������
		String searchNumberWithoutTypeArray[] = null;// ��б�ָܷ�����Ŵ�ŵ�����
		String searchNumberWithoutTypePre = null;// �ָ���ǰ�벿��
		String searchNumberWithoutTypeFol = null;// �ָ��ĺ�벿��
		int searchNumberWithoutTypeRear = 0;// �Ѻ�벿��ת��Ϊ����
		Book_Shelf resultString = null;

		// �Ƚ����š�����Ž����з�
				if (Character.isLetter(waitToSearch.charAt(1))) {// �ڶ�λ����ĸ�����������λ����λ
					type = waitToSearch.substring(0, 2);// ����ŵ�����
					searchNumberWithoutType = waitToSearch.substring(2, length);// �����ȥ������
					searchNumberWithoutTypeArray = searchNumberWithoutType.split("/");// ��б�ָܷ�
					searchNumberWithoutTypePre = searchNumberWithoutTypeArray[0];// ǰ�벿��
					searchNumberWithoutTypeFol = searchNumberWithoutTypeArray[1];// ��벿��
					StringBuilder sb=new StringBuilder();
					sb = getValidF(searchNumberWithoutTypeArray[1]);
					searchNumberWithoutTypeRear = Integer.valueOf(sb.toString());// �Ѻ�벿��ת��Ϊ����

				} else if (Character.isDigit(waitToSearch.charAt(1))) {// �ڶ�λ�����֣��������һλ����λ
					type = String.valueOf(waitToSearch.charAt(0));// ����ŵ�����
					searchNumberWithoutType = waitToSearch.substring(1, length);// �����ȥ������
					searchNumberWithoutTypeArray = searchNumberWithoutType.split("/");// ��б�ָܷ�
					searchNumberWithoutTypePre = searchNumberWithoutTypeArray[0];// ǰ�벿��
					searchNumberWithoutTypeFol = searchNumberWithoutTypeArray[1].split("-")[0];// ��벿��
					StringBuilder sb=new StringBuilder();
					sb = getValidF(searchNumberWithoutTypeArray[1]);
					searchNumberWithoutTypeRear = Integer.valueOf(sb.toString());// �Ѻ�벿��ת��Ϊ����

				} else {
					System.out.println("����ŷ���ƥ�䷢������");
				}

				// �Ȱ�����ҳ����һ�������
				String sql = "select * from book_shelf where search_number_type = '" + type + "'";// Ҫִ�е�SQL���
				List<Book_Shelf> bookShelfList = getBookShelfListBySql(sql);
				
				if(bookShelfList.size()==0){//������б����Ҳ��������͵���
					System.out.println("Ŀǰ���ݿ���δ¼��"+type+"���͵��鼮��");
				}
				
				bookShelfList.forEach(System.out::println);

				// �����Ա�
//				System.out.println(bookShelfList.size());
				for (int i = 0; i < bookShelfList.size(); i++) {
					Book_Shelf modelSingle=bookShelfList.get(i);
					
					if(modelSingle.getZhuzi()==1) {
						continue;
					}
					String str1[] = modelSingle.getSearch_number_start().split("/");// ��ʼ��ͨ��б�˷ָ�
					
					String startPre = str1[0];	
					StringBuilder sb = new StringBuilder();
					if(str1.length<2) {
						sb.append("0");
					}else
					sb = getValidF(str1[1]);
					
					int starRear = Integer.valueOf(sb.toString());
					
					String str2[] =modelSingle.getSearch_number_end().split("/");// ������ͨ��б�˷ָ�
					String endPre = str2[0];
					
					sb=new StringBuilder();
					if(str2.length<2) {
						sb.append("0");
					}else
					sb = getValidF(str2[1]);

					int endRear = Integer.valueOf(sb.toString());
//					System.out.print(endRear);

					// ǰ��εĿ�ʼ�ͽ�����ͬ��ֱ���ú�����ȵ����
//					System.out.println(startPre);
//					System.out.println(endPre);
//					System.out.println(searchNumberWithoutTypePre);
					
					if ((startPre.compareTo(endPre) == 0) && (startPre.compareTo(searchNumberWithoutTypePre) == 0)) {
						if ((searchNumberWithoutTypeRear >= starRear) && (searchNumberWithoutTypeRear <= endRear)) {
							System.out.println("�����Ϣ�ɹ��ҵ���");

							resultString = modelSingle;
							return resultString;
						} else {
							continue;// û���ҵ���������һ����ܽ��в���
						}

					}
					// ǰ��εĿ�ʼ��ͬ��������ͬ�����,�ý�����ܵĺ����űȽ�
					else if (startPre.compareTo(searchNumberWithoutTypePre) < 0
							&& endPre.compareTo(searchNumberWithoutTypePre) == 0) {
						if (searchNumberWithoutTypeRear <= endRear) {
							System.out.println("�����Ϣ�ɹ��ҵ���");
							resultString = modelSingle;
							return resultString;
						} else {
							continue;// û���ҵ���������һ����ܽ��в���
						}
					}
					// ǰ��εĿ�ʼ��ͬ��ͬ����������ͬ���Ǵ�����С�ڽ�����ʱ������
					else if (startPre.compareTo(searchNumberWithoutTypePre) <= 0
							&& endPre.compareTo(searchNumberWithoutTypePre) > 0) {
							System.out.println("�����Ϣ�ɹ��ҵ���");
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

