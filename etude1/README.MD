COSC326- Etude1 2022 Semester One.
@author George Churton.

date.java examines stdin input from the file date-input.txt
The program takes each line of date-input as a new input entry and thus date.

Using this logic I have tested the program-- making sure it runs following the given specifications.
These specifications can be found at: https://www.cs.otago.ac.nz/cosc326/2022S1/date.pdf 

date.java can be run using the following syntax in the command line:
java <program-name> <stdin>

that is:

java date.java date-input.txt

Here is the input i've used for testing:

4-6-92
04/06/1992
3 AUG 97
12-Sep-1955
29-02-1985
29/2/2008
2/1/1752
2/1/1753
1/1/2999
1/1/3001
0 0 01
1 2 00


The input seen from lines 19-22 as well as from 29-30 test the valid imput specifications.
Whilst the next two lines test the leap year specifications.
Finally the input seen from lines 25-28 test the upper and lower bound logic-- ensuring that dates that are not in range are not accepted.

All of the given inputs, if valid, additionally act as tests because if valid the dates will be reformatted into the dd MMM yyyy format.
