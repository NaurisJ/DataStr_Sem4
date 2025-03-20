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
	
	
	public void add(Ttype element) throws Exception{
		if (element == null) {
			throw new Exception("Element can not be null!");
		}
		
		//TODO ja ir pirmais elements, tad izveidot jaunu mezglu un to uzlikt ka saknes mezglu
		
		if (!isFull()) {
			if (isEmpty()) {
				MyBSTNode<Ttype> newNode = new MyBSTNode(element);
				root = newNode;
				counter++;
			} else {
				addHelper(element,root);
			}
		} else {
			throw new Exception("BST is full!");
		}
	}
	
	private void addHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		//parbaudam pa kuru pusi elements tiks pievienots.
		
		// ja elements ir lielaks par currentNode elementu, tad japarvietojas uz labo pusi.
		if (((Comparable)element).compareTo(currentNode.getElement()) == 1) {
			// ja neeksiste labais bersn ,tad var veidot jaunu mezglu un pielipinat kaa labo bernu
			if (currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setRightCh(newNode);
				newNode.setParent(currentNode);
				counter++;
			} else {
				addHelper(element, currentNode.getRightCh());
			}
			
			
		}else if (((Comparable)element).compareTo(currentNode.getElement()) == -1) {
			if (currentNode.getLeftCh() == null) {
				MyBSTNode<Ttype> newNode = new MyBSTNode<>(element);
				currentNode.setLeftCh(newNode);
				newNode.setParent(currentNode);
				counter++;
			} else {
				addHelper(element,currentNode.getLeftCh());
			}
		}
		
		// ja elements ir mazakaks par currentNode elementu, tad japarvirzas uz kreiso pusi
	}
	
}
