import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlateauCentralG extends javax.swing.JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private String img = LireDonnes.getRepertoire() + "centre.png";
	private ArrayList<TuileG> JPA = new ArrayList<TuileG>();
	private int tailleMax = 40;
	private VueProjet v;

	public PlateauCentralG(VueProjet vu) {
		super();
		this.v = vu;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setName("Plateau Central");
		this.setVisible(true);
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		c.gridwidth = 8;
		c.gridy = 0;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		add(new JLabel("          PLATEAU CENTRAL          ", JLabel.CENTER), c);
		c.gridwidth = 1;
		c.ipadx = 30;
		c.ipady = 30;
		this.addMouseListener(this);
		int num = 0;
		for (int i = 0; i < 5; i++) {
			c.gridy = i + 1;
			for (int j = 0; j < 8; j++) {
				c.gridx = j;
				JPA.add(new TuileG(this.getName() + " Tuile " + i + " - " + j, v));
				add(JPA.get(num), c);
				num++;
			}
		}
		setOpaque(false);
	}

	protected void updateTuile(ArrayList<Tuile> alt) {
		for (int t = 0; t < tailleMax; t++) {
			if (t < alt.size()) {
				JPA.get(t).updateTuile(alt.get(t));
			} else {
				JPA.get(t).updateTuile(null);
			}
		}

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon m = new ImageIcon(img);
		Image monImage = m.getImage();
		g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(((JPanel) e.getSource()).getName());
		//repaint();
		v.getInputArea().setText("0");
		v.getTextArea().append("0\n\n");
		v.runnableViewProjet.run();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}