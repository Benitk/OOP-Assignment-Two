package dataStructure;

import java.util.ArrayList;
import java.util.List;

import algorithms.Graph_Algo;
import gui.Graph_GUI;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {
		DGraph dg=new DGraph();
		
		Point3D p1=new Point3D(-4.0,4.0);
		Point3D p2=new Point3D(3,8);
		Point3D p3=new Point3D(8,5);
		Point3D p4=new Point3D(12,5);
		Point3D p5=new Point3D(10,10);
		Point3D p6=new Point3D(20,14);
		
		nodeData nd1 = new nodeData(p1);
		nodeData nd2 = new nodeData(p2);
		nodeData nd3 = new nodeData(p3);
		nodeData nd4 = new nodeData(p4);
		nodeData nd5 = new nodeData(p5);
		nodeData nd6 = new nodeData(p6);
		
		dg.addNode(nd1);
		dg.addNode(nd2);
		dg.addNode(nd3);
		dg.addNode(nd4);
		dg.addNode(nd5);
		dg.addNode(nd6);
		
		dg.connect(1,2,1);
		dg.connect(2,3,2);
		dg.connect(1,3,0.2);
    	dg.connect(3,4,0.5);
		dg.connect(3,5,5);
    	dg.connect(5,6,4);
		dg.connect(4,6,2);
		dg.connect(6,1,1.2);

		List<Integer> targets=new ArrayList<Integer>();
		targets.add(4);
		targets.add(3);
		targets.add(5);
		targets.add(1);
		
		Graph_Algo ga= new Graph_Algo();
		ga.init(dg);
		
		List<node_data> targetsMMM=new ArrayList<node_data>();
		targetsMMM = ga.TSP(targets);
		for(int i=0;i<targetsMMM.size();i++)
			System.out.print(targetsMMM.get(i).getKey()+",");
		
		
		Graph_GUI gg=new Graph_GUI();
		
		
		

		

		
	}

}
