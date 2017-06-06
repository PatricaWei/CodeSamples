import java.util.Scanner;

/**
 * Tests DiceStatistics and Die classes.
 * 
 * @author Patricia Wei
 * @author 
 * 
 * @version 
 */
public class DiceTester
{
    /**
     * Creates a DieTester object.
     */
    public DiceTester()
    {
    }

    /**
     * Prints the menu. Expand as needed. 
     */
    public void printMenu( )
    {
        System.out.println("\nChoose from the following options:");
        System.out.println("\t1\tRoll a die.");
        System.out.println("\t2\tSum the rolls of a set of dice.");
        System.out.println("\t3\tFrequency of rolls with a set of dice.");
        System.out.println("\t4\tPlay Three of a Kind.");
        System.out.println("\t5\tPlay Unique Three.");
        System.out.println("\t6\tPlay N of a Kind.");
        System.out.println("\t7\tPlay Unique N Dice.");
        System.out.println("\t99\tQuit.");
    }

    /**
     * Reads the user selection.
     * 
     * @return  the number that the user typed in
     */
    public int readUserChoice( )
    {
        Scanner in = new Scanner (System.in);
        int number = in.nextInt( );
        return number;
    }

    /**
     * Asks the user how many sides a die is to have.  
     * 
     * @return  the number of sides.
     */
    public int selectSideValue( )
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input the number of sides for the die: ");
        int sides = in.nextInt();
        return sides;
    }

    /**
     * Asks the user how many dice to roll.  
     * 
     * @return  the number of dice.
     */
    public int selectNumberOfDice( )
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input the number of dice: ");
        int num = in.nextInt( );
        if (num < 1)
        {
            System.out.println("Type in a number greater than 0. " +
                "Number has been set to 1.");
            num = 1;
        }
        return num;
    }

    /**
     * Asks the user how many trials are desired.
     * 
     * @return  the number of trials.
     */
    public int selectNumberOfTrials()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of trials you want: ");
        int numTrials = in.nextInt();
        return numTrials;
    }

    /**
     * Creates an array of Dice.
     * 
     * @return an array of Dice based upon user input
     */
    public Die[] createArrayOfDice()
    {
        int x = selectNumberOfDice();
        Die[] diceArray = new Die[x];
        for(int index=0; index < diceArray.length; index++)
        {
            diceArray[index] = new Die( selectSideValue() );
        }
        return diceArray;
    }

    /**
     * Routes the user's choice to a particular task and 
     * outputs the results.
     * 
     * @param num   the number input by the user
     */
    public void routeChoice ( int num )
    {
        if (num == 1)
        {
            int s = selectSideValue( );
            Die d = new Die(s);
            int x = d.roll();
            System.out.println("The roll is " + x);
        }
        else if (num == 2)
        {
            Die[] dice = createArrayOfDice( );
            int x = DiceStatistics.sumOfRoll( dice );
            System.out.println("The sum of roll of " +
                dice.length +
                " is " + x);
        }
        else if (num == 3)
        {
            int trialCount = selectNumberOfTrials( );
            Die[] diceArray = createArrayOfDice( );
            DiceStatistics.getFrequencyOfSumsOfRolls(diceArray,trialCount);
        }
        else if (num == 4)
        {
            int side1 = selectSideValue( ) ;
            int side2 = selectSideValue( ) ;
            int side3 = selectSideValue( ) ;
            Die die1 = new Die(side1);
            Die die2 = new Die(side2);
            Die die3 = new Die(side3);
            int t = DiceStatistics.rollThreeOfAKind(die1,die2,die3);
            System.out.println("It takes " + t + 
                " rolls to get three of a kind.");           
        }
        else if (num == 5)
        {
            int side1 = selectSideValue( ) ;
            int side2 = selectSideValue( ) ;
            int side3 = selectSideValue( ) ; 
            Die die1 = new Die(side1);
            Die die2 = new Die(side2);
            Die die3 = new Die(side3);

            int t = DiceStatistics.rollThreeUnique(die1,die2,die3);
            System.out.println("It takes " + t + 
                " rolls to get three unique numbers");
        }
        else if (num == 6)
        {
            Die[ ] diceArray = createArrayOfDice( );
            int t = DiceStatistics.rollNOfAKind( diceArray );
            System.out.println("It takes " + t + 
                " rolls to get " + diceArray.length + 
                " of a kind.");           
        }
        else if (num == 7)
        {
            Die[ ] diceArray = createArrayOfDice( );
            int t = DiceStatistics.rollNUnique( diceArray );
            System.out.println("It takes " + t + 
                " rolls to get " + diceArray.length + 
                " unique rolls.");
        }
    }

    /**
     * Oversees the testing of the Die and DiceStatistics 
     * methods.
     * 
     * @param args  arguments from the command line.
     */
    public static void main(String [ ] args)
    {
        DiceTester bob = new DiceTester( );
        int userPick = 0;
        while ( userPick != 99)
        {
            bob.printMenu( );
            userPick = bob.readUserChoice( );
            bob.routeChoice ( userPick );
        }
    }
}
