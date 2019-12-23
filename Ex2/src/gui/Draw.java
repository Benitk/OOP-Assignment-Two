package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Range;
import utils.StdDraw;

/**
 * 
 * @author Ben itzhak
 * @author Liad ben moshe
 *
 */
public class Draw {
	/**
	 * constructor that get collection of functions
	 * 
	 * @param functions_list
	 */
	public Draw(graph g) {
		setGraph(g);
	}

	public void draw_graph(int type, ArrayList<node_data> src_dest) {
		StdDraw.setCanvasSize(this.get_Width(), this.get_Height()); // GUI windo witdh and height
		setRangeX(this.getGraphDraw().GraphScaleX());
		setRangeY(this.getGraphDraw().GraphScaleY());
		StdDraw.setXscale(this.get_RangeX().get_min() - 1, this.get_RangeX().get_max() + 1);
		StdDraw.setYscale(this.get_RangeY().get_min() - 1, this.get_RangeY().get_max() + 1);

		// directions compute;
		double directionX = 0;
		double directionY = 0;
		double middleX = 0;
		double middleY = 0;
		// draw points
		Iterator<node_data> iter = this.getGraphDraw().getV().iterator();
		Iterator<edge_data> iter_edge;
		while (iter.hasNext()) {
			nodeData current = (nodeData) iter.next();
			iter_edge = current.get_edges().values().iterator();
			// draw edges
			while (iter_edge.hasNext()) {
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setPenRadius(0.008);
				edgeData current_edge = (edgeData) iter_edge.next();


				// calcations
				directionX = current.getLocation().x()*0.1 + current_edge.getNodeDest().getLocation().x()*0.9;
				directionY = current.getLocation().y()*0.1 + current_edge.getNodeDest().getLocation().y()*0.9;


				// draw edges
				StdDraw.line(current.getLocation().x(), current.getLocation().y(),
						current_edge.getNodeDest().getLocation().x(),
						current_edge.getNodeDest().getLocation().y());

				if(type == 1) {
					StdDraw.setPenColor(Color.GREEN);
					StdDraw.setPenRadius(0.008);
					Iterator<node_data> path = src_dest.iterator();
					nodeData path_src = null;
					nodeData path_dest;
					if(path.hasNext())
						path_src=(nodeData) path.next();
					while(path.hasNext()) {
						path_dest = (nodeData) path.next();

						StdDraw.line(path_src.getLocation().x(), path_src.getLocation().y(),
								path_dest.getLocation().x(),
								path_dest.getLocation().y());
						path_src = path_dest;
					}
				}

				// draw direction
				StdDraw.setPenColor(Color.YELLOW);
				StdDraw.setPenRadius(0.02);
				StdDraw.point(directionX, directionY);
				// edge weight 
				middleX = (current.getLocation().x() + current_edge.getNodeDest().getLocation().x()) / 2;
				middleY = (current.getLocation().y() + current_edge.getNodeDest().getLocation().y()) / 2;
				StdDraw.setPenColor(new Color(0, 153, 0));
				StdDraw.setFont(new Font("Arial", Font.PLAIN, 20));
				StdDraw.text(middleX, middleY + 0.2, String.valueOf(current_edge.getWeight()));

			}
			// draw point
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.setPenRadius(0.03);
			StdDraw.point(current.getLocation().x(), current.getLocation().y());

			// node key
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setFont(new Font("Arial", Font.PLAIN, 20));
			StdDraw.text(current.getLocation().x(), current.getLocation().y() + 0.3, String.valueOf(current.getKey()));
		}
		if(type == 1) {
			// draw algoritem label
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setFont(new Font("Arial", Font.PLAIN, 30));
			StdDraw.text(middleX,this.get_RangeY().get_max() - 1 , "Shortest Path");
		}
		else {
			// draw algoritem label
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setFont(new Font("Arial", Font.PLAIN, 30));
			StdDraw.text(middleX,this.get_RangeY().get_max() - 1 , "Graph");
		}
	}

	public DGraph getGraphDraw() {
		return _graphDraw;
	}

	public int get_Width() {
		return _Width;
	}

	public int get_Height() {
		return _Height;
	}

	public int get_Resolution() {
		return _Resolution;
	}

	public Range get_RangeY() {
		return _RangeY;
	}

	public Range get_RangeX() {
		return _RangeX;
	}
	public void setRangeX(Range r) {
		this._RangeX = r;
	}
	public void setRangeY(Range r) {
		this._RangeY = r;
	}

	/**** Private methods and data ******/

	private void setGraph(graph _graph) {
		this._graphDraw = (DGraph) _graph;
	}

	private DGraph _graphDraw;
	private int _Width = 1000, _Height = 700, _Resolution = 1000;
	private Range _RangeX, _RangeY;

}
