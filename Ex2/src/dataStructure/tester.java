package dataStructure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorithms.Graph_Algo;
import gui.Draw;
import gui.Graph_GUI;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {
		DGraph dg=new DGraph();
		
		Point3D p1=new Point3D(-4.0,4.0);
		Point3D p2=new Point3D(3,8);
		Point3D p3=new Point3D(8,5);
		Point3D p4=new Point3D(12,5);
		Point3D p5=new Point3D(15,5);
		Point3D p6=new Point3D(20,11);
		Point3D p7=new Point3D(-4.0,-5.0);
		

		nodeData nd1 = new nodeData(p1);
		nodeData nd2 = new nodeData(p2);
		nodeData nd3 = new nodeData(p3);
		nodeData nd4 = new nodeData(p4);
		nodeData nd5 = new nodeData(p5);
		nodeData nd6 = new nodeData(p6);
		nodeData nd7 = new nodeData(p7);

		dg.addNode(nd1);
		dg.addNode(nd2);
		dg.addNode(nd3);
		dg.addNode(nd4);
		dg.addNode(nd5);
		dg.addNode(nd6);//think:this key is 6 ,did we need to change this to 1?,because is new graph
		dg.addNode(nd7);
		
		
		dg.connect(1,7,0.3);
		dg.connect(7,5,0.2);
		dg.connect(6,4,1);
		dg.connect(2,4,3);
		dg.connect(1,3,1);
		dg.connect(3,6,2);
		dg.connect(5,6,1);
		dg.connect(3,7,2);
		dg.connect(3,4,2);
		dg.connect(1,2,1);
		dg.connect(2,3,1);
		dg.connect(4,1,2);
		
		
		List<Integer> targets=new ArrayList<Integer>();
		targets.add(1);
		targets.add(6);
		targets.add(5);
		targets.add(7);
		targets.add(2);
		Graph_Algo ga= new Graph_Algo();
		ga.init(dg);
		System.out.println(ga.shortestPathDist(2,6));
		List<node_data> targetsMMM=new ArrayList<node_data>();
		targetsMMM = ga.shortestPath(1,7);
		for(int i=0;i<targetsMMM.size();i++)
			System.out.println(targetsMMM.get(i).getKey());
		
//		Graph_GUI h = new Graph_GUI(ga);
//		h.setVisible(true);
//		ga.get_graphAlgo().addNode(nd7);
		
		/*
		 * System.out.println(ga.shortestPathDist(1,6)); System.out.println("*****");
		 * List<node_data>arr=new ArrayList<node_data>(); arr=ga.shortestPath(1,6);
		 * for(int i=0;i<arr.size();i++) { System.out.println(arr.get(i).getKey());//woW
		 * }
		 */
		
		
	}

}
