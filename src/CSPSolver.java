
public class CSPSolver {
	
//	public static void main(String[] args) {
//		// Create a constraint network
//		Network net = new Network();
//		// Declare variables
//		IntVariable x = new IntVariable(net);
//		IntVariable y = new IntVariable(net);
//		// x >= 0
//		x.ge(0);
//		// y >= 0
//		y.ge(0);
//		// x + y == 35
//		x.add(y).equals(35);
//		// 2x + 4y == 94
//		x.multiply(2).add(y.multiply(4)).equals(94);
//		// Solve the problem
//		Solver solver = new DefaultSolver(net);
//		Solution solution = solver.findFirst();
//		int xv = solution.getIntValue(x);
//		int yv = solution.getIntValue(y);
//		System.out.println("x = " + xv + ", y = " + yv);
//	}
//}
//
//class FirstStepHandler implements SolutionHandler {
//	IntVariable x;
//
//	IntVariable y;
//
//	public FirstStepHandler(IntVariable x, IntVariable y) {
//		this.x = x;
//		this.y = y;
//	}
//
//	public synchronized void solved(Solver solver, Solution solution) {
//		if (solution != null) {
//			int xv = solution.getIntValue(x);
//			int yv = solution.getIntValue(y);
//			System.out.println("x = " + xv + ", y = " + yv);
//		}
//	}

	
	
//	static void golomb(int m) {
//		int n = (1 << (m - 1)) - 1;
//		Network net = new Network();
//		IntVariable[] a = new IntVariable[m];
//		a[0] = new IntVariable(net, 0);
//		for (int i = 1; i < m; i++) {
//			a[i] = new IntVariable(net, 1, n);
//			a[i - 1].lt(a[i]);
//		}
//		IntVariable[] d = new IntVariable[m * (m - 1) / 2];
//		int k = 0;
//		for (int i = 0; i < m; i++) {
//			for (int j = i + 1; j < m; j++) {
//				d[k++] = a[j].subtract(a[i]);
//			}
//		}
//		new NotEquals(net, d);
//		net.setObjective(a[m - 1]);
//
//		Solver solver = new DefaultSolver(net, Solver.MINIMIZE);
//		Solution solution = solver.findBest();
//		System.out.print("0");
//		for (int i = 1; i < m; i++) {
//			System.out.print("," + solution.getIntValue(a[i]));
//		}
//		System.out.println();
//		System.out.println("Time = " + solver.getElapsedTime());
//	}
//
//	public static void main(String[] args) {
//		int m = 8;
//		if (args.length == 1) {
//			m = Integer.parseInt(args[0]);
//		}
//		golomb(m);
//	}

//	// eight queens  problem and solution 
//	static void queens(int n) {
//		Network net = new Network();
//		IntVariable[] q = new IntVariable[n];
//		IntVariable[] u = new IntVariable[n];
//		IntVariable[] d = new IntVariable[n];
//		for (int i = 0; i < n; ++i) {
//			q[i] = new IntVariable(net, 1, n);
//			u[i] = q[i].add(i);
//			d[i] = q[i].subtract(i);
//		}
//		new NotEquals(net, q);
//		new NotEquals(net, u);
//		new NotEquals(net, d);
//		Solver solver = new DefaultSolver(net);
//		for (solver.start(); solver.waitNext(); solver.resume()) {
//			Solution solution = solver.getSolution();
//			for (int i = 0; i < n; i++) {
//				int j = solution.getIntValue(q[i]);
//				System.out.print(j + " ");
//			}
//			System.out.println();
//		}
//		solver.stop();
//		long count = solver.getCount();
//		//long time = solver.getElapsedTime() / 1000;
//		
//		long time = 10;
//		System.out.println(count + " solutions found in " + time + " seconds");
//	}
//
//	public static void main(String[] args) {
//		int n = 8;
//		if (args.length == 1) {
//			n = Integer.parseInt(args[0]);
//		}
//		queens(n);
//	}

