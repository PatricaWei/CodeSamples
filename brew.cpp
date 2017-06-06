#include <iostream>
using namespace std;

/*
 * Full Specs:
 https://fgamedia.org/faculty/anand/home/challenges/Halloween-2016-Challenge.v2.pdf
 * Find number of total possible brews, given 25 ingredients. Temperature of 
 * final brew is 25˚C.
 *
 * Exceptions for temperatures:
 * If the brew is at 8˚C and exactly 5 ingredients have been added, add 1 more
 * ingredient before raising the temperature to 9˚C.
 * If the brew is at 11˚C and exactly 3 ingredients have been added, add 2 more
 * ingredients before raising the temperature to 12˚C.
 * If the brew is at 15˚C and 14 ingredients have been added, do not add another
 * ingredient until bringing up the temperature to 17˚C.
 * If the brew is at 21˚C and 22 ingredients have been added, do not add another
 * ingredient until bringing up the temperature to 22˚C.
 *
 */
 
int main()
{
   int const MAX_TEMP = 26;
   int const MAX_INGREDIENTS = 26;

   // hold possible ways to add ingredients for each temperature
   long long brew[MAX_TEMP][MAX_INGREDIENTS];

   long mandatoryIngredients = 0; // how many extra ingredients you must add
                                // (for restrictions)

   // Initialize all elements of array to 0
   for(long i=0; i < MAX_TEMP; i++)
   {
      for(int j=0; j < MAX_INGREDIENTS; j++)
      {
         brew[i][j] = 0;
      }
   }

   // At temperature = 0, the number of ways to add each ingredient is 1
   for(long i=0; i <= MAX_INGREDIENTS; i++)
   {
      brew[0][i] = 1;
   }

   for(int currentTemp = 0; currentTemp < MAX_TEMP-1; currentTemp++)
   {
      for(int currentIngredients = 0; currentIngredients <= MAX_TEMP-1;
          currentIngredients++)
      {
         // DEAL WITH RESTRICTIONS
         if(currentTemp==15 && currentIngredients==14)
         {
            brew[17][14] += brew[15][14];
            continue;
         }
         if(currentTemp==21 && currentIngredients==22)
         {
            brew[22][22] += brew[21][22];
            continue;
         }

         mandatoryIngredients = 0;

         if(currentTemp==8 && currentIngredients==5)
            mandatoryIngredients = 1; // must add 1 ingredient to brew
         if(currentTemp==11 && currentIngredients==3)
            mandatoryIngredients = 2; // must add 2 ingredients to brew

         for(long targetIngredients = currentIngredients + mandatoryIngredients;
             targetIngredients <= MAX_INGREDIENTS-1; targetIngredients++)
         {
            brew[currentTemp+1][targetIngredients] +=
            brew[currentTemp][currentIngredients];
         }
      }
   }

   cout << "Number of ways: " << endl;
   cout << brew[MAX_TEMP-1][MAX_INGREDIENTS-1] << endl << endl;
}
