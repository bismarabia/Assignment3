package DataStructure.hw3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class counter {
    public static void main(String[] arg) throws IOException{

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a threshold: ");
        int thresHold = sc.nextInt();

        try {
            Scanner file = new Scanner(new FileInputStream("src/DataStructure/Hw3/totc.txt"));
            StringBuffer str = new StringBuffer();

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
                if (wordMap.get(key) > thresHold)
                    System.out.println(key + ":\t" + wordMap.get(key));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


