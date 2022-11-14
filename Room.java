import java.util.HashMap;
import java.util.Set;
/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Pereira Carreira Samuel
 */
public class Room
{
    
    private String aDescription; // attribut qui décrit le lieu 
    private HashMap<String, Room> aExits;
    /**
     * Constructeur de la classe Room
     * @param pDescription la descrption
     * Créer un nouveau lieu 
     */
    public Room(final String pDescription)
    {
      this.aDescription = pDescription;
      this.aExits = new HashMap<String, Room>();
    }
    
    /**
     * L'accesseur de l'attribut aDescription
     */    
    public String getDescription(){return this.aDescription;}
    
    public Room getExit(String pDirection)
    {
       return aExits.get(pDirection);
    }
    
    public String getExitString()
    {
        String vExits = "";
        
        for ( String vS : this.aExits.keySet() )
            vExits= vExits + " " + vS ;
        return vExits.toString();
        
    }
   
    /**
     * procédure qui crée les sorties d'une pièce
     */
    public void setExit(final String pDirection, final Room pNeighbor )
    {
       aExits.put(pDirection, pNeighbor);
    }
    /**
     * Fonction qui retourne la description de la pièce et les sorties possibles
     */
    
    public String getLongDescription()
    {
        return "You are" + this.aDescription + "\n Exits: " + getExitString();
    }
} // Room
