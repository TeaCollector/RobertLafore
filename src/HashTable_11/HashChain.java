package HashTable_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashChain {
    public static void main(String[] args) throws IOException {
        int aKey;
        Link aDataItem;
        int size, n, keyPerCell = 100;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();

        HashTableSortList htsl = new HashTableSortList(size);

        for (int i = 0; i < n; i++) {
            aKey = (int) (Math.random() * keyPerCell * size);
            aDataItem = new Link(aKey);
            htsl.insert(aDataItem);
        }
        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    htsl.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter the key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Link(aKey);
                    htsl.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    htsl.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = htsl.find(aKey);
                    if (aDataItem != null) System.out.println("Found " + aKey);
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

class Link {
    public Link next;
    private int iData;

    public Link(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }

    public void displayLink() {
        System.out.print(iData + " ");
    }
}

class SortedList {
    private Link first;

    public void SortedList() {
        first = null;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        Link previous = null;           // Начиная с первого элемента
        Link current = first;           // Начиная с первого элемента

        while (current != null && key > current.getKey()) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = theLink;            // first --> на новый элемент
        } else {
            previous.next = theLink;
        }
        theLink.next = current;
    }

    public void delete(int key) {
        Link previous = null;
        Link current = first;
        while (current != null && key != current.getKey()) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = first.next;                     // Если удаляемый элемент это первый
        } else {
            previous.next = current.next;
        }
    }

    public Link find(int key) {
        Link current = first;

        while (current != null && current.getKey() <= key) {
            if (current.getKey() == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void displayList() {
        Link current = first;

        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}

class HashTableSortList {
    private SortedList[] hashSLArr;
    private int arraySize;

    public HashTableSortList(int arraySize) {
        this.arraySize = arraySize;
        hashSLArr = new SortedList[arraySize];
        for (int i = 0; i < arraySize; i++) {
            hashSLArr[i] = new SortedList();
        }
    }

    public void displayTable() {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + ". ");
            hashSLArr[i].displayList();
        }
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key);
        hashSLArr[hashVal].insert(theLink);
    }

    public void delete(int key) {
        int hashVal = hashFunc(key);
        hashSLArr[hashVal].delete(key);
    }

    public Link find(int key) {
        int hashVal = hashFunc(key);
        Link theLink = hashSLArr[hashVal].find(key);
        return theLink;
    }
}
