
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 * DiceMonster was created to test the Die and DiceStatistics classes.
 * 
 * @author Patricia Wei
 */
public class DiceMonster
{
    /**
     * The main method calls the Die and DiceStatistics classes.
     * It then checks each method in Die and some of DiceStatistics 
     * to see if they were completed correctly.
     */
    public static void main(String [] args)
    {
        out.println("Testing for the methods (named correctly)");
        try
        {
            Class<?> c = Class.forName("Die");

            Method[] allMethods = c.getDeclaredMethods();
            // search for the methods that are required

            List<String> names = new ArrayList<String>();
            for(Method m : allMethods)
            {
                names.add(m.getName());
            }
            if(!names.contains("getSides") || !names.contains("setSides") 
            || !names.contains("roll"))
            {
                throw new RuntimeException("Die - methods are named incorrectly");
            }
        }
        catch (ClassNotFoundException x) 
        {
            System.exit(1);
        }
        out.println("testing getSides");
        Die dice= new Die();
        if (dice.getSides() != 6)
        {
            throw new RuntimeException("getSides is dumb");
        }
        for(int i=-2; i<=1; i++)
        {
            Die x= new Die(i);
            if (x.getSides() == i)
            {
                throw new RuntimeException("getSides is dumb. You can't have a "
                    + i + " sided dice!");
            }
        }
        for (int i=2; i<1000; i++)
        {
            Die x = new Die(i);
            out.println("Your getSides: " + x.getSides() + "\t" + "Actual: " + i); 
            if (x.getSides() != i)
            {
                throw new RuntimeException("getSides is dumb");
            }
        }

        out.println("testing setSides");
        for(int i=-2; i<=1; i++)
        {
            Die x= new Die(i);
            if (x.getSides() == i)
            {
                throw new RuntimeException("setSides is dumb. You can't set a die to " + i);
            }
        }
        for (int i=2; i<1000; i++)
        {
            Die x = new Die();
            x.setSides(i);
            out.println("Your number of sides: " + x.getSides() + "\t" + "Actual: " + i); 
            if (x.getSides() != i)
            {
                throw new RuntimeException("setSides is dumb");
            }
        }

        Die y= new Die(100);
        out.println("testing roll");
        for (int i=0; i<1000; i++)
        {
            int rolled = y.roll();
            if ((rolled<=0) || (rolled>100))
                throw new RuntimeException("roll is dumb");
        }

        System.out.println("Die wins!");
        System.out.println("testing DiceStatistics");

        out.println("Testing for the methods (named correctly)");
        try
        {
            Class<?> c = Class.forName("DiceStatistics");

            Method[] allMethods = c.getDeclaredMethods();
            // search for the methods that are required

            List<String> names = new ArrayList<String>();
            for(Method m : allMethods)
            {
                names.add(m.getName());
            }
            if(!names.contains("sumOfRoll") || !names.contains("getFrequencyOfSumsOfRolls") 
            || !names.contains("rollThreeOfAKind")  || !names.contains("rollThreeUnique")
            || !names.contains("rollNOfAKind")  || !names.contains("rollNUnique"))
            {
                throw new RuntimeException("DiceStatistics - methods are named incorrectly");
            }
        }
        catch (ClassNotFoundException x) 
        {
            System.exit(1);
        }

        out.println("testing sumOfRoll");
        for (int i=0; i<1000; i++)
        {
            Die die1= new Die();
            Die die2= new Die();
            Die die3= new Die();
            Die[] diceArr = {die1, die2, die3};
            int sum = DiceStatistics.sumOfRoll(diceArr);
            if (sum > 18)
                throw new RuntimeException("sumOfRoll is dumb");
        }

        System.out.println("\nCheck off the rest with your partner!");
        System.out.println("Items to check: \n\tgetFrequencyOfSumsOfRolls, ");
        System.out.println("\trollThreeOfAKind,\n\trollThreeUnique, \n\t\rollNOfAKind, and \t\n\trollNUnique");
        System.out.println("Good luck.");
    }
}
