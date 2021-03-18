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

public class JoueurG extends javax.swing.JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private VueProjet v;
	private String img = LireDonnes.getRepertoire() + "player.png";
	private String img2 = LireDonnes.getRepertoire() + "Player2.png";
	private String img3 = LireDonnes.getRepertoire() + "JokerBackground.png";
	private String img4 = LireDonnes.getRepertoire() + "JokerBackground2.png";
	private ArrayList<TuileG> L1 = new ArrayList<TuileG>();
	private ArrayList<TuileG> L2 = new ArrayList<TuileG>();
	private ArrayList<TuileG> L3 = new ArrayList<TuileG>();
	private ArrayList<TuileG> L4 = new ArrayList<TuileG>();
	private ArrayList<TuileG> L5 = new ArrayList<TuileG>();
	private ArrayList<TuileG> LPlancher = new ArrayList<TuileG>();
	private ArrayList<ArrayList<TuileG>> LignesMotifs = new ArrayList<ArrayList<TuileG>>();

	private ArrayList<ArrayList<TuileG>> Mur = new ArrayList<ArrayList<TuileG>>();
	private ArrayList<TuileG> tempMur1 = new ArrayList<TuileG>();
	private ArrayList<TuileG> tempMur2 = new ArrayList<TuileG>();
	private ArrayList<TuileG> tempMur3 = new ArrayList<TuileG>();
	private ArrayList<TuileG> tempMur4 = new ArrayList<TuileG>();
	private ArrayList<TuileG> tempMur5 = new ArrayList<TuileG>();
	private JLabel labelPanel;
	private GridBagConstraints c;
	private boolean currentPlayer = false;
	private boolean mode;
	
	public JoueurG(String nam, int score, VueProjet vu,boolean mode) {
		super();
		this.v = vu;
		this.mode=mode;
		setName(nam);
		// setLayout(new GridLayout(7, 13));
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		labelPanel = new JLabel("", JLabel.CENTER);
		c.gridwidth = 13;
		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 0;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		labelPanel.setVisible(true);
		c.anchor = GridBagConstraints.LINE_START;
		add(labelPanel, c);
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;

		this.addMouseListener(this);
		LignesMotifs.add(L1);
		LignesMotifs.add(L2);
		LignesMotifs.add(L3);
		LignesMotifs.add(L4);
		LignesMotifs.add(L5);
		LignesMotifs.add(LPlancher);

		Mur.add(tempMur1);
		Mur.add(tempMur2);
		Mur.add(tempMur3);
		Mur.add(tempMur4);
		Mur.add(tempMur5);
		JLabel lab;
		for (int i = 0; i < 5; i++) { // Ligne
			c.gridy = 1 + i;
			c.gridx = 0;
			lab = new JLabel("" + (i + 1), JLabel.CENTER);
			lab.setVerticalAlignment(JLabel.TOP);
			c.anchor = GridBagConstraints.LINE_START;
			add(lab, c);
			c.anchor = GridBagConstraints.CENTER;
			for (int j = 0; j < 7; j++) { // Colone
				c.gridx = j + 1;
				if (j <= i) {
					LignesMotifs.get(i).add(new TuileG(nam + " Ligne " + i + " Tuile " + j, v));
					add(LignesMotifs.get(i).get(j), c);
				} else {
					LignesMotifs.get(i).add(new TuileG(null, v));
					add(LignesMotifs.get(i).get(j), c);
				}
			}
			for (int j = 0; j < 5; j++) {
				c.gridx = 8 + j;
				Mur.get(i).add(new TuileG(nam + "MUR Ligne " + i + " Tuile " + j, v));
				add(Mur.get(i).get(j), c);
			}

		}

//Plancher
		c.anchor = GridBagConstraints.LINE_START;
		c.gridy = 6;
		c.gridx = 0;
		JLabel p = new JLabel(" > ", JLabel.CENTER);
		p.setVerticalAlignment(JLabel.TOP);
		add(p, c);
		c.anchor = GridBagConstraints.CENTER;
		for (int i = 0; i < 7; i++) {
			c.gridx = i + 1;
			LPlancher.add(new TuileG(null, v));
			add(LPlancher.get(i), c);
		}

	}

	protected void updateTuile(ArrayList<ArrayList<Tuile>> alt) {
		// Lignes Motifs
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j <= i; j++) {
				LignesMotifs.get(i).get(j).updateTuile(alt.get(i).get(j));
			}
		}
		// Plancher
		for (int i = 0; i < 7; i++) {
			LPlancher.get(i).updateTuile(alt.get(5).get(i));
		}
		// Mur

		for (int i = 6; i <= 10; i++) {
			for (int j = 0; j < 5; j++) {
				Mur.get(i - 6).get(j).updateTuile(alt.get(i).get(j));
			}
		}
	}

	public void updateCurrentPlayer(String s) {
		if (s == this.getName()) {
			// labelPanel.setOpaque(true);
			// labelPanel.setBackground(new Color(108, 194, 209));
			currentPlayer = true;
		} else {
			// labelPanel.setOpaque(false);
			// labelPanel.setBackground(new Color(238, 238, 238));
			currentPlayer = false;
		}
	}

	protected void updateScore(int s) {
		labelPanel.setText(this.getName() + "  -  Score = " + s);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (mode) {
			if (currentPlayer == false) {
				ImageIcon m = new ImageIcon(img);
				Image monImage = m.getImage();
				g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
			} else {
				ImageIcon m = new ImageIcon(img2);
				Image monImage = m.getImage();
				g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		} else {
			if (currentPlayer == false) {
				ImageIcon m = new ImageIcon(img3);
				Image monImage = m.getImage();
				g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
			} else {
				ImageIcon m = new ImageIcon(img4);
				Image monImage = m.getImage();
				g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Thread t;
		// TODO Auto-generated method stub
		System.out.println(((JPanel) e.getSource()).getName());
		repaint();
		//System.out.println(e.getX() + " * " + e.getY() + " > " + 100 * e.getY() / this.getHeight());
		// si on click en haut du plateau, renvoi le numero de la ligne, sinon plancher
		if (100 * e.getY() / this.getHeight() < 23) {
			v.getInputArea().setText("1");
			v.getTextArea().append(">1\n\n");
		} else if (100 * e.getY() / this.getHeight() < 38) {
			v.getInputArea().setText("2");
			v.getTextArea().append(">2\n\n");
		} else if (100 * e.getY() / this.getHeight() < 51) {
			v.getInputArea().setText("3");
			v.getTextArea().append(">3\n\n");
		} else if (100 * e.getY() / this.getHeight() < 66) {
			v.getInputArea().setText("4");
			v.getTextArea().append(">4\n\n");
		} else if (100 * e.getY() / this.getHeight() < 78) {
			v.getInputArea().setText("5");
			v.getTextArea().append(">5\n\n");
		} else {
			v.getInputArea().setText("0");
		}
		if (100 * e.getY() / this.getHeight() < 78 && v.getmultiClick()==true) {
			Runnable rrr = new Runnable() {
				public void run() {
					if (100 * e.getY() / getHeight() < 23) {
						v.getInputArea().setText("1");
						v.getTextArea().append(">1\n\n");
					} else if (100 * e.getY() / getHeight() < 38) {
						v.getInputArea().setText("2");
						v.getTextArea().append(">2\n\n");
					} else if (100 * e.getY() / getHeight() < 51) {
						v.getInputArea().setText("3");
						v.getTextArea().append(">3\n\n");
					} else if (100 * e.getY() / getHeight() < 66) {
						v.getInputArea().setText("4");
						v.getTextArea().append(">4\n\n");
					} else if (100 * e.getY() / getHeight() < 78) {
						v.getInputArea().setText("5");
						v.getTextArea().append(">5\n\n");
					}
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
		}
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