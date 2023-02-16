package HashTable_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashDouble {
    private DataItemHD[] hashArray;
    private int arraySize;
    private DataItemHD nonItem;

    public HashDouble(int size) {
        arraySize = size;
        hashArray = new DataItemHD[arraySize];
        nonItem = new DataItemHD(-1);
    }

    public void display() {
        System.out.print("Table: ");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null)
                System.out.print(hashArray[i].getKey() + " ");
            else
                System.out.println("***");
        }
        System.out.println("");
    }

    public DataItemHD find(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }

    public void insert(int key, DataItemHD item) {
        int hashVal = hashFunc(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
    }

    public DataItemHD delete(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFunc2(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItemHD temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public int hashFunc2(int key) {
        return 5 - (key % 5);
    }
}

class DataItemHD {
    private int iData;

    public DataItemHD(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }
}

class HashDoubleApp {
    public static void main(String[] args) throws IOException {
        int aKey;
        DataItemHD iData;
        int size, n;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();

        HashDouble theHashTable = new HashDouble(size);

        for (int i = 0; i < n; i++) {
            aKey = (int) (Math.random() * 2 * size);
            iData = new DataItemHD(aKey);
            theHashTable.insert(aKey, iData);
        }
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.display();
                    break;
                case 'i':
                    System.out.print("Enter the key value to insert: ");
                    aKey = getInt();
                    iData = new DataItemHD(aKey);
                    theHashTable.insert(aKey, iData);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    iData = theHashTable.find(aKey);
                    if (iData != null) System.out.println("Found " + aKey);
                    else System.out.println("Could not find " + aKey);
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
