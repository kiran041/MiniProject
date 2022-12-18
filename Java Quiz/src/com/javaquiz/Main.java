package com.javaquiz;

import java.util.Scanner;

public class Main {

	public void attendQuiz() {
		Database d = new Database();
		d.conductQuiz();
	}

	public static void main(String[] args) {
		Student student = new Student();
		Scanner sc = new Scanner(System.in);

		Main main = new Main();

		boolean flag = true;
		System.out.println(" ******** WELCOME TO JAVA QUIZ ******** \n");
		while (flag) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("   ----------------- Home Menu -----------------");
			System.out.println("\nSelect your choice from below options to proceed : ");
			System.out.println("    1. Attend Quiz");
			System.out.println("    2. Fetch record of particular student");
			System.out.println("    3. Fetch record of All students");
			System.out.println("    4. Quit....");

			System.out.print("\n Choice >>");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				main.attendQuiz();
				break;

			case 2:
				student.particularStudentDetails();
				break;
			case 3:
				student.studentList();
				break;
			case 4:
				System.out.println("\nQuit ....\nThanks,Have a nice day....");
				flag = false;
				break;
			default:
				System.out.println("Please select valid choice");

			}
		}
		sc.close();
	}

}
