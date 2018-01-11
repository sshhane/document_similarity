package ie.gmit.sw;

import java.util.Scanner;

public class Menu {

	public Menu() throws InterruptedException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Press 1 to enter Files\nPress 2 to quit\n");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			// Enter files
			System.out.println("Enter file 1:");
			String file1 = scanner.next();
			System.out.println("Enter file 2:");
			String file2 = scanner.next();

			new Launcher().Launch(file1, file2);

			break;
		case 2:
			System.out.println("Quit");
			break;
		default:
			// Wrong input
			System.out.println("Invalid input");
		}
	}

}
