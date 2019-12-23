package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import algorithms.Graph_Algo;

import utils.Point3D;

public class Graph_GUI extends JFrame implements ActionListener, MouseListener {


	public Graph_GUI(Graph_Algo gAlgo) {
		set_graphGui(gAlgo);
		initGUI();
	}

	private void initGUI() {
		this.setSize(1000, 700);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu();
		panel1();
		panel3();
		panel2();
		//panel2();
		/*
		 *
		 * MenuBar menuBar = new MenuBar(); Menu menu = new Menu("Menu");
		 * menuBar.add(menu); this.setMenuBar(menuBar);
		 * 
		 * MenuItem showGraph = new MenuItem("show Graph");
		 * showGraph.addActionListener(this);
		 * 
		 * MenuItem save = new MenuItem("save Graph"); save.addActionListener(this);
		 * 
		 * JMenu Algorithms = new JMenu("Algorithms on Graph");
		 * 
		 * menu.add(showGraph); menu.add(item2); menu.add(Algorithms);
		 * 
		 * this.addMouseListener(this);
		 */
	}
	private void panel1() {
		set_panel1(new JPanel());  
		this.get_panel1().setLayout(null);
		this.get_panel1().setBounds(0,0,300,this.getHeight()); 
		Color dark = new Color(44, 45, 50);
		this.get_panel1().setBackground(dark);  
		
		
		//labels
		JLabel label=new JLabel("Algorithms"); 
		label.setForeground(Color.WHITE);
		label.setFont((new Font("Arial", Font.PLAIN, 30)));
		label.setBounds(75, 20, 200, 100);
		
		// buttons
		panel1_Button b1 = new panel1_Button("is Connected");
		panel1_Button b2 = new panel1_Button("shortest Path Dist");
		panel1_Button b3 = new panel1_Button("shortest Path");
	    panel1_Button b4 = new panel1_Button("TSP");
	    b1.getbutton().addActionListener(this);
	    b2.getbutton().addActionListener(this);
	    b3.getbutton().addActionListener(this);
	    b4.getbutton().addActionListener(this);
	
	    this.get_panel1().add(label);
	    this.get_panel1().add(b1.getbutton());
	    this.get_panel1().add(b2.getbutton());
	    this.get_panel1().add(b3.getbutton());
	    this.get_panel1().add(b4.getbutton());
		this.add(this.get_panel1());  
	}
	
	private void panel2() {
		set_panel2(new JPanel());  
		this.get_panel2().setLayout(null);
		this.get_panel2().setBounds(300,0,this.getWidth()-300,this.getHeight()); 
		Color green = new Color(16, 152, 150);
		this.get_panel2().setBackground(green);  
		
		// text field
		set_console(new JTextField());  
		this.get_console().setBounds(70,420,550,50);  
		this.get_console().setBackground(new Color(242, 242, 244));
		this.get_console().setEditable(false);
		this.get_console().setFont((new Font("Arial", Font.PLAIN, 20)));
		this.get_console().setHorizontalAlignment(SwingConstants.CENTER);
		
		//button
		set_show_graph(new JButton("Show Graph"));
		this.get_show_graph().setBounds(270, 490, 150, 60);
		get_show_graph().setForeground(Color.WHITE);
		get_show_graph().setFont((new Font("Arial", Font.PLAIN, 15)));
		get_show_graph().setBackground(new Color(44, 45, 50));
		get_show_graph().setBorderPainted(false);
		get_show_graph().addActionListener(this);
		
		
		this.get_panel2().add(get_show_graph());
		this.get_panel2().add(_console);
		this.add(this.get_panel2());  
	}
	
	private void panel3() {
		set_panel3(new JPanel());  
		this.get_panel3().setLayout(null);
		this.get_panel3().setBounds(300,60,this.getWidth()-300,200); 
		Color dark_green = new Color(3, 81, 81);
		
		// label 
		
		JLabel label2=new JLabel("Graph GUI"); 
		label2.setForeground(Color.WHITE);
		label2.setFont((new Font("Arial", Font.PLAIN, 50)));
		label2.setBounds(220,50, 500, 100);
		this.get_panel3().setBackground(dark_green);  
		this.get_panel3().add(label2);
		this.add(this.get_panel3());  
	}



	private void menu() {
		set_mb(new JMenuBar());
		set_menu(new JMenu("Menu"));
		setMenuItem1(new JMenuItem("save Graph"));
		this.getMenuItem1().addActionListener(this);
		this.get_menu().add(this.getMenuItem1());
		this.get_mb().add(this.get_menu());
		this.setJMenuBar(this.get_mb());
		this.addMouseListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);
		
		// line for label 1
		g.setColor(Color.WHITE);
		g.setFont((new Font("Arial", Font.PLAIN, 40)));
		g.drawLine(0, 170, 300, 170);
		
		// line for label 2
	//	g.setColor(Color.WHITE);
		//g.setFont((new Font("Arial", Font.PLAIN, 40)));
	//	g.drawLine(110, 260, 200, 260);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// click on show Graph
		if(e.getActionCommand().equals("Show Graph")) {
			Draw d = new Draw(this.get_graphGui().get_graphAlgo());
			d.draw_graph();
		}
		// is connected
		if (e.getActionCommand().equals("is Connected")) {
			  _console.setText("is Connected: " + String.valueOf(this.get_graphGui().isConnected()));
		}
		// shortest path dist
		if (e.getActionCommand().equals("shortest Path Dist")) {
			//_console.setText(String.valueOf(this.get_graphGui().shortestPathDist(src, dest)));
		}
		
		
		 

		String str = e.getActionCommand();

		if (str.equals("Item 1")) {
			Point3D p1 = new Point3D(100, 100);
			Point3D p2 = new Point3D(50, 300);
			Point3D p3 = new Point3D(400, 150);

			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
}

	@Override
	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		System.out.println(x+","+y);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}


	public Graph_Algo get_graphGui() {
		return _graph_gui;
	}


	public JMenu get_menu() {
		return _menu;
	}


	public JPanel get_panel1() {
		return _panel1;
	}

	public JMenuItem getMenuItem1() {
		return i1;
	}

	public JTextField get_console() {
		return _console;
	}


	public JPanel get_panel2() {
		return _panel2;
	}
	public JPanel get_panel3() {
		return _panel3;
	}


	public JButton get_show_graph() {
		return _show_graph;
	}
	public JMenuBar get_mb() {
		return _mb;
	}

	/**** private data ****/
	private void set_panel2(JPanel _p) {
		this._panel2 = _p;
	}

	private void set_show_graph(JButton _show_graph) {
		this._show_graph = _show_graph;
	}
	
	private void set_mb(JMenuBar _mb) {
		this._mb = _mb;
	}
	
	private void set_panel3(JPanel _p) {
		this._panel3 = _p;
	}
	private void set_graphGui(Graph_Algo _graph_gui) {
		this._graph_gui = _graph_gui;
	}
	private void set_panel1(JPanel _p) {
		this._panel1 = _p;
	}
	private void set_menu(JMenu _menu) {
		this._menu = _menu;
	}

	private void setMenuItem1(JMenuItem i1) {
		this.i1 = i1;
	}
	private void set_console(JTextField _console) {
		this._console = _console;
	}
	
	private Graph_Algo _graph_gui;
	private JMenu _menu;
	private JMenuItem i1;
	private JPanel _panel1;
	private JPanel _panel2;
	private JPanel _panel3;
	private JTextField _console;
	private JButton _show_graph;
	private JMenuBar _mb;

}
