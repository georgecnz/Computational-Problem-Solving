import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.*;
/*
* Etude 5- COSC326
* @author George Churton & Dougal Colquhoun.
* Look Who's Talking.
* This file takes basic english sentences and translates them to Te Reo Maori. 
*/

public class translator{
    public static int tenseMarker = 0; //1 = past tense, 2 = present tense, 3 = future tense
    public static int subjectMarker = 0; //1 = one person, 2 = two people, 3 = three or more people.
    public static String sentenceStarter;
    public static String subject;
    public static String verb;
    public static String lastWordOut;
    public static String aMacron; 
    public static String oMacron;

    /* main method upon the program is run
    * @param args
    */

    public static void main(String[] args){
        System.out.println("______________________________________________________________");
        System.out.println("Translating english sentences from input file...");
        System.out.println("");

        try{
            BufferedReader reader = new BufferedReader(new FileReader("english.txt"));
            String sentence = reader.readLine();
            while(sentence != null){

                setup(sentence);
                sentenceBuilder(verb, subject, sentenceStarter);
                sentence = reader.readLine();
            }  
            reader.close();     

        }catch(Exception e){
            System.err.println(e + "Invalid: file not found");
        }
        System.out.println("______________________________________________________________");
    }

    /*
    * the static method setup acts as a intermediary for input to be passed to computational methods
    * @param String sentence - each line of the input file represents a sentence.
    */

    public static void setup(String sentence){
        String compSentence = sentence.toLowerCase();
        aMacron =  "\u0101";
        oMacron = "\u014D";

        starterCalc(compSentence);
        subjectInspector(compSentence);
        verbDictionary(compSentence);
        
    }

    public static void starterCalc(String compSentence){
        
         if((compSentence.contains("read") || compSentence.contains("went") || compSentence.contains("called") || compSentence.contains(" wanted") || compSentence.contains("made") 
         || compSentence.contains("saw") || compSentence.contains("asked") || compSentence.contains("learnt") || compSentence.contains("wanted "))){
             tenseMarker = 1; //past tense
             }
        
        if(compSentence.contains(" will ") || compSentence.contains("make")){
            tenseMarker = 3; //future tense
        }

        if((compSentence.contains(" are ") || compSentence.contains(" am") || compSentence.contains("am " ) || compSentence.contains(" we ") || compSentence.contains(" is "))){
            tenseMarker = 2; //present
    }

        if(tenseMarker == 1){
            sentenceStarter = "I";
        }

         if(tenseMarker == 2){
            sentenceStarter = "Kei te";
         }

         if(tenseMarker == 3){
            sentenceStarter = "Ka";
        }
    }

    /*
    * the static method subjectInspector finds the number of speakers involved within a sentence.
    * @param String sentence - each line of the input file represents a sentence.
    */


    public static void subjectInspector(String compSentence){
        if(compSentence.contains("1 incl") || compSentence.contains(("1 excl"))){
            onePerson(compSentence);

        }else if(compSentence.contains("2 incl")){
            twoIncl(compSentence);

        }else if(compSentence.contains("2 excl")){
            twoExcl(compSentence);

        }else if(compSentence.matches("(.*)[0-9] incl(.*)")){
            threeIncl(compSentence);

        }else if(compSentence.matches("(.*)[0-9] excl(.*)")){
            threeExcl(compSentence);
            
        }else{
            onePerson(compSentence);
        }
 
    }

    /*
    * the static method onePerson is invoked by the subjectInspector method when there is one speaker.
    * here english can be translated accordingly.
    * @param String sentence - each line of the input file represents a sentence.
    */


        public static void onePerson(String compSentence){
            if(compSentence.contains("i") || compSentence.contains("me") || (compSentence.contains("i "))){
                subject = "au";
            }
    
            //one person- excludes speaker
            if(compSentence.contains("you")){
                subject = "koe";
            }
            //one person- neither the speaker nor the listener(s)
            if(compSentence.contains("he") || compSentence.contains("she") 
            || compSentence.contains("him") || compSentence.contains("her")){
                subject = "ia";
            }
        
        }

