package datastr;

public class MyBSTNode <Ttype>{
	private Ttype element;
	private MyBSTNode<Ttype> leftCh = null;
	private MyBSTNode<Ttype> rightCh = null;
	private MyBSTNode<Ttype> parent = null;
	
	public MyBSTNode(Ttype inputElement) {
		if (inputElement != null) {
			element = inputElement;
		} else {
			element = (Ttype) new Object();
		}
	}
	
	public String toString() {
		return element + " ";
	}

}
