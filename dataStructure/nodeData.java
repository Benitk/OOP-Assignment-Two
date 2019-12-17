package dataStructure;

import utils.Point3D;

public class nodeData implements node_data{
	public nodeData(int key, int tag, Point3D point, double weight, String info) {
		setLocation(point);
		setKey(key);
		setTag(tag);
		setWeight(weight);
		setInfo(info);
		
	}

	private void setKey(int key) {
		this._key = key;
		
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
		this._tag = t;
	}

	
	/******* private data **********/
	/*
	 * private enum nodeColor{ white, gray, black }
	 */
		private int _tag, _key;
		private String _info;
		private double _weight; 
		private Point3D _location;	
}
