package dataStructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import algorithms.Graph_Algo;
import gui.Graph_GUI;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {

		DGraph dg = new DGraph();

		Point3D p1 = new Point3D(-4.0, 4.0);
		Point3D p2 = new Point3D(3, 8);
		Point3D p3 = new Point3D(8, 5);
		Point3D p4 = new Point3D(12, 5);
		Point3D p5 = new Point3D(10, 10);
		Point3D p6 = new Point3D(20, 14);
		Point3D p7 = new Point3D(14, 14);

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

		dg.connect(1, 2, 1);
		dg.connect(2, 3, 2);
		dg.connect(1, 3, 0.2);
		dg.connect(3, 4, 0.5);
		dg.connect(3, 5, 5);
		dg.connect(5, 6, 4);
		dg.connect(4, 6, 2);
		

		List<Integer> targets = new ArrayList<Integer>();
		targets.add(6);
		targets.add(4);
		
		

		Graph_Algo ga = new Graph_Algo();
		ga.init(dg);
		ga.get_graphAlgo().addNode(new nodeData(p7));
		List<Integer> Tsplist=new ArrayList<Integer>();
		Graph_GUI gugu = new Graph_GUI(ga);
		Tsplist.add(1);
		Tsplist.add(700);
		try {
		String[] a = "3,4,".split(",");
		a[2] = "s";
		}
		catch(ArrayIndexOutOfBoundsException e) 
		{ 
			System.out.println("ben");	
		} 
		catch (Exception e) {
			System.out.println(e.getClass());
		}
		/*
		 * try { ga.TSP(Tsplist);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * System.out.println(e.getMessage()); } System.out.println(length);
		 */
		
		
		

	}

}
