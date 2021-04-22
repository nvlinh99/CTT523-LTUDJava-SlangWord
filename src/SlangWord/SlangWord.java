package SlangWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author LinhNV
 *
 */
public class SlangWord {
	/**
	 * @param args
	 */
    public static Scanner inputWord = new Scanner(System.in);
    
	public static void showMenu(){
		System.out.println("WELCOME TO SLANG WORD DICTIONARY!");
        System.out.println("1. Search by Slang Word.");
        System.out.println("2. Search by Definition.");
        System.out.println("3. Show history.");
        System.out.println("4. Add a Slang Word.");
        System.out.println("5. Edit a Slang Word.");
        System.out.println("6. Delete a Slang Word.");
        System.out.println("7. Reset list to default.");
        System.out.println("8. Random a Slangword.");
        System.out.println("9. Quiz: Find word with Slang Word.");
        System.out.println("10. Quiz: Find word with Definition.");
        System.out.println("11. Clear History.");
        System.out.println("0. Exit.");
        System.out.println(">> Choose:  ");
        int selection = inputWord.nextInt();
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Functions.loadDataFromFile();
		showMenu();
		System.out.println("Hallo");
	}

}
