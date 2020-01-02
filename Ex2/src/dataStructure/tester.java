package dataStructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import algorithms.Graph_Algo;
import gui.Graph_GUI;
import utils.Point3D;

public class tester {

	public static void main(String[] args) {



		//		DGraph dg=new DGraph();
		//		
		//		Point3D p1=new Point3D(-4.0,4.0);
		//		Point3D p2=new Point3D(3,8);
		//		Point3D p3=new Point3D(8,5);
		//		Point3D p4=new Point3D(12,5);
		//		Point3D p5=new Point3D(13,10);
		//		Point3D p6=new Point3D(25,14);
		//		Point3D p7=new Point3D(20,1);
		//		Point3D p8=new Point3D(2,20);
		//		Point3D p9=new Point3D(11,16);
		//		Point3D p10=new Point3D(16,14);
		//		Point3D p11=new Point3D(17,17);
		//		Point3D p12=new Point3D(7,17);
		//		
		//		
		//		nodeData nd1 = new nodeData(p1);
		//		nodeData nd2 = new nodeData(p2);
		//		nodeData nd3 = new nodeData(p3);
		//		nodeData nd4 = new nodeData(p4);
		//		nodeData nd5 = new nodeData(p5);
		//		nodeData nd6 = new nodeData(p6);
		//		nodeData nd7 = new nodeData(p7);
		//		nodeData nd8 = new nodeData(p8);
		//		nodeData nd9 = new nodeData(p9);
		//		nodeData nd10 = new nodeData(p10);
		//		nodeData nd11 = new nodeData(p11);
		//		nodeData nd12 = new nodeData(p12);
		//		
		//		
		//		dg.addNode(nd1);
		//		dg.addNode(nd2);
		//		dg.addNode(nd3);
		//		dg.addNode(nd4);
		//		dg.addNode(nd5);
		//		dg.addNode(nd6);
		//		dg.addNode(nd7);
		//		dg.addNode(nd8);
		//		dg.addNode(nd9);
		//		dg.addNode(nd10);
		//		dg.addNode(nd11);
		//		dg.addNode(nd12);
		//		
		//		
		//		
		//		
		//		dg.connect(1,3,1);
		//		dg.connect(3,5,3);
		//		dg.connect(5,10,10);
		//    	dg.connect(10,12,12);
		//		dg.connect(12,1,1);
		//		dg.connect(1,2,2);
		//		dg.connect(2,4,2);
		//		dg.connect(2,9,5);
		//		dg.connect(8,7,2);
		//		dg.connect(4,8,1);
		//		dg.connect(9,8,1);
		//		dg.connect(4,6,6);
		//		dg.connect(3,4,4);
		//		dg.connect(5,6,1);
		//		dg.connect(6,7,1);
		//		dg.connect(6,11,2);
		//		dg.connect(11,10,2);
		//		dg.connect(7,11,2);
		//		dg.connect(10,11,2);
		//		
		//		
		//    	
		//		
		//
		//		List<Integer> targets=new ArrayList<Integer>();
		//		targets.add(4);
		//		targets.add(2);
		//		targets.add(2);
		//		targets.add(5);
		//		targets.add(54);
		//		targets.add(2);
		//		targets.add(2);
		//		targets.add(2);
		//		targets.add(9);
		//		targets.add(10);
		//		targets.add(11);
		//		targets.add(12);//add parameters to save the min wight and than add it to the list
		//		
		//		Graph_Algo ga= new Graph_Algo();
		//		ga.init(dg);
		//		System.out.println(ga.isConnected());
		//		
		//		List<node_data> targetsMMM=new ArrayList<node_data>();
		//		targetsMMM = ga.TSP(targets);
		//		for(int i=0;i<targetsMMM.size();i++)
		//			System.out.print(targetsMMM.get(i).getKey()+",");
		//		Graph_GUI gugu =new Graph_GUI(ga);
		Graph_Algo test = new Graph_Algo();
		for (int i = 1; i<=1000;i++){
			Point3D tP = new Point3D(10,i);
			test.get_graphAlgo().addNode(new nodeData(tP));

		}
		for (int i=1;i<1000;i++){
			test.get_graphAlgo().connect(i,i+1,i*10+100);
		}
		test.get_graphAlgo().connect(1000,1,1000);

		for (int i =1;i<=10000;i++){
			int r = 1+ (int)(Math.random()*999);
			int r2 = 1+(int)(Math.random()*999);
			if (r!= r2) {
				test.get_graphAlgo().connect(r, r2, r + i);
			}
		}
		List<Integer> tar = new ArrayList<>();
		for (int i = 1;i<=1000;i++){
			int r = (int)(Math.random()*3);
			if (r==2){
				tar.add(i);
			}
		}
		//      System.out.println(test.get_graphAlgo().nodeSize());
		//      Date date = new Date();
		//      double ff = date.getTime();
		//      List t =test.TSP2(tar);
		//      System.out.println();
		//      date = new Date();
		//      double f = date.getTime();
		//      System.out.println(f-ff);
		//      System.out.println(t.size());









	}



}


