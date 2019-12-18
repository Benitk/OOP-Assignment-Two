package dataStructure;

public class edgeData implements edge_data {

	public edgeData(nodeData src, nodeData dest,double weight) {
		this.Src=src;
		this.Dest=dest;
		this.Info=null;
		this.Tag=0;
		this.Weight=weight;
	}

	@Override
	public int getSrc() {

		return this.Src.getKey();
	}

	@Override
	public int getDest() {

		return this.Dest.getKey();
	}

	@Override
	public double getWeight() {

		return this.Weight;
	}

	@Override
	public String getInfo() {

		return this.Info;
	}

	@Override
	public void setInfo(String s) {
		this.Info=s;
	}

	@Override
	public int getTag() {
		
		return this.Tag;
	}

	@Override
	public void setTag(int t) {
		this.Tag=t;

	}
	////////////////private/////////////////
	private nodeData Src;
	private nodeData Dest;
	private double Weight;
	private String Info;
	private int Tag;


}
