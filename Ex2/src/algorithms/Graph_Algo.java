package algorithms;

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
public class Graph_Algo implements graph_algorithms{

	public Graph_Algo() {
		set_graphAlgo(new DGraph());
	}
	@Override
	public void init(graph g) {
		set_graphAlgo((DGraph)g);

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

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
		nodeData vertex1 = (nodeData) this.get_graphAlgo().get_graph().get(1);
		for(int i = 2; i <= size; i++) {
			this.GreenTag();// make every tag green -- > didnt visit there
			if(isConnected(i, vertex1) == 0) {
				return false;
			}
		}
		nodeData current;
		for(int i = 2; i <= size; i++) {
			current = (nodeData) this.get_graphAlgo().get_graph().get(i);
			this.GreenTag();// make every tag green -- > didnt visit there
			if(isConnected(vertex1.getKey(), current) == 0) {
				return false;
			}
		}
		
		return true;
	}

	public int isConnected(int toFind, nodeData current) {
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
