package lab7;
public class test {
public static void main(String[] args) {
	
	GA_NQueenAlgo a=new GA_NQueenAlgo();
	Node node=a.execute();
	node.displayBoard();
	System.out.println(node.getH());
}
}
