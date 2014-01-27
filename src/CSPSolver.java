
public class CSPSolver {
	static int nodes = 5;
		
		static void rightOf(IntVariable v1, IntVariable v2) {
			v1.equals(v2.add(1));
		}
		
		static void nextTo(IntVariable v1, IntVariable v2) {
			v1.subtract(v2).abs().equals(1);
		}
		
		static String find(int value, IntVariable[] vs, Solution solution) {
			for (IntVariable v : vs) {
				if (solution.getIntValue(v) == value) {
					return v.getName();
				}
			}
			return null;
		}
		
		public static void main(String[] args) {
			Network net = new Network();
			IntVariable a1 = new IntVariable(net, 1, nodes, "a1");
			IntVariable green = new IntVariable(net, 1, nodes, "green");
			IntVariable ivory = new IntVariable(net, 1, nodes, "ivory");
			IntVariable yellow = new IntVariable(net, 1, nodes, "yellow");
			IntVariable blue = new IntVariable(net, 1, nodes, "blue");
			IntVariable[] color = {
					a1, a2, a3, a4, a5
			};
			IntVariable englishman = new IntVariable(net, 1, nodes, "Englishman");
			IntVariable spaniard = new IntVariable(net, 1, nodes, "Spaniard");
			IntVariable ukrainian = new IntVariable(net, 1, nodes, "Ukrainian");
			IntVariable norwegian = new IntVariable(net, 1, nodes, "Norwegian");
			IntVariable japanese = new IntVariable(net, 1, nodes, "Japanese");
			IntVariable[] nationality = {
					englishman, spaniard, ukrainian, norwegian, japanese
			};
			IntVariable coffee = new IntVariable(net, 1, nodes, "coffee");
			IntVariable tea = new IntVariable(net, 1, nodes, "tea");
			IntVariable milk = new IntVariable(net, 1, nodes, "milk");
			IntVariable orangeJuice = new IntVariable(net, 1, nodes, "orange juice");
			IntVariable water = new IntVariable(net, 1, nodes, "water");
			IntVariable[] drink = {
					coffee, tea, milk, orangeJuice, water
			};
			IntVariable oldGold = new IntVariable(net, 1, nodes, "Old Gold");
			IntVariable kools = new IntVariable(net, 1, nodes, "Kools");
			IntVariable chesterfields = new IntVariable(net, 1, nodes, "Chesterfields");
			IntVariable luckyStrike = new IntVariable(net, 1, nodes, "Lucky Strike");
			IntVariable parliaments = new IntVariable(net, 1, nodes, "Parliaments");
			IntVariable[] smoke = {
					oldGold, kools, chesterfields, luckyStrike, parliaments
			};
			IntVariable dog = new IntVariable(net, 1, nodes, "dog");
			IntVariable snails = new IntVariable(net, 1, nodes, "snails");
			IntVariable fox = new IntVariable(net, 1, nodes, "fox");
			IntVariable horse = new IntVariable(net, 1, nodes, "horse");
			IntVariable zebra = new IntVariable(net, 1, nodes, "zebra");
			IntVariable[] pet = {
					dog, snails, fox, horse, zebra 
			};
			new NotEquals(net, color);
			new NotEquals(net, nationality);
			new NotEquals(net, drink);
			new NotEquals(net, smoke);
			new NotEquals(net, pet);
			// The Englishman lives in the a1 node.
			englishman.equals(a1);
			// The Spaniard owns the dog.
			spaniard.equals(dog);
			// Coffee is drunk in the green node.
			coffee.equals(green);
			// The Ukrainian drinks tea.
			ukrainian.equals(tea);
			// The green node is immediately to the right of the ivory node.
			rightOf(green, ivory);
			// The Old Gold smoker owns snails.
			oldGold.equals(snails);
			// Kools are smoked in the yellow node.
			kools.equals(yellow);
			// Milk is drunk in the middle node.
			milk.equals(3);
			// The Norwegian lives in the first node.
			norwegian.equals(1);
			// The man who smokes Chesterfields lives in the node next to the man with the fox.
			nextTo(chesterfields, fox);
			// Kools are smoked in the node next to the node where the horse is kept.
			nextTo(kools, horse);
			// The Lucky Strike smoker drinks orange juice.
			luckyStrike.equals(orangeJuice);
			// The Japanese smokes Parliaments.
			japanese.equals(parliaments);
			// The Norwegian lives next to the blue node.
			//nextTo(norwegian, blue);
	
			Solver solver = new DefaultSolver(net);
			int count = 0;
			for (solver.start(); solver.waitNext(); solver.resume()) {
				Solution solution = solver.getSolution();
				count++;
				System.out.println("Solution " + count);
//				System.out.println(solution);
				for (int node = 1; node <= nodes; node++) {
					System.out.println("\tNode " + node
							+ ": " + find(node, color, solution)
							+ ", " + find(node, nationality, solution)
							+ ", " + find(node, drink, solution)
							//+ ", " + find(node, smoke, solution)
							//+ ", " + find(node, pet, solution)
							);
				}
				System.out.println();
			}
		}
	
}
