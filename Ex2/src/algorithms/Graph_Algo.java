package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;


public class Graph_Algo implements graph_algorithms, Serializable {

	public Graph_Algo() {
		set_graphAlgo(new DGraph());
	}
	/*
	 * init a Graph_algo from a graph
	 */
	@Override
	public void init(graph g) {
		set_graphAlgo((DGraph)g);

	}

	/*
	 * load a graph from a file
	 */
	@Override
	public void init(String file_name) {
		graph file_graph = null; 

		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 

			file_graph = (graph)in.readObject(); 
			this.init(file_graph);

			in.close(); 
			file.close(); 
		} 

		catch(IOException e) 
		{ 
			throw new RuntimeException("File is not readable");	
		} 

		catch(ClassNotFoundException e) 
		{ 
			throw new RuntimeException("File is not Found");	
		} 



	}

	/*
	 * save the Graph_algo
	 */
	@Override
	public void save(String file_name) {
		graph dgraph = this.copy();
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(dgraph); 

			out.close(); 
			file.close(); 
		}   
		catch(IOException e) 
		{ 
			throw new RuntimeException("File is not writable");
		} 
	}

	/**
	 * this algoritem check if one node 'A' connected to every other node
	 * then check if every other node can reach back to A.
	 * if size == 1 return true becuase
	 * graph is connected if it has exactly one connected component.
	 */
	@Override
	public boolean isConnected() {

		if(this.get_graphAlgo().get_graph().isEmpty()) {
			return false;
		}

		int size = this.get_graphAlgo().nodeSize();
		if(size == 1) {
			return true;
		}
		nodeData current;
		Iterator<node_data> iter = (Iterator<node_data>) this.get_graphAlgo().getV().iterator();
		nodeData first = (nodeData) iter.next();
		while(iter.hasNext()) {
			this.GreenTag();
			if(isConnected(iter.next().getKey(),first)==0)
				return false;
		}
		Iterator<node_data> iter2 = (Iterator<node_data>) this.get_graphAlgo().getV().iterator();
		while(iter2.hasNext()) {
			this.GreenTag();
			current = (nodeData) iter2.next();
			if(current.getKey() != first.getKey()) {
				if(isConnected(first.getKey(),current)==0)
					return false;
			}
		}
		return true;
	}

	private int isConnected(int toFind, nodeData current) {
		int sum = 0;
		if(current.getTag() == 3) {
			return 0;
		}
		// found 
		else if(current.get_edges().get(toFind) != null) {
			return 1;
		}
		// not found
		else {
			current.setTag(3); // 3 --> Red
			Iterator<edge_data> iter = current.get_edges().values().iterator();
			while(iter.hasNext()) {
				edgeData current_edge = (edgeData)iter.next();
				nodeData dest = (nodeData) this.get_graphAlgo().get_graph().get(current_edge.getDest());
				sum += isConnected(toFind, dest);
			}
		}
		return sum;
	}

	/*
	 * return the short distance from src to dest 
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}

		nodeData start =(nodeData) this._graphAlgo.getNode(src);
		nodeData end =(nodeData) this._graphAlgo.getNode(dest);
		if(start == null) {
			throw new RuntimeException("Source Vertex don't exist");
		}
		else if(end == null) {
			throw new RuntimeException("destination Vertex don't exist");
		}
		SetNodeWeightMaxInt();
		// set src weight to 0
		start.setWeight(0.0);
		// makes every tag green
		GreenTag();
		// check if there is a path
		if(isConnected(dest,(nodeData)this.get_graphAlgo().getNode(src))==0) {
			throw new RuntimeException("There isnt a path between those nodes");
		}
		// makes every tag green
		GreenTag();//you must do here greentag() because the isconnected mix it

		shortestPathDist(start,end);
		return end.getWeight();
	}

	private void shortestPathDist(nodeData current,nodeData end) {
		if(current.getTag()==3 || current==end)
			return;
		UpDateWeightNeighbor(current);
		current.setTag(3);
		Iterator<edge_data> iter = current.get_edges().values().iterator();
		while(iter.hasNext()) {
			edgeData currentEdge=(edgeData) iter.next();
			shortestPathDist(currentEdge.getNodeDest(),end);
		}

	}

	private void UpDateWeightNeighbor(nodeData current) {
		Iterator<edge_data> iter = current.get_edges().values().iterator();
		while(iter.hasNext()) {
			edgeData currentEdge=(edgeData) iter.next();
			double value = current.getWeight()+currentEdge.getWeight();
			if(value<=currentEdge.getNodeDest().getWeight()) {
				currentEdge.getNodeDest().setWeight(value);
				currentEdge.getNodeDest().setInfo(Integer.toString(current.getKey()));
			}
		}
	}
	/*
	 * return a list of node_data that is the shortest way from src to dest
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		ArrayList<node_data>listPathRev = new ArrayList<node_data>();//list in reverse
		shortestPathDist(src,dest);
		nodeData current=(nodeData) this.get_graphAlgo().getNode(dest);
		listPathRev.add(current);
		String info="";
		while(current!=this.get_graphAlgo().getNode(src)) {
			info=current.getInfo();
			current=(nodeData) this.get_graphAlgo().getNode(Integer.parseInt(info));
			listPathRev.add(current);
		}
		ArrayList<node_data>listPath = new ArrayList<node_data>();
		for(int i=listPathRev.size()-1;i>=0;i--)
			listPath.add(listPathRev.get(i));

		return listPath;
	}
	private ArrayList<Integer> checklist(List<Integer> targets){

		ArrayList<Integer> newlist=(ArrayList<Integer>) targets;
		for(int i=0;i<newlist.size();i++) {
			// node dont exist in graph throw 
			if(this.get_graphAlgo().get_graph().get(newlist.get(i)) == null) {
				throw new RuntimeException("Vertex "+newlist.get(i)+" don't exist");
			}
			int count=0;
			for(int j=0;j<newlist.size();j++) {
				if(newlist.get(i)==newlist.get(j)) {
					count++;
				}
				if(count>1) {
					newlist.remove(i);
					i=0;
					break;
				}

			}

		}
		return newlist;
	}


	@Override
	public List<node_data> TSP(List<Integer> targets){
		double count=0;
		double limit=Math.pow(targets.size(),4);
		ArrayList<Integer>arrTarget=checklist(targets);
		ArrayList<Integer> arrkey= new ArrayList<>();
		while(arrkey.size()<arrTarget.size()) {

			for(int i=0;i<arrTarget.size();i++) {
				boolean flag=true;
				for(int j=0;j<arrTarget.size();j++) {
					if(!arrkey.contains(arrTarget.get(i)) && !arrkey.contains(arrTarget.get(j))) {
						GreenTag();
						if(arrTarget.get(j)!=arrTarget.get(i)) {
							if(isConnected(arrTarget.get(j),(nodeData) this.get_graphAlgo().getNode(arrTarget.get(i)))==0) {
								flag=false;
							}
						}
					}
				}
				if(flag==true && !arrkey.contains(arrTarget.get(i))) {
					arrkey.add(arrTarget.get(i));
				}
			}
			count++;
			if(count>limit)
				throw new RuntimeException("There is no path with all this location");
		}
		List<node_data> Tsplist=new ArrayList<node_data>();
		for(int k=0;k<arrkey.size()-1;k++) {
			if(!Tsplist.contains(this.get_graphAlgo().getNode(arrkey.get(k+1)))){
				if( Tsplist.size()!=0 && Tsplist.get(Tsplist.size()-1)==this.get_graphAlgo().getNode(arrkey.get(k)))
					Tsplist.remove(Tsplist.size()-1);
				Tsplist.addAll(shortestPath(arrkey.get(k),arrkey.get(k+1)));
				if(k!=arrkey.size()-2) {
					node_data d1=Tsplist.get(Tsplist.size()-1);
					Tsplist.remove(Tsplist.get(Tsplist.size()-1));
					if(!Tsplist.contains(d1))
						Tsplist.add(d1);
				}
			}
		}
		return Tsplist;
	}
	public List<node_data> TSP2(List<Integer> targets){
		List<node_data> Tsplist=new ArrayList<node_data>();
		ArrayList<Integer>arrTarget=checklist(targets);
		for(int i=0;i<arrTarget.size()-1;i++) {
			Tsplist.addAll(shortestPath(arrTarget.get(i),arrTarget.get(i+1)));
		}
		return Tsplist;
	}

	/*
	 * copy this graph algo
	 */
	@Override
	public graph copy() {
		graph g = new DGraph(this.get_graphAlgo());
		return g;
	}
	public DGraph get_graphAlgo() {
		return _graphAlgo;
	}
	private void set_graphAlgo(DGraph _graphAlgo) {
		this._graphAlgo = _graphAlgo;
	}
	///////////private///////////////
	private void GreenTag() {
		Iterator<node_data> iter = this.get_graphAlgo().getV().iterator();
		while(iter.hasNext()) {
			nodeData current = (nodeData)iter.next();
			current.setTag(1);
		}
	}


	private void SetNodeWeightMaxInt(){

		Iterator<node_data> iter = this.get_graphAlgo().getV().iterator();
		while(iter.hasNext()) {
			nodeData current = (nodeData)iter.next();
			current.setWeight(Double.MAX_VALUE);;
		}

	}
	private DGraph _graphAlgo;

}
