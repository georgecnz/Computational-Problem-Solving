#include "Integer.h"
#include <sstream>
#include <string>
#include <iostream>
#include <numeric>
#include <cmath>
#include <stack>  
#include <algorithm>
#include <functional>  

/*
* COSC326- Semester 1.
* @author George Churton & Dougal Colquhoun
* Rational Thinking, Etude 8.
* This file creates a big integer class for rational.cpp
*/

using namespace std;

namespace cosc326{
	//Default constructor-- setting value to 0 
	Integer::Integer() {
		number.push_back(0); //pushing elements on vector.
		sign = true;
		elements = number.size();
	}

	//A copy constructor that duplicates the provided Integer j(i)
	Integer::Integer(const Integer& i) {
			number = i.number;
			sign = i.sign;
			elements = number.size();
		}
		
	//– One that takes a std::string of digits (possibly with a leading + or -)
	Integer::Integer(const std::string& s) {
		//pointer to an array or character
		std::string str(s);
		
		if(str.at(0) == '+'){
			str.erase(0,1);
			sign = true;

		}else if(str.at(0) == '-'){
			str.erase(0,1);
			sign = false;

		}else if(str.length() == 0){
			str = "0";
		
		}else{
			sign = true;
		}

		int length = str.size();
		int i = 0, temp;
		std::string tempstr(s);

		while(i < length){
			tempstr = str.substr(i,1);
			temp = std::atoi(tempstr.c_str());
			number.insert(number.begin(), temp);
			i++;
		}

		elements = number.size();
	}
	

	//deconstructor
	Integer::~Integer() {
	}

	//The unary = operator.
	Integer& Integer::operator=(const Integer& i) {
	number = i.number;
	sign = i.sign;
	return *this;
    }


	//The unary operator: -
	Integer Integer::operator-() const{
		Integer num = (*this);
			if (sign == true) {
				num.sign = false;
			} else if (sign == false) {
				num.sign = true;
			}
			return num;
		}

	//The unary operator: +
	Integer Integer::operator+() const {
		Integer value(*this);
		value.sign = true;
		return value;
	}

		//for the following assignment operators this accessor returns values.
		const std::vector<int>& Integer::getNum() const {
        return number;
    }

	 unsigned int Integer::getElements() const {
        return elements;
    }

	//compound assignment operator +=
	Integer& Integer::operator+=(const Integer& i) {
		std::vector<int> inspect = i.getNum();

		if((sign == false && i.sign == false) || (sign == true && i.sign == true) || (sign == false && i.sign == true)){
		if(inspect.size() < 0){
			throw "no value to assign";
		}

		if(i.sign == true && sign == true){
		int size = inspect.size();
		elements = number.size();

		int t = 0, s;
		if(size > elements){
			number.push_back(size - elements);
			elements = number.size();
		}

		for(int a = 0; a < elements; a++){
			if(a < size)
				s = (number[a] + inspect[a]) + t;
			else
				s = number[a] + t;
				t = s/10;
				number[a] = (s%10);
		}
		if(t)
		number.push_back(t);
		return *this;	
	}
		}if(i.sign == false || sign == false){
			int m = inspect.size();
			int n = number.size();
			int a, t = 0, s;
			
		for (a = 0; a < n; a++){
			if(a < m){
				s = number[a] - inspect[a]+ t;
				//std::cout << s << std::endl;
			}else
				s = number[a]+ t;
			if(s < 0)
				s += 10,
				t = -1;
			else
				t = 0;
			number[a] = s;
		}
		while(n > 1 && number[n - 1] == 0)
			number.pop_back(),
			n--;
				sign = false;
			return *this;
		}

	return *this;
	}


