package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.internal.matchers.ThrowableMessageMatcher;

import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Range;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable {

	public Graph_Algo() {
		set_graphAlgo(new DGraph());
	}
	@Override
	public void init(graph g) {
		set_graphAlgo((DGraph)g);

	}

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
			e.printStackTrace();
		} 

		catch(ClassNotFoundException e) 
		{ 
			e.printStackTrace(); 
		} 



	}

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
			e.printStackTrace();
			//throw new IOException("File is not writable");
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


	@Override
	public List<node_data> TSP(List<Integer> targets) {
		Iterator<Integer> targetlist = targets.iterator();
		int listnum=targetlist.next();
		int listnum2=targetlist.next();
		List<node_data>TspList=new ArrayList<node_data>();
		TspList.addAll(shortestPath(listnum,listnum2));
		Iterator<node_data> iter = TspList.iterator();
		while(targetlist.hasNext()){
			listnum=targetlist.next();
			while(iter.hasNext()) {
				if(iter.next().getKey()==listnum) {
					if(targetlist.hasNext()) {
						listnum=targetlist.next();
						iter=TspList.iterator();
					}
				}
			}
			int index=TspList.size()-1;
			if(!TspList.contains(this.get_graphAlgo().getNode(listnum))) {
				TspList.addAll(shortestPath(TspList.get(TspList.size()-1).getKey(),listnum));
				TspList.remove(index);
			}
			
		}
		return TspList;
	}



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
