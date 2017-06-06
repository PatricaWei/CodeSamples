
/**
 * Includes several activities that can be done with a set of dice,
 * such as rolling 3 of a kind, rolling N of a kind, rolling 3 unique
 * values, rolling N unique value, and frequencies of rolls.
 *
 * The activities demonstrate some basic random statistics for a die 
 * and for a set of dice.
 * 
 * @author  Patricia Wei 
 * @author  
 * 
 * @version 
 */
public class DiceStatistics
{

    /**
     * Creates a DieStatistics object that can be used to
     * run activities that demonstrate some statistics with dice.
     */
    public DiceStatistics()
    {
    }

    /**
     * Rolls n number of dice and returns their sum.
     * 
     * @param diceArr     an array with non-null Die objects
     * @return the sum of the rolls of the array of dice
     */
    public static int sumOfRoll(Die[] diceArr)
    {
        int sum = 0;
        for(int i=0; i<diceArr.length; i++)
        {
            sum += diceArr[i].roll();
        }
        return sum;
    }

    /**
     * Rolls a set of dice a given number of times, keeping
     * track of the frequencies of the sums of the rolls.
     * Then the method outputs their frequences.
     * 
     * @param diceArr           a non-empty array with non-null Die objects
     * @param numberOfTrials    number of trials that are going to be run
     * 
     */
    public static void getFrequencyOfSumsOfRolls(Die[] diceArr , 
    int numberOfTrials)
    {
        // Sums the sides from all the dice.
        int sumOfSides = 0;
        for(int i=0; i < diceArr.length; i++)
        {
            sumOfSides += (int) (diceArr[i].getSides());
        }
        System.out.println("Sum of sides " + sumOfSides);

        // Sets up the data structures to hold the "tally marks". 
        int [] frequency = new int [sumOfSides+1];
        //int rollResult;
        int sum=0;
        for(int i=0; i< numberOfTrials; i++)
        {
            sum=0;
            for(int j=0; j < diceArr.length; j++)
            {
                sum += diceArr[j].roll();
            }
            frequency[sum]++;
        }

        // Outputs the frequencies.
        System.out.println("Sum\tFrequency");
        for (int f = diceArr.length ; f < frequency.length ; f++)
        {
            System.out.println(f + "\t" + frequency[f]);
        }
        System.out.println("\n");
    }    

    /**
     * Rolls three dice until all rolls have the same value.
     * 
     * @param die1 a non-null Die
     * @param die2 a non-null Die
     * @param die3 a non-null Die
     * @return     the number of times three dice are rolled 
     *             before all three rolled values are equal
     */
    public static int rollThreeOfAKind(Die die1, Die die2 , Die die3)
    {
        int roll1 = die1.roll( );
        int roll2 = die2.roll( );
        int roll3 = die3.roll( );
        int counter = 1; 
        System.out.println("\n" + roll1 + "\t" + roll2 + 
            "\t" + roll3 + "\troll count = " + counter);

        while ( ! (roll1 == roll2 &&
            roll2 == roll3) )
        {
            roll1 = die1.roll();
            roll2 = die2.roll();
            roll3 = die3.roll();

            counter++;

            System.out.println(roll1 + "\t" + roll2 + "\t" + 
                roll3 + "\troll count = " + counter);
        }
        return counter;
    }

    /**
     * Rolls three dice until all are unique.
     * 
     * @param die1 a non-null Die
     * @param die2 a non-null Die
     * @param die3 a non-null Die
     * @return     the number of times three dice are rolled 
     *             before all three rolled values are unique
     */
    public static int rollThreeUnique(Die die1, Die die2 , Die die3 )
    {
        int roll1 = die1.roll( );
        int roll2 = die2.roll( );
        int roll3 = die3.roll( );
        int counter = 1;
        System.out.println(roll1 + "\t" + roll2 + "\t" + 
            roll3 + "\troll count = " + counter);
        while(roll1==roll2 || roll1==roll3 || roll2==roll3)
        {
            roll1 = die1.roll();
            roll2 = die2.roll();
            roll3 = die3.roll();

            counter ++;

            System.out.println(roll1 + "\t" + roll2 + "\t" + 
                roll3 + "\troll count = " + counter);
        }

        return counter;
    }

    /**
     * Rolls "N" dice until all the roll values are the same.
     *   
     * @param diceArray a non-empty array with non-null Die objects
     * 
     * @return the number of times N dice are rolled 
     *         before all N rolled values are the same
     */
    public static int rollNOfAKind(Die[ ] diceArray)
    {
        // Checks that the array has a length.
        if (diceArray == null)
            return 0;
        int number = diceArray.length;
        if (number == 0) 
            return 0;

        // Creates an array to hold the roll value of each die.
        int[ ] rollOfDice = new int [number];

        // Initialize a counter that keeps track of the rolls.
        int count = 0;

        // The same variable is a flag with the following meaning:
        //     once one die's value does not match one other's value,
        //     "same" becomes false; otherwise, it is true.
        // Note: same starts out as false.
        boolean same = false;
        boolean tester;
        while ( ! same )
        {
            count++;
            tester = true;
            // Stores and prints the rolled values in rollOfDice.
            for (int index = 0 ; index < diceArray.length ; index++)
            {
                rollOfDice[index] = diceArray[index].roll();
                System.out.print( "\t" + rollOfDice[index] );
            }
            System.out.println("\troll count = " + count);

            // Tests to see if all other rolled values 
            // are the same as "match".
            int match = rollOfDice[0];
            for(int i=1; i < rollOfDice.length; i++)
            {
                if(rollOfDice[i] != match)
                {
                    tester = false;
                }
            }

            if(tester)
            {
                same = true;
            }

        }
        return count;
    }

    /**
     * Rolls "N" dice until all the rolled values are unique.
     * 
     * @param diceArray   a non-empty array with non-null Die objects
     * @return     the number of times N dice are rolled 
     *             before all N rolled values are unique
     */
    public static int rollNUnique( Die[ ] diceArray )
    {
        if (diceArray == null)
            return 0;
        int number = diceArray.length;
        if (number == 0) 
            return 0;

        // Creates an array to hold the roll value of each die.
        int[ ] rollOfDice = new int [number];

        // Initialize a counter that keeps track of the rolls.
        int count = 1;
        // MAHHHHHLIESSSSSSHHHHHHHAAAAAAAAAAAAAAAAA
        // The same variable is a flag with the following meaning:
        //     once one die's value does not match one other's value,
        //     "same" becomes false; otherwise, it is true.
        // Note: same starts out as false.
        boolean same = true;
        boolean tester;

        while( same)
        {
            count++;
            tester = true;
            // Stores and prints the rolled values in rollOfDice.
            for (int index = 0 ; index < diceArray.length ; index++)
            {
                rollOfDice[index] = diceArray[index].roll();
                System.out.print( "\t" + rollOfDice[index] );
            }
            System.out.println("\troll count = " + count);

            int match;
            for(int i=0; i < rollOfDice.length; i++)
            {
                match = rollOfDice[i];
                for(int j=i+1; j < rollOfDice.length; j++)
                {
                    if(rollOfDice[j]==match)
                    {
                        tester = false;
                    }
                }

            }

            if(tester)
            {
                same = false;
            }
        }

        return count;
    }
}

