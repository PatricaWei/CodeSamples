
/**
 * A Die object may have its numbers of sides specified
 * and may be rolled to simulate rolling a real die. 
 * 
 * @author  Patricia Wei
 * @author
 * 
 * @version 
 */
public class Die
{
    /**
     * number of sides for this die
     */
    private int sides;

    /**
     * Creates a Die object with six sides.
     */
    public Die()
    {
        sides = 6;
    }

    /**
     * Creates a Die object with the number of sides
     * specified as a parameter.
     * 
     * @param s     number of sides of this die
     */
    public Die(int s)
    {
        sides=s;
    }

    /**
     * Retrieve the number of sides.
     *
     * @return      number of sides of this die
     */
    public int getSides()
    {
        if(sides>1)
            return sides;

        return 2;
    }

    /**
     * Sets the number of sides of this die.
     * 
     * @param s     the new number of sides for this die
     */
    public void setSides(int s)
    {
        sides=s;
    }

    /**
     * Simulates the rolling of a die.
     * 
     * @return number between 1 and sides, inclusively
     */
    public int roll()
    {
        return 1 + (int)(Math.random()*sides);
    }
}
