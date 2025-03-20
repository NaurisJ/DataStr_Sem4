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
			
			// kreisa puse
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
	
	//TODO
	//search
	//funkcijas deklaracija, ieklaujot mekleto elementu kaa input
	// veikt nepieciesamas parbaudes - element == null, ja ir tukss
	// izsauksim helper funkciju, sakot no root mezgla
	
	//izveidot privatu searchhelp funkciju
	// parbaudam vai  currentNode elementa vertiba sakrit ar mekletu, ja ja, tad argriezam true
	// ja nesakrit, tad parbaudit kur veikt meklesanu - pa labo pusi vai pa kreiso
	// ja pa kreiso - tad izsaucam helper funkciju rekursivi uz currentnode mezgla kreiso bernu
	// ja pa labo - tad izsaucam helper funkciju rekursivi uz currentNode mezgla labo bernu
	
	public boolean search(Ttype element) throws Exception{
		if (element == null) {
			throw new Exception("Element null");
		}
		
		if (isEmpty()) {
			throw new Exception("BST is empty");
		}
		return (boolean) searchHelper(element,root);
		
		
	}
	
	private boolean searchHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		if (element.equals(currentNode.getElement())) {
			return true;
		} else {
			if (((Comparable)element).compareTo(currentNode.getElement()) == 1) {
				if (currentNode.getRightCh() != null) {
					return searchHelper(element,currentNode.getRightCh());
				} else {
					return false;
				}
			} else if (((Comparable)element).compareTo(currentNode.getElement()) == -1) {
				if (currentNode.getLeftCh() != null) {
					return searchHelper(element,currentNode.getLeftCh());
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	
	public void print() throws Exception{
		if (isEmpty()) {
			throw new Exception("BST is empty");
		}
		printHelper(root);
	}
	
	private void printHelper(MyBSTNode<Ttype> currentNode) {
		// PREFIX apstaigasana ||| Sakne-kreisaisBerns-labisBerns
		System.out.println("P -> " + currentNode.getElement());
		
		// ja eksiste kreisais berns
		if (currentNode.getLeftCh() != null) {

			System.out.println("Left ch -> " + currentNode.getLeftCh().getElement() + " (" + currentNode.getElement()
			+ ")");
			printHelper(currentNode.getLeftCh());			
		}
		// ja eksiste labais berns
		if (currentNode.getRightCh() != null) {
			
			System.out.println("Right ch -> " + currentNode.getRightCh().getElement() + " ( " +
			currentNode.getElement() + ")");
			printHelper(currentNode.getRightCh());
		}
	}
	
	public void makeEmpty() throws Exception{
		if (!isEmpty()) {
			root = null;
			counter = 0;
			System.gc();
		}
	}
	
	public void delete(Ttype element) throws Exception{
		if (isEmpty()) {
			throw new Exception("BST is empty");
		}
		
		if (!search(element)) {
			throw new Exception("Element doesn't exist in BST and can't be deleted");
		}
		
		deleteHelper(element,root);
	}
	
	private void deleteHelper(Ttype element, MyBSTNode<Ttype> currentNode) {
		if (element.equals(currentNode.getElement())) {
			//ja currentnode ir kaa lapa
			if (currentNode.getLeftCh() == null && currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				
				//janonem saite uz kreiso bernu
				if (parentOfCurrentNode.getLeftCh().getElement().equals(element)) {
					parentOfCurrentNode.setLeftCh(null);
				} else if (parentOfCurrentNode.getRightCh().getElement().equals(element)) {
					parentOfCurrentNode.setRightCh(null);
				}
			}
			
			// ja currentnode mezglam ir tikai viens berns
			else if (currentNode.getLeftCh() == null && currentNode.getRightCh() != null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				MyBSTNode<Ttype> rightChildOfCurrentNode = currentNode.getRightCh();
				
				parentOfCurrentNode.setRightCh(rightChildOfCurrentNode);
				rightChildOfCurrentNode.setParent(parentOfCurrentNode);
			}
			else if (currentNode.getLeftCh() != null && currentNode.getRightCh() == null) {
				MyBSTNode<Ttype> parentOfCurrentNode = currentNode.getParent();
				MyBSTNode<Ttype> leftChildOfCurrentNode = currentNode.getLeftCh();
				
				parentOfCurrentNode.setLeftCh(leftChildOfCurrentNode);
				leftChildOfCurrentNode.setParent(parentOfCurrentNode);
			}
			
			// ja currentnode mezglam ir abi berni
			else if (currentNode.getLeftCh() != null && currentNode.getRightCh() != null) {
				
			}
		} else {
			// ja elements ir lielaks par currentnode elementu
			if (((Comparable)element).compareTo(currentNode.getElement()) == 1) {
				if (currentNode.getRightCh() != null) {
					deleteHelper(element,currentNode.getRightCh());
				}
			} else if (((Comparable)element).compareTo(currentNode.getElement()) == -1) {
				if (currentNode.getLeftCh() != null) {
					deleteHelper(element, currentNode.getLeftCh());
				}
			}
		}
	}
	
	
	
}
