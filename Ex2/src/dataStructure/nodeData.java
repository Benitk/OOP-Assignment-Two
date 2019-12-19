package dataStructure;

import java.io.Serializable;
import java.util.HashMap;
import utils.Point3D;

/**
 * 
 * @author user
 *
 */
public class nodeData implements node_data, Serializable {
	public nodeData(Point3D point) {
		setLocation(point);
		setKey(++_number_key);
		setWeight(Integer.MAX_VALUE);
		setInfo("");
		set_edges();
		setTag(1);
	}

	// adding a new edge with this node as src and input as dest
	public void new_edge(nodeData dest, double weight) {
		edgeData new_edge = new edgeData(this, dest, weight);
		this.get_edges().put(dest._key, new_edge);
	}

	public HashMap<Integer, edge_data> get_edges() {
		return this._edges;
	}

	@Override
	public int getKey() {
		return this._key;
	}

	@Override
	public Point3D getLocation() {
		return this._location;
	}

	@Override
	public void setLocation(Point3D p) {
		this._location = p;

	}

	@Override
	public double getWeight() {
		return this._weight;
	}

	@Override
	public void setWeight(double w) {
		this._weight = w;

	}

	@Override
	public String getInfo() {
		return this._info;
	}

	@Override
	public void setInfo(String s) {
		this._info = s;

	}

	@Override
	public int getTag() {
		return this._tag;
	}

	@Override
	public void setTag(int t) {
		if (1 > t || 3 > t) {
			this._tag = 1;
		}
		this._tag = t;
	}

	/******* private data **********/

	//private enum tagColor{Green, Yellow, Red}

	private void setKey(int key) {
		this._key = key;
	}

	private void set_edges() {
		this._edges = new HashMap<Integer, edge_data>();
	}

	// Integer represent dest.key and edgeData
	private HashMap<Integer, edge_data> _edges;
	//private tagColor _tagColor;
	private int _tag, _key;
	private String _info;
	private double _weight;
	private Point3D _location;
	private static int _number_key;
}
