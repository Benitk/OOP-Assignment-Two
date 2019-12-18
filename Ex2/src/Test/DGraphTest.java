package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;

class DGraphTest {
	private DGraph g = new DGraph();

	@BeforeAll
	static void printinit() {
		System.out.println("initialize Vertices");
		System.out.println("(2,3), (5,-1), (-2,4), (-4,-2)");
	}

	@BeforeEach
	void init() {
		Point3D p1 = new Point3D(2,3);
		Point3D p2 = new Point3D(5,-1);
		Point3D p3 = new Point3D(-2,4);
		Point3D p4 = new Point3D(-4,-2);
		nodeData v1 = new nodeData(p1, 10, "1");
		nodeData v2 = new nodeData(p2, 15, "2");
		nodeData v3 = new nodeData(p3, 5, "3");
		nodeData v4 = new nodeData(p4, 13, "4");
		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
	}
	
	
	
	

	@Test
	void getNodetest() {
		double expected_weight = g.getNode(2).getWeight();
		double actual_weight = 15;
		Point3D expected_location = g.getNode(2).getLocation();
		Point3D actual_location = new Point3D(5,-1);;
		String expected_info = g.getNode(2).getInfo();
		String actual_info = "2";
		assertEquals(expected_weight, actual_weight, "getNode Test");
		assertEquals(expected_location, actual_location, "getNode Test");
		assertEquals(expected_info, actual_info, "getNode Test");
	}

}
