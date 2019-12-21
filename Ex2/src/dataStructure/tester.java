package dataStructure;


import algorithms.Graph_Algo;
import gui.Draw;
import gui.Graph_GUI;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {
		DGraph dg=new DGraph();
		Point3D p1=new Point3D(2.0,4.0);
		Point3D p2=new Point3D(9,-9);
		Point3D p3=new Point3D(7,-5);
		Point3D p4=new Point3D(3, 7);
		Point3D p5=new Point3D(2,-5);
		Point3D p6=new Point3D(5.0,4.5);
		Point3D p7=new Point3D(0,0);
		Point3D p8=new Point3D(-2,1);
		Point3D p9=new Point3D(8, 4);
		Point3D p10=new Point3D(2,5);
		nodeData nd1 = new nodeData(p1);
		nodeData nd2 = new nodeData(p2);
		nodeData nd3 = new nodeData(p3);
		nodeData nd4 = new nodeData(p4);
		nodeData nd5 = new nodeData(p5);
		nodeData nd6 = new nodeData(p6);
		nodeData nd7 = new nodeData(p7);
		nodeData nd8 = new nodeData(p8);
		nodeData nd9 = new nodeData(p9);
		nodeData nd10 = new nodeData(p10);
		dg.addNode(nd1);
		dg.addNode(nd2);
		dg.addNode(nd3);
		dg.addNode(nd4);
		dg.addNode(nd5);
		dg.addNode(nd6);
		dg.addNode(nd7);
		dg.addNode(nd8);
		dg.addNode(nd9);
		dg.addNode(nd10);
		dg.connect(1, 2, 40);
		dg.connect(2, 3, 4);
		dg.connect(3, 4, 4);
		dg.connect(10, 1, 4);
		dg.connect(7, 8, 40);
		dg.connect(9, 5, 4);
		dg.connect(3, 7, 4);
		dg.connect(5, 1, 4);
		dg.connect(9, 2, 40);
		dg.connect(2, 6, 4);
		dg.connect(6, 4, 4);
		dg.connect(4, 1, 4);
	
		Draw d = new Draw(dg);
		//d.draw_graph();
		Graph_Algo ga= new Graph_Algo();
		ga.init(dg);
		Graph_GUI zx = new Graph_GUI(ga);
		zx.setVisible(true);
		/*
		 * System.out.println(ga.isConnected()); ga.save("check.txt"); Graph_Algo gb=
		 * new Graph_Algo(); gb.init("check.txt");
		 * System.out.println(gb.get_graphAlgo().get_graph().get(1).getLocation());
		 */
		
		
	}

}
