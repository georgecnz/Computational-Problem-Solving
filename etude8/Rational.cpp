#include "Rational.h"

/* Etude 8- Rational Thinking
* COSC326 Sem1 2022
* Rational.cpp builds on the Integer.cpp file so that rational expressions can be computated.
* @authors George Churton and Dougal Colquhoun..
*/


namespace cosc326 {

    Rational::Rational() {
        Integer zero("0");
        whole = Integer("0");
        denom = Integer("0");
        numerator = Integer("0");
        sign = true;
    }

    Rational::Rational(const std::string& str) {
            std::string s = str;
            std::string wStr = "";
            std::string dStr = "";
            std::string nStr = "";
              if(s.at(0) == '-'){
                sign = false;
                s.erase(0,1);
            }
            else if(s.at(0) == '+'){
                sign = true;
                s.erase(0,1);
            }
            else{
                sign = true;
            }
            
            for(int i = 0; i < s.length(); i++){
             if(s[i] == '.'){
                isDecimal = true;
                }

                if(s[i] == '/'){
                isFraction = true;
                }
            }
            //e.g -3/2
            if(isFraction == true && isDecimal == false){
                size_t slash = s.find('/');
                Integer n(s.substr(0, (slash)));
                Integer d(s.substr((slash+1)));
                numerator = n;
                denom = d;       
             }

            //e.g 5
            if((isFraction == false) && (isDecimal == true)){
                    size_t dot = s.find('.');
                    Integer w(s.substr(0, (dot)));
                    whole = w;
            }

            //e.g -3.2/4 as input.
            if((isFraction == true) && (isDecimal == true)){
                size_t dot = s.find('.');
                size_t slash = s.find('/');
                Integer w(s.substr(0, (dot)));
                Integer n(s.substr((dot+1), (slash-dot-1)));
                Integer d(s.substr((slash+1)));
                whole = w;
                numerator = n;
                denom = d;     
                }
            }

    //accessors for printStream-
     const Integer& Rational::getNumerator() const{
        return numerator;
    }

     const Integer& Rational::getDenom() const{
        return denom;
    }

     const Integer& Rational::getWhole() const{
        return whole;
    }

    const bool Rational::getDecimalState() const{
        return isDecimal;
    }

    const bool Rational::getFractionState()const{
        return isFraction;
    }

    const bool Rational::getSign()const{
        return sign;
    }

     void Rational::setWhole(const Integer& in) {
        whole = in;
     }

    //copy constructor
    Rational::Rational(const Rational& r) {
        sign = r.sign;
        numerator = r.numerator;
        whole = r.whole;
        denom = r.denom;

    }

