import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FabriqueG extends javax.swing.JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private VueProjet v;
	private String img = LireDonnes.getRepertoire() + "fabri2.png";
	private String nom;
	private ArrayList<TuileG> JPA = new ArrayList<TuileG>();

	public FabriqueG(String nam, VueProjet vu) {
		super();
		this.v = vu;
		this.nom = nam;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		setName(nam);
		this.setVisible(true);
		this.addMouseListener(this);
		c.gridwidth = 2;
		c.gridy = 0;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;

		JLabel test = new JLabel("" + this.getName().charAt(9) + "", JLabel.CENTER);

		this.add(test, c);
		c.gridwidth = 1;
		c.ipadx = 10;
		c.ipady = 10;

		for (int i = 0; i < 4; i++) {
			JPA.add(new TuileG(nam + " Tuile " + i, v));
			if (i == 0) {
				c.gridy = 1;
				c.gridx = 0;
				c.anchor = GridBagConstraints.LINE_END;
			} else if (i == 1) {
				c.gridy = 1;
				c.gridx = 1;
				c.anchor = GridBagConstraints.LINE_START;
			} else if (i == 2) {
				c.gridy = 2;
				c.gridx = 0;
				c.anchor = GridBagConstraints.LINE_END;
			} else if (i == 3) {
				c.gridy = 2;
				c.gridx = 1;
				c.anchor = GridBagConstraints.LINE_START;
			}
			add(JPA.get(i), c);
		}

	}

	protected void updateTuile(ArrayList<Tuile> alt) {

		for (int t = 0; t < 4; t++) {
			if (alt.size() > 0)
				JPA.get(t).updateTuile(alt.get(t));
			else {
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
		Thread t;
		// TODO Auto-generated method stub
		System.out.println(((JPanel) e.getSource()).getName());
		repaint();
		v.getInputArea().setText(this.nom.charAt(9) + "");
		v.getTextArea().append(">" + nom.charAt(9) + "\n\n");
		Runnable rrr = new Runnable() {
			public void run() {
				v.getInputArea().setText(nom.charAt(9) + "");
				v.getTextArea().append(">" + nom.charAt(9) + "\n\n");
			}
		};
		t=new Thread(rrr);
		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		t.interrupt();
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