
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

	static void queens(int n) {
		Network net = new Network();
		IntVariable[] q = new IntVariable[n];
		IntVariable[] u = new IntVariable[n];
		IntVariable[] d = new IntVariable[n];
		for (int i = 0; i < n; ++i) {
			q[i] = new IntVariable(net, 1, n);
			u[i] = q[i].add(i);
			d[i] = q[i].subtract(i);
		}
		new NotEquals(net, q);
		new NotEquals(net, u);
		new NotEquals(net, d);
		Solver solver = new DefaultSolver(net);
		for (solver.start(); solver.waitNext(); solver.resume()) {
			Solution solution = solver.getSolution();
			for (int i = 0; i < n; i++) {
				int j = solution.getIntValue(q[i]);
				System.out.print(j + " ");
			}
			System.out.println();
		}
		solver.stop();
		long count = solver.getCount();
		//long time = solver.getElapsedTime() / 1000;
		
		long time = 10;
		System.out.println(count + " solutions found in " + time + " seconds");
	}

	public static void main(String[] args) {
		int n = 8;
		if (args.length == 1) {
			n = Integer.parseInt(args[0]);
		}
		queens(n);
	}

}
