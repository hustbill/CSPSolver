//This is the client program for CSPSolver
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	static void runExample(Network net) {
		Solver solver = new DefaultSolver(net);
		long timeout = 600; // 1 * 60 * 1000;
		System.out.println("# Solutions");
		for (solver.start(); solver.waitNext(); solver.resume()) {
			// Solution solution = solver.getSolution();
			Solution solution = solver.getSolution();
			// Solution solution = solver.findBest(timeout);
			System.out.println(solution);
			solver.stop();
		}

		long count = solver.getCount();
		long time = solver.getElapsedTime();
		System.out.println("time = " + time);
		System.out.println("Found " + count + " solutions in " + time
				+ " milli seconds");

		// System.out.println("# Problem");
		// System.out.println(net);
		System.out.println();
	}

	public static HashMap<Integer, String> generateConstraints(int actors,
			int constraints) {
		String[] pairs = new String[constraints];
		HashMap<Integer, String> constraintsMap = new HashMap<Integer, String>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < actors; i++)
			list.add(i); // store actors into ArrayList
		Random rand = new Random();
		int actor1, actor2;
		int i = 0;
		while (constraints > 0) {
			// System.out.println("list.size()" + list.size());
			actor1 = rand.nextInt(list.size()); // select one actor
			actor2 = rand.nextInt(list.size()); // select second actor

			if (actor1 != actor2) {
				// String pair = actor1 + "," + actor2;
				pairs[i] = actor1 + "," + actor2;
				// System.out.println("Constraint Pair= " + pairs[i]);
				// constraintsMap.put(constraints, pairs[i]); // create a
				// constraint < actor1 , actor2 >
				constraints--;
				i++;
			}
		}
		checkEqual(pairs);
		for (i = 0; i < pairs.length; i++) {
			if (pairs[i] != null)
				constraintsMap.put(i, pairs[i]);
		}
		System.out.println(constraintsMap.size());
		return constraintsMap;
	}

	/*
	 * check the equal of two strings constraint pair <1, 7> is equal to < 7, 1>
	 */
	public static String[] checkEqual(String[] pairs) {
		for (int i = 0; i < pairs.length; i++) {
			for (int j = 0; j < pairs.length; j++) {
				if (pairs[i] != null && pairs[j] != null) {
					if (pairs[i] != pairs[j]) {
						if (compare(pairs[i], pairs[j])) {
							System.out.println("pair[" + j + "] = " + pairs[j]);
							removeElements(pairs, pairs[j]);
						}
					} else {
						// System.out.println("remove duplicate pair["+ j
						// +"] = "+ pairs[j]);
						removeElements(pairs, pairs[j]);
					}
				}
			}
		}
		return pairs;
	}

	/*
	 * http://stackoverflow.com/questions/642897/removing-an-element-from-an-array
	 * -java
	 */
	/*
	 * public static String[] removeElements(String[] input, String deleteMe) {
	 * List result = new LinkedList(); for(String item : input)
	 * if(!deleteMe.equals(item)) result.add(item); return
	 * result.toArray(input); }
	 */
	public static String[] removeElements(String[] input, String deleteMe) {
		if (input != null) {
			List<String> list = new ArrayList<String>(Arrays.asList(input));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && list.get(i).equals(deleteMe)) {
					list.remove(i);
				}
			}
			return list.toArray(new String[0]);
		} else {
			return new String[0];
		}
	}

	public static Boolean compare(String a, String b) {
		if (a != null && b != null) {
			String[] a_actors = a.split(",");
			String[] b_actors = b.split(",");
			if (a_actors != null && b_actors != null) {
				if (a_actors[0] == b_actors[1] && a_actors[1] == b_actors[0]) {
					System.out.println("a_actors[0]= " + a_actors[0]
							+ "\tb_actors[1]= " + b_actors[1]);
					System.out.println("a_actors[1]= " + a_actors[1]
							+ "\tb_actors[0]= " + b_actors[0]);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * <1, 7> and <7, 1> are same constraint, need to remove one .
	 */

	public static HashMap<Integer, String> delDuplicateConstraints(
			HashMap<Integer, String> constraintsMap) {

		int constraint1, constraint2;
		Random rand = new Random();
		constraint1 = rand.nextInt(constraintsMap.size());
		constraint2 = rand.nextInt(constraintsMap.size());
		String pair;
		for (Map.Entry<Integer, String> e : constraintsMap.entrySet()) {
			pair = e.getValue();
		}
		return constraintsMap;
	}

	/**
	 * check the conflict between two HashMaps- separate map and collocate map
	 */

	public static void checkConflict(Map<Integer, String> separateMap,
			Map<Integer, String> collocateMap) {

		for (Map.Entry<Integer, String> htEntries : separateMap.entrySet()) {
			if (collocateMap.containsKey(htEntries.getKey())
					&& collocateMap.get(htEntries.getKey()).equals(
							htEntries.getValue())) {
				System.out.println("\tKey: " + htEntries.getKey() + " Value: "
						+ htEntries.getValue());
				collocateMap.remove(htEntries);

			}
		}
	}

	public static int[][] readXml(String filename) {
		int[][] data = new int[100][5];
		final String dir = System.getProperty("user.dir");
		System.out.println("current dir = " + dir);

		try {
			File file = new File(dir + "//" + filename); //testCases.xml
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element "
					+ doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("case");

			System.out.println("Information of all test cases");

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) fstNode;
					data[s][0] = Integer.parseInt(getValue("nodes", element));
					//System.out.println("nodes: " +  data[s][0] );
					data[s][1] = Integer.parseInt(getValue("actors", element));
					//System.out.println("actors: " +  data[s][1] );
					//sepConstraints
					data[s][2] = Integer.parseInt(getValue("sepConstraints", element));
					//System.out.println("sepConstraints: " +  data[s][2] );
					//colConstraints
					data[s][3] = Integer.parseInt(getValue("colConstraints", element));
					//System.out.println("colConstraints: " +  data[s][3] );
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	public static void main(String[] args) {
		Network net = new Network();
		int nodes_num = 3;
		int actors_num = 20; // the number of actors

		int sep_Constraints = 10;
		int col_Constrasints = 10;

		int[][] data = new int[100][5];
		if (args != null) {
			data= readXml(args[0]);  //args[0] = testCases.xml;
			// nodes_num = Integer.parseInt(args[0]);
			// actors_num = Integer.parseInt(args[1]);
			// sep_Constraints = Integer.parseInt(args[2]);
			// col_Constrasints = Integer.parseInt(args[3]);
		} else{
			data = readXml("testCases.xml");
		}

		
		for(int k=0; k< 6 ; k++) {
			
			nodes_num =data[k][0];
			actors_num = data[k][1];
			sep_Constraints = data[k][2];
			col_Constrasints = data[k][3];
		
		/* open data.txt and read parameters: nodes, actors, constraints */

		Map<Integer, String> separateMap = new HashMap<Integer, String>();
		Map<Integer, String> collocateMap = new HashMap<Integer, String>();

		separateMap = generateConstraints(actors_num, sep_Constraints);
		collocateMap = generateConstraints(actors_num, col_Constrasints);
		checkConflict(separateMap, collocateMap);
		String[] actorList = new String[actors_num];
		IntVariable[] actorVarArr = new IntVariable[actors_num];
		// Add actors into domains
		for (int i = 0; i < actors_num; i++) {
			actorList[i] = "actor" + i;
			actorVarArr[i] = new IntVariable(net, 1, nodes_num, actorList[i]);
			net.add(actorVarArr[i]);
		}
		Variable actor1, actor2, actor3, actor4;
		int actor1_num;
		int actor2_num, actor3_num, actor4_num;
		//System.out.println("\n Seperate actors!");
		for (Map.Entry<Integer, String> htEntries : separateMap.entrySet()) {
			String[] actors = htEntries.getValue().split(",");
			actor1_num = Integer.parseInt(actors[0]);
			actor2_num = Integer.parseInt(actors[1]);
			actor1 = actorVarArr[actor1_num]; // select one actor
			actor2 = actorVarArr[actor2_num]; // select second actor
			new NotEquals(net, actor1, actor2); // Separate actors
			//System.out.println("<" + actor1 + ", " + actor2 + ">");
		}
		//System.out.println("\n Collocate actors!");
		for (Map.Entry<Integer, String> htEntries : collocateMap.entrySet()) {
			String[] actors = htEntries.getValue().split(",");
			actor3_num = Integer.parseInt(actors[0]);
			actor4_num = Integer.parseInt(actors[1]);
			actor3 = actorVarArr[actor3_num]; // select one actor
			actor4 = actorVarArr[actor4_num]; // select second actor
			new NotEquals(net, actor3, actor4); // Collocate actors
			//System.out.println("<" + actor3 + ", " + actor4 + ">");
		}
		
		System.out.println("nodes_num = " + nodes_num);		
		System.out.println("actors_num = " + actors_num);		
		System.out.println("Seperate Constraints = " + separateMap.size());
		System.out.println("Collocate Constraints = " + collocateMap.size());
		System.out.println("Total Constraints = "
				+ (separateMap.size() + collocateMap.size()));
		runExample(net); // output the result.
	  }
	}
}
