Etude 6: Counting it up
Run this program on the command line. Make sure to be in the same directory as count.java.
0: *set working directory*
1: $javac count.java
2: $java count
3: *input desired numbers*

Files containing just integers can be passed by:
$java count < test.txt
This works using cmd on windows, using UNIX command line shell on Linux and using terminal on Mac OS X.

This program is designed to take an input of two integers from the command line, n and k then output the value of n choose k. In this version both values must be positive, n must be larger than k and n must be the first value inputted. The input of "exit" will allow the user to quit the program easily.

The brief says to create a program that uses 64-bit integers, this program does that, as it only uses longs which are 64bit precision integers.

TEST CASES:
Input: 52 5
Output: Answer: 2598960

Input: 66 33
Output: Answer: 7219428434016265740

Input: 100 12
Output: Answer: 1050421051106700

Input: 1000 8
Output: Answer: 5668336450989879509

Input: 5000 6
Output: Answer: 3189614394181905884

Input: 10000 5
Output: Answer: 832500291625002000

Input: 100000 4
Output: Answer: 4166416671249975000

Input: 1000000 3
Output: Answer: 166666166667000000

Input: 10000000 2
Output: Answer: 49999995000000

Input: 100000000 2
Output: Answer: 4999999950000000

Input: 1000000000 2
Output: Answer: 499999999500000000

Input: 12 15
Output: INVALID: (12 15) n must be larger than k

Input: String 50
Output: INVALID: (String 50) Please only input integers

Input: 3.14 2
Output: INVALID: (3.14 2) Please only input integers

Input: -88 7
Output: INVALID: (-88 7) n must be larger than k

Input: 30 30
Output: Answer: 1

Input: 40 0
Output: Answer: 1

Input: 50 1
Output: Answer: 50

Input: 9223372036854775807 1
Output: Answer: 9223372036854775807

Input: 9223372036854775808 1
Output: INVALID: (9223372036854775808 1) Please only input integers

Input: 314159265 2
Output: Answer: 4934802197120992878

Input: 4294967296 2
Output: Answer: 9223372034707292160

Input: 3765432 3
Output: Answer: 8898008798195298760

Input: 121212 4
Output: Answer: 8993934679994659395

Input: 1999999999 2
Output: Answer: 1999999997000000001

Input: 125 15
Output: Answer: 9064807833193439800

To assist the making of this program we used a Scanner from the java.util library. The supporting documentation was useful for getting it working properly. The Wikipedia page on binomial coefficient and pascals triangle were very helpful in getting our head around the problem.
https://docs.oracle.com/javase/7/docs/api/index.html.
https://en.wikipedia.org/wiki/Binomial_coefficient
https://en.wikipedia.org/wiki/Pascal%27s_triangle
https://en.wikipedia.org/wiki/Triangular_number#Forumla
https://brilliant.org/wiki/pascals-triangle/

TEAMWORK DECLARATION:
The majority of this program was completed in streamed lab times so that ideas could be exchanged meaning the weight of the etude was shared. Outside lab times we would communicate via discord and all work completed was uploaded to git for sharing and version tracking.

-Dougal Colquhoun, George Churton
