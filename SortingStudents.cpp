#include <string>
#include <iostream>
#include <sstream>
using namespace std;

class Student
{
private:
   string lastName;
   string firstName;
   long totalPoints;
   static int sortKey;

public:
   static const string DEFAULT_NAME;
   static const int DEFAULT_POINTS = 0;
   static const int MAX_POINTS = 1000;
   static const int SORT_BY_FIRST = 88;
   static const int SORT_BY_LAST = 98;
   static const int SORT_BY_POINTS = 108;
   static const int DEFAULT_SORT_KEY = SORT_BY_LAST;

public:
   Student(string lst = DEFAULT_NAME, string fst = DEFAULT_NAME,
           long pts = DEFAULT_POINTS);

   string getLastName() { return lastName; }
   string getFirstName() { return firstName; }
   long getTotalPoints()  { return totalPoints; }
   static int getSortKey() { return sortKey; }

   bool setLastName(string last);
   bool setFirstName(string first);
   bool setPoints(long pts);
   static bool setSortKey(int key);
   string toString();

   static int compareTwoStudents(Student firstStud, Student secondStud);

private:
   static bool validString(string testStr);
   static bool validPoints(long testPoints);
};

class StudentArrayUtilities
{
public:
   static string toString(string title, Student data[], int arraySize);
   static void arraySort(Student array[], int arraySize);
   static double getMedianDestructive(Student array[], int arraySize);

private:
   static bool floatLargestToTop(Student data[], int top);
   static void mySwap(Student &a, Student &b);
   static bool isEven(long num);
};

const string Student::DEFAULT_NAME = "zz-error";
int Student::sortKey = Student::DEFAULT_SORT_KEY;

int main()
{
   Student group0[0];
   int group0ArraySize = 0;

   Student group1[] = {Student("atticus","finch",100)};
   int group1ArraySize = int(sizeof(group1)) / sizeof(group1[0]);

   Student group2[] =
   {
      Student("atticus","finch",100),
      Student("scout","finch",50),
      Student("jem","finch",65),
      Student("boo","radley",88),
      Student("maudie","atkinson",47),
      Student("guy","montag",32),
      Student("romeo","montague",16),
      Student("juliet","capulet",15),
      Student("scarlett","o'hara",73),
      Student("willy","wonka",552),
      Student("elie","wiesel",100),
      Student("harper","lee",100),
      Student("ray","bradbury", 94),
      Student("julia","alvarez",82),
      Student("roald","dahl",77)
   };
   int group2ArraySize = int(sizeof(group2)) / sizeof(group2[0]);

   Student group3[] =
   {
      Student("atticus","finch",100),
      Student("scout","finch",50),
      Student("jem","finch",65),
      Student("boo","radley",88),
      Student("maudie","atkinson",47),
      Student("guy","montag",32),
      Student("romeo","montague",16),
      Student("juliet","capulet",15),
      Student("scarlett","o'hara",73),
      Student("willy","wonka",552),
      Student("elie","wiesel",100),
      Student("harper","lee",100),
      Student("ray","bradbury", 94),
      Student("julia","alvarez",82),
      Student("roald","dahl",77),
      Student("john","green",70)
   };
   int group3ArraySize = int(sizeof(group3)) / sizeof(group3[0]);

   cout << "Before: " << endl;
   for(int i=0; i < group3ArraySize; i++)
   {
      cout << group3[i].toString();
   }
   cout << endl;

   StudentArrayUtilities::arraySort(group3,group3ArraySize);
   cout << StudentArrayUtilities::toString("\nAfter default sort:", group3,
                                           group3ArraySize);

   Student::setSortKey(Student::SORT_BY_FIRST);
   StudentArrayUtilities::arraySort(group3,group3ArraySize);
   cout << StudentArrayUtilities::toString("\nAfter sort by first:", group3,
                                           group3ArraySize);

   Student::setSortKey(Student::SORT_BY_POINTS);
   StudentArrayUtilities::arraySort(group3,group3ArraySize);
   cout << StudentArrayUtilities::toString("\nAfter sort by points:", group3,
                                           group3ArraySize);

   Student::setSortKey(Student::SORT_BY_FIRST);

   cout << "Median of even class: " << StudentArrayUtilities::
            getMedianDestructive(group3,group3ArraySize) << endl;

   if(Student::getSortKey() == Student::SORT_BY_FIRST)
      cout << "Successfully preserved sort key." << endl;

   cout << "Median of class with 0 elements: " << StudentArrayUtilities::
   getMedianDestructive(group0, group0ArraySize) << endl;

   cout << "Median of class with 1 element: " << StudentArrayUtilities::
   getMedianDestructive(group1, group1ArraySize) << endl;

   cout << "Median of odd class: " << StudentArrayUtilities::
   getMedianDestructive(group2, group2ArraySize) << endl;
}

Student::Student(string last, string first, long points)
{
   if(!setLastName(last))
      lastName = DEFAULT_NAME;
   if(!setFirstName(first))
      firstName = DEFAULT_NAME;
   if(!setPoints(points))
      totalPoints = DEFAULT_POINTS;
}

bool Student::setLastName(string last)
{
   if(!validString(last))
      return false;
   lastName = last;
   return true;
}

bool Student::setFirstName(string first)
{
   if(!validString(first))
      return false;
   firstName = first;
   return true;
}

bool Student::setPoints(long pts)
{
   if(!validPoints(pts))
      return false;
   totalPoints = pts;
   return true;
}

bool Student::setSortKey(int key)
{
   if(key==SORT_BY_LAST || key==SORT_BY_FIRST || key==SORT_BY_POINTS)
   {
      sortKey = key;
      return true;
   }
   return false;
}