	//FT06 Problem
//	private static void ft06(Network net) {
//		int n = 37;
//		IntVariable[] v = new IntVariable[n];
//		for (int i = 0; i < v.length; i++) {
//			v[i] = new IntVariable(net, 0, IntDomain.MAX_VALUE);
//		}
//		IntVariable[][] jobs = {
//				{ v[1], v[2], v[3], v[4], v[5], v[6], v[0] },
//				{ v[7], v[8], v[9], v[10], v[11], v[12], v[0] },
//				{ v[13], v[14], v[15], v[16], v[17], v[18], v[0] },
//				{ v[19], v[20], v[21], v[22], v[23], v[24], v[0] },
//				{ v[25], v[26], v[27], v[28], v[29], v[30], v[0] },
//				{ v[31], v[32], v[33], v[34], v[35], v[36], v[0] } };
//		int[][] jobs_pt = {
//				{ 1, 3, 6, 7, 3, 6 },
//				{ 8, 5, 10, 10, 10, 4 },
//				{ 5, 4, 8, 9, 1, 7 },
//				{ 5, 5, 5, 3, 8, 9 },
//				{ 9, 3, 5, 4, 3, 1 },
//				{ 3, 3, 9, 10, 4, 1 } };
//		IntVariable[][] machines = {
//				{ v[2], v[11], v[16], v[20], v[29], v[34] },
//				{ v[3], v[7], v[17], v[19], v[26], v[31] },
//				{ v[1], v[8], v[13], v[21], v[25], v[36] },
//				{ v[4], v[12], v[14], v[22], v[30], v[32] },
//				{ v[6], v[9], v[18], v[23], v[27], v[35] },
//				{ v[5], v[10], v[15], v[24], v[28], v[33] } };
//		int[][] machines_pt = {
//				{ 3, 10, 9, 5, 3, 10 },
//				{ 6, 8, 1, 5, 3, 3 },
//				{ 1, 5, 5, 5, 9, 1 },
//				{ 7, 4, 4, 3, 1, 3 },
//				{ 6, 10, 7, 8, 5, 4 },
//				{ 3, 10, 8, 9, 4, 9 } };
//		for (int j = 0; j < jobs.length; j++) {
//			new Sequential(net, jobs[j], jobs_pt[j]);
//		}
//		for (int m = 0; m < machines.length; m++) {
//			new Serialized(net, machines[m], machines_pt[m]);
//		}
//		net.setObjective(v[0]);
//	}
//	
//	private static void solve(Network net, String solverName, long timeout) {
//		boolean showMonitor = true;
//		boolean showSolution = false;
//		int opt = Solver.MINIMIZE;
//		Solver solver = null;
//		if (solverName.equals("bb")) {
//			solver = new DefaultSolver(net, opt, "bb");
//		} 
//		if (showMonitor) {
//			Monitor monitor = new Monitor();
//			monitor.setX(0, (int) (timeout / 1000));
//			solver.setMonitor(monitor);
//		}
//
//		System.out.println("Start " + solver + ", timeout = " + timeout
//				+ " msecs");
//
//		Solution bestSolution;
//		for (solver.start(timeout); solver.waitNext(); solver.resume()) {
//			if (showSolution) {
//				Solution solution = solver.getSolution();
//				int value = solution.getObjectiveIntValue();
//				System.out.println(value);
//			}
//		}
//		solver.stop();
//		bestSolution = solver.getBestSolution();
//		System.out.println("Best = " + bestSolution.getObjectiveIntValue());
//	}
//	
//
//	public static void main(String[] args) {
//		Network net = new Network();
//		ft06(net);
//		String solverName = "para";
//		if (args.length >= 1) {
//			solverName = args[0];
//		}
//		long timeout = 30*1000;
//		solve(net, solverName, timeout);
//	}
	
//	//example:  java + cream = solver
//	public static void main(String[] args) {
//		Network net = new Network();
//		IntVariable J = new IntVariable(net, 0, 9);
//		IntVariable A = new IntVariable(net, 0, 9);
//		IntVariable V = new IntVariable(net, 0, 9);
//		IntVariable C = new IntVariable(net, 0, 9);
//		IntVariable R = new IntVariable(net, 0, 9);
//		IntVariable E = new IntVariable(net, 0, 9);
//		IntVariable M = new IntVariable(net, 0, 9);
//		IntVariable S = new IntVariable(net, 0, 9);
//		IntVariable O = new IntVariable(net, 0, 9);
//		IntVariable L = new IntVariable(net, 0, 9);
//		new NotEquals(net, new IntVariable[] { J, A, V, C, R, E, M, S, O, L });
//		J.notEquals(0);
//		C.notEquals(0);
//		S.notEquals(0);
//		IntVariable JAVA = J.multiply(1000).add(A.multiply(100)).add(
//				V.multiply(10)).add(A);
//		IntVariable CREAM = C.multiply(10000).add(R.multiply(1000)).add(
//				E.multiply(100)).add(A.multiply(10)).add(M);
//		IntVariable SOLVER = S.multiply(100000).add(O.multiply(10000)).add(
//				L.multiply(1000)).add(V.multiply(100)).add(E.multiply(10)).add(
//				R);
//		JAVA.add(CREAM).equals(SOLVER);
//		Solver solver = new DefaultSolver(net);
//		for (solver.start(); solver.waitNext(); solver.resume()) {
//			Solution solution = solver.getSolution();
//			System.out.println(solution.getIntValue(JAVA) + " + "
//					+ solution.getIntValue(CREAM) + " = "
//					+ solution.getIntValue(SOLVER));
//		}
//		solver.stop();
//	}
	
