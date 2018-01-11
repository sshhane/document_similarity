package ie.gmit.sw;

import java.util.Scanner;

public class Menu {

	public void show() {
		
		
	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();
	    
	    while (choice != 2) {

		    switch (choice) {
		        case 1:
		            // Enter files
		        		System.out.println("Enter file 1:");
		        		String file1 = scanner.nextLine();
		        		System.out.println("Enter file 1:");
		        		String file2 = scanner.nextLine();

		            break;
		        case 2:
		            // quit
		            break;
		        default:
		            // Wrong
		    }
	    }
	}

}
