package typechart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * TypeTable contains the dictionary that stores all the type data and has
 * methods that print out the weaknesses and strengths of a type given an input
 * type.
 */
public class TypeTable {
    
    DictList[] typeTable = null;
    int numTypes = 0;
    int size = 0;
    
    /**
     * Given an integer, nextPrime finds the smallest prime that is greater than
     * the integer.
     * 
     * @param input the input integer
     * @return an integer of the smallest prime greater than the input.
     */
    private int nextPrime(int input) {
        boolean isPrime = false;
        int testPrime = input;
        while (!isPrime) {
            if (testPrime % 2 == 0 || testPrime % 3 == 0) {
                testPrime++;
            } else {
                for (int i = 5; i <= Math.sqrt(testPrime); i += 6) {
                    if (testPrime % i == 0 || testPrime % i + 2 == 0) {
                        isPrime = false;
                        testPrime++;
                        break;
                    }
                    isPrime = true;
                }
            }
        }
        
        return testPrime;
    }
    
    /**
     * Takes in a String name of a file and reads the contents of the file,
     * putting the data into dictionary.
     * 
     * @param tableFile String name of the input file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    TypeTable(String tableFile) throws FileNotFoundException, IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(tableFile));
        String row;
        String[] columns = null;
        String[] currentRow;
        boolean firstRow = true;
        boolean secondRow = true;
        
        while ((row = br.readLine()) != null) {
            if (firstRow) {
                columns = row.split(",");
                numTypes = columns.length;
                size = nextPrime(numTypes * 7);
                typeTable = new DictList[size];
                firstRow = false;
            } else {
                currentRow = row.split(",");
                String vsType = null;
                for (int i = 0; i < numTypes; i++) {                    
                    if (i == 0) {
                        vsType = currentRow[i];
                    } else {
                        float multiplier = Float.parseFloat(currentRow[i]);
                        int key = Math.abs(columns[i].hashCode() % size);
                        if (secondRow) {
                            if (typeTable[key] == null) {
                                typeTable[key] = new DictList(new TypeList(columns[i], vsType, multiplier));
                            } else {
                                typeTable[key].addNode(new TypeList(columns[i], vsType, multiplier));
                            }
                        } else {
                            if (typeTable[key].size == 1) {
                                typeTable[key].head.entry.addType(vsType, multiplier);
                            } else {
                                DictNode current = typeTable[key].head;
                                while (current != null) {
                                    if (current.entry.type().equals(columns[i])) {
                                        current.entry.addType(vsType, multiplier);
                                        break;
                                    }
                                    current = current.next;
                                }
                            }
                        }
                    }
                }
                secondRow = false;
            }
        }
        br.close();
    }
    
    /**
     * Takes in either one or two String types (if only one type then type2 is
     * "none") and finds the location of the type(s) in the dictionary. It calls
     * printString to print out the results.
     * 
     * @param type1 the first String input type
     * @param type2 the second String input type ("none" if only one type)
     */
    public void find(String type1, String type2) {
        int key1 = Math.abs(type1.hashCode() % size);
        int key2 = Math.abs(type2.hashCode() % size);
        DictNode list1 = null;
        DictNode list2 = null;
        
        if (typeTable[key1].size == 1) {
            list1 = typeTable[key1].head;
        } else {
            DictNode current = typeTable[key1].head;
            while (current != null) {
                if (current.entry.type().equals(type1)) {
                    list1 = current;
                }
                current = current.next;
            }
        }
        if (!type2.equals("none")) {
            if (typeTable[key2].size == 1) {
                list2 = typeTable[key2].head;
            } else {
                DictNode current = typeTable[key2].head;
                while (current != null) {
                    if (current.entry.type().equals(type2)) {
                        list2 = current;
                    }
                    current = current.next;
                }
            }
            
            printString(list1.entry, list2.entry);
            return;
        }
        printString(list1.entry, null);
    }
    
    /**
     * Given either one or two TypeLists (if only one type, list2 is null), 
     * printString walks through the list(s) and only print out the strengths/
     * weakness of the given type(s).
     * 
     * @param list1 the first input TypeList
     * @param list2 the second input TypeList (null if only one type)
     */
    private void printString(TypeList list1, TypeList list2) {
        
        String inputedType = list1.type();
        
        if (list2 != null) {
           inputedType = inputedType + "/" + list2.type();
           TypeNode current2 = list2.head();
           while (current2 != null) {
               list1.modType(current2.type(), current2.multiplier());
               current2 = current2.next;
           }
        }
        
        System.out.println("Inputed type(s): " + inputedType);
        System.out.println("Weak against:");
        
        TypeNode current = list1.head();
        
        while(current != null) {
            if (current.multiplier() > 1) {
                System.out.println(current.type() + " - " + current.multiplier() + "x damage against " + inputedType);
            }
            current.restore();
            current = current.next;
        }
        
        System.out.println("Resistant to:");
        
        current = list1.head();
        
        while(current != null) {
            if (current.multiplier() < 1) {
                if (current.multiplier() == 0) {
                    System.out.println(inputedType + " is immune against " + current.type());
                } else {
                    System.out.println(current.type() + " - " + current.multiplier() + "x damage against " + inputedType);
                }
            }
            current = current.next;
        }
        
        System.out.println("");
    } 
}
