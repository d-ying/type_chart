package typechart;

/**
 * TypeList stores the multipliers of all types against the type stored in
 * the type field in TypeNodes.
 */
public class TypeList {
    
    private TypeNode head;
    private final String type;
    
    /**
     * Creates a new TypeList with head set to a new TypeNode containing the
     * name of the vsType and its multiplier against the type in the input
     * type which is then stored in the type field.
     * 
     * @param type the type that all multipliers will be used against
     * @param vsType the current type that is being compared
     * @param multiplier the damage multiplier of the currently compared type  
     */
    TypeList(String type, String vsType, float multiplier) {
        head = new TypeNode(vsType, multiplier);
        this.type = type;
    }
    
    /**
     * Adds another TypeNode containing another type to compare to the TypeList
     * and sets that as the head, setting the old head to the next of the new
     * head.
     * 
     * @param vsType the current type that is being compared
     * @param multiplier the damage multiplier of the currently compared type
     */
    public void addType(String vsType, float multiplier) {
        TypeNode oldHead = head;
        head = new TypeNode(vsType, multiplier);
        head.next = oldHead;
    }
    
    /**
     * Takes in a type and matches it to one already in the TypeList, modifying
     * the damage multiplier of that type by the damage multiplier of the second
     * inputed type.
     * 
     * @param vsType the String name of the vsType to modify
     * @param multiplier2 the damage multiplier of the vsType on the second type 
     */
    public void modType(String vsType, float multiplier2) {
        TypeNode current = head;
        while (current != null) {
            if (vsType.equals(current.type())) {
                current.modMultiplier(multiplier2);
                break;
            }
            current = current.next;
        }
    }
    
    /**
     * Returns the type that the TypeList stores the multipliers for.
     * 
     * @return a String of the type that the TypeList stores multipliers for 
     */
    public String type() {
        return type;
    }
    
    /**
     * Returns the current head of the TypeList.
     * 
     * @return the head of the TypeList 
     */
    public TypeNode head() {
        return head;
    }
    
    /**
     * Restores the multipliers of the all the other types against the type in
     * in the field type.
     */
    public void restore() {
        TypeNode current = head;
        while (current != null) {
            current.restore();
            current = current.next;
        }
    }
}
