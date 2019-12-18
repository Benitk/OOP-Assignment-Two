package dataStructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import algorithms.Graph_Algo;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {
		DGraph dg=new DGraph();
		Point3D p1=new Point3D(2.0,4.0);
		Point3D p2=new Point3D(3,8);
		Point3D p3=new Point3D(8,5);
		Point3D p4=new Point3D(12,5);
		nodeData nd1 =new nodeData(1,p1,20,"ben");
		node_data nd2 =new nodeData(1,p2,30,"ben");
		node_data nd3 =new nodeData(1,p3,10,"ben");
		node_data nd4 =new nodeData(1,p4,10,"ben");
		dg.addNode(nd1);
		dg.addNode(nd2);
		dg.addNode(nd3);
		DGraph dggg=new DGraph(dg);
		dggg.addNode(nd4);
		Graph_Algo ga= new Graph_Algo();
		ga.init(dg);
		ga.get_graphAlgo().addNode(nd3);
		System.out.println(dg.get_graph());
		System.out.println(dggg.get_graph());
		
		
		
	}

}
