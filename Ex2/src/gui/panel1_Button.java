package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class panel1_Button {
	private static int _height = 220;
	private JButton _button;
	private final Color _dark = new Color(44, 45, 50);
	public int getheight() {
		return _height;
	}
	public void setheight(int _height) {
		panel1_Button._height = _height;
	}
	public JButton getbutton() {
		return _button;
	}
	public void setbutton(JButton _button) {
		this._button = _button;
	}
	public Color getBackColor() {
		return _dark;
	}
	
	public panel1_Button(String name) {
		setbutton(new JButton(name));  
		this.getbutton().setBounds(0, this.getheight(), 300, 45);
		this.getbutton().setForeground(Color.WHITE);
		this.getbutton().setFont((new Font("Arial", Font.PLAIN, 17)));
		this.getbutton().setBackground(this.getBackColor());
		this.getbutton().setBorderPainted(false);
		setheight(this.getheight()+60);
	}
}
