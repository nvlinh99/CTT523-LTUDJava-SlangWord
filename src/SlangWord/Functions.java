package SlangWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
			System.out.println("Load file: Ok!\n");
		}
		catch (IOException e) {
        	System.out.println("LOI: Load file that bai! \n");
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
        historySlangWord.add(def);
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
}