	//Examples:  magic
//	public static void main(String[] args) {
//		Network net = new Network();
//		int n = 3;
//		int sum = n * (n * n + 1) / 2;
//		IntVariable[][] v = new IntVariable[n][n];
//		for (int i = 0; i < n; i++)
//			for (int j = 0; j < n; j++)
//				v[i][j] = new IntVariable(net, 1, n * n);
//		IntVariable[] u = {
//				v[0][0], v[0][1], v[0][2],
//				v[1][0], v[1][1], v[1][2],
//				v[2][0], v[2][1], v[2][2] };
//		new NotEquals(net, u);
//		for (int i = 0; i < n; i++)
//			v[i][0].add(v[i][1]).add(v[i][2]).equals(sum);
//		for (int j = 0; j < n; j++)
//			v[0][j].add(v[1][j]).add(v[2][j]).equals(sum);
//		v[0][0].add(v[1][1]).add(v[2][2]).equals(sum);
//		v[0][2].add(v[1][1]).add(v[2][0]).equals(sum);
//		Solver solver = new DefaultSolver(net);
//		for (solver.start(); solver.waitNext(); solver.resume()) {
//			Solution solution = solver.getSolution();
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++)
//					System.out.print(solution.getIntValue(v[i][j]) + " ");
//				System.out.println();
//			}
//			System.out.println();
//		}
//		solver.stop();
//	}
//	
	//Examples: production planning 
//	static void pp() {
//		Network net = new Network();
//		// number of materials
//		int m = 3;
//		// limit of each material
//		int[] limit = { 1650, 1400, 1800 };
//		// number of products
//		int n = 2;
//		// profit of each product
//		int[] p = { 5, 4 };
//		// amount of materials required to make each product 
//		int[][] a = { { 15, 10, 9 }, { 11, 14, 20 } };
//
//		// initialize variables for products
//		IntVariable[] x = new IntVariable[n];
//		for (int j = 0; j < n; j++) {
//			x[j] = new IntVariable(net);
//			x[j].ge(0);
//		}
//		// generate constraits of limiting materials
//		for (int i = 0; i < m; i++) {
//			IntVariable sum = new IntVariable(net, 0);
//			for (int j = 0; j < n; j++) {
//				sum = sum.add(x[j].multiply(a[j][i]));
//			}
//			sum.le(limit[i]);
//		}
//		// total profit
//		IntVariable profit = new IntVariable(net, 0);
//		for (int j = 0; j < n; j++) {
//			profit = profit.add(x[j].multiply(p[j]));
//		}
//		// maximize the total profit
//		net.setObjective(profit);
//		// iteratively find a better solution until the optimal solution is found 
//		Solver solver = new DefaultSolver(net, Solver.MAXIMIZE | Solver.BETTER);
//		for (solver.start(); solver.waitNext(); solver.resume()) {
//			Solution solution = solver.getSolution();
//			System.out.println("Profit = " + solution.getIntValue(profit));
//			for (int j = 0; j < n; j++) {
//				System.out.println("x[" + j + "]=" + solution.getIntValue(x[j]));
//			}
//			System.out.println();
//		}
//		solver.stop();
//	}
//
//	public static void main(String[] args) {
//		pp();
//	}
	
	
	