int Student::compareTwoStudents(Student firstStud, Student secondStud)
{
   int result = 0;
   int same = 0; // returns 0 if two integers are equal
   int greaterThan = 1; // returns positive if firstStud > secondStud
   int lessThan = -1; // returns negative if firsStud < secondStud

   switch(Student::getSortKey())
   {
      case SORT_BY_LAST:
         result =  firstStud.lastName.compare(secondStud.lastName);
         break;
      case SORT_BY_FIRST:
         result = firstStud.firstName.compare(secondStud.firstName);
         break;
      case SORT_BY_POINTS:
         if(firstStud.totalPoints == secondStud.totalPoints)
            result = same;
         else if(firstStud.totalPoints > secondStud.totalPoints)
            result = greaterThan;
         else
            result = lessThan;
         break;
      default:
         result =  firstStud.lastName.compare(secondStud.lastName);
   }
   return result;
}

string Student::toString()
{
   string resultString;
   ostringstream cnvrtFirst, cnvrtLast, cnvrtPoints;

   cnvrtFirst << firstName;
   cnvrtLast << lastName;
   cnvrtPoints << totalPoints;

   resultString = " " + cnvrtLast.str() + ", " + cnvrtFirst.str() + " points: "
      + cnvrtPoints.str() + "\n";

   return resultString;
}

bool Student::validString(string testStr)
{
   if(testStr.length() > 0 && isalpha(testStr[0]))
      return true;
   return false;
}

bool Student::validPoints(long testPoints)
{
   if(testPoints >= 0 && testPoints <= MAX_POINTS)
      return true;
   return false;
}

string StudentArrayUtilities::toString(string title, Student data[],
                                       int arraySize)
{
   string output = title + "\n";
   for(int k=0; k < arraySize; k++)
      output += " " + data[k].toString();

   output += "\n";
   return output;
}


void StudentArrayUtilities::arraySort(Student array[], int arraySize)
{
   for(int k=0; k < arraySize; k++)
   {
      if(!floatLargestToTop(array, arraySize-1-k))
         return;
   }
}

double StudentArrayUtilities::getMedianDestructive(Student array[],
                                                   int arraySize)
{
   long midIndex = (arraySize - 1) / 2;
   double median;

   if(arraySize <= 0)
      return 0;
   if(arraySize == 1)
      return array[midIndex].getTotalPoints();

   int originalSortKey = Student::getSortKey();
   Student::setSortKey(Student::SORT_BY_POINTS);
   arraySort(array, arraySize);
   Student::setSortKey(originalSortKey);

   if(isEven(arraySize))
   {
      median = (array[midIndex].getTotalPoints() +
                array[midIndex + 1].getTotalPoints()) / 2;
      return median;
   }
   else
   {
      median = array[midIndex].getTotalPoints();
      return median;
   }
}

bool StudentArrayUtilities::floatLargestToTop(Student data[], int top)
{
   bool changed = false;

   for(int k=0; k < top; k++)
   {
      if(Student::compareTwoStudents(data[k], data[k+1]) > 0)
      {
         mySwap(data[k], data[k+1]);
         changed = true;
      }
   }

   return changed;
}

void StudentArrayUtilities::mySwap(Student &a, Student &b)
{
   Student temp("","",0);

   temp = a;
   a = b;
   b = temp;
}

bool StudentArrayUtilities::isEven(long num)
{
   return num % 2 == 0;
}

/*--------------- RUN --------------------
Before: 
 atticus, finch points: 100
 scout, finch points: 50
 jem, finch points: 65
 boo, radley points: 88
 maudie, atkinson points: 47
 guy, montag points: 32
 romeo, montague points: 16
 juliet, capulet points: 15
 scarlett, o'hara points: 73
 willy, wonka points: 552
 elie, wiesel points: 100
 harper, lee points: 100
 ray, bradbury points: 94
 julia, alvarez points: 82
 roald, dahl points: 77
 john, green points: 70


After default sort:
  atticus, finch points: 100
  boo, radley points: 88
  elie, wiesel points: 100
  guy, montag points: 32
  harper, lee points: 100
  jem, finch points: 65
  john, green points: 70
  julia, alvarez points: 82
  juliet, capulet points: 15
  maudie, atkinson points: 47
  ray, bradbury points: 94
  roald, dahl points: 77
  romeo, montague points: 16
  scarlett, o'hara points: 73
  scout, finch points: 50
  willy, wonka points: 552


After sort by first:
  julia, alvarez points: 82
  maudie, atkinson points: 47
  ray, bradbury points: 94
  juliet, capulet points: 15
  roald, dahl points: 77
  atticus, finch points: 100
  jem, finch points: 65
  scout, finch points: 50
  john, green points: 70
  harper, lee points: 100
  guy, montag points: 32
  romeo, montague points: 16
  scarlett, o'hara points: 73
  boo, radley points: 88
  elie, wiesel points: 100
  willy, wonka points: 552


After sort by points:
  juliet, capulet points: 15
  romeo, montague points: 16
  guy, montag points: 32
  maudie, atkinson points: 47
  scout, finch points: 50
  jem, finch points: 65
  john, green points: 70
  scarlett, o'hara points: 73
  roald, dahl points: 77
  julia, alvarez points: 82
  boo, radley points: 88
  ray, bradbury points: 94
  atticus, finch points: 100
  harper, lee points: 100
  elie, wiesel points: 100
  willy, wonka points: 552

Median of even class: 75
Successfully preserved sort key.
Median of class with 0 elements: 0
Median of class with 1 element: 100
Median of odd class: 77
Program ended with exit code: 0
--------------------------------------*/