    /*
    * the static method twoExcl is invoked by the subjectInspector method when there is two speakers excluding listeners.
    * here english can be translated accordingly.
    * @param String sentence - each line of the input file represents a sentence.
    */


        public static void twoExcl(String compSentence){
            if((compSentence.contains(" we ") || compSentence.contains("us"))){
                subject = "m"+ aMacron + "ua";

            }else if(compSentence.contains("they ") || compSentence.contains("them") || (compSentence.contains(" they"))){
                subject = "r" + aMacron + "ua";
            }
        }

    /*
    * the static method twoIncl is invoked by the subjectInspector method when there is two speakers including listeners.
    * here english can be translated accordingly.
    * @param String sentence - each line of the input file represents a sentence.
    */
    
    
        public static void twoIncl(String compSentence){
            if(compSentence.contains(" we ") || compSentence.contains("us") || (compSentence.contains("we "))){
                subject = "t" + aMacron + "ua";

            }else if(compSentence.contains("you")){
                subject = "k" + oMacron + "rua";

            }else if(compSentence.contains("they") || compSentence.contains("them")){
                subject = "r" + aMacron + "ua";
        }
    }
        
    /*
    * the static method threeIncl is invoked by the subjectInspector method when there is three speakers including listeners.
    * here english can be translated accordingly.
    * @param String sentence - each line of the input file represents a sentence.
    */
        
        public static void threeIncl(String compSentence){
            if(compSentence.contains("we") || compSentence.contains("us")){
                subject = "t" + aMacron + "tou";
            }

            if(compSentence.contains("you")){
                subject = "koutou";
            }
          
             if(compSentence.contains("they") || compSentence.contains("them")){
                subject = "r" + aMacron + "tou";
            }
        }

    /*
    * the static method threeExcl is invoked by the subjectInspector method when there is three speakers excluding listeners.
    * here english can be translated accordingly.
    * @param String sentence - each line of the input file represents a sentence.
    */

        public static void threeExcl(String compSentence){
            if(compSentence.contains("we") || compSentence.contains("us")){
                subject = "m" + aMacron + "tou";
            }
            
            if(compSentence.contains("they") || compSentence.contains("them")){
                subject = "r" + aMacron + "tou";
            }
        }

    /*
    * the static method verbDictionary contains the Te Reo version of english verbs for translation
    * @param String sentence - each line of the input file represents a sentence.
    */  


public static void verbDictionary(String compSentence){
    if(compSentence.contains("go")){
        verb = "haere";

    }else if(compSentence.contains("went")){
        verb = "haere";

    }else if(compSentence.contains("make") || compSentence.contains("made") || compSentence.contains("making") ){
        verb = "hanga";

    }else if(compSentence.contains("see") || compSentence.contains("saw")){
        verb = "kite";

    }else if(compSentence.contains("want")){
        verb = "hiahia";

    }else if(compSentence.contains("call")){
        verb = "karanga";

    }else if(compSentence.contains("ask")){
        verb = "p" + aMacron + "tai";

    }else if(compSentence.contains("read")){
        verb = "p" + aMacron + "nui";

    }else if(compSentence.contains("learn")){
        verb = "ako";

    }else if((compSentence.length() > 0 && compSentence.split("\\s+").length <= 1)){
        System.out.println("invalid sentence");
        verb = "";
        sentenceStarter = "";
        subject = "";

    }else{
        String quote = "\"";
        lastWordOut = compSentence.substring(compSentence.lastIndexOf(" ")+1);
        System.out.println("unknown verb " + quote + lastWordOut + quote); 
        verb = "";
        sentenceStarter = "";
        subject = "";
        }
    }


/*
* sentenceBuilder collects local variables/words that have been translated and builds them back into a sentence
* @param String verb, subject, sentenceString
* furthermore, here sentences are printed as output.
*/

public static void sentenceBuilder(String verb, String subject, String sentenceStarter){
    if(sentenceStarter != "" && subject != "" && verb != ""){
            System.out.println(sentenceStarter + " " + verb + " " + subject);

            verb = "";
            sentenceStarter = "";
            subject = "";
        
        }
    }

}