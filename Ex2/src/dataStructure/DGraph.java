package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DGraph implements graph {

	public DGraph() {
		set_graph(new HashMap<Integer, nodeData>());
	}

	// getter for graph
	public HashMap<Integer, nodeData> get_graph() {
		return _graph;
	}

	/**
	 * if dont exist or empty return null
	 */
	@Override
	public node_data getNode(int key) {
		/* try { */
			nodeData src_vertex = this.get_graph().get(key);
			return src_vertex;
	/*	} catch (Exception e) {
			return null;
		}*/
	}

	/**
	 * if src dont exist or empty return null src.get_edges() --> return the _edge
	 * hashmap
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}
		
		try {
			nodeData src_vertex = this.get_graph().get(src);
			// null.get_edges() can throw
			edgeData src_to_dest = src_vertex.get_edges().get(dest);
			return src_to_dest;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * override if exist
	 * maybe check should check first if exist
	 * 
	 */
	@Override
	public void addNode(node_data n) {
		if(n != null) {
		this.get_graph().put(n.getKey(),(nodeData) n);
		}
		throw new RuntimeException("Input is null");
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}
	
		nodeData src_vertex = this.get_graph().get(src);
		nodeData dest_vertex =  this.get_graph().get(dest);
		if(src_vertex == null) {
			throw new RuntimeException("Source Vertex don't exist");
		}
		else if(dest_vertex == null) {
			throw new RuntimeException("destination Vertex don't exist");
		}
		// create edge
		else {
			src_vertex.new_edge(dest_vertex, w);
		}
	}

	@Override
	public Collection<node_data> getV() {
		if(this.get_graph().isEmpty()) {
			throw new RuntimeException("Graph is empty");
		}
		ArrayList<node_data> getv = new ArrayList<node_data>(this.get_graph().values());
		return getv;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		nodeData n_vertex = this.get_graph().get(node_id);
		if(n_vertex == null) {
			throw new RuntimeException("Vertex don't exist");
		}
		else if(n_vertex.get_edges().isEmpty()) {
			throw new RuntimeException("there is no edges from this vertex");
		}
		else {
			ArrayList<edge_data> get_edges = new ArrayList<edge_data>(n_vertex.get_edges().values());
			return get_edges;
		}
	}
	/**
	 * return the value of the deleted node else null
	 * O(n) for checking each vertex
	 */
	@Override
	public node_data removeNode(int key) {
		// if vertex doesnt exist
		nodeData vertex =  this.get_graph().get(key);
		if(vertex == null) {
			throw new RuntimeException("Vertex don't exist");
		}
		
		// delete this vertex with all his edges as dest
		Iterator<node_data> iter = getV().iterator();
		while(iter.hasNext()) {
			nodeData current = (nodeData)iter.next();
			if(current.get_edges().get(key) != null) {
				current.get_edges().remove(key);
			}
		}
		// delete this vertex with all his edges as src
		return vertex;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}
		
		nodeData src_vertex = this.get_graph().get(src);
		nodeData dest_vertex =  this.get_graph().get(dest);
		if(src_vertex == null) {
			throw new RuntimeException("Source Vertex don't exist");
		}
		else if(dest_vertex == null) {
			throw new RuntimeException("destination Vertex don't exist");
		}
		else {
			return src_vertex.get_edges().remove(dest);
		}
	}

	@Override
	public int nodeSize() {
		return this.get_graph().size();
	}

	@Override
	public int edgeSize() {
		int edge_size = 0;
		Iterator<node_data> iter = getV().iterator();
		while(iter.hasNext()) {
			nodeData current = (nodeData)iter.next();
			edge_size += current.get_edges().size();
			}
		return edge_size;
		}
		

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

	/***** private data ****/
	private void set_graph(HashMap<Integer, nodeData> _graph) {
		this._graph = _graph;
	}

	private HashMap<Integer, nodeData> _graph;
}
