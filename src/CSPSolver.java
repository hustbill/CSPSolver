//This is the client program for CSPSolver
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
				//Solution solution = solver.getSolution();
				Solution solution = solver.getSolution();
				//Solution solution = solver.findBest(timeout);
				System.out.println(solution);
				solver.stop();
			}
		
			long count = solver.getCount();
			long time = solver.getElapsedTime();
			System.out.println("time = " + time);
		    System.out.println("Found " + count + " solutions in " + time + " milli seconds");
			
			//System.out.println("# Problem");
			//System.out.println(net);
			System.out.println();
		}

		public static HashMap<Integer, Integer> generateConstraints(int actors, int constraints) {
			HashMap<Integer, Integer> constraints_map = new HashMap<Integer, Integer>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0; i< actors; i++) 
				list.add(i);			// store actors into ArrayList
			Random rand = new Random();
			int actor1 , actor2;
			while(list.size()> 0 &&  constraints >0) {
				//System.out.println("list.size()" + list.size());
				actor1 = rand.nextInt(list.size());
				list.remove(actor1);         // select one actor 
				actor2 = rand.nextInt(list.size());    //select second actor
				constraints_map.put(actor1, actor2);    // create a constraint < actor1 , actor2 >
				constraints--;
				//System.out.println(constraints);
			}
			System.out.println(constraints_map.size());
			return constraints_map;
		}
		
		/**
		 *  check the conflict between two HashMaps- separate map and collocate map
		 */

		 public void checkConflict( Map<Integer, Integer> ht, Map<Integer, Integer> ht2) {
			 
			    for (Map.Entry<Integer, Integer> htEntries : ht.entrySet()) {
			        if(ht2.containsKey(htEntries.getKey()) && ht2.get(htEntries.getKey()).equals(htEntries.getValue())){
			            System.out.println("\tKey: " + htEntries.getKey() + " Value: " + htEntries.getValue());
			        }
			    }		 
		 }
		
		
		public static void main(String[] args) {
			Network net = new Network();	
			int nodeNum = 55;		
			int actors_num = 100;  // the number of actors
			int constraints_num = 10;
			
			
			String[] actorList = new String[actors_num];
			IntVariable[] actorVarArr = new IntVariable[actors_num];
			// Add actors into domains
			for(int i=0; i< actors_num; i++) {
				actorList[i] = "actor" + i ;
				actorVarArr[i] = new IntVariable(net, 1, nodeNum, actorList[i]);
			   net.add(actorVarArr[i]);
			}
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i =0; i< actors_num; i++) {
				list.add(i);
			}
			
			Variable actor1 , actor2, actor3, actor4;
			Random rand = new Random();
			int sepConstraints_num = constraints_num;
			while( sepConstraints_num >0) {
					actor1 =actorVarArr[ rand.nextInt(list.size())]; // select one actor 				       
					actor2 = actorVarArr[rand.nextInt(list.size())];    //select second actor
					if (actor1 != actor2)
						new NotEquals(net, actor1, actor2);	   //Separate actors		
				sepConstraints_num--;
			}
	//	
	     	int colConstraints_num = constraints_num;
	     	System.out.println(" Collocate actors");
			while( colConstraints_num >0) {
					actor3 =actorVarArr[ rand.nextInt(list.size())]; // select third actor 	
					actor4 = actorVarArr[rand.nextInt(list.size())];    //select  forth actor
					System.out.println("<"+ actor3+ ", " + actor4 +">");  
					if (actor4 != actor3)						
						actor3.equals(actor4);			
				colConstraints_num--;
			}
//			System.out.println("Constraints Number= " + countOfConstraints);		
			runExample(net); //output the result.
		}
}
