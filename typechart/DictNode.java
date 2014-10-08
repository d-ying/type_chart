package typechart;

/**
 * DictNode is the node that DictList uses to store the chain of TypeLists in
 * the dictionary.
 */
public class DictNode {
    
    TypeList entry;
    DictNode next;
    
    /**
     * Creates a new DictNode object, setting the entry of the object to the
     * inputed TypeList.
     * 
     * @param entry the input TypeList 
     */
    DictNode(TypeList entry) {
        this.entry = entry;
        next = null;
    } 
}