    //Rational r1(a); // r1 = a
    Rational::Rational(const Integer& a) {
            Integer i(a);
            Integer zero("0");

            if(i.sign == false){
                whole = -i;
                isFraction = false;
                isDecimal = false;

            }else{
            whole = +i;
            isDecimal = false;
            isFraction = false;
            sign = true;
            }
          
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Rational::Rational(const Integer& a, const Integer& b) {
        Integer left(a);
        Integer initalLeft(a);
        Integer right(b);
        Integer zero("0"); 
        Integer one("1");
        Integer overflow;
        bool negfig = false;

            if(left.sign == false){
            left = +left;
            negfig = true;
        }

        if(right.sign == false){
            right = +right;
            negfig = true;
        }


    //using Integer GCD logic for simpification
       Integer divide(gcd(left,right));

       if(divide != zero){
           left/=divide;
           right/=divide;
           sign = true;
           overflow = (left/right);   
        }

       if(right == one){
           
           if(negfig == true){
               whole = -left;
               isDecimal = true;

           }else{
           whole = left;
           isDecimal = true;
           }

       }else if(left >= right){
           if(negfig == true){
                whole = -overflow;
               numerator = (left%right);
               denom = right;
           }
           whole = overflow;
           numerator = (left%right);
           denom = right;

            if(denom == one){
                if(negfig == true){
               whole = -left;
               isDecimal = true;
                
                }else{
              whole = left;
              isDecimal = true;
                }
            }

            else if(numerator == zero){
                if(negfig == true){
               whole = -overflow;
               isDecimal = true;
                }
                   whole = overflow;
                   isDecimal = true;
                   isFraction = false;
              }else{
           
            isFraction = true;
            isDecimal = true;
              }
       

       }else if(right >= left){
           if(negfig == true){
               numerator = -left;
               denom = right;
               isFraction = true;
               isDecimal = false;
           }
           numerator = left;
           denom = right;
           isFraction = true;
           isDecimal = false;
       }
    }
        
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Rational r3(a, b, c); // r3 = a + b/c
    Rational::Rational(const Integer& a, const Integer& b, const Integer& c) {
      Integer left(a);
        Integer mid(b);
        Integer right(c);
        Integer one("1");
        bool negfig = false;
        Integer overflow;

       Integer zero("0"); 
       //using Integer GCD logic for simpification

        if(left.sign == false){
            left = +left;
            negfig = true;
        }

        if(mid.sign == false){
            mid = +mid;
            negfig = true;
        }

        if(right.sign == false){
            right = +right;
            negfig = true;
        }

      
       Integer divide(gcd(right,mid));
           if(divide != zero){
           mid/=divide;
           right/=divide;
           sign = true;
           overflow = (mid/right);   
        }

       if(right == one){
           if(negfig == true){
               whole = left;
               whole+=mid;
               whole = -whole;
               isFraction = false;
               isDecimal = true;

           }else{
           whole = left; 
           whole+=mid;
           isFraction = false;
           isDecimal = true;
           }


       }else if(mid >= right){
           if(negfig == false){
           whole = (overflow + left);
           numerator = (mid%right);
           denom = right;
           }

           if(negfig == true){
            whole = (overflow + left);
            whole = -whole;
           numerator = (mid%right);
           denom = right;
           }

            if(denom == one){

            if(negfig == false){
                 whole = (mid + overflow);
                 whole += left;
                 isDecimal = true;

                 }if(negfig == true){
                      whole = (mid + overflow);
                      whole += left;
                      whole = -whole;
                      isDecimal = true;
                      isFraction = false;
                 }
            
            }else if(numerator == zero){
                if(negfig == false){
                   whole = overflow;
                   whole += left;
                   isDecimal = true;
                   isFraction = false;
                }

                if(negfig == true){
                   whole = overflow;
                   whole += left;
                   whole = -whole;
                   isDecimal = true;
                   isFraction = false;
                }

            }else{
            isFraction = true;
            isDecimal = true;
            }
       

       }else if(right >= mid){
           if(negfig == false){
           whole = left;
           numerator = mid;
           denom = right;
           isFraction = true;
           isDecimal = true;
           }

            if(negfig == true){
           whole = left;
           whole = -left;
           numerator = mid;
           denom = right;
           isFraction = true;
           isDecimal = true;
            }
        }
    }
  
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //deconstructor
    Rational::~Rational() {
    }

    Rational& Rational::operator=(const Rational& r) {
        return *this;
    }

    Rational Rational::operator-() const {
        Rational inspect(*this);
        inspect.sign = true;
        return Rational(*this);
    }

    Rational Rational::operator+() const {
        cosc326::Rational i(*this);
        i.sign = true;
        return Rational(*this);
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    Rational& Rational::operator+=(const Rational& r) {
            Integer zero("0");
            Rational i(*this);
            Rational ii(r);
            Integer("1");

            //if zero then return other number.
            if(i.getWhole() == zero && i.getDenom() == zero && ii.getNumerator() == zero){
                isFraction = false;
                isDecimal = false;
                whole = ii.getWhole();
                *this = i;
                return *this;
            }

            if(ii.getWhole() == zero && ii.getDenom() == zero && i.getNumerator() == zero){
                isFraction = false;
                isDecimal = false;
                i.whole = i.getWhole();
                *this = i;
                return *this;
            }

            //if contains two whole numbers then the result is simple...
            if(((i.getDenom() == zero) && (i.getNumerator() == zero) && (i.getWhole() != zero)) && ((ii.getDenom() == zero) && (ii.getNumerator() == zero) && (ii.getWhole() != zero))){
                Integer a(i.getWhole());
                Integer b(ii.getWhole());
                whole = Integer(a += b);
                *this = i;
                sign = true;
                isFraction = false;
                isDecimal = false;
                return *this;
            }


            //if two fraction given
            //if adding fractions: two fractions are added as follows a/b + c/d = ad + bc / bd:
            //works for positive numbers --> gcd doesnt handle negative --> fixed
           
            if((i.getWhole() == zero)  && ( (ii.getWhole() == zero))){       
                bool negValue = false;
                Integer zero = Integer("0");
                Integer iNum = (i.getNumerator());
                Integer iDen = (i.getDenom());
                Integer iiNum = (ii.getNumerator());
                Integer iiDen = (ii.getDenom());

                Integer tempNumerator;
                Integer tempNumerator1;
                Integer ansNumerator; //c
                Integer ansDenom; //d
                Rational DenomI;

                tempNumerator = cosc326::Integer(iNum * iiDen);
                tempNumerator1 = cosc326::Integer(iiNum * iDen);
                ansNumerator = cosc326::Integer(tempNumerator + tempNumerator1);
                ansDenom = cosc326::Integer(iDen * iiDen);

                if(ansNumerator < zero){
                    ansNumerator = +ansNumerator;
                    negValue = true;
                }

                if(ansDenom < zero){
                    ansDenom = +ansDenom;
                    negValue = true;
                }

                Integer divide=gcd(ansNumerator,ansDenom);
                Integer one("1");

                     if(divide != one){
                        ansNumerator/=divide;
                        ansDenom/=divide;
                    }
            
                isFraction = true;
                isDecimal = false;

                if(negValue == true){
                    denom = ansDenom;
                    numerator = -numerator;

                }else{
                denom = ansDenom;
                numerator = ansNumerator;
                denom = ansDenom;
                numerator = ansNumerator;
                }
                   
               
                if(numerator == denom){
                    whole = numerator/denom;
                    isDecimal = true;
                    isFraction = false;
                    *this = i;
                    return *this;
                     }


                *this = i;
                return *this;
            }
      
               if(i.getWhole() == zero && ii.getNumerator() == zero){
                Integer a(ii.getWhole());
                Integer wholeDenom("1");
                bool negValue = false;

                Integer oneNum;
                Integer oneDom;
                Integer twoNum;
                Integer twoDom;

                oneNum = Integer(i.getNumerator());
                oneDom = Integer(i.getDenom());
                twoNum = Integer(ii.getWhole());
                twoDom = Integer("1");
             
                Integer tempNumerator;
                Integer tempNumerator1;
                Integer ansNumerator; 
                Integer ansDenom; 
          
                tempNumerator = cosc326::Integer(oneNum * twoDom); 
                tempNumerator1 = Integer(twoNum * oneDom); 
                ansNumerator = cosc326::Integer(tempNumerator + tempNumerator1);
                ansDenom = cosc326::Integer(oneDom * twoDom); 
                Rational simplify = Rational(ansNumerator,ansDenom);                
                *this = simplify;
                return *this;
                }   

                if(ii.getWhole() == zero && i.getNumerator() == zero){
                Integer a(i.getWhole());
                Integer wholeDenom("1");
                bool negValue = false;

                Integer oneNum;
                Integer oneDom;
                Integer twoNum;
                Integer twoDom;

                oneNum = Integer(ii.getNumerator());
                oneDom = Integer(ii.getDenom());
                twoNum = Integer(i.getWhole());
                twoDom = Integer("1");
             
                Integer tempNumerator;
                Integer tempNumerator1;
                Integer ansNumerator; 
                Integer ansDenom; 
          
                tempNumerator = cosc326::Integer(oneNum * twoDom); 
                tempNumerator1 = Integer(twoNum * oneDom); 
                ansNumerator = cosc326::Integer(tempNumerator + tempNumerator1);
                ansDenom = cosc326::Integer(oneDom * twoDom); 

                if(ansNumerator < zero){
                    ansNumerator = +ansNumerator;
                    negValue = true;
                }

                if(ansDenom < zero){
                    ansDenom = +ansDenom;
                    negValue = true;
                }

                if(negValue == true){
                    denom = ansDenom;
                    numerator = -numerator;

                }else{
                denom = ansDenom;
                numerator = ansNumerator;
                }

                  
                if(numerator == denom){
                    whole = numerator/denom;
                    isDecimal = true;
                    isFraction = false;
                    *this = i;
                    return *this;
                     }


                    
                isFraction = true;
                isDecimal = false;
                *this = i;
                return *this;
                 } 

                 //if adding to r3(a,b,c) r1(a)
                 if (i.getWhole() != zero && i.getNumerator() != zero && ii.getNumerator() == zero){
                        Integer wholeans; 
                        wholeans = cosc326::Integer(i.getWhole() + ii.getWhole());
                        whole = wholeans;
                        denom = i.getDenom();
                        numerator = i.getNumerator();
                        isFraction = true;
                        isDecimal = true;
                       
                        if(numerator == denom){
                            whole = numerator/denom;
                            isDecimal = true;
                            isFraction = false;
                            *this = i;
                            return *this;
                            }


                        *this = i;
                        return *this;
                 }        


                   if (i.getWhole() != zero && ii.getNumerator() != zero && i.getNumerator() == zero){
                        Integer wholeans; 
                        wholeans = cosc326::Integer(ii.getWhole() + i.getWhole());
                        whole = wholeans;
                        denom = ii.getDenom();
                        numerator = ii.getNumerator();
                        isFraction = true;
                        isDecimal = true;

                       
                        if(numerator == denom){
                            whole = numerator/denom;
                            isDecimal = true;
                            isFraction = false;
                            *this = i;
                            return *this;
                            }

                        *this = i;
                        return *this;
                   }


                   if(i.getWhole() != zero && i.getNumerator() != zero && ii.getWhole() == zero && ii.getNumerator() != zero){
                       Rational fractionOne(i.getNumerator(),ii.getNumerator());
                       Rational fractionTwo(ii.getNumerator(), ii.getDenom());
                       Rational ansFraction(fractionOne + fractionTwo);
                       Integer wholeAdd = i.getWhole() + ii.getWhole();
                       Rational ans(ansFraction + wholeAdd);
                       *this = ans;
                       return *this;
                 }   
               

                if(i.getWhole() != zero && ii.getWhole() != zero && i.getWhole() != zero && ii.getWhole() != zero){
                    Integer num;
                    Integer dom;

                    if(i.getWhole().sign == false || ii.getWhole().sign == false){
                       Integer num1 = i.getNumerator() * ii.getDenom();
                       Integer num2 = ii.getNumerator() * i.getDenom();
                       Integer num = num1 - num2;
                       Integer dom = i.getDenom() * ii.getDenom();
                       Rational ans(num,dom);
                       whole = ans.getWhole();
                       whole-=i.getWhole();
                       whole-=ii.getWhole();
                       numerator = ans.getNumerator();
                       denom = ans.getDenom();
   
                       *this = ans;
                       return *this;

                    }else{
                    Integer wholeAns = i.getWhole() + ii.getWhole();
                    Integer num1 = i.getNumerator() * ii.getDenom();
                    Integer num2 = ii.getNumerator() * i.getDenom();
                    Integer num = num1 + num2;
                    Integer dom = i.getDenom() * ii.getDenom();
                    
                    //simplify
                       Rational ans(num,dom);
                       whole = ans.getWhole();
                       whole+=i.getWhole();
                       whole+=ii.getWhole();
                       numerator = ans.getNumerator();
                       denom = ans.getDenom();
   
                       *this = ans;
                       return *this;
                        }
                }
              return *this;
         }

        
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    Rational& Rational::operator-=(const Rational& r) {
        Integer zero("0");
            Rational i(*this);
            Rational ii(r);

            //if zero then return other number.
            if(i.getWhole() == zero && i.getDenom() == zero && i.getNumerator() == zero){
                isFraction = false;
                isDecimal = false;
                ii.whole = ii.getWhole();
                *this = ii;
                return *this;
            }
            //fractions 
            if(ii.getWhole() == zero && ii.getDenom() != zero && ii.getNumerator() != zero && i.getWhole() == zero){
            
                //  std::cout << "here -0" << std::endl;

                Integer num1 = i.getNumerator() * ii.getDenom();
                Integer num2 = i.getDenom() * ii.getNumerator();
                Integer num = num1 - num2;
                Integer d1 = i.getDenom() * ii.getDenom();
                Rational ans(num,d1);
                whole = ans.getWhole();
                numerator = ans.getNumerator();
                denom = ans.getDenom();
                *this = i;
                return *this;

            }

                  if (i.getWhole() != zero && i.getNumerator() != zero && ii.getNumerator() == zero){
                       std::cout << "here 0" << std::endl;
                        Integer wholeans; 
                        wholeans = cosc326::Integer(i.getWhole() - ii.getWhole());
                        whole = wholeans;
                        denom = i.getDenom();
                        numerator = i.getNumerator();
                        isFraction = true;
                        isDecimal = true;
                        
                        if(numerator == denom){
                        whole = numerator/denom;
                        isDecimal = true;
                        isFraction = false;
                        *this = i;
                        return *this;
                        }

                        *this = i;
                        return *this;
                 }        


                   if (ii.getWhole() != zero && ii.getNumerator() != zero && i.getNumerator() == zero){
                       //std::cout << "here " << std::endl;
                        Integer wholeans; 
                        wholeans = cosc326::Integer(i.getWhole() - ii.getWhole());
                        whole = wholeans;
                        denom = ii.getDenom();
                        numerator = ii.getNumerator();
                        isFraction = true;
                        isDecimal = true;

                       if(numerator == denom){
                        whole = numerator/denom;
                        isDecimal = true;
                        isFraction = false;
                        *this = i;
                        return *this;
                        }

                        *this = i;
                        return *this;
                 }    

                 if(i.getWhole() != zero && i.getNumerator() != zero && ii.getWhole() != zero && ii.getNumerator() != zero){
                    //  std::cout << "here 55" << std::endl;
                    Integer w1(ii.getWhole());
                    Integer w2(i.getWhole());

                    if(w1.sign == false){
                        Rational ans(i + ii);
                        whole = ans.getWhole();
                        numerator = ans.getNumerator();
                        denom = ans.getDenom();
                        isFraction = true;
                        isDecimal = true;
                        sign = false;
                    }

                    if(w2.sign == false){
                        Rational ans(ii + i);
                        whole = ans.getWhole();
                        numerator = ans.getNumerator();
                        denom = ans.getDenom();
                        sign = false;
                    }

                    bool negfig = false;
                    Rational firstFraction(i.getNumerator(),i.getDenom());
                    Rational secondFraction(ii.getNumerator(), ii.getDenom());
                    Rational fractionAns(firstFraction - secondFraction);

                    Integer whole1(i.getWhole());
                    Integer whole2(ii.getWhole()); 
                   
                   if(whole2 > whole1){
                    whole = (whole2 -= whole1);
                    negfig = true;
                   }

                   if(whole1 > whole2){
                       whole = (whole1 -= whole2);
                   }
                   
                    
                    if(fractionAns.getWhole().sign == false){
                        whole-=fractionAns.getWhole();
                        if(negfig == true){
                            whole = -whole;
                        }
                    }

                    numerator = fractionAns.getNumerator();
                    denom = fractionAns.getDenom();
                    Rational ans(whole,numerator,denom);
                     whole = ans.getWhole();
                     numerator = ans.getNumerator();
                     denom = ans.getDenom();

                    isDecimal = true;
                    isFraction = true;
                    *this = i;
                    return *this;
                }

        return *this;
    }


//-----------------------------------------------------------------------------------------------------------------------------------------------------------

    Rational& Rational::operator*=(const Rational& r) {
        Integer zero("0");
        Rational i(*this);
        Rational ii(r);

         if(ii.getNumerator() == zero && i.getNumerator() != zero){
           Integer ansD;
           Integer ansN;
           
           ansD = cosc326::Integer(ii.getWhole() * i.getDenom());
           ansN = cosc326::Integer(ii.getWhole() * i.getNumerator());
        
           i.denom = ansD;
           i.numerator = ansN;
           isDecimal = false;
           isFraction = true;
           numerator = ansN;
           denom = ansD;

           Integer passD(ansD);
           Integer passN(ansN);

           numerator = passN;
           denom = passD;

           if(numerator == denom){
                whole = numerator/denom;
                isDecimal = true;
                isFraction = false;
                *this = i;
                return *this;
                }


           *this = i;
           return *this;
         }


         if(i.getNumerator() == zero && ii.getNumerator() != zero && ii.getWhole() == zero){
             Integer ansD;
             Integer ansN;
           
           ansD = cosc326::Integer(i.getWhole() * ii.getDenom());
           ansN = cosc326::Integer(i.getWhole() * ii.getNumerator());
           
           i.denom = ansD;
           i.numerator = ansN;
           isDecimal = false;
           isFraction = true;
           numerator = ansN;
           denom = ansD;

           Integer passD(ansD);
           Integer passN(ansN);

           numerator = passN;
           denom = passD;

           if(numerator == denom){
                whole = numerator/denom;
                isDecimal = true;
                isFraction = false;
                *this = i;
                return *this;
                }


           *this = i;
           return *this;
            
    
        }if((i.getWhole() == zero) && (ii.getWhole() == zero)){
                    bool negValue = false;
                    Integer numeratorAns;
                    Integer donominatorAns;
                    donominatorAns = cosc326::Integer(i.getNumerator() * ii.getNumerator());
                    numeratorAns = cosc326::Integer(i.getDenom() * ii.getDenom());
                    denom = donominatorAns;
                    numerator = numeratorAns;

                     if (numeratorAns >= donominatorAns) {
                         whole = (numeratorAns/donominatorAns);
                          numeratorAns = (numeratorAns - (whole * donominatorAns));
                        }

                    if(numeratorAns.sign == false || donominatorAns.sign == false){
                        numeratorAns = +numeratorAns;
                        donominatorAns = +donominatorAns;
                        negValue = true;
                    }
                    Integer one("1");
                    Integer g = gcd(numeratorAns, donominatorAns);

                    if (g != one){
                     numeratorAns = (numeratorAns / g);
                     donominatorAns = (donominatorAns / g);
                     denom = donominatorAns;
                     numerator = numeratorAns;
                    }
                    
                      if(negValue == true){
                         numerator = -numeratorAns;
                     }

                     if(whole == zero){
                         isFraction = true;
                         isDecimal = false;
                     }else{
                         isDecimal = true;
                         isFraction = true;
                     }

                     
              if(numerator == denom){
                whole = numerator/denom;
                isDecimal = true;
                isFraction = false;
                *this = i;
                return *this;
                }
                    *this = i;
                    return *this;
            
            
           } if((i.getNumerator() == zero) && (ii.getNumerator() == zero)){
              // std::cout << "here" << std::endl;
            Integer zero("0");
            Rational i(*this);
            Rational ii(r);
            Integer wholeAns; 

            if(i.getWhole() == zero){
                *this = i;
                return *this;

            }else if(ii.getWhole() == zero){
                *this = ii;
               return *this;

            }else{
                wholeAns = i.getWhole() * ii.getWhole();

                if(wholeAns.sign == true){
                    whole = +wholeAns;
                    isDecimal = true;
                    isFraction = false;

                }else{
                whole = wholeAns;
                isDecimal = true;
                isFraction = false;
                }
            }

            *this = i;
            return *this;
           }     

                      if (i.getWhole() != zero && i.getNumerator() != zero && ii.getNumerator() == zero){
                        Integer wholeans; 
                        wholeans = cosc326::Integer(i.getWhole() * ii.getWhole());
                        whole = wholeans;
                        denom = i.getDenom();
                        numerator = i.getNumerator();

                        isFraction = true;
                        isDecimal = true;
                        *this = i;
                        return *this;
                 }        

                   if (ii.getWhole() != zero && ii.getNumerator() != zero && i.getNumerator() == zero){
                        Integer wholeans; 
                        wholeans = cosc326::Integer(i.getWhole() * ii.getWhole());
                        whole = wholeans;
                        denom = ii.getDenom();
                        numerator = ii.getNumerator();
                        isFraction = true;
                        isDecimal = true;
                        *this = i;
                        return *this;
                 }    

                if(i.getWhole() != zero && i.getNumerator() != zero && ii.getWhole() != zero && ii.getNumerator() != zero){
                        Integer wholeans; 
                        wholeans = cosc326::Integer(i.getWhole() * ii.getWhole());
                        Rational fract1 = Rational(i.getNumerator(), i.getDenom());
                        Rational fract2 = Rational(ii.getNumerator(), ii.getDenom());
                        Rational ansFract = Rational(fract1 * fract2);
                        whole = wholeans;
                        numerator = ansFract.getNumerator();
                        denom = ansFract.getDenom();
                        isFraction = true;
                        isDecimal = true;
                        *this = i;
                        return *this;
                }


                   if(ii.getWhole() != zero && ii.getNumerator() != zero && i.getWhole() != zero && i.getNumerator() != zero){
                        Integer wholeans; 
                        wholeans = cosc326::Integer(ii.getWhole() * i.getWhole());
                        Rational fract1 = Rational(ii.getNumerator(), ii.getDenom());
                        Rational fract2 = Rational(i.getNumerator(), i.getDenom());
                        Rational ansFract = Rational(fract1 * fract2);
                        whole = wholeans;
                        numerator = ansFract.getNumerator();
                        denom = ansFract.getDenom();
                        isFraction = true;
                        isDecimal = true;
                        *this = i;
                        return *this;
                }

                if(i.getWhole() != zero && i.getNumerator() != zero && ii.getWhole() == zero && ii.getNumerator() != zero){
                    Integer wholeans; 
                    Integer one("1");
                    bool negValue = false;
                        Rational fract1 = Rational(ii.getNumerator(), ii.getDenom());
                        Rational fract2 = Rational(i.getNumerator(), i.getDenom());
                        Rational wholeFract = Rational(i.getWhole());
            
                        Rational ansFract = Rational(fract1 * fract2);
                        Rational ansWhole = Rational(wholeFract * fract2);

                        Integer wholeDom = ansWhole.getDenom();
                        Integer wholeNum = ansWhole.getNumerator();
                        whole = wholeNum;
                        numerator = ansFract.getNumerator();
                        denom = ansFract.getDenom();
                       isDecimal = true;
                       isFraction = true;
                }

                if(i.getWhole() != zero && i.getNumerator() != zero && ii.getWhole() != zero && ii.getNumerator() != zero){
                    Rational firstFraction(i.getNumerator(),i.getDenom());
                    Rational secondFraction(ii.getNumerator(), ii.getDenom());
                    Rational fractionAns(firstFraction * secondFraction);
                    whole = (i.getWhole() * ii.getWhole());
                    numerator = fractionAns.getNumerator();
                    denom = fractionAns.getDenom();
                    isDecimal = true;
                    isFraction = true;
                     *this = i;
                    return *this;
                }

       return *this;
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------
    Rational& Rational::operator/=(const Rational& r) {
        Integer zero("0");
        Rational i(*this);
        Rational ii(r);

        if(ii.getWhole() != zero && i.getNumerator() == zero && i.getWhole() != zero && i.getNumerator() == zero){
            Integer ans(i.getWhole() / ii.getWhole());
            whole = ans;
            *this = i;
            return *this;
        }
     
         //a/b / c/d = ad/bc --> working
        if(ii.getWhole() == zero && i.getWhole() == zero){
            // << "here" << std::endl;
           Integer ansD = (Rational(i.getDenom() * ii.getNumerator()).getWhole());
           Integer ansN = (Rational(i.getNumerator() * ii.getDenom()).getWhole());
           numerator = ansN;
           denom = ansD;

           isDecimal = false;
           isFraction = true;

           Rational out(numerator,denom);
           denom = out.getDenom();
           numerator = out.getNumerator();

              if(numerator == denom){
                whole = numerator/denom;
                isDecimal = true;
                isFraction = false;
                *this = i;
                return *this;
                }

            *this = i;
            return *this;
           
         }

       if(ii.getNumerator() == zero && i.getNumerator() != zero && i.getWhole() != zero){
                //std::cout << "here1" << std::endl;
                Integer improperFractNum  =  i.getWhole() * i.getDenom(); //* 1 = Ans Denom
                Integer num = ii.getWhole() * i.getDenom();
                Rational ans(num,improperFractNum);
                whole = ans.getWhole();
                denom = ans.getDenom();
                numerator = ans.getNumerator(); 
                
                if(whole > zero && denom > zero){
                    isFraction = true;
                    isDecimal = true;
                }else{
                    isFraction = false;
                    isDecimal = true;
                }

                 if(numerator == denom){
                whole = numerator/denom;
                isDecimal = true;
                isFraction = false;
                *this = i;
                return *this;
                }

                *this = i;
                return *this;
        }

            if(i.getNumerator() == zero && ii.getNumerator() != zero && ii.getWhole() != zero){
                // std::cout << "here2" << std::endl;                             
                Integer improperFractNum  =  ii.getWhole() * ii.getDenom(); //* 1 = Ans Denom
                Integer num = i.getWhole() * ii.getDenom();
                Rational ans(num,improperFractNum);
                whole = ans.getWhole();
                denom = ans.getDenom();
                numerator = ans.getNumerator(); 
                isDecimal = true;
                isFraction = true;

                *this = i;
                return *this;
        }

        if(i.getNumerator() == zero && ii.getNumerator() != zero && ii.getWhole() == zero){
            //std::cout << "here3" << std::endl;
            Integer num = ii.getDenom() * i.getWhole();
            Integer den = ii.getNumerator();
            //simplify using encapsualtion
            cosc326::Rational ans(num,den);
            whole = ans.getWhole();
            numerator = ans.getNumerator();
            denom = ans.getDenom();
              *this = i;
              return *this;
            
        }

        if(ii.getNumerator() == zero && i.getNumerator() != zero && i.getWhole() == zero){
            //  std::cout << "here4" << std::endl;
            Integer num = i.getDenom() * ii.getWhole();
            Integer den = i.getNumerator();
            //simplify using encapsualtion
            cosc326::Rational ans(num,den);
            whole = ans.getWhole();
            numerator = ans.getNumerator();
            denom = ans.getDenom();

              return *this;
            
        }
        return *this;
    }

//______________________________________________________________________________________________________________________________________________________________________________________
    Rational operator+(const Rational& lhs, const Rational& rhs) {
         Rational left(lhs);
         Rational right(rhs);
            left += right;
        return left;
    }

    Rational operator-(const Rational& lhs, const Rational& rhs) {
         Rational left(lhs);
         Rational right(rhs);
         left -= right;
        return left;
    }

    Rational operator*(const Rational& lhs, const Rational& rhs) {
        Rational left(lhs);
        Rational right(rhs);
        left *= right;
        return left;
    }

    Rational operator/(const Rational& lhs, const Rational& rhs) {
        Rational left(lhs);
        Rational right(rhs);
        left /= right;
        return left;
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    std::ostream& operator<<(std::ostream& os, const Rational& i) {
     if(i.getDecimalState() == false && i.getFractionState() == true){
        Rational expression = i;
        Integer n = expression.getNumerator();
        Integer d = expression.getDenom();

           if(i.getSign() == false){
            os << "-" << n << "/" << d;
           return os;

           }else{
           os << n << "/" << d;
           return os;
           }

       }else if(i.getDecimalState() == true && i.getFractionState() == false){
           Integer w = i.getWhole();

            if(i.getSign() == false){
            os << "-" << w;
           return os;

           }else{
           os << w;
           return os;
           }
        

       }else if(i.getDecimalState() == true && i.getFractionState() == true){
        Rational expression = i;
        Integer n = expression.getNumerator();
        Integer d = expression.getDenom();
        Integer w = expression.getWhole();

            if(i.getSign() == false){
            os << w << "." << n << "/" << d;
           return os;

            }else{
           os << w << "." << n << "/" << d;
           return os;
            }

       }else if(i.getDecimalState() == false && i.getFractionState() == false){
            Integer w = i.getWhole();

            if(i.getSign() == false){
            os << "-" << w;
            return os;

            }else{
                os << w;
                return os;
            }
       }
       return os;
    }

    std::istream& operator>>(std::istream& is, Rational& i) {
        std::string input;
        is >> input;
        i = Rational(input);
        return is;
    }

    bool operator<(const Rational& lhs, const Rational& rhs) {
        if (lhs == rhs) {
            return false;
        } 

        Rational left(lhs);
        Rational right(rhs);
        Integer zero("0");
 
        //built upon integer class' comaprison opertors.
        if(left.getNumerator() == zero && right.getNumerator() == zero){
            if(left.getWhole() < right.getWhole()){
                return true;
            }else{
                return false;
            }
        }

        if(left.getWhole() == zero && right.getNumerator() == zero){
            if(left.getWhole() < right.getWhole()){
                return true;
            }else{
                return false;
            }
        }

        if(left.getNumerator() == zero && right.getWhole() == zero){
            return true;
        }

          if(left.getWhole() == zero && right.getNumerator() == zero){
            return false;
        }

        if(left.getWhole() == zero && right.getWhole() == zero){
            if(left.getDenom() < right.getDenom()){
                return true;
            }
        }

          if(left.getWhole() != zero && right.getWhole() != zero && left.getNumerator() != zero && right.getNumerator() != zero){
            if(left.getWhole() < right.getWhole()){
                return true;
            }
        }

        return false;
    }

    bool operator> (const Rational& lhs, const Rational& rhs) {
      if (lhs == rhs) {
            return false;
        } 

        Rational left(lhs);
        Rational right(rhs);
        Integer zero("0");
 
        //built upon integer class' comaprison opertors.
        //compare whole numbers if no fractions
        if(left.getNumerator() == zero && right.getNumerator() == zero){
            if(left.getWhole() > right.getWhole()){
                return true;
            }else{
                return false;
            }
        }

        if(left.getWhole() == zero && right.getNumerator() == zero){
            if(left.getWhole() > right.getWhole()){
                return true;
            }else{
                return false;
            }
        }

        if(left.getNumerator() == zero && right.getWhole() == zero){
            return false;
        }

          if(left.getWhole() == zero && right.getNumerator() == zero){
            return true;
        }

        if(left.getWhole() == zero && right.getWhole() == zero){
            if(left.getDenom() > right.getDenom()){
                return true;
            }
        }

        if(left.getWhole() != zero && right.getWhole() != zero && left.getNumerator() != zero && right.getNumerator() != zero){
            if(left.getWhole() > right.getWhole()){
                return true;
            }
        }

        return false;
    }


    bool operator<=(const Rational& lhs, const Rational& rhs) {
        if ((lhs == rhs) || (lhs < rhs)) {
            return true;
        }
        return false;
    }

    bool operator>=(const Rational& lhs, const Rational& rhs) {
        if ((lhs == rhs) || (lhs > rhs)) {
            return true;
        }
        return false;
        }
    

    bool operator==(const Rational& lhs, const Rational& rhs) {
        Rational left(lhs);
        Rational right(rhs);
        Integer zero("0");

         if(left.getNumerator() == zero && right.getNumerator() == zero){
            if(left.getWhole() == right.getWhole()){
                return true;
            }else{
                return false;
            }
        }

        if(left.getWhole() == zero && right.getNumerator() == zero){
            if(left.getWhole() == right.getWhole()){
                return true;
            }else{
                return false;
            }
        }

        if(left.getNumerator() == zero && right.getWhole() == zero){
            return false;
        }

          if(left.getWhole() == zero && right.getNumerator() == zero){
            return false;
        }

        if(left.getWhole() == zero && right.getWhole() == zero){
            if((left.getNumerator() == right.getNumerator()) && (left.getDenom() == right.getDenom())){
                return true;
            }
        }

        if(left.getWhole() == right.getWhole() && left.getNumerator() == right.getNumerator() && left.getDenom() == right.getDenom()){
        return true;
        }


     return false;
    }

    bool operator!=(const Rational& lhs, const Rational& rhs) {
         if (lhs == rhs) {
            return false;
        }
        return true;
    }
}

//---------------------------------------------------------------------------------------------------------------------
