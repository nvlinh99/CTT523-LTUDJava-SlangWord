package SlangWord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author LinhNV
 *
*/

public class Functions {
	private static final String SLANG_WORD_INPUT = "./Data/slang.txt";
	private static final String SLANG_WORD_INPUT_DEFAULT = "./Data/default_slang.txt";
	public static HashMap<String,List<String>> hashMap = new HashMap<String,List<String>>();
	public static List<String> historySlangWord=new ArrayList<>();
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
        	System.out.println("Error: load data fail! \n");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                    System.out.println("Load complete!\n");
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
        List<String> def = hashMap.get(sWord);
        historySlangWord.add(sWord);
        if (def == null) {
        	System.out.println("Can't find: " + sWord);
        }
        else {
        	System.out.println("=> Meaning: " + def);
        }
	}
	
	public static void searchByDefinition() {
		System.out.println(">> Type to find Slang word: ");
		String def = inputWord.nextLine();
        List<String> sWords = new ArrayList<>();
        for (String i: hashMap.keySet())
        {
            if (hashMap.get(i).contains(def)){
            	sWords.add(i);
            }
        }
        if(sWords.isEmpty()) {
        	System.out.println("No Slang Word found!");
        }
        else {
	        System.out.println(">> List Slang Word <<");
	        for (String sWord: sWords) {
	        	System.out.println(sWord);
	        }
	        System.out.println("\n");
        }
	}
	
	public static void showSearchHistory() {
		if(historySlangWord.isEmpty()) {
			System.out.println(">> NO DATA!");
		}
		else {
			System.out.println(">> Search History: ");
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
   			for (String i: hashMap.keySet())
            {
                fw.write(i + "`");
                List<String> data = hashMap.get(i);
                for (int t = 0; t < data.size(); t++)
                {
                    fw.write(data.get(t));
                    if (t+1< data.size()) {
                    	fw.write("|");
                    }
                }
                fw.write("\n");
            }
        }
        catch (IOException e) {
        	System.out.println(">> LOI: Update file that bai! \n");
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
		System.out.println("What is your new Slang Word: ");
        String newSWord = inputWord.nextLine();
        newSWord = newSWord.toUpperCase();
        System.out.println("What is the definition: ");
        String newDef = inputWord.nextLine();
        List<String> defList = new ArrayList<>();
        defList.add(newDef);
        if(hashMap.containsKey(newSWord)) {
        	System.out.println(">> Slang word already exists!");
        	System.out.println(">> Overwrite? (Y/N): ");
            String override = inputWord.nextLine();
            if (override.equals("Y") || override.equals("y")) {
            	hashMap.put(newSWord, defList);
            	updateDataToFile();
            }
            else
            {
                List<String> duplicateList = hashMap.get(newSWord);
                for (String Sword: duplicateList)
                {
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
            System.out.println("Add New Slang Word Successfully");
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
			System.out.println("This Slang word dont have any definition!");
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
	        		System.out.print("Notice: Update Slang word successfull! ");
	        	}
	        	else {
	        		System.out.println("Error: New Slang word must be different old Slang word.");
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
		        System.out.println("|2. Delelte definition.");
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
				    System.out.print("Notice: Add definition successfull! ");
				}
				else if (defOption == 2){
					tmpDefList.remove(defIndex - 1);
				    System.out.print(">> Type new definition : ");
				    String newDefi = inputWord.nextLine();
				    tmpDefList.add(newDefi);
				    hashMap.put(sWord, tmpDefList);
				    updateDataToFile();
				    System.out.print("Notice: Update definition successfull! ");
				}
				else if (defOption == 3){
					if (tmpDefList.size()==1) {
				        System.out.println("Can't delete this definition!");
				        break;
				    }
				    tmpDefList.remove(defIndex - 1);
				    hashMap.put(sWord, tmpDefList);
				    updateDataToFile();
				    System.out.print("Notice: Delete definition successfull! ");
				}      	
				break;
        	}
	}
	
	public static void deleteSlangWord() {
		System.out.print(">> Type Slang word to remove: ");
        String sWord = inputWord.nextLine();
        if (hashMap.containsKey(sWord))
        {
            System.out.println("Confirm to delete:(Y/N)");
            String delFlg = inputWord.nextLine();
            if (delFlg.equals("Y") || delFlg.equals("y") ) {
            	hashMap.remove(sWord);
            	updateDataToFile();
			    System.out.print("Notice: Delete Slang word successfull! ");
            }
        }
        else {
        	System.out.print("Not found: " + sWord);
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
        	System.out.println("Error: Reset fail! \n");
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                    System.out.println("Reset Slang word complete!\n");
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
	}
}