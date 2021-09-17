package pkgCore;

import java.util.Comparator;
import java.util.UUID;

public class Word implements Comparable<Word> {

	private UUID WordID;
	private String Word;

	public Word(String strWord) {
		WordID = UUID.randomUUID();
		Word = strWord.toUpperCase(); //test
		//System.out.println("Testing");
	}
	
	@Override
	public boolean equals(Object obj) {
		Word w = (Word)obj;
		return (w.getWord().equals(this.getWord()));
	}

	@Override
	public int compareTo(Word o) {
		return this.getWord().compareTo(o.getWord());
	}

	public static Comparator<Word> CompWord = new Comparator<Word>() {
		public int compare(Word o1, Word o2) {
			if (o1.getWord().equals(o2.getWord())) {
				return o1.getWord().compareTo(o2.getWord());
			}
			return o1.getWord().compareTo(o2.getWord());
		}
	};

	public String getWord() {
		return Word;
	}

}
