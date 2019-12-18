package algorithms;

import java.util.List;

import dataStructure.DGraph;
import dataStructure.graph;
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

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
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
	private DGraph _graphAlgo;

}
