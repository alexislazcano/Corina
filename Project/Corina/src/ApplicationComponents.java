/**
 * @(#)ApplicationComponents.java
 *
 *
 * @author 
 * @version 1.00 2014/4/6
 */

import java.util.Scanner;

public class ApplicationComponents {
	
    public void menu() {
		RFIDAuth rfidauth = new RFIDAuth();		
		System.out.println("");
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------Corina------------------------");
		System.out.println("-----------------------------------------------------");
		System.out.println("Please select an option from below(enter the number):");
		System.out.println("1.) Start the application in normal mode");
		System.out.println("2.) Start a component check(Not yet ready)");
		System.out.println("3.) Exit");
		System.out.println("-----------------------------------------------------");
		Scanner keyboard = new Scanner(System.in);
		int choice = keyboard.nextInt();
		switch(choice){
			case 1: rfidauth.authenticateStart();
					break;
			case 3: break;
			default: System.out.println("That is not a valid choice.");
					break;
		}
	
    }
    
    public void applicationStart(){
    }
    
    
}