/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Pereira Carreira Samuel
 */
public class Game
{
   
    /**
    * Un attribut du type Room à visibilité privée
    */
    private Room aCurrentRoom;
    /**
    * Un attribut du type Parser à visibilité privée
    */
    private Parser aParser;
    
    /**
      * Un constructeur de la classe Game 
    */
    public Game()
    {
        this.aParser = new Parser(); 
        this.createRooms();
    }
    
    /**
    * La méthode play qui initialise le jeu
    */
    public void play()
    {
        boolean vFinished = false;
        
        printWelcome();
        while (vFinished == false)
        {
            Command vCommand = this.aParser.getCommand();
            vFinished = processCommand(vCommand);
        }
        System.out.println("Thank you for playing. Good bye.");
    }
    /**
    * La procédure look à visibilité privée
    * @param pWordDirection la direction désiré
    */
     private void look(final Command pWordDirection)
    {
        if (pWordDirection.hasSecondWord())
        {
            System.out.println("I don't know how to look at something in particular yet");
            return;
        }
        System.out.println(this.aCurrentRoom.getLongDescription());
    }
    /**
    * La procédure printLocationInfo à visibilité privée
    */
    private void printLocationInfo()
    {
        System.out.println(this.aCurrentRoom.getDescription());
    }
     /**
    * La procédure createRooms à visibilité privée
    * crée differentes pièces
    */
    private void createRooms()
    {
        Room vEntree = new Room(" in the main entrance of the Laboratory" );
        Room vLocalTecnique = new Room( " in a technical area" );
        Room vDoucheChimique = new Room(" in the chemical shower" );
        Room vLabo1 = new Room(" in a lab 1" );
        Room vLabo2 = new Room(" in a lab 2" );
        Room vBureau = new Room(" in a office room");
        Room vSalleCoufre = new Room(" in a safe room");
        
        this.aCurrentRoom = vEntree;
        
        vEntree.setExit("North",vLocalTecnique);
        vEntree.setExit("East",vDoucheChimique);
        
        vLocalTecnique.setExit("South", vEntree);
        
        vDoucheChimique.setExit("West", vEntree);
        vDoucheChimique.setExit("East", vLabo1);
        
        vLabo1.setExit("South", vLabo2);
        vLabo1.setExit("West", vDoucheChimique);
        
        vLabo2.setExit("North", vLabo1);
        vLabo2.setExit("East", vBureau);
        
        vBureau.setExit("West", vLabo2); 
        vBureau.setExit("Down", vSalleCoufre);
        
        vSalleCoufre.setExit("Up", vBureau);
        
    }
    
    /**
    * La procédure goRoom à visibilité privée
    * @param pWordDirection la direction désiré
    * sert à changer de pièce
    */
    private void goRoom(final Command pWordDirection)
    {
        if (!pWordDirection.hasSecondWord())
        {
            System.out.println("Go where ?");
            return;
        }
        
        Room vNextRoom = null;
        String vDirection = pWordDirection.getSecondWord();
        
        if (vDirection.equals("North"))
        {
            vNextRoom = this.aCurrentRoom.getExit("North");
        }
        
        else if(vDirection.equals("South"))
        {
            vNextRoom = this.aCurrentRoom.getExit("South");
        }
        else if(vDirection.equals("West"))
        {
            vNextRoom = this.aCurrentRoom.getExit("West");
        }
        else if(vDirection.equals("East"))
        {
            vNextRoom = this.aCurrentRoom.getExit("East");
        }
        else if(vDirection.equals("Down"))
        {
            vNextRoom = this.aCurrentRoom.getExit("Down");
        }
        else if(vDirection.equals("Up"))
        {
            vNextRoom = this.aCurrentRoom.getExit("Up");
        }
        else
        {
            System.out.println("Unknown direction !");
        }
        
        if (vNextRoom == null)
        {   
            System.out.println("There is no door !");
        }
        
        else
        {
           this.aCurrentRoom = vNextRoom; 
           printLocationInfo();
        }
    }
    /**
    * La procédure printWelcome à visibilité privée
    * affiche la description de la pièce
    */
    private void printWelcome()
    {
        
        String vString = "Welcome to the World of Zuul! \n"+
                          "World of Zuul is a new, incredibly boring adventure game. \n"+
                          "Type 'help' if you need help.";   
        System.out.println(vString);
        System.out.println(this.aCurrentRoom.getLongDescription());
        printLocationInfo();
    }
    /**
    * La procédure printHelp à visibilité privée
    * affiche les commandes disponibles à l'utilisateur
    */
    private void printHelp()
    {
        String vHelp = "Your command words are:\n";     
        System.out.println(vHelp);
        aParser.showCommands();
    }
       /**
    * La fonction quit à visibilité privée
    * @param pQuitCommand commande 
    * Sert à sortir du jeu
    */
    private boolean quit(final Command pQuitCommand)
    {
        if (pQuitCommand.hasSecondWord() == true)
        {
            System.out.println("Quit what ?");
            return false;
        }
        return true;
    }
    /**
    * La fonction processCommand à visibilité privée
    * @param pWordCommand commande 
    */
    private boolean processCommand(final Command pWordCommand)
    {
        if (pWordCommand.isUnknown())
        {
            System.out.println("I don't know what you mean...");
            return false;
        }
        else if ( pWordCommand.getCommandWord().equals("quit"))
        {
           return quit(pWordCommand);
        }
        else if ( pWordCommand.getCommandWord().equals("help"))
        {
            printHelp();
            return false;
        }
        else if ( pWordCommand.getCommandWord().equals("go"))
        {
            goRoom(pWordCommand);
            return false;
        }
        else if ( pWordCommand.getCommandWord().equals("look"))
        {
            look(pWordCommand);
            return false;
        }
        return false;
    }
} // Game
