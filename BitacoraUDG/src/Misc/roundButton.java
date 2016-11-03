package Misc;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class roundButton extends JButton{
	private boolean round;
    private Color colorPressed, colorFondo;
    private int f,f1;
    private ImageIcon imgAux;
	/**
	 * Method roundButton
	 *
	 *
	 */
	public roundButton(String title) {
		super(title);
		colorPressed = new Color(2,104,14);
    	colorFondo = new Color(4,140,20);
		round = true;
		setContentAreaFilled(false);
	}
	
	public roundButton(ImageIcon img)
	{
		super(img);
		round=true;
		setContentAreaFilled(false);
	}
	
	public roundButton(String title, Color fondo,Color pressed) {
		super(title);
		round = true;
		setContentAreaFilled(false);
	}
	
	public roundButton(ImageIcon img, int i, int i1) {
		super("search",img);
		round = false;
		f = i;
		f1 = i1;
		imgAux = img;
		colorFondo = Color.lightGray;
		colorPressed = Color.LIGHT_GRAY;
		setContentAreaFilled(false);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		if(getModel().isArmed())
		{
			g.setColor(colorPressed);
		}
		else
		{
			g.setColor(colorFondo);
		}
		if(round)
		{
			g.fillOval(0,0,getSize().width-1,getSize().height-1);
		}
		else
		{
			g.fillRoundRect(0,0,imgAux.getIconWidth()-1,imgAux.getIconHeight()-1,f,f1);
		}
		super.paintComponent(g);
	}
	@Override
	protected void paintBorder(Graphics g)
	{
		g.setColor(Color.black);
		if(round)
		{
			g.drawOval(0,0,getSize().width-1,getSize().height-1);
		}
		else
		{
			g.drawRoundRect(0,0,imgAux.getIconWidth()-1,imgAux.getIconHeight()-1,f,f1);
		}
	}
	Shape figura;
	@Override
	public boolean contains(int x, int y)
	{
		if(round)
		{
			figura = new Ellipse2D.Float(0,0,getWidth(),getHeight());
		}
		else
		{
			figura = new RoundRectangle2D.Double(0,0,imgAux.getIconWidth(),imgAux.getIconHeight(),f,f1);
		}
		return (figura.contains(x,y));
	}
}