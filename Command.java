 

/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author votre nom
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     *   Le constructeur naturel de la classe Command
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }
    
    public String getCommandWord(){return this.aCommandWord;}
    public String getSecondWord(){return this.aSecondWord;}
    
    public boolean  hasSecondWord(){
        return this.aSecondWord != null;
    }
    
    public boolean isUnknown(){
        return this.aCommandWord == null;
    }
}// Command
