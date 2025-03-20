package datastr;

public class MyBST <Ttype>{

	private MyBSTNode<Ttype> root = null;
//	private MyBSTNode<Ttype> lastLeaf = null;
	private int counter = 0;
	
	// tiks izmantots Object bezargumenta konstruktors
	
	public int length() {
		return counter;
	}
	
	public boolean isEmpty() {
		return (counter == 0);
	}
	
	// loti iespejams, ka nekad neizpildsisies, jo OS nodrosinas RAM parvaldibu
	private boolean isFull() {
		try {
			new MyBSTNode<Integer>(45); // meginam RAM atmina ielikt mezglu
			return false; // ja tas izdodas tad saraksts nav pilns
		} catch(OutOfMemoryError e) {
			return true; // bet ja mezglu nevar vairs RAM atmina ielikt, tad saraksts ir pilns
		}
	}
	
}
