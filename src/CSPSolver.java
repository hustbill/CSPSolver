
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
			IntVariable a2 = new IntVariable(net, 1, nodes, "a2");
			IntVariable a3 = new IntVariable(net, 1, nodes, "a3");
			IntVariable a4 = new IntVariable(net, 1, nodes, "a4");
			IntVariable a5 = new IntVariable(net, 1, nodes, "a5");
			IntVariable[] color = {
					a1, a2, a3, a4, a5
			};
			IntVariable b1 = new IntVariable(net, 1, nodes, "b1");
			IntVariable b2 = new IntVariable(net, 1, nodes, "b2");
			IntVariable b3 = new IntVariable(net, 1, nodes, "b3");
			IntVariable b4 = new IntVariable(net, 1, nodes, "b4");
			IntVariable b5 = new IntVariable(net, 1, nodes, "b5");
			IntVariable[] nationality = {
					b1, b2, b3, b4, b5
			};
			IntVariable c1 = new IntVariable(net, 1, nodes, "c1");
			IntVariable c2 = new IntVariable(net, 1, nodes, "c2");
			IntVariable c3 = new IntVariable(net, 1, nodes, "c3");
			IntVariable c4 = new IntVariable(net, 1, nodes, "c4");
			IntVariable c5 = new IntVariable(net, 1, nodes, "c5");
			IntVariable[] drink = {
					c1, c2, c3, c4, c5
			};
			IntVariable d1 = new IntVariable(net, 1, nodes, "d1");
			IntVariable d2 = new IntVariable(net, 1, nodes, "d2");
			IntVariable d3 = new IntVariable(net, 1, nodes, "d3");
			IntVariable d4 = new IntVariable(net, 1, nodes, "d4");
			IntVariable d5 = new IntVariable(net, 1, nodes, "d5");
			IntVariable[] smoke = {
					d1, d2, d3, d4, d5
			};
			IntVariable e1 = new IntVariable(net, 1, nodes, "e1");
			IntVariable e2 = new IntVariable(net, 1, nodes, "e2");
			IntVariable e3 = new IntVariable(net, 1, nodes, "e3");
			IntVariable e4 = new IntVariable(net, 1, nodes, "e4");
			IntVariable e5 = new IntVariable(net, 1, nodes, "e5");
			IntVariable[] pet = {
					e1, e2, e3, e4, e5 
			};
			new NotEquals(net, color);
			new NotEquals(net, nationality);
			new NotEquals(net, drink);
			new NotEquals(net, smoke);
			new NotEquals(net, pet);
			// The b1 lives in the a1 node.
			b1.equals(a1);
			// The b2 owns the e1.
			b2.equals(e1);
			// c1 is drunk in the a2 node.
			c1.equals(a2);
			// The b3 drinks c2.
			b3.equals(c2);
			// The a2 node is immediately to the right of the a3 node.
			rightOf(a2, a3);
			// The d1 smoker owns e2.
			d1.equals(e2);
			// d2 are smoked in the a4 node.
			d2.equals(a4);
			// c3 is drunk in the middle node.
			c3.equals(3);
			// The b4 lives in the first node.
			b4.equals(1);
			// The man who smokes d3 lives in the node next to the man with the e3.
			nextTo(d3, e3);
			// d2 are smoked in the node next to the node where the e4 is kept.
			nextTo(d2, e4);
			// The d4 smoker drinks c4.
			d4.equals(c4);
			// The b5 smokes d5.
			b5.equals(d5);
			// The b4 lives next to the a5 node.
			//nextTo(b4, a5);
	
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
