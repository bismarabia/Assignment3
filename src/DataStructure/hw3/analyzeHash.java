package DataStructure.hw3;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class analyzeHash{

    private static String[] array1;
    private static String[] array2;
    private static int collisionCounter = 0;

    public static void main(String[] ar){

        linearProbing();
        QuadraticProbing();

    }

    public static int findPosLinear(Object x) {
        int currentPos = ( x == null ) ? 0 : Math.abs( x.hashCode( ) % array1.length );
        while( array1[ currentPos ] != null ) {
            if( x == null ){
                if( array1[ currentPos ] == null )
                    break;
            }
            currentPos++;
            currentPos = (currentPos) % array1.length;
            collisionCounter++;
        }
        array1[ currentPos ] = (String) x;
        return currentPos;
    }
    private static void linearProbing(){
        try {
            Scanner file = new Scanner(new FileInputStream("src/DataStructure/Hw3/Q3_input.txt"));
            StringBuilder str = new StringBuilder();
            while (file.hasNext())
                str.append(file.nextLine()).append(" ");

            String[] str_array = str.toString().toLowerCase().split("[^a-zA-Z0-9']");
            Hashtable<String, Integer> hashTable = new Hashtable<>();
            array1 = new String[str_array.length];

            System.out.println("size of "+str_array.length);
            for (String string : str_array){
                hashTable.put(string, findPosLinear(string));
            }

            for(String key : hashTable.keySet())
                System.out.println("hashTable Key = "+key);

            System.out.println("The number of collision in linear Probing = "+collisionCounter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int findPosQuadratic(Object x) {
        int currentPos = ( x == null ) ? 0 : Math.abs( x.hashCode( ) % array1.length );
        int offset = 1;
        while( array2[ currentPos ] != null ) {

            if( x == null )
            {
                if( array2[ currentPos ] == null )
                    break;
            }

            currentPos = (currentPos + offset) % array2.length;
            offset+=2;
            collisionCounter++;
        }
        array2[ currentPos ] = (String) x;
        return currentPos;
    }
    private static void QuadraticProbing(){
        try {
            Scanner file = new Scanner(new FileInputStream("src/DataStructure/Hw3/Q3_input.txt"));
            StringBuilder str = new StringBuilder();
            while (file.hasNext())
                str.append(file.nextLine()).append(" ");

            String[] str_array = str.toString().toLowerCase().split("[^a-zA-Z0-9']");
            Hashtable<String, Integer> hashTable = new Hashtable<>();
            array2 = new String[str_array.length];
            System.out.println("size of "+str_array.length);
            for (String string : str_array){
                hashTable.put(string, findPosQuadratic(string));
            }

            for(String key : hashTable.keySet())
                System.out.println("hashTable Key = "+key);

            System.out.println("The number of collision in Quadratic Probing = "+collisionCounter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
