package typechart;

/**
 * TypeNode is a node that TypeList uses to store the vsType and its multiplier
 * against the type in the type field of TypeList.
 */
public class TypeNode {
    
    private String type;
    private float multiplier;
    private final float multiplierBackup;
    TypeNode next;
    
    /**
     * Creates a new TypeNode with the type and backup type set to the input 
     * type and the multiplier and backup multiplier set to the input 
     * multiplier.
     * 
     * @param type the String input type
     * @param multiplier the input multiplier 
     */
    TypeNode(String type, float multiplier) {
        this.type = type;
        this.multiplier = multiplier;
        multiplierBackup = multiplier;
        next = null;
    }
    
    /**
     * Takes in a float multiplier and multiplies the multiplier field by that
     * input.
     * 
     * @param multiplier2 the input multiplier 
     */
    public void modMultiplier(float multiplier2) {
        multiplier = multiplier * multiplier2;
    }
    
    /**
     * Returns the type that this node stores the multiplier for.
     * 
     * @return the type stored in this node's type field. 
     */
    public String type() {
        return type;
    }
    
    /**
     * Returns the multiplier of this nodes type against the TypeList's type.
     * 
     * @return the multiplier stored in this node's multiplier field 
     */
    public float multiplier() {
        return multiplier;
    }
    
    /**
     * Restores the multiplier field using the value from the backupMultiplier
     * field.
     */
    public void restore() {
        multiplier = multiplierBackup;  
    } 
}
