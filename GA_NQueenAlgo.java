package lab7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();
	
	/**
	 * 
	 */
	public GA_NQueenAlgo() {
		initPopulation();
	}

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		// Enter your code here
		Node re;

		int k = 0;
		while (k++ < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<Node>();
			for (int i = 0; i < POP_SIZE; i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				re = reproduce(x, y);
				if (rd.nextDouble() < MUTATION_RATE) {
					mutate(re);	
				}
				if (re.getH() == 0) {
					return re;
				}
				newPopulation.add(re);
			}
			population = newPopulation;
		}
		Collections.sort(population);
		return population.get(0);
	}

	// Mutate an individual by selecting a random Queen and //move it to a random
	// row.
	public void mutate(Node node) {
		// Enter your code here
		node.setRow(rd.nextInt(Node.N), rd.nextInt(Node.N));

	}

	// Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		// Enter your code here
		int c = rd.nextInt(Node.N);
		Node node = new Node();
		node.generateBoard();
		for (int i = 0; i < c; i++) {
			node.setRow(i, x.getRow(i));
		}
		for (int i = c; i < Node.N; i++) {
			node.setRow(i, y.getRow(i));
		}

		return node;
	}

	// Select K individuals from the population at random and //select the best out
	// of these to become a parent.
	public Node getParentByTournamentSelection() {
		// Enter your code here
		ArrayList<Node> nodes = new ArrayList<>();
		int k = 7;
		for (int i = 0; i < k; i++) {
			nodes.add(getParentByRandomSelection());
			Collections.sort(nodes);
		}

		return nodes.get(0);
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		// Enter your code here
		int index = rd.nextInt(POP_SIZE -1);
		Node parent = population.get(index);

		return parent;
	}
}
