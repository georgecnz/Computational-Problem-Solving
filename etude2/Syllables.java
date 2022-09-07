import java.util.*;

public class Syllables {
    public static void main(String[] args) throws Exception {
        
        Scanner scan = new Scanner(System.in); 
        System.out.println("Please enter a word (or multiple), enter y to exit program):");

        do { // loop to allow continuous input
            
            String word = scan.nextLine();

            if (word.equals("y")) { // to exit program
                break;
            }

            if (word.equals("")){
                System.out.println("Please enter a word");
                continue;
            }
            
            // need to convert string to all lowercase or similar 
            String wordSameCase = word.toLowerCase();
            word = wordSameCase;

            // to check only letters entered and only one word 
            if (!word.matches("[a-z]*")){
                System.out.println("Error: Please only enter one word, letters only");
                continue;
            }

            int syllables = syllableCount(word);
            System.out.println(syllables);

        } while (scan.hasNextLine());

        scan.close();
    }

    private static int syllableCount(String word){
        int syllables = 0; // holds number of syllables
        
        // adds a syllables per vowel
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || 
                word.charAt(i) == 'i' || word.charAt(i) == 'o' ||
                word.charAt(i) == 'u' || word.charAt(i) == 'y') {
                    syllables++;
                    // subtracts a syllable if more than one vowel in a row
                    if (i != 0 && (word.charAt(i - 1) == 'a' || 
                        word.charAt(i - 1) == 'e' || 
                        word.charAt(i - 1) == 'i' || word.charAt(i - 1) == 'o' ||
                        word.charAt(i - 1) == 'u' || word.charAt(i - 1) == 'y')) {
                        syllables--; 
                        if (i != 1 && (word.charAt(i - 2) == 'e' || word.charAt(i - 2) == 'a'
                        || word.charAt(i - 2) == 'i' || word.charAt(i - 2) == 'o' ||
                        word.charAt(i - 2) == 'u' || word.charAt(i - 2) == 'y')){
                            syllables++;
                        }                
                           
                    }  
                }
        }

        // fixes syllable count with prefixes and a vowel after
        if (word.matches("((pre)|(re)|(extra)|(anti)|(auto)|(semi)){1}[aeiouy]{1}.*") ||
            word.matches("((tele)|(ultra)|(contra)|(de)|(hetero)|(tri)|(homo)){1}[aeiouy]{1}.*") 
            || word.matches("((homeo)|(intra)|(intro)|(macro)|(micro)|(mono)|(omni)){1}[aeiouy]{1}.*") ||
            word.matches("((pro)|(uni)|(mega)|(aor)){1}[aeiouy]{1}.*")){
            syllables++;
        }

        // fixes syllable count for words ending in e and not a vowel before
        if (word.charAt(word.length() - 1) == 'e' && (word.charAt(word.length() - 2) != 'a'
            && word.charAt(word.length() - 2) != 'e' && word.charAt(word.length() - 2) != 'i'
            && word.charAt(word.length() - 2) != 'o' && word.charAt(word.length() - 2) != 'u'
            && word.charAt(word.length() - 2) != 'y')){
            syllables--;
        }

        // fixes syllable count for specific suffixes
        if (word.matches(".*((rious)|(vious)|(pious)|(dious)|(bious)|(nious)|(rian))") ||
            word.matches(".*((rium)|(dient)|(eo)|(iate)|(lion)|(sium)|(nium))") ||
            word.matches(".*[aeiouy](ing).*")){
                syllables++;
            }

        // fixes syllable count for words ending in le without a vowel before and
        // the word coincidence
        if (word.matches("(.*[^aeiouy](le))||(coincidence)")){
            syllables++;
        }  

        if (syllables == 0){
            syllables++;
        }
        
        return syllables;
    }

}
