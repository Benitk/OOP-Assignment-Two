package gui;

import java.awt.Color;

import java.util.Iterator;

import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Range;
import utils.StdDraw;
/**
 * this class Draws all the functions in the collection in a GUI window using the
 * given parameters for the GUI windo and the range & resolution
 * using array of colors to generate new color for each function up to eight
 *  then return to the first color
 * @author ben itzhak
 * @author shnai cohen
 *
 */
public class Draw {
	/**
	 * constructor that get collection of functions
	 * @param functions_list
	 */
	public Draw(graph g) {
		setList(g);
	}
	
	/**
	 * Draws all the functions in the collection using StdDraw
	 * @param w
	 * @param h
	 * @param rx
	 * @param ry
	 * @param res
	 */
	public void draw_graph() {
		StdDraw.setCanvasSize(this.get_Width(), this.get_Height()); // GUI windo witdh and height
		StdDraw.setXscale(this.get_RangeX().get_min(), this.get_RangeX().get_max());
		StdDraw.setYscale(this.get_RangeY().get_min(), this.get_RangeY().get_max());
		// draw points
		/*
		 * double slopeX = 0; double slopeY = 0; double slope = 0; double line_equationY
		 * = 0; double direction = 0;
		 */
		Iterator<node_data> iter = this.getGraphDraw().getV().iterator();
		Iterator<edge_data> iter_edge;
		while(iter.hasNext()) {
			nodeData current = (nodeData) iter.next();
			iter_edge = current.get_edges().values().iterator();
			// draw edges
			while(iter_edge.hasNext()) {
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setPenRadius(0.008);
				edgeData current_edge = (edgeData) iter_edge.next();
				
				// calcations
				/*
				 * slopeY = current.getLocation().y() -
				 * this.getGraphDraw().get_graph().get(current_edge.getDest()).getLocation().y()
				 * ; slopeX = current.getLocation().x() -
				 * this.getGraphDraw().get_graph().get(current_edge.getDest()).getLocation().x()
				 * ; slope = slopeY/slopeX; if(current.getLocation().y() <
				 * this.getGraphDraw().get_graph().get(current_edge.getDest()).getLocation().y()
				 * ) { direction = 0.2; } else { direction = -0.2; } line_equationY = slope *
				 * ((this.getGraphDraw().get_graph().get(current_edge.getDest()).getLocation().x
				 * () - direction) - current.getLocation().x()) + current.getLocation().y();
				 */
					
				
				StdDraw.line(current.getLocation().x(), current.getLocation().y(),
						this.getGraphDraw().get_graph().get(current_edge.getDest()).getLocation().x(),
						this.getGraphDraw().get_graph().get(current_edge.getDest()).getLocation().y());
				/*
				 * StdDraw.setPenColor(Color.YELLOW); StdDraw.setPenRadius(0.03);
				 * StdDraw.point(this.getGraphDraw().get_graph().get(current_edge.getDest()).
				 * getLocation().x() - direction, line_equationY);
				 */
			}	
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.setPenRadius(0.03);
			StdDraw.point(current.getLocation().x(), current.getLocation().y());
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
	/**** Private methods and data ******/

	private void setList(graph _graph) {
		this._graphDraw = (DGraph) _graph;
	}
	
	private DGraph _graphDraw;
	private int _Width = 1000, _Height = 700, _Resolution = 1000;
	private Range _RangeX = new Range(-10,10), _RangeY = new Range(-10,10);

}
