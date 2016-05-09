package DataStructure.hw3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class counter {
    public static void main(String[] arg) throws IOException{
        try {
            Scanner file = new Scanner(new FileInputStream("F:\\totc.txt"));
            StringBuilder str = new StringBuilder();

            while (file.hasNext())
                str.append(file.nextLine()).append(" ");

            String[] str_array = str.toString().toLowerCase().split("[^a-zA-Z0-9']");
            Map<String, Integer> wordMap = new HashMap<>();

            for (String character : str_array)
                if (!character.equals(""))
                    if (wordMap.containsKey(character))
                        wordMap.put(character, wordMap.get(character) + 1);
                    else
                        wordMap.put(character, 1);

            for(String key : wordMap.keySet())
                if (wordMap.get(key) > 0)
                    System.out.println(key + ":\t" + wordMap.get(key));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


