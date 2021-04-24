package SlangWord;

import java.util.Scanner;

/**
 * @author LinhNV
 *
 */
public class SlangWord {
	/**
	 * @param args
	 */
	public static Scanner scan = new Scanner(System.in);
    
	public static void showMenu(){
		System.out.println("--------------------------------------");
		System.out.println(">> WELCOME TO SLANG WORD DICTIONARY!<<");
		System.out.println("--------------------------------------");
        System.out.println("|1. Search by Slang Word.");
        System.out.println("|2. Search by Definition.");
        System.out.println("|3. Show search history.");
        System.out.println("|4. Add a Slang Word.");
        System.out.println("|5. Edit a Slang Word.");
        System.out.println("|6. Delete a Slang Word.");
        System.out.println("|7. Reset list to default.");
        System.out.println("|8. Random a Slangword.");
        System.out.println("|9. Quiz: Find word with Slang Word.");
        System.out.println("|10. Quiz: Find word with Definition.");
        System.out.println("|11. Clear History.");
        System.out.println("|12. Exit.");
        System.out.println("--------------------------------------\n");
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Functions.loadDataFromFile();
		String option = null;
        boolean exitFlag = false;
		showMenu();
		while (true) {
            System.out.printf(">> Your option: ");
            option = scan.nextLine();
            switch (option) {
                case "1":
                	Functions.searchBySlangWord();                    
                    break;
                case "2":
                	Functions.searchByDefinition();                    
                    break;
                case "3":
                	Functions.showSearchHistory();                   
                    break;
                case "4":
                	Functions.addNewSlangWord();                  
                    break;
                case "5":
                	Functions.editSlangWord();                  
                    break;
                case "6":
                	Functions.deleteSlangWord();                  
                    break;
            }
            if (exitFlag) {
                break;
            }
            System.out.println();
            showMenu();
        }
		System.out.println("Hallo");
	}
}
