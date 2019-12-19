package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
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
		Iterator<node_data> iter = (Iterator<node_data>) this.get_graphAlgo().getV().iterator();
		nodeData first=(nodeData) iter.next();
		while(iter.hasNext()) {
			this.GreenTag();
			if(isConnected(iter.next().getKey(),first)==0)
				return false;
		}
		Iterator<node_data> iter2 = (Iterator<node_data>) this.get_graphAlgo().getV().iterator();
		while(iter2.hasNext()) {
			this.GreenTag();
			if(isConnected(first.getKey(),(nodeData)iter2.next())==0)
				return false;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
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
	private DGraph _graphAlgo;

}