////Example: family, age 	
	/**
	 * Example obtained from "Finite Domain Constraint Programming in Oz"
	 * 
	 * Maria and Clara are both heads of households, and both families have three
	 * boys and three girls. Neither family includes any children closer in age than
	 * one year, and all children are under age 10. The youngest child in Maria's
	 * family is a girl, and Clara has just given birth to a little girl.
	 * 
	 * In each family, the sum of the ages of the boys equals the sum of the ages of
	 * the girls, and the sum of the squares of the ages of the boys equals the sum
	 * of the the squares of ages of the girls. The sum of the ages of all children
	 * is 60.
	 * 
	 * What are the ages of the children in each family?
	 * 
	 */
//	private static IntVariable sum(IntVariable[][] v) {
//		IntVariable sum = null;
//		for (int i = 0; i < v.length; i++) {
//			for (int j = 0; j < v[i].length; j++) {
//				sum = (sum == null) ? v[i][j] : sum.add(v[i][j]);
//			}
//		}
//		return sum;
//	}
//
//	private static IntVariable sum(IntVariable[] v) {
//		IntVariable sum = null;
//		for (int i = 0; i < v.length; i++) {
//			sum = (sum == null) ? v[i] : sum.add(v[i]);
//		}
//		return sum;
//	}
//
//	private static IntVariable sum2(IntVariable[] v) {
//		IntVariable sum = null;
//		for (int i = 0; i < v.length; i++) {
//			IntVariable x = v[i].multiply(v[i]);
//			sum = (sum == null) ? x : sum.add(x);
//		}
//		return sum;
//	}
//
//	public static void solve() {
//		int FAMILIES = 2;
//		int CHILDREN = 6;
//		int maxAge = 9;
//
//		Network net = new Network();
//
//		IntVariable[][] isBoy = new IntVariable[FAMILIES][CHILDREN];
//		IntVariable[][] age = new IntVariable[FAMILIES][CHILDREN];
//		IntVariable[][] boyAge = new IntVariable[FAMILIES][CHILDREN];
//		IntVariable[][] girlAge = new IntVariable[FAMILIES][CHILDREN];
//
//		for (int family = 0; family < FAMILIES; family++) {
//			for (int child = 0; child < CHILDREN; child++) {
//				isBoy[family][child] = new IntVariable(net, 0, 1);
//				age[family][child] = new IntVariable(net, 0, maxAge);
//				if (child > 0)
//					age[family][child].gt(age[family][child - 1]);
//				boyAge[family][child] = age[family][child]
//						.multiply(isBoy[family][child]);
//				girlAge[family][child] = age[family][child]
//						.subtract(boyAge[family][child]);
//			}
//		}
//
//		isBoy[0][0].equals(0);
//		isBoy[1][0].equals(0);
//		age[1][0].equals(0);
//
//		for (int family = 0; family < FAMILIES; family++) {
//			sum(isBoy[family]).equals(3);
//			sum(boyAge[family]).equals(sum(girlAge[family]));
//			sum2(boyAge[family]).equals(sum2(girlAge[family]));
//		}
//		sum(age).equals(60);
//
//		Solver solver = new DefaultSolver(net);
//		for (solver.start(); solver.waitNext(); solver.resume()) {
//			Solution solution = solver.getSolution();
//			for (int family = 0; family < FAMILIES; family++) {
//				System.out.print("Family " + family + ": ");
//				for (int child = 0; child < CHILDREN; child++) {
//					int _isBoy = solution.getIntValue(isBoy[family][child]);
//					int _age = solution.getIntValue(age[family][child]);
//					System.out.print(_isBoy != 0 ? "Boy  " : "Girl ");
//					System.out.print(_age + "  ");
//				}
//				System.out.println();
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		solve();
//	}

	
	//Example: Zebra puzzle
	static int houses = 5;
		
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
			IntVariable red = new IntVariable(net, 1, houses, "red");
			IntVariable green = new IntVariable(net, 1, houses, "green");
			IntVariable ivory = new IntVariable(net, 1, houses, "ivory");
			IntVariable yellow = new IntVariable(net, 1, houses, "yellow");
			IntVariable blue = new IntVariable(net, 1, houses, "blue");
			IntVariable[] color = {
					red, green, ivory, yellow, blue
			};
			IntVariable englishman = new IntVariable(net, 1, houses, "Englishman");
			IntVariable spaniard = new IntVariable(net, 1, houses, "Spaniard");
			IntVariable ukrainian = new IntVariable(net, 1, houses, "Ukrainian");
			IntVariable norwegian = new IntVariable(net, 1, houses, "Norwegian");
			IntVariable japanese = new IntVariable(net, 1, houses, "Japanese");
			IntVariable[] nationality = {
					englishman, spaniard, ukrainian, norwegian, japanese
			};
			IntVariable coffee = new IntVariable(net, 1, houses, "coffee");
			IntVariable tea = new IntVariable(net, 1, houses, "tea");
			IntVariable milk = new IntVariable(net, 1, houses, "milk");
			IntVariable orangeJuice = new IntVariable(net, 1, houses, "orange juice");
			IntVariable water = new IntVariable(net, 1, houses, "water");
			IntVariable[] drink = {
					coffee, tea, milk, orangeJuice, water
			};
			IntVariable oldGold = new IntVariable(net, 1, houses, "Old Gold");
			IntVariable kools = new IntVariable(net, 1, houses, "Kools");
			IntVariable chesterfields = new IntVariable(net, 1, houses, "Chesterfields");
			IntVariable luckyStrike = new IntVariable(net, 1, houses, "Lucky Strike");
			IntVariable parliaments = new IntVariable(net, 1, houses, "Parliaments");
			IntVariable[] smoke = {
					oldGold, kools, chesterfields, luckyStrike, parliaments
			};
			IntVariable dog = new IntVariable(net, 1, houses, "dog");
			IntVariable snails = new IntVariable(net, 1, houses, "snails");
			IntVariable fox = new IntVariable(net, 1, houses, "fox");
			IntVariable horse = new IntVariable(net, 1, houses, "horse");
			IntVariable zebra = new IntVariable(net, 1, houses, "zebra");
			IntVariable[] pet = {
					dog, snails, fox, horse, zebra 
			};
			new NotEquals(net, color);
			new NotEquals(net, nationality);
			new NotEquals(net, drink);
			new NotEquals(net, smoke);
			new NotEquals(net, pet);
			// The Englishman lives in the red house.
			englishman.equals(red);
			// The Spaniard owns the dog.
			spaniard.equals(dog);
			// Coffee is drunk in the green house.
			coffee.equals(green);
			// The Ukrainian drinks tea.
			ukrainian.equals(tea);
			// The green house is immediately to the right of the ivory house.
			rightOf(green, ivory);
			// The Old Gold smoker owns snails.
			oldGold.equals(snails);
			// Kools are smoked in the yellow house.
			kools.equals(yellow);
			// Milk is drunk in the middle house.
			milk.equals(3);
			// The Norwegian lives in the first house.
			norwegian.equals(1);
			// The man who smokes Chesterfields lives in the house next to the man with the fox.
			nextTo(chesterfields, fox);
			// Kools are smoked in the house next to the house where the horse is kept.
			nextTo(kools, horse);
			// The Lucky Strike smoker drinks orange juice.
			luckyStrike.equals(orangeJuice);
			// The Japanese smokes Parliaments.
			japanese.equals(parliaments);
			// The Norwegian lives next to the blue house.
			//nextTo(norwegian, blue);
	
			Solver solver = new DefaultSolver(net);
			int count = 0;
			for (solver.start(); solver.waitNext(); solver.resume()) {
				Solution solution = solver.getSolution();
				count++;
				System.out.println("Solution " + count);
//				System.out.println(solution);
				for (int house = 1; house <= houses; house++) {
					System.out.println("\tHouse " + house
							+ ": " + find(house, color, solution)
							+ ", " + find(house, nationality, solution)
							+ ", " + find(house, drink, solution)
							//+ ", " + find(house, smoke, solution)
							//+ ", " + find(house, pet, solution)
							);
				}
				System.out.println();
			}
		}
	
}