	//compound assignment operator -= 
	Integer& Integer::operator-=(const Integer& i) {
		std::vector<int> inspect = i.getNum();
		int m = inspect.size();
		int n = number.size();

	
		//if figure is zero then no assignment can take place.
		if(inspect.size() <= 0 || number.size() <= 0){
			throw "no value to assign";
		}
		
		if(sign == true){
		int a, t = 0, s;
		for (a = 0; a < n; a++){
			if(a < m)
				s = number[a] - inspect[a]+ t;
			else
				s = number[a]+ t;
			if(s < 0)
				s += 10,
				t = -1;
			else
				t = 0;
			number[a] = s;
		}
		while(n > 1 && number[n - 1] == 0)
			number.pop_back(),
			n--;
		return *this;
	
		}if(sign == false || i.sign == false){
			int size = inspect.size();
			int elements = number.size();

		int t = 0, s;
		if(size > elements){
			number.push_back(size - elements);
			elements = number.size();
		}

		for(int a = 0; a < elements; a++){
			if(a < size)
				s = (number[a] + inspect[a]) + t;
			else
				s = number[a] + t;
				t = s/10;
				number[a] = (s%10);
		}
		if(t)
		number.push_back(t);
		return *this;	
		}

		return *this;
	}

	
	//compound assignment operators *= 
	Integer& Integer::operator*=(const Integer& i) {
		std::vector<int> inspect = i.getNum();

		//if figure is zero then no assignment can take place.
		//if(inspect.size() <= 0 || number.size() <= 0){
		//	throw "0";
		//}

		int m = inspect.size();
		int n = number.size();

		vector<int> v(n + m, 0);
		for (int a = 0; a < n;a++)
		for (int j = 0; j < m;j++){
			v[a + j] += (number[a] ) * (inspect[j]);
		}
	n += m;
	number.resize(v.size());
	for (int s, b = 0, t = 0; b < n; b++)
	{
		s = t + v[b];
		v[b] = s % 10;
		t = s / 10;
		number[b] = v[b] ;
	}
	for (int c = n - 1; c >= 1 && !v[c]; c--)
			number.pop_back();
		return *this;
		}		

	

	  Integer& Integer::operator/=(const Integer& i) {
		    Integer here(i);
            Integer zero("0");
            Integer one("1");
            Integer count("1");

			int c=0;
 			while(*this >= i){
   			  *this -= i;
  			   c++;
 			}
			
			//std::string ans = to_string(c);
			number.empty();
			number.clear();
			number.insert(number.begin(), c);
			return *this;
	  }
	
	
	//compound assignment operator %=
	Integer& Integer::operator%=(const Integer& i) {
		  Integer zero("0");
            if(*this == zero || i == zero){
				throw "cannot mod zero";
                return *this;
            }
            while(*this > i){
                *this-=i;
            }
	    return *this;
	}


	Integer operator+(const Integer& lhs, const Integer& rhs) {
		Integer l = Integer(lhs);
		Integer r = Integer(rhs);
		l += r;
		return l;
	}

	Integer operator-(const Integer& lhs, const Integer& rhs) {
		Integer l = Integer(lhs);
		Integer r = Integer(rhs);
		l -= r;
		return l;
	}

	Integer operator*(const Integer& lhs, const Integer& rhs) {
		Integer fig(lhs);
		fig *= rhs;
		return fig;
	}

	Integer operator/(const Integer& lhs, const Integer& rhs) {
		Integer fig(lhs);
		fig /= rhs;
		return fig;
	}

	Integer operator%(const Integer& lhs, const Integer& rhs) {
		Integer fig(lhs);
		fig %= rhs;
		return fig;
	}


	std::ostream& operator<<(std::ostream& os, const Integer& i) {
		std::string temp;

		std::vector<int> n = i.getNum();
        if (i.sign == false){
            temp += "-";
        }

        for (int a = (n.size() - 1); a >= 0; a--)
            temp += std::to_string(n[a]);

        os << temp;
		return os;
	}

	std::istream& operator>>(std::istream& is, Integer& i) {
		std::string temp;
		is >> temp;
		i = Integer(temp);
		return is;
	}

	bool operator<(const Integer& lhs, const Integer& rhs) {
			std::vector<int> i = lhs.getNum();
			std::vector<int> ii = rhs.getNum();
			int m = ii.size();
			int n = i.size();
			
			if(n != m)
			return n < m;
			
			while(n--)
			if(i[n] != ii[n])
			return i[n] < ii[n];
			return false;
	}

	bool operator> (const Integer& lhs, const Integer& rhs) {
			return rhs < lhs;
	}

	bool operator<=(const Integer& lhs, const Integer& rhs) {
			 return lhs < rhs || lhs == rhs;
			}


	bool operator>=(const Integer& lhs, const Integer& rhs) {
	 		return lhs > rhs || lhs == rhs;
		
	}

	bool operator==(const Integer& lhs, const Integer& rhs) {
			std::vector<int> i = lhs.getNum();
			std::vector<int> ii = rhs.getNum();
			if(i == ii){
				return true;
			}
			return false;
	}

	bool operator!=(const Integer& lhs, const Integer& rhs) {
			std::vector<int> i = lhs.getNum();
			std::vector<int> ii = rhs.getNum();
			if(i != ii){
				return true;
			}
			return false;
	}
	
	//Greatest Common Divisor method
	//EuclideanGCD
	//using repeated subtraction here
	Integer gcd(const Integer& a, const Integer& b) {
		Integer left(+a);
		Integer right(+b);
		Integer temp;
		Integer zero("0");

		while(right != left){
			
			if(left > right){
				left-=right;

			}else{
				right-=left;
			}
		}		
	return left;		
	}	
}  