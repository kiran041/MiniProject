package com.javaquiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class ConnectionTest {
	Connection connection = null;

	public Connection getConnectionDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionsdatabase", "root",
					"Kiran@041");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}

public class Database {

	Scanner sc = new Scanner(System.in);

	// To calculate the grade based on score
	public static String calculateScore(int score) {
		String grade;
		if (score < 5) {
			grade = "Class D -Fail";
		}

		else {
			if (score == 5) {
				grade = "Class C";
			} else if (score >= 6 && score < 8) {
				grade = "Class B";
			} else
				grade = "Class A";
		}
		return grade;

	}

	public void conductQuiz() {
		Student s = new Student();
		s = s.studentinfo();
		PreparedStatement psr = null;
		PreparedStatement psi = null;
		Connection con = null;
		int score = 0;
		int correctAns = 0;
		int incorrectAns = 0;

		int i = 1;

		try {

			ConnectionTest connectionTest = new ConnectionTest();
			con = connectionTest.getConnectionDetails();

			psr = con.prepareStatement("select * from questions ORDER BY RAND()");

			ResultSet rs = psr.executeQuery();

			System.out.println("\n");
			System.out.println("# Quiz Started...");
			while (rs.next()) {
				System.out.println("\n-------------------------------------------------");
				System.out.println("Question " + i + " :" + rs.getString(1));
				System.out.println("    A) " + rs.getString(2));
				System.out.println("    B) " + rs.getString(3));
				System.out.println("    C) " + rs.getString(4));
				System.out.println("    D) " + rs.getString(5));

				System.out.print("Select correct option(A/B/C/D) >> ");
				String ans = sc.next();
				if (ans.equalsIgnoreCase(rs.getString(6))) {
					System.out.println("CORRECT ANSWER\n");
					score++;
					i++;
					correctAns++;

				} else {
					System.out.println("SORRY ,INCORRECT ANSWER \n");
					i++;
					incorrectAns++;

				}

			}

			System.out.println("***************************************\n YOUR SCORE IS : " + score + " out of 10 >> "
					+ calculateScore(score));
			System.out.println("Correct Answers : " + correctAns);
			System.out.println("Wrong Answers : " + incorrectAns + "\n***************************************\n\n\n");

			psi = con.prepareStatement("insert into studentrecord(id,Name)values(?,?)");
			psi.setInt(1, s.studentId);
			psi.setString(2, s.studentName);
			psi.executeUpdate();

			PreparedStatement psu = con.prepareStatement("update studentrecord set score=? where id=?");
			psu.setInt(1, score);
			psu.setInt(2, s.studentId);
			psu.executeUpdate();

			con.close();
			psr.close();
			psu.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

}
