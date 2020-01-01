package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import utils.Range;

public class DGraph implements graph, Serializable {
	
	
	/**
	 * init DGraph with hashmap <Integer, node_data> represent as <node.key,node>
	 *  and fill it from a given graph
	 * using Iterator to run on each node and add it to new graph
	 * @param g
	 */
	public DGraph(DGraph g) {
		set_graph(new HashMap<Integer, node_data>());
		Iterator<node_data> iter = g.getV().iterator(); // key, node
		while(iter.hasNext()) {
			nodeData current = (nodeData) iter.next();
			this.get_graph().put(current.getKey(), current);
		}
		set_mc(0);
	}

	//init Dgraph with hashmap <Integer, node_data> represent as <node.key,node>
	public DGraph() {
		set_graph(new HashMap<Integer, node_data>());
		set_mc(0);
	}

	/*
	 * return the node_data by key
	 * if dont exist or empty return null
	 */
	@Override
	public node_data getNode(int key) {

		nodeData vertex = (nodeData)this.get_graph().get(key);
		return vertex;
	}

	/**
	 * if src node doesnt exist or  graph is empty return null,
	 * else if src == dest throw Exception
	 * src.get_edges() --> return the edge_data
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		if(src == dest) {
			throw new RuntimeException("No edge from a vertex to himself");
		}

		try {
			nodeData src_vertex = (nodeData) this.get_graph().get(src);
			edgeData src_to_dest = (edgeData) src_vertex.get_edges().get(dest);
			return src_to_dest;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * if node is null throw Exception
	 * else add it to the hashmap 
	 * increment mc and nodeKeys (_number_key)
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

	/**
	 * if src == dest throw Exception - not possible to connect node to himself
	 * src or dest not null create new edge with src.new_edge(dest, weight);
	 */
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

	/**
	 * return the collection of the node_data
	 */
	@Override
	public Collection<node_data> getV() {
		if(this.get_graph().isEmpty()) {
			throw new RuntimeException("Graph is empty");
		}
		return this.get_graph().values();
	}
	

	
	/**
	 * check if node is valid in the hashmap else throw Exception
	 * return given node inner hashmap values aka his edges the from him to any dest
	 * 
	 */
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
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
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

	/**
	 * check validation of src and dest else throw Exception	 
	 * Delete the edge from the graph, 
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
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
	/** 
	 * return the number of nodes.
	 * @return size
	 */

	@Override
	public int nodeSize() {
		return this.get_graph().size();
	}
	/** 
	 * return the number of edges (assume directional graph).
	 * @return size
	 */

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

	/**
	 * return the Mode Count - counting changes in the graph.
	 * @return mc
	 */
	@Override
	public int getMC() {
		return mc;
	}
	/**
	 * iterate on all the nodes in the graph and return
	 * the Max and min Y value, check the scale
	 * this method is for drawing the graph with GUI class
	 * @return
	 */
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
	
	/**
	 * iterate on all the nodes in the graph and return
	 * the Max and min X value, check the scale
	 * this method is for drawing the graph with GUI class
	 * @return
	 */
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
	public int get_number_key() {
		return _number_key;
	}
	// getter for graph
	public HashMap<Integer, node_data> get_graph() {
		return _graph;
	}


	/***** private data ****/
	private void set_mc(int m) {
		this.mc = m;
	}

	private void set_graph(HashMap<Integer, node_data> _graph) {
		this._graph = _graph;
	}

	private void set_number_key(int _number_key) {
		this._number_key = _number_key;
	}
	private int _number_key;
	private int mc;
	private HashMap<Integer, node_data> _graph;
}
