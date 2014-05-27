/**
 * 
 */
package asgn2Simulators;

import java.awt.GraphicsConfiguration;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JFrame;

/**
 * @author Bec
 *
 */
public class GUI extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = -703100886255993604L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public GUI(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

}
