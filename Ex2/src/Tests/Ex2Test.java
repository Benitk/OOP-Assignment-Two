
	package Tests;

	import static org.junit.jupiter.api.Assertions.*;

	import org.junit.jupiter.api.BeforeAll;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import algorithms.*;
	import dataStructure.*;
	import utils.*;
	import gui.*;

	/**
	 * EX2 Structure test:
	 * 1. make sure your code compile with this dummy test (DO NOT Change a thing in this test). 
	 * 2. the only change require is to run your GUI window (see line 64).
	 * 3. EX2 auto-test will be based on such file structure.
	 * 4. Do include this test-case in you submitted repository, in folder Tests (under src).
	 * 5. Do note that all the required packages are imported - do NOT use other 
	 * 
	 * @author boaz.benmoshe
	 *
	 */
	class Ex2Test {
		private static graph _graph;
		private static graph_algorithms _alg;
		public static final double EPS = 0.001; 
		private static Point3D min = new Point3D(0,0,0);
		private static Point3D max = new Point3D(100,100,0);
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			_graph = tinyGraph();
		}
		
		@BeforeEach
		void setUp() throws Exception {
		}

		@Test
		void testConnectivity() {
			_alg = new Graph_Algo(_graph);
			boolean con = _alg.isConnected();
			if(!con) {
				fail("shoulbe be connected");
			}
		}
		@Test
		void testgraph() {
			boolean ans = drawGraph(_graph);
			assertTrue(ans);
		}
		
		private static graph tinyGraph() {
			graph ans = new DGraph();
			Point3D p100=new Point3D(-4.0,4.0);
			Point3D p200=new Point3D(3,8);
			Point3D p300=new Point3D(8,5);
			nodeData nd100 = new nodeData(p100);
			nodeData nd200 = new nodeData(p200);
			nodeData nd300 = new nodeData(p300);
			ans.addNode(nd100);
			ans.addNode(nd200);
			ans.addNode(nd300);
			ans.connect(1,2,1);
			ans.connect(2,3,3);
			ans.connect(3,1,10);
			
			return ans;
		}
		boolean drawGraph(graph g) { 
			// YOUR GUI graph draw 
			return true;
			
		}

	}


