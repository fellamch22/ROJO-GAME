import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

public class TuileG extends javax.swing.JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private VueProjet v;

	private String repertoire = LireDonnes.getRepertoire();

	private String imageBleu = repertoire + "bleu.png";
	private String imageRouge = repertoire + "red.png";
	private String imageJaune = repertoire + "jaune.png";
	private String imageNoir = repertoire + "noir.png";
	private String imageBlanc = repertoire + "blanc.png";
	private String imageGreen = repertoire + "green.png";
	private String imageEmpty = repertoire + "emptyy.png";
	private String imageJoker = repertoire + "joker.png";

	private Color c = new Color(238, 238, 238);

	private ImageIcon mb = new ImageIcon(imageBleu);
	private ImageIcon mr = new ImageIcon(imageRouge);
	private ImageIcon mj = new ImageIcon(imageJaune);
	private ImageIcon mn = new ImageIcon(imageNoir);
	private ImageIcon ml = new ImageIcon(imageBlanc);
	private ImageIcon mg = new ImageIcon(imageGreen);
	private ImageIcon me = new ImageIcon(imageEmpty);
	private ImageIcon mk = new ImageIcon(imageJoker);

	private Image scaledImageBleu = mb.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageRouge = mr.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageJaune = mj.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageNoir = mn.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageBlanc = ml.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageGreen = mg.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageEmpty = me.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
	private Image scaledImageJoker = mk.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);

	public TuileG(String nam, VueProjet vu) {
		super();
		this.v = vu;
		setSize(60, 60);
		setName(nam);
		setOpaque(false);
		this.addMouseListener(this);
	}

	protected void updateTuile(Tuile tu) {
		setOpaque(false);
		if (tu == null) {
			c = Color.GRAY;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == 'b') {
			c = Color.BLUE;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == 'r') {
			c = Color.RED;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == 'l') {
			c = Color.WHITE;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == 'n') {
			c = Color.BLACK;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == 'j') {
			c = Color.YELLOW;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == '-') {
			c = Color.GREEN;
			// this.setBackground(c);
			repaint();
		} else if (tu.toString().charAt(0) == '*') {
			c = Color.PINK;
			// this.setBackground(c);
			repaint();
		}

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (c == Color.BLUE) {
			g.drawImage(scaledImageBleu, 0, 0, this);
		} else if (c == Color.RED) {
			g.drawImage(scaledImageRouge, 0, 0, this);
		} else if (c == Color.YELLOW) {
			g.drawImage(scaledImageJaune, 0, 0, this);
		} else if (c == Color.BLACK) {
			g.drawImage(scaledImageNoir, 0, 0, this);
		} else if (c == Color.GREEN) {
			g.drawImage(scaledImageGreen, 0, 0, this);
		} else if (c == Color.WHITE) {
			g.drawImage(scaledImageBlanc, 0, 0, this);
		} else if (c == Color.GRAY) {
			g.drawImage(scaledImageEmpty, 0, 0, this);
		} else if (c == Color.PINK) {
			g.drawImage(scaledImageJoker, 0, 0, this);
		}

	}

	// les fonctions de mouselistener
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(((TuileG) e.getSource()).getName() + c.toString());
		repaint();
		if (c == Color.BLUE) {
			v.getInputArea().setText("1");
			v.getTextArea().append(">1\n\n");
		} else if (c == Color.WHITE) {
			v.getInputArea().setText("2");
			v.getTextArea().append(">2\n\n");
		} else if (c == Color.YELLOW) {
			v.getInputArea().setText("3");
			v.getTextArea().append(">3\n\n");
		} else if (c == Color.RED) {
			v.getInputArea().setText("4");
			v.getTextArea().append(">4\n\n");
		} else if (c == Color.BLACK) {
			v.getInputArea().setText("5");
			v.getTextArea().append(">5\n\n");
		}
		v.runnableViewProjet.run();
	}

	// les fonctions de mouselistener
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}