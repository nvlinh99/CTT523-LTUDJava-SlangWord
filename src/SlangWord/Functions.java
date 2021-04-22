package SlangWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author LinhNV
 *
*/

public class Functions {
	private static final String SLANG_WORD_INPUT = "./Data/slang.txt";
	public static HashMap<String,List<String>> hashMap = new HashMap<String,List<String>>();
	
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
}
