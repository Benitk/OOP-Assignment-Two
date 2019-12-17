package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

public class DGraph implements graph{
	
	public DGraph() {
		this._graph = new LinkedHashMap<Integer, nodeData>();
	}
	
	
	@Override
	public node_data getNode(int key) {
		if(this._graph.isEmpty()) {
			return null; // later maybe throw
		} /// if dont exist 
		return this._graph.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// if dont exist
		return this._graph.get(src).get_edges().get(dest);
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}
	/***** private data ****/
	private LinkedHashMap<Integer, nodeData> _graph;
}
