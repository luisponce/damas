

/**
 * 
 */
public enum Casilla {
   /**
    * EMPTY: casilla vacia
    */
    EMPTY (0),
    
    /**
     * BLACK: Casilla con ficha negra
     */
    BLACK (-1),
    
    /**
     * WHITE: Casilla con ficha blanca
     */
    WHITE (1), 
    
    /**
     * BLACK QUEEN: Casilla con ficha negra coronada
     */
    BLACKQUEEN (-2),
    
    /**
     * WHITE QUEEN: Casilla con ficha blanca coronada
     */
    WHITEQUEEN (2);
    
    private int val;

    private Casilla(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
    
    
}
