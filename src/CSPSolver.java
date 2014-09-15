//This is the client program for CSPSolver
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;
import java.util.List;


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
			HashMap<Integer, Integer> constraintsMap = new HashMap<Integer, Integer>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0; i< actors; i++) 
				list.add(i);			// store actors into ArrayList
			Random rand = new Random();
			int actor1 , actor2;
			while( constraints >0) {
				//System.out.println("list.size()" + list.size());
				actor1 = rand.nextInt(list.size());    // select one actor				
				actor2 = rand.nextInt(list.size());    // select second actor
				if(actor1 != actor2) {
					constraintsMap.put(actor1, actor2);    // create a constraint < actor1 , actor2 >
					constraints--;
				}
			}
			//System.out.println(constraintsMap.size());
			return constraintsMap;
		}
		
		
		public static HashMap<Integer, String> generateConstraints2(int actors, int constraints) {
			String[] pairs = new String[constraints];
			HashMap<Integer, String> constraintsMap = new HashMap<Integer, String>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0; i< actors; i++) 
				list.add(i);			// store actors into ArrayList
			Random rand = new Random();
			int actor1 , actor2;
			int i =0;
			while( constraints >0) {
				//System.out.println("list.size()" + list.size());
				actor1 = rand.nextInt(list.size());    // select one actor				
				actor2 = rand.nextInt(list.size());    // select second actor
			
				if(actor1 != actor2) {
					//String pair = actor1 + "," + actor2;
					pairs[i] =  actor1 + "," + actor2;
					//System.out.println("Constraint Pair= " + pairs[i]);
					//constraintsMap.put(constraints, pairs[i]);    // create a constraint < actor1 , actor2 >
					constraints--;
					i++;
				}
			}
			checkEqual(pairs);
			for( i=0; i< pairs.length; i++) {
				if( pairs[i] != null) 
				constraintsMap.put(i, pairs[i]);
			}
			System.out.println(constraintsMap.size());
			return constraintsMap;
		}
		
		/*  check the equal of two strings 
		constraint pair <1, 7> is equal to < 7, 1> 	*/
	public static String[] checkEqual(String[] pairs) {		
		for(int i =0; i< pairs.length; i++) {
			for(int j =0; j < pairs.length; j++) {	
				if (pairs[i] !=null && pairs[j] !=null ) {
				 if(pairs[i] != pairs[j]) {
					if (compare(pairs[i], pairs[j]))  {
						    System.out.println("pair["+ j +"] = "+ pairs[j]);
					    removeElements(pairs, pairs[j]);
					}
				} else {
					 // System.out.println("remove duplicate pair["+ j +"] = "+ pairs[j]);
					 removeElements(pairs, pairs[j]);
				}
			 }
			}
		}
		return pairs;
	}
 	/* http://stackoverflow.com/questions/642897/removing-an-element-from-an-array-java*/
	/*public static String[] removeElements(String[] input, String deleteMe) {
    List result = new LinkedList();
    for(String item : input)
        if(!deleteMe.equals(item))
            result.add(item);
    return result.toArray(input);
} */
public static String[] removeElements(String[] input, String deleteMe) {	
	if (input != null) {
		List<String> list = new ArrayList<String>(Arrays.asList(input));
		for (int i = 0; i < list.size(); i++) {				
			if (list.get(i) !=null && list.get(i).equals(deleteMe)) {				
				list.remove(i);
			}
		}
		return list.toArray(new String[0]);
	} else {
		return new String[0];
	}
}
	public static Boolean compare(String a, String b) {
	  if ( a != null && b != null ) {
		String[] a_actors = a.split(",");
		String[] b_actors = b.split(",");
		if( a_actors !=null &&  b_actors !=null ) {
			if(a_actors[0] == b_actors[1] && a_actors[1] == b_actors[0]){
				System.out.println("a_actors[0]= " + a_actors[0] + "\tb_actors[1]= " + b_actors[1]);
				System.out.println("a_actors[1]= " + a_actors[1] + "\tb_actors[0]= " + b_actors[0]);
				return true;
			}
		} 
	 }
	 return false;		
	}
		
		
		/*
		 *  <1, 7> and <7, 1> are same constraint, need to remove one . 
		 */
		
		public static HashMap<Integer, String> delDuplicateConstraints( HashMap<Integer, String> constraintsMap) {
			
			int constraint1,  constraint2;
			Random rand = new Random();
			constraint1 = rand.nextInt(constraintsMap.size());
			constraint2 = rand.nextInt(constraintsMap.size());
			String pair;
			for(Map.Entry<Integer, String> e:  constraintsMap.entrySet()) {
				pair = e.getValue();
			}
			return  constraintsMap;
		}
		
		/**
		 *  check the conflict between two HashMaps- separate map and collocate map
		 */

		 public static void checkConflict( Map<Integer, Integer> sepMap, Map<Integer, Integer> colMap) {
			 
			    for (Map.Entry<Integer, Integer> htEntries : sepMap.entrySet()) {
			        if(colMap.containsKey(htEntries.getKey()) && colMap.get(htEntries.getKey()).equals(htEntries.getValue())){
			            System.out.println("\tKey: " + htEntries.getKey() + " Value: " + htEntries.getValue());
			            colMap.remove(htEntries);
			            
			        }
			    }		 
		 }
				
		public static void main(String[] args) {
			Network net = new Network();	
			int nodes_num = 3;		
			int actors_num = 20;  // the number of actors
			int constraints_num = 100;
			 Map<Integer, Integer> separateMap = new HashMap<Integer, Integer>();
			 Map<Integer, Integer> collocateMap = new HashMap<Integer, Integer>();
			 
			 
			 separateMap= generateConstraints( actors_num,  constraints_num);
			 collocateMap= generateConstraints( actors_num,  constraints_num);
			 checkConflict(separateMap, collocateMap);
			 
//			 Map<Integer, String> constraintMap = new HashMap<Integer, String>();
//			 constraintMap = generateConstraints2( actors_num,  constraints_num);
//			 System.out.println("constraintMap.size() = " +  constraintMap.size() );	 
//			 
//				for( Map.Entry<Integer, String> sepEntries : constraintMap.entrySet()) {
//					String pair = sepEntries.getValue();
//					System.out.println(sepEntries.getKey() + "pair= " + pair); 
//				}
			
			String[] actorList = new String[actors_num];
			IntVariable[] actorVarArr = new IntVariable[actors_num];
			// Add actors into domains
			for(int i=0; i< actors_num; i++) {
				actorList[i] = "actor" + i ;
				actorVarArr[i] = new IntVariable(net, 1, nodes_num, actorList[i]);
			   net.add(actorVarArr[i]);
			}
			
		
			Variable actor1 , actor2, actor3, actor4;
			Integer actor1_num, actor2_num, actor3_num, actor4_num;
			System.out.println("\n Seperate actors!");
			 for (Map.Entry<Integer, Integer> htEntries : separateMap.entrySet()) {
				actor1_num = htEntries.getKey();
				actor2_num = htEntries.getValue();
				actor1 = actorVarArr[actor1_num]; // select one actor 				       
				actor2 = actorVarArr[actor2_num];    //select second actor
				new NotEquals(net, actor1, actor2);	   //Separate actors
				System.out.println("<"+ actor1+ ", " + actor2 +">");  
			 }
				System.out.println("\n Collocate actors!"); 
			 for (Map.Entry<Integer, Integer> htEntries : collocateMap.entrySet()) {
					actor3_num = htEntries.getKey();
					actor4_num = htEntries.getValue();
					actor3 = actorVarArr[actor3_num]; // select one actor 				       
					actor4 = actorVarArr[actor4_num];    //select second actor
					new NotEquals(net, actor3, actor4);	   //Collocate actors
					System.out.println("<"+ actor3+ ", " + actor4 +">");  
				 }
					 

//			System.out.println("Constraints Number= " + countOfConstraints);	
			 System.out.println("Seperate Constraints" + separateMap.size());
			 System.out.println("Collocate Constraints" + collocateMap.size());
			 System.out.println("Total Constraints=" + (separateMap.size() + collocateMap.size()));
			runExample(net); //output the result.
		}
}
