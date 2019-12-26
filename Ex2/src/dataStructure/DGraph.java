package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import utils.Range;

public class DGraph implements graph, Serializable {
	
	public DGraph(DGraph g) {
		set_graph(new HashMap<Integer, node_data>());
		Iterator<node_data> iter = g.getV().iterator(); // key, node
		while(iter.hasNext()) {
			nodeData current = (nodeData) iter.next();
			this.get_graph().put(current.getKey(), current);
		}
		set_mc(0);
	}

	public DGraph() {
		set_graph(new HashMap<Integer, node_data>());
		set_mc(0);
	}

	// getter for graph
	public HashMap<Integer, node_data> get_graph() {
		return _graph;
	}

	/**
	 * if dont exist or empty return null
	 */
	@Override
	public node_data getNode(int key) {
		/* try { */
		nodeData vertex = (nodeData)this.get_graph().get(key);
		return vertex;
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
			nodeData src_vertex = (nodeData) this.get_graph().get(src);
			// null.get_edges() can throw
			edgeData src_to_dest = (edgeData) src_vertex.get_edges().get(dest);
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
		if(n == null) {
			throw new RuntimeException("Input is null");
		}
		set_number_key(this.get_number_key() + 1);
		((nodeData) n).setKey(this.get_number_key());
		this.get_graph().put(this.get_number_key(),(nodeData) n);
		this.set_mc(this.getMC()+1);
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}

		nodeData src_vertex = (nodeData) this.get_graph().get(src);
		nodeData dest_vertex =  (nodeData) this.get_graph().get(dest);
		if(src_vertex == null) {
			throw new RuntimeException("Source Vertex don't exist");
		}
		else if(dest_vertex == null) {
			throw new RuntimeException("destination Vertex don't exist");
		}
		// create edge
		else {
			src_vertex.new_edge(dest_vertex, w);
			this.set_mc(this.getMC()+1);
		}
	}

	@Override
	public Collection<node_data> getV() {
		if(this.get_graph().isEmpty()) {
			throw new RuntimeException("Graph is empty");
		}
		return this.get_graph().values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		nodeData n_vertex = (nodeData) this.get_graph().get(node_id);
		if(n_vertex == null) {
			throw new RuntimeException("Vertex don't exist");
		}
		else if(n_vertex.get_edges().isEmpty()) {
			throw new RuntimeException("there is no edges from this vertex");
		}
		else {
			return n_vertex.get_edges().values();
		}
	}
	/**
	 * return the value of the deleted node else null
	 * O(n) for checking each vertex
	 */
	@Override
	public node_data removeNode(int key) {
		// if vertex doesnt exist
		nodeData vertex = (nodeData) this.get_graph().get(key);
		if(vertex == null) {
			throw new RuntimeException("Vertex don't exist");
		}

		// delete this vertex with all his edges as dest
		Iterator<node_data> iter = getV().iterator();
		while(iter.hasNext()) {
			nodeData current = (nodeData)iter.next();
			if(current.get_edges().get(key) != null) {
				current.get_edges().remove(key);
				this.set_mc(this.getMC()+1);
			}
		}
		// delete this vertex with all his edges as src
		this.set_mc(this.getMC()+1);
		return this.get_graph().remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}

		nodeData src_vertex = (nodeData) this.get_graph().get(src);
		nodeData dest_vertex = (nodeData) this.get_graph().get(dest);
		if(src_vertex == null) {
			throw new RuntimeException("Source Vertex don't exist");
		}
		else if(dest_vertex == null) {
			throw new RuntimeException("destination Vertex don't exist");
		}
		else {
			this.set_mc(this.getMC()+1);
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
		return mc;
	}
	public Range GraphScaleY() {
		double minY, maxY;
		Iterator<node_data> iter = this.getV().iterator();
		minY = maxY = iter.next().getLocation().y();
		while(iter.hasNext()) {
			nodeData current = (nodeData) iter.next();
			if(current.getLocation().y() < minY) {
				minY = current.getLocation().y();
			}
			else if(current.getLocation().y() > maxY){
				maxY = current.getLocation().y();
			}
		}
		return new Range(minY, maxY);
	}
	public Range GraphScaleX() {
		double minX, maxX;
		Iterator<node_data> iter = this.getV().iterator();
		minX = maxX = iter.next().getLocation().x();
		while(iter.hasNext()) {
			nodeData current = (nodeData) iter.next();
			if(current.getLocation().x() < minX) {
				minX = current.getLocation().x();
			}
			else if(current.getLocation().x() > maxX){
				maxX = current.getLocation().x();
			}
		}
		return new Range(minX, maxX);
	}
	

	/***** private data ****/
	private void set_mc(int m) {
		this.mc = m;
	}
	
	private void set_graph(HashMap<Integer, node_data> _graph) {
		this._graph = _graph;
	}
	public int get_number_key() {
		return _number_key;
	}


	public void set_number_key(int _number_key) {
		DGraph._number_key = _number_key;
	}
	private static int _number_key;
	private int mc;
	private HashMap<Integer, node_data> _graph;
}
