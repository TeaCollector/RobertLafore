package HashTable_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashTable {
    private DataItemHD[] hashArray;
    private int arraySize;
    private DataItemHD nonItem;

    public HashTable(int size) {
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
                System.out.print("*** ");
        }
        System.out.println("");
    }

    public DataItemHD find(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }

    public void insert(DataItemHD item) {
        int key = item.getKey();
        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            ++hashVal;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
    }

    public DataItemHD delete(int key) {
        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItemHD temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }
}

class DataItem {
    private int iData;

    public DataItem(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }
}

class HashTableApp {
    public static void main(String[] args) throws IOException {
        int aKey;
        DataItemHD iData;
        int size, n;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();

        HashTable theHashTable = new HashTable(size);

        for (int i = 0; i < n; i++) {
            aKey = (int) (Math.random() * 2 * size);
            iData = new DataItemHD(aKey);
            theHashTable.insert(iData);
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
                    theHashTable.insert(iData);
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