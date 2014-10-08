package typechart;

/**
 * DictList is the object that the dictionary in TypeList stores in each cell.
 */
public class DictList {

    DictNode head;
    int size;
    
    /**
     * Creates a new DictList object with the head set to a new DictNode 
     * containing the input TypeList.
     * 
     * @param list the input TypeList 
     */
    DictList(TypeList list) {
        head = new DictNode(list);
        size = 1;
    }
    
    /**
     * Adds a new DictNode with its entry as the input TypeList to the DictList 
     * and sets the head to the new node while making the old head the next 
     * node.
     * 
     * @param list the input TypeList 
     */
    public void addNode (TypeList list) {
        DictNode oldHead = head;
        head = new DictNode(list);
        head.next = oldHead;
        size++;
    }
}
