package com.javaquiz;



import java.util.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student {

	int studentId;
	String studentName;
	Scanner sc = new Scanner(System.in);

	public Student studentinfo() {

		boolean flag = false;
		Connection con =null;
		PreparedStatement psr=null;
		
		List list = new ArrayList();

		try {
			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionsdatabase", "root",
//					"Kiran@041");
			 psr = con.prepareStatement("select id from studentrecord");

			ResultSet rs = psr.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		Student stud = new Student();
		do {

			System.out.print("Enter your id :");

			stud.studentId = sc.nextInt();
			flag = false;
			if (list.contains(stud.studentId)) {
				System.out.println("This id is already exist...plz input another one\n");
				flag = true;

			}
		} while (flag);

		System.out.print("Enter your name :");
		stud.studentName = sc.next();

		return stud;
	}
	
	public void studentList() {
		boolean isAnyoneAttended = false;
		Connection con =null;
		PreparedStatement psr=null;
		
		try {

			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();
			 psr = con.prepareStatement("select * from studentrecord ");

			ResultSet rs = psr.executeQuery();

			while (rs.next()) {
				isAnyoneAttended= true;
				System.out.println("Id    : "+rs.getInt(1));
				System.out.println("Name  : "+rs.getString(2));
				System.out.println("Score : "+rs.getInt(3));
				System.out.println("-------------------");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if(isAnyoneAttended == false) {
			System.out.println("\nNo records to Display !!!!!");
			System.out.println("Still no one attended the Quiz....\n");
		}
	}
	public void particularStudentDetails() {
		boolean availableId=false;
		
		
	System.out.println("Enter the id of student");
	   int id = sc.nextInt();
	   Connection con =null;
		PreparedStatement psr=null;
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");/ 
//
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionsdatabase", "root",
//					"Kiran@041");
			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();
			 psr = con.prepareStatement("select * from studentrecord");

			ResultSet rs = psr.executeQuery();

			while (rs.next()) {
			    if(rs.getInt(1)==id) {
			    	
				System.out.println("Id    : "+rs.getInt(1));
				System.out.println("Name  : "+rs.getString(2));
				System.out.println("Score : "+rs.getInt(3));
				System.out.println("\n");
				availableId = true;
				break;
			    }
			    
				
			}
			if(availableId == false) {
		    	System.out.println("Sorry !!!! Given Id is not register with us...\n");
		    }
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

