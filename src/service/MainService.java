package service;

import datastr.MyBST;

public class MainService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyBST<Integer> bstInteger = new MyBST<Integer>();
		
		try {
			System.out.println("--------------------------------------ADD-----------------------");
			bstInteger.add(7);
			bstInteger.add(10);
			bstInteger.add(4);
			bstInteger.add(15);
			bstInteger.add(6);
			bstInteger.print();
			System.out.println("----------------------------SEARCH------------------------------");
			System.out.println("6? ->" + bstInteger.search(6));
			System.out.println("15? ->" + bstInteger.search(15));
			System.out.println("100? ->" + bstInteger.search(100));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
