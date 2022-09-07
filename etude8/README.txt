Etude 8- Rational Thinking
Otago University COSC326.
@authors George Churton & Dougal Colquham 

The aim of our classes is to achieve precision through creating an Integer class for arbitarily large numbers and then ussing these integers to implement rational numbers(fractions).

----Resources Utililised----
Due to our lack of experience with c++, there were numerous c++ reference pages that we used to build our Integer and Rational classes.
Here are some of them:

https://www.cplusplus.com/reference/string/stoi/ 
https://www.cplusplus.com/reference/string/string/substr/ 
https://www.cplusplus.com/reference/vector/vector/push_back/ 
https://www.cplusplus.com/reference/vector/vector/begin/ 
https://www.cplusplus.com/reference/vector/vector/pop_back/ 
https://www.cplusplus.com/reference/vector/vector/empty/
https://www.cplusplus.com/reference/vector/vector/erase/ 
https://www.cplusplus.com/reference/vector/vector/front/ 
https://www.cplusplus.com/reference/string/string/find/ 
http://www.cplusplus.com/doc/tutorial/control/ 


Other resources include:
https://en.wikipedia.org/wiki/Rational_number --> for fraction opperators and assignment calculations 
https://en.wikipedia.org/wiki/Greatest_common_divisor --> the reducing fractions section and gcd algorithm review, specifically Euclidean algorithm
https://www.youtube.com/watch?v=V0xlphMvqQQ&ab_channel=Mrs.Consorti-LearningChannel --> simpifying fractions with gcd.
https://decimal.info/rational/is-5.1-a-rational-number.html --> checking constructor r(a,b,c) output.
https://www.google.com/search?q=google+calculator&oq=google 
https://www.youtube.com/watch?v=0HiJaZWDAiA&ab_channel=MathwithMr.J 
https://www.geeksforgeeks.org/bigint-big-integers-in-c-with-example/  
https://www.geeksforgeeks.org/initialize-a-vector-in-cpp-different-ways/?ref=leftbar-rightbar 

--Testing--
We utilised a simple java program to check our integer and rational operators/assignment operators
This is the basic program, we changed the integer values and opperators/assignment operators manually in testing.

The basic java program was as follows:

public class operate{
    public static void main(String[] args) {
        int j = 270;
        int k = 5;
        int a = (j /= k);
        System.out.println(a);
    }
}

In this example we were testing the /= operator and matching that to the answer we were recieving in our Integer/rational class'.
Despite this, in reality most of the testing that included fractions was done by hand-- utalising google calculator.

Integer testing was done in this fashion (via a seperate Main.cpp file)

Integer j("10");
Integer k("2");
Integer ans(k *= j)
std::cout << ans << std::endl; 

or as 
std::cout << (k *= k) << std::endl; 

Where we changed the opperator manually.

Rational testing was done in a simular fashion-- utilising our constructors. e.g:

cosc326::Integer a("50");
cosc326::Integer b("10");
cosc326::Integer c("5");
cosc326::Integer d("10");

cosc326::Rational fig(a,d,c);
cosc326::Rational fig1(d,c);
cosc326::Rational fig2(d);

std::cout << (fig1 *= fig2) << std::endl; 

or as

cosc326::Rational(fig1 *= fig2);

---Teamwork Declaration---
This etude was done in person during lab streams and in free time that me and Dougal had.

---Compiling and running our program---
We utilisied a new version of c++ in this etude. Specifically c++11 was utilisied.

For compiling please use:
g++ --std=c++11 *.cpp -o  e8

For running
./e8

--Note--
As c++11 has been used modern IDE's may suggest there are errors in our code, 
however in compiling if c++ is used as the standard you will find no errors.
