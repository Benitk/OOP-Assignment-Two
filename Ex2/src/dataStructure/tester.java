package dataStructure;


import algorithms.Graph_Algo;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {
		DGraph dg=new DGraph();
		Point3D p1=new Point3D(2.0,4.0);
		Point3D p2=new Point3D(3,8);
		Point3D p3=new Point3D(8,5);
		Point3D p4=new Point3D(12,5);
		Point3D p5=new Point3D(15,5);
		nodeData nd1 = new nodeData(p1);
		nodeData nd2 = new nodeData(p2);
		nodeData nd3 = new nodeData(p3);
		nodeData nd4 = new nodeData(p4);
		nodeData nd5 = new nodeData(p5);
		dg.addNode(nd1);
		dg.addNode(nd2);
		dg.addNode(nd3);
		dg.addNode(nd4);
		dg.addNode(nd5);
		dg.connect(1, 5, 40);
		dg.connect(3, 2, 4);
		dg.connect(4, 3, 4);
		dg.connect(2, 1, 4);
		dg.connect(3, 5, 4);
		dg.connect(5, 4, 4);
		dg.connect(2, 3, 4);
		dg.connect(4, 2, 4);
				
		Graph_Algo ga= new Graph_Algo();
		ga.init(dg);
		System.out.println(ga.isConnected());
		ga.save("check.txt");
		Graph_Algo gb= new Graph_Algo();
		gb.init("check.txt");
		System.out.println(gb.get_graphAlgo().get_graph().get(1).getLocation());
		
		
	}

}
