package pkgCore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {

	private ArrayList<Word> words = new ArrayList<Word>();

	public Dictionary() {
		LoadDictionary();
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	private void LoadDictionary() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("util/words.txt");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			while (reader.ready()) {
				String line = reader.readLine();
				if (!line.trim().isBlank() && !line.trim().isEmpty())
					words.add(new Word(line.trim()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(words, Word.CompWord);
	}

	public Word findWord(String strWord) {

		Word w = new Word(strWord);
		int idx = Collections.binarySearch(this.words, w, Word.CompWord);

		if (idx < 0)
			return null;
		else
			return words.get(idx);
	}

	/**
	 * match - Recursive method to find a match between a string and wildcard
	 * characters ? and *
	 * 
	 * @author BRG
	 * @version Lab #2
	 * @since Lab #2
	 * @param first  - String with wildcards
	 * @param second - String without wildcards
	 * @return
	 */
	private boolean match(String first, String second) {
		if(first.charAt(0) == '?') //wildcard 1
        {
            if(first.length()==1 && second.length()==1) //end of the strings
                return true;
            
            else if(first.length()==1 || second.length()==1) //different lengths
                return false;
            
            else
                return match(first.substring(1),second.substring(1)); //continue to next
        }
        
        else if(first.charAt(0) == '*') //wildcard 2
            return true;
        
        else if (first.charAt(0) == second.charAt(0))
        {
            if(first.length()==1 && second.length()==1) //end of the strings
                return true;
            
            else if(first.length()==1 || second.length()==1) //different lengths
                return false;
            
            else
                return match(first.substring(1),second.substring(1)); //continue to next
            
        } //end
        
        return false;
    }

}
