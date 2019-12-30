package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;

//why re you gay
@TestMethodOrder(OrderAnnotation.class)
class DGraphTest {
	private static DGraph g = new DGraph();

	@BeforeAll
	static void printinit() {
		System.out.println("initialize Vertices");
		System.out.println("(2,3), (5,-1), (-2,4), (-4,-2)");
	}

	@BeforeAll
	static void init() {
		Point3D p1 = new Point3D(2,3);
		Point3D p2 = new Point3D(5,-1);
		Point3D p3 = new Point3D(-2,4);
		Point3D p4 = new Point3D(-4,-2);
		nodeData v1 = new nodeData(p1);
		nodeData v2 = new nodeData(p2);
		nodeData v3 = new nodeData(p3);
		nodeData v4 = new nodeData(p4);
		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
		g.connect(1, 2, 5);
		g.connect(3, 4, 3.5);
	}

	/** 
	 * get Node test
	 */
	@Test
	@Order(1)
	void getNodeTest() {
		String ans[] = {"2.0,3.0,0.0", "5.0,-1.0,0.0", "-2.0,4.0,0.0", "-4.0,-2.0,0.0"};
		for(int i = 1; i <= g.nodeSize(); i++) {
			assertEquals(i, g.getNode(i).getKey(),"getNode key Test");
			assertEquals(1, g.getNode(i).getTag(),"getNode tag Test");
			assertEquals("", g.getNode(i).getInfo(),"getNode info Test");
			assertEquals(Integer.MAX_VALUE, g.getNode(i).getWeight(),"getNode weight Test");
			assertEquals(ans[i-1], g.getNode(i).getLocation().toString(), "getNode locations Test");
		}
	}
	/**
	 * get edge test
	 */
	@Test
	@Order(2)
	void getEdgeTest() {
		// edge 1 to 2
		assertEquals("", g.getEdge(1, 2).getInfo(),"getEdge info Test");
		assertEquals(0, g.getEdge(1, 2).getTag(),"getEdge tag Test");
		assertEquals(1, g.getEdge(1, 2).getSrc(),"getEdge src key Test");
		assertEquals(2, g.getEdge(1, 2).getDest(),"getEdge dest key Test");
		assertEquals(5, g.getEdge(1, 2).getWeight(),"getEdge weight Test");

		// edge 3 to 4
		assertEquals("", g.getEdge(3, 4).getInfo(),"getEdge info Test");
		assertEquals(0, g.getEdge(3, 4).getTag(),"getEdge tag Test");
		assertEquals(3, g.getEdge(3, 4).getSrc(),"getEdge src key Test");
		assertEquals(4, g.getEdge(3, 4).getDest(),"getEdge dest key Test");
		assertEquals(3.5, g.getEdge(3, 4).getWeight(),"getEdge weight Test");
	}

	/**
	 * add Node test
	 */
	@Test
	@Order(3)
	void addNodeTest() {
		Point3D p = new Point3D(4,10);
		g.addNode(new nodeData(p));
		int nodeKey = g.get_number_key();
		assertEquals(1, g.getNode(nodeKey).getTag(),"addNode tag Test");
		assertEquals("", g.getNode(nodeKey).getInfo(),"addNode info Test");
		assertEquals(Integer.MAX_VALUE, g.getNode(nodeKey).getWeight(),"addNode weight Test");
		assertEquals("4.0,10.0,0.0", g.getNode(nodeKey).getLocation().toString(), "addNode locations Test");
	}
	/**
	 * connect test
	 */
	@Test
	@Order(4)
	void ConnectTest() {
		g.connect(2, 3, 4.2);
	
		assertEquals("", g.getEdge(2, 3).getInfo(),"connect info Test");
		assertEquals(0, g.getEdge(2, 3).getTag(),"connect tag Test");
		assertEquals(2, g.getEdge(2, 3).getSrc(),"connect src key Test");
		assertEquals(3, g.getEdge(2, 3).getDest(),"connect dest key Test");
		assertEquals(4.2, g.getEdge(2, 3).getWeight(),"connect weight Test");
		
	}
	/**
	 * getV test
	 */
	@Test
	@Order(5)
	void getVTest() {
		Collection<node_data> v =  g.getV();
		int nodeKey_counter = 0;
		Iterator<node_data> iter = v.iterator();
		while(iter.hasNext()) {
			assertEquals(++nodeKey_counter, iter.next().getKey(), "getV key Test");
		}		
	}
	/**
	 * getE test
	 */
	@Test
	@Order(6)
	void getETest() {
		g.connect(2, 1, 3);
		// node 2 is connected to 2->1 and 2->3
		int expected[] = {1, 3};
		Collection<edge_data> e =  g.getE(2);
		Iterator<edge_data> iter = e.iterator();
		while(iter.hasNext()) {
			assertEquals(expected[0], iter.next().getDest(), "getE dest Test");
			assertEquals(expected[1], iter.next().getDest(), "getE dest Test");
		}		
	}
	
	/**
	 * removeNode test
	 */
	@Test
	@Order(7)
	void removeNodeTest() {
		// add new node
		Point3D p = new Point3D(-3,5);
		g.addNode(new nodeData(p));
		int nodeKey = g.get_number_key();
		assertEquals(nodeKey, g.getNode(g.get_number_key()).getKey(),"remove Node key Test");
		
		g.removeNode(nodeKey);
		if(g.getNode(nodeKey) != null) {
			fail("Error: node number 6 should be deleted from graph");
		}
	}
	
	/**
	 * removeEdge test
	 */
	@Test
	@Order(8)
	void removeEdgeTest() {
		// add new edge
		g.connect(1, 4, 2);
		assertEquals(1, g.getEdge(1, 4).getSrc(),"remove edge src key Test");
		assertEquals(4, g.getEdge(1, 4).getDest(),"remove edge dest key Test");
		
		g.removeEdge(1, 4);
		if(g.getEdge(1, 4) != null) {
			fail("Error: edge 1->4 should be deleted from graph");
		}
	}
	/**
	 *  node and edge sizes test
	 */
	@Test
	@Order(9)
	void NodeAndEdge_sizeTest() {

		int expected_nodeSize = 5; //(2,3), (5,-1), (-2,4), (-4,-2), (4,10)
		int expected_edgeSize = 4; // 1->2, 3->4 2->3 2->1
		assertEquals(expected_nodeSize, g.nodeSize(),"node size Test");
		assertEquals(expected_edgeSize, g.edgeSize(),"edge size Test");
	}
	/**
	 * get mc test
	 */
	@Test
	@Order(10)
	void getMCTest() {
		int expected_MC = 13;
		assertEquals(expected_MC, g.getMC(),"MC Test");
	}
	
	/**
	 * get GraphScale test
	 */
	@Test
	@Order(11)
	void GraphScaleTest() {
		double expectedRangeX[] = {-4,5}; 
		double expectedRangeY[] = {-2, 10}; 

		assertEquals(expectedRangeX[0], g.GraphScaleX().get_min(),"ScaleX Test");
		assertEquals(expectedRangeX[1], g.GraphScaleX().get_max(),"ScaleX Test");
		assertEquals(expectedRangeY[0], g.GraphScaleY().get_min(),"ScaleY Test");
		assertEquals(expectedRangeY[1], g.GraphScaleY().get_max(),"ScaleY Test");
	}
	
}
