package SlangWord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author LinhNV
 *
*/

public class Functions {
	private static final String SLANG_WORD_INPUT = "./Data/slang.txt";
	private static final String SLANG_WORD_INPUT_DEFAULT = "./Data/default_slang.txt";
	private static final String SLANG_WORD_HISTORY = "./Data/backup_history.txt";
	public static HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
	public static List<String> historySlangWord = new ArrayList<>();
	public static Scanner inputWord = new Scanner(System.in);
	
	public static void loadDataFromFile() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(SLANG_WORD_INPUT)));
			String line = null;
			while((line=br.readLine())!=null) {
	            if(line.contains("`")){
	                String[] str = line.split("`");
	                String[] split_str = str[1].split("\\|");
	                List<String> child = Arrays.asList(split_str);
	                hashMap.put(str[0], child);
	            }
	        }
		}
		catch (IOException e) {
        	System.out.println("!!! Error: Load data fail! \n");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
	}
	
	public static void searchBySlangWord() {
		System.out.print(">> Type word to find: ");
        String sWord = inputWord.nextLine();
        sWord = sWord.toUpperCase();
        List<String> defList = hashMap.get(sWord);
        historySlangWord.add(sWord);
        if (defList == null) {
        	System.out.println("!!! Can't find definition of: " + sWord);
        }
        else {
        	System.out.println("=> Definitions:");
        	for(String def : defList) {
        		System.out.println("- " + def);
        	}
        }
	}
	
	public static void searchByDefinition() {
		System.out.print(">> Type to find Slang word: ");
		String def = inputWord.nextLine();
        List<String> sWords = new ArrayList<>();
        for (String i: hashMap.keySet())
        {
        	List<String> defLis = hashMap.get(i);
        	for(String ii: defLis) {
        		if (ii.contains(def)){
                	sWords.add(i);
                }
        	}
        }
        
        if(sWords.isEmpty()) {
        	System.out.println("!!! No Slang Word found!");
        }
        else {
	        System.out.println("*** List Slang word contain '" + def + "' in definition: ");
	        for (String sWord: sWords) {
	        	System.out.println(sWord);
	        }
        }
	}
	
	public static void showSearchHistory() {
		if(historySlangWord.isEmpty()) {
			System.out.println("!!! No History!");
		}
		else {
			System.out.println("*** Search History: ");
			for (String sWord: historySlangWord) {
				System.out.println(sWord);
			}
		}
	}
	
	public static void updateDataToFile() {		
		BufferedWriter bw = null;
		FileWriter fw = null;
        try {
        	fw = new FileWriter(new File(SLANG_WORD_INPUT));
   			bw = new BufferedWriter(fw);
   			for (String sWord: hashMap.keySet())
            {
                fw.write(sWord + "`");
                List<String> defList = hashMap.get(sWord);
                for (int i = 0; i < defList.size(); i++)
                {
                    fw.write(defList.get(i));
                    if (i + 1 < defList.size()) {
                    	fw.write("|");
                    }
                }
                fw.write("\n");
            }
        }
        catch (IOException e) {
        	System.out.println("!!! Error: Update file fail!\n");
            e.printStackTrace();
        } finally {
            try {
                if (bw != null && fw != null) {
                    fw.close();
                	bw.close();
                    
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
    }
	
	public static void addNewSlangWord() {
		System.out.print(">> Type new Slang Word: ");
        String newSWord = inputWord.nextLine();
        newSWord = newSWord.toUpperCase();
        System.out.print(">> Type definition: ");
        String newDef = inputWord.nextLine();
        List<String> defList = new ArrayList<>();
        defList.add(newDef);
        if(hashMap.containsKey(newSWord)) {
        	System.out.println("!!! Slang word already exists!");
        	System.out.print(">> Overwrite? (Y/N): ");
            String override = inputWord.nextLine();
            if (override.equals("Y") || override.equals("y")) {
            	hashMap.put(newSWord, defList);
            	updateDataToFile();
            }
            else {
                List<String> duplicateList = hashMap.get(newSWord);
                for (String Sword: duplicateList) {
                    defList.add(Sword);
                }
                hashMap.put(newSWord, defList);
                updateDataToFile();
            }
        }
        else
        {
        	hashMap.put(newSWord, defList);
        	updateDataToFile();
            System.out.println("!!! Add new Slang word Completed!");
        }
	}
	
	public static void editSlangWord() {
		System.out.print(">> Type Slang word to edit: ");
		String sWord = inputWord.nextLine();
		sWord = sWord.toUpperCase();
		if(!hashMap.containsKey(sWord)) {
			System.out.println(sWord + " not found!");
		}
		
		List<String> defList = hashMap.get(sWord);
		List<String> tmpDefList = new ArrayList<>();
		
		if(defList.isEmpty()) {
			System.out.println("!!! This Slang word dont have any definition!");
		}
		
		for(String def: defList) {
			tmpDefList.add(def);
		}
		
		System.out.println("\n--------------------------------------");
		System.out.println(">> MENU EDIT <<");
		System.out.println("--------------------------------------");
        System.out.println("|1. Edit Slang word.");
        System.out.println("|2. Edit Definition.");
        System.out.println("--------------------------------------\n");
        System.out.print(">> Your option: ");
        String option = inputWord.nextLine();
        switch (option) {
	        case "1":
	        	System.out.print(">> Type new name of Slang word: ");   
	        	String newSWord = inputWord.nextLine();
	        	newSWord = newSWord.toUpperCase();
	        	if(!newSWord.equals(sWord)) {
	        		hashMap.put(newSWord, defList);
	        		updateDataToFile();
	        		System.out.print("!!! Update Slang word successfull! ");
	        	}
	        	else {
	        		System.out.println("!!! New Slang word must be different old Slang word.");
	        		break;
	        	}
	        	break;
	        case "2":
				int count = 1;
				System.out.println("\n--------------------------------------");
				System.out.println(">> LIST DEFINITIONS <<");
				System.out.println("--------------------------------------");
				for (String def: defList){
				    System.out.println(count + " - " + def);
				    count++;
				}
		        System.out.println("--------------------------------------\n");
				System.out.print(">> Which definition you want to edit: ");
				int defIndex = inputWord.nextInt();
				System.out.flush();
				System.out.println("\n--------------------------------------");
				System.out.println(">> OPTION EDIT DEFINITION <<");
				System.out.println("--------------------------------------");
		        System.out.println("|1. Add definition.");
		        System.out.println("|2. Update definition.");
		        System.out.println("|3. Delelte definition.");
		        System.out.println("--------------------------------------\n");
				System.out.print(">> Choose: ");
				int defOption = inputWord.nextInt();
				inputWord.nextLine();
				if (defOption == 1) {
					System.out.print(">> Type new definition: ");
				    String newDefi = inputWord.nextLine();
				    tmpDefList.add(newDefi);
				    hashMap.put(sWord, tmpDefList);
				    updateDataToFile();
				    System.out.print("!!! Add definition successfull! ");
				}
				else if (defOption == 2){
					tmpDefList.remove(defIndex - 1);
				    System.out.print(">> Type new definition : ");
				    String newDefi = inputWord.nextLine();
				    tmpDefList.add(newDefi);
				    hashMap.put(sWord, tmpDefList);
				    updateDataToFile();
				    System.out.print("!!! Update definition successfull! ");
				}
				else if (defOption == 3){
					if (tmpDefList.size()==1) {
				        System.out.println("!!! Can't delete this definition!");
				        break;
				    }
				    tmpDefList.remove(defIndex - 1);
				    hashMap.put(sWord, tmpDefList);
				    updateDataToFile();
				    System.out.print("!!! Delete definition successfull! ");
				}      	
				break;
			default:
				System.out.print("!!! No selection. Pls try again!");
				break;
        	}
	}
	
	public static void deleteSlangWord() {
		System.out.print(">> Type Slang word to remove: ");
        String sWord = inputWord.nextLine();
        if (hashMap.containsKey(sWord))
        {
            System.out.print(">> Confirm to delete:(Y/N)");
            String delFlg = inputWord.nextLine();
            if (delFlg.equals("Y") || delFlg.equals("y") ) {
            	hashMap.remove(sWord);
            	updateDataToFile();
			    System.out.println("!!! Delete Slang word successfull! ");
            }
        }
        else {
        	System.out.println("!!! Not found: " + sWord);
        }
	}
	
	public static void resetSlangWord() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(SLANG_WORD_INPUT_DEFAULT)));
			String line = null;
			while((line = br.readLine()) != null) {
	            if(line.contains("`")){
	                String[] str = line.split("`");
	                String[] split_str = str[1].split("\\|");
	                List<String> child = Arrays.asList(split_str);
	                hashMap.put(str[0], child);
	            }
	        }
		}
		catch (IOException e) {
        	System.out.println("!!! Error: Reset fail! \n");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                    System.out.println("!!! Reset Slang word complete!\n");
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
	}
	
	public static String randomSlangWord(){
        int pos = 0;
        Random rd = new Random();
        int randInt = rd.nextInt(hashMap.size());
        String rsl = "";
        for (String i: hashMap.keySet())
        {
            if (pos == randInt) {
                rsl = i;
                break;
            } else {
            	pos++;
            }
        }
        return rsl;
    }
	
	public static void printRandomSlangWord() {
		System.out.print(">> A Random Slang Word: ");
		String rdSWord = randomSlangWord();
	    System.out.print(rdSWord);
	    System.out.println();
	    System.out.println(">> Definitions: ");
	    List<String> defList = hashMap.get(rdSWord);
	    for(String def : defList) {
	    	System.out.println(" - " + def);
	    }
	}
	
	public static void quizFindDefinition() {
		Random rd = new Random();
        
		List<String> ansList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        List<String> defList = new ArrayList<>();
        
        String sWord1 = randomSlangWord();
        String sWord2 = randomSlangWord();
        String sWord3 = randomSlangWord();
        String sWord4 = randomSlangWord();
        
        tempList.add(sWord2);
        tempList.add(sWord3);
        tempList.add(sWord4);
        
        String sWordPicked = sWord1;
		List<String> defListPicked = hashMap.get(sWord1);
		String defPicked = defListPicked.get(rd.nextInt(defListPicked.size()));
		ansList.add(defPicked);
        
        for(String sWord: tempList) {
        	defList = hashMap.get(sWord);
        	String def = defList.get(rd.nextInt(defList.size()));
        	ansList.add(def);
        }
        
        System.out.println("Question: Find Definition of: " + sWordPicked);
        Collections.shuffle(ansList);
        
        for(int i = 0; i < ansList.size(); i++) {
        	System.out.println(i + " - " + ansList.get(i));
        }
        
        System.out.print(">> Your answer: ");
        int yourAns = inputWord.nextInt();
        System.out.println();
        if(ansList.get(yourAns).equals(defPicked) ) {
        	System.out.println("!!! Yeahh, Correct!");
        }
        else {
        	System.out.println("!!! Incorrect!");
        	System.out.println("!!! The right answer is: " + defPicked);
        }
	}
	
	public static void quizFindSlangWord() {
		Random rd = new Random();
		List<String> ansList = new ArrayList<>();
        
        String sWord1 = randomSlangWord();
        String sWord2 = randomSlangWord();
        String sWord3 = randomSlangWord();
        String sWord4 = randomSlangWord();
        
        ansList.add(sWord2);
        ansList.add(sWord3);
        ansList.add(sWord4);
        
        String sWordPicked = sWord1;
		List<String> defListPicked = hashMap.get(sWord1);
		String defPicked = defListPicked.get(rd.nextInt(defListPicked.size()));
		
		ansList.add(sWordPicked);
        
        System.out.println("Question > Find Slang word of: " + defPicked);
        Collections.shuffle(ansList);
        
        for(int i = 0; i < ansList.size(); i++) {
        	System.out.println(i + " - " + ansList.get(i));
        }
        
        System.out.print(">> Your answer: ");
        int yourAns = inputWord.nextInt();
        System.out.println();
        if(ansList.get(yourAns).equals(sWordPicked) ) {
        	System.out.println("!!! Yeahhh, Correct!");
        }
        else {
        	System.out.println("!!! Incorrect!");
        	System.out.println("!!! The right answer is: " + sWordPicked);
        }
	}
	
	public static void backupHistory() {
		BufferedWriter bw = null;
		FileWriter fw = null;
        try {
        	fw = new FileWriter(new File(SLANG_WORD_HISTORY));
   			bw = new BufferedWriter(fw);
   			for (String hisotry: historySlangWord)
            {
   				fw.write(hisotry + "\n");
            }
        }
        catch (IOException e) {
        	System.out.println("!!! Error! Back up failed!\n");
            e.printStackTrace();
        } finally {
            try {
                if (bw != null && fw != null) {
                    fw.close();
                	bw.close();
                    
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
	}
	
	public static void loadHistory() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(SLANG_WORD_HISTORY)));
			String line = null;
			while((line = br.readLine())!=null) {
				historySlangWord.add(line);
	        }
		}
		catch (IOException e) {
        	System.out.println("!!! Error: load history fail! \n");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
	}
	
	public static void clearHistory() {
		System.out.flush();
        historySlangWord.clear();
        System.out.println("!!! Clear history complete!");
	}
	
	public static void exitApp() {
		System.out.flush();
		updateDataToFile();
		backupHistory();
		System.exit(0);
	}
}