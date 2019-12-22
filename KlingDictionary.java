import java.util.ArrayList;
import java.util.Iterator;



//This class represents a dictionary of Klingon words,
//where each word is represented by class "KlingWord"
public class KlingDictionary {

	// one member variable that represents the dictionary as an ArrayList;
	// all methods below will add/read/remove/etc. from this variable
	public ArrayList<KlingWord> dict;

	// constructor
	public KlingDictionary () {
		dict = new ArrayList<KlingWord>();
	}

	// Helper method to build the dictionary "dict" from two String arrays
	// Returns the number of words that have been added successfully
	/*** Warning: Do NOT modify this method ***/
	public int buildDictionary() {
		// A list of Klingon words to be added to this dictionary
		String[] knWordsArray= {"adanji", "baH", "baktag", "batleth", "Bekk", "fote", "forshak", "ghoptu", "lok",	"eff", "grr",	"keshmalek",	"drumpf", "daH", "Kyamo"};
		// The corresponding English translations are stored in the same order below
		String[] enWordsArray = {"perfume", "blah", "insult", "sword", "soldier", "vote", "car", "insult", "look", "insult", "insult", "gameover", "prince", "duh", "Beautiful"};

		// Variables to be used inside the for-loop
		String knWord = "";
		String enWord = "";
		int numWords = 0;
		for(int i = 0; i < knWordsArray.length; i++) {
			knWord = knWordsArray[i];  //read the KN word
			enWord = enWordsArray[i];  //read the corresponding EN translation
			KlingWord word = new KlingWord(knWord, enWord); //create a KlingWord object

			addWord(word); //this will only work properly after you implement addWord() below!!
			numWords++; //update word counter
		}

		return numWords;
	}

	// -------------- Assignment#2 Dictionary Methods Below -------------- //
	//add a non duplicate klingon word. if addition successful, return 0, -1 otherwise
	public int addWord(KlingWord newWord) {
		Iterator<KlingWord> itr = dict.iterator();

	while(itr.hasNext()) {
		if (itr.next().getKN().toLowerCase().equals(newWord.getKN().toLowerCase()))   // when the two word are the same
			return -1;
	}
	dict.add(newWord);  // add new word since it's not in the dictionary
	return 0;
	}
	//adds klingon word to the end or replaces a duplicate with the word 
	//returns 0 for proper replacement, -1 otherwise
	public int replaceOrAddWord(KlingWord oldWord, KlingWord newWord) {
		int index = -1;

		Iterator<KlingWord> itr = dict.iterator();

		while(itr.hasNext()) {    // iterates through the KlingWord

			KlingWord temporary = itr.next();

			if(temporary.getKN().toLowerCase().equals(oldWord.getKN().toLowerCase())) {    // checks if the word are the same
				index = dict.indexOf(temporary);    // set the index to the index of temporary
				itr.remove(); //remove the last iteration
			} 
		}

		// if oldWord is not found in the dictionary
		if (index == -1) {
			dict.add(newWord);
			return -1;
		}

		dict.add(index,newWord);
		return 0;	

	}

	//method delets all klingon words whose english meaning is badEN and returns # deleted
	public int deleteFromDict(String badEN){

		int counter = 0; // keeps track of number of words

		Iterator<KlingWord> itr = dict.iterator();

		while(itr.hasNext()) {
			if (itr.next().getEN().toLowerCase().equals(badEN.toLowerCase())) {      // checks if the word is equal to badEN
				itr.remove();
				counter++;
			}
		}

		return counter;
	}


	//method removes all klingon words whose length is greater than 3 letters
	public void shortDict(){
		int limit = 3;  // word length limit
		Iterator<KlingWord> itr = dict.iterator();

		while(itr.hasNext()) {
			if(itr.next().getKN().toLowerCase().length() > limit)   // check if the word is over the limit
				itr.remove();
		}
	}


	/*creates a subdictionary of klingon words whose initial and final letter are the same 
 regardless of english spelling*/
	public KlingDictionary createSubDict(){
		KlingDictionary newDict = new KlingDictionary();
		Iterator<KlingWord> itr = dict.iterator();

		while(itr.hasNext()) {

			KlingWord temporary = itr.next();

			char firstLetter = temporary.getKN().toLowerCase().charAt(0);
			char lastLetter = temporary.getKN().toLowerCase().charAt(temporary.getKN().toLowerCase().length() - 1);
			if(firstLetter == lastLetter) {   // check if first and last letters are the same
				newDict.addWord(temporary);
			}
		}

		return newDict;
	}

	//*Prints all the KlingWord objects inside the ArrayList dict.
	/* Remember: the method toString() in class KlingWord will be invoked automatically
/* when an object of class KlingWord is passed to System.out.println(). */
	public void printDictionary(){
		for( KlingWord kw : dict ){
			System.out.println(kw);
		}
	}


	// Main method includes constructing your dictionary and testing its methods
	public static void main(String[] args) {
		int result;
		KlingDictionary klingdict = new KlingDictionary();

		// Build the dictionary
		result = klingdict.buildDictionary();
		System.out.println("buildDictionary() result => " + result);
		// Print dictionary
		klingdict.printDictionary();

		// Add word
		result = klingdict.addWord(new KlingWord("klingothing","nothing"));
		// Remember, '\n' below stands for: print a "new line"
		System.out.println("\naddKlingWordictoDict() result => " + result);
		klingdict.printDictionary();

		// Replace or add word
		KlingWord testword = new KlingWord("forshak","vehicle");
		KlingWord newWord = new KlingWord("gamma","beta");
		result = klingdict.replaceOrAddWord(testword, newWord);
		System.out.println("\nreplaceOrAddWord(" + testword.getKN() +
				"," + newWord.getKN() + ") result => " + result);
		klingdict.printDictionary();

		// Get special words in a new dictionary
		KlingDictionary specialDict = klingdict.createSubDict();
		System.out.println("\nCalled createSubDict()...");
		specialDict.printDictionary(); // print new dictionary with special words

		// Apply new law that requires removing long words
		klingdict.shortDict();
		System.out.println("\nApplied shortenDict()...");
		klingdict.printDictionary();

		// Delete all words that have this English meaning
		String badEN = "insult";
		result = klingdict.deleteFromDict(badEN);
		System.out.println("\ndeleteFromDict() result => " + result);
		klingdict.printDictionary();
	}

}
