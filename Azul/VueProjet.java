
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VueProjet implements MouseListener, MouseMotionListener {
	private int x1, y1, x2, y2;
	private int nbfabriques;
	private int nbjoueurs;
	private ArrayList<FabriqueG> fabriquesG = new ArrayList<FabriqueG>();
	private ArrayList<JoueurG> joueurG = new ArrayList<JoueurG>();
	private JFrame win;
	private FabriqueG Fabrique;
	private JPanel FabriquesPanel;
	private PlateauCentralG PlateauCentral;

	private ConsolePanelG ConsolePanel;
	public Runnable runnableViewProjet;
	private boolean multiClick;
	public JFrame getWin() {return win;}
	
	public VueProjet(Partie p,boolean mode) {
		nbjoueurs = p.getJoueurs().size();
		nbfabriques = p.zone.getFabrique().size();

		// Variables Graphiques
		win = new JFrame();
		FabriquesPanel = new JPanel();
		JPanel PlateauJoueursPanel = new JPanel();
		JPanel PlateauJoueurBas = new JPanel();

		// Config JFrame
		win.setTitle("AZUL");
		win.setSize(new Dimension(1680, 1050));
		win.setLocationRelativeTo(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// La fenetre principale a 3 lignes avec une unique case.
		// La premiere ligne ( JPanel FabriquesPanel ) = les fabriques 
		// La 2e ligne  ( JPanel PlateauJoueursPanel)  = les plateaux joueur 1 et 2 et plateau central PlateauCentralG
		// la 3e ligne  ( JPanel PlateauJoueurBas)     =  les plateaux facultatifs joueur 3 et 4, ainsi que la consolepanel 
		win.setLayout(new GridLayout(3, 1));

		// BEGIN Config FabriquePanel
		
			// FabriquePanel comporte toutes les fabriques, soit 1 ligne avec 5 , 7 ou 9 fabriques/Cases .
		FabriquesPanel.setLayout(new GridLayout(1, nbfabriques));
		for (int i = 1; i <= nbfabriques; i++) {

			Fabrique = new FabriqueG("Fabrique " + i,this);
			Fabrique.updateTuile(p.zone.getFabrique().get(i - 1).getTuiles());
			FabriquesPanel.add(Fabrique);
			fabriquesG.add(Fabrique);

		}
		// END PANEL FabriquePanel
		

		// BEGIN Config PlateauJoueursPanel
				// 1 ligne avec 3 cases : PlateauJoueur 1 , PlateauCentral ,PlateauJoueur 2
			PlateauJoueursPanel.setLayout(new GridLayout(1, 3));
			PlateauJoueursPanel.setName("Plateau Central ");
			JoueurG J;
			for (int i = 1; i <= nbjoueurs; i++) {
				J = new JoueurG(p.getJoueurs().get(i - 1).getNom(), p.getJoueurs().get(i - 1).getScore(),this,mode);
				joueurG.add(J);
			}
	
				// Config Plateau Central
			PlateauCentral = new PlateauCentralG(this);
	
				// Panels.add(PlateauCentral);
			PlateauCentral.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ev) {
					System.out.println(((JPanel) ev.getSource()).getName());
				}
			});
			PlateauCentral.updateTuile(p.zone.getCentre().getTuiles());
			PlateauCentral.repaint();
	
				// Ajout des JPanels au PlateauJoueursPanel
	
			PlateauJoueursPanel.add(joueurG.get(0));
			PlateauJoueursPanel.add(PlateauCentral);
			PlateauJoueursPanel.add(joueurG.get(1));
		
		// END Panel PlateauJoueursPanel
		
		// BEGIN Panel PlateauJoueurBas
			// 1 ligne avec 3 cases : PlateauJoueur 3 , ConsolePanel ,PlateauJoueur 4
			PlateauJoueurBas.setLayout(new GridLayout(1, 3));
			if (nbjoueurs >= 3) {
				PlateauJoueurBas.add(joueurG.get(2));
			} else {
				PlateauJoueurBas.add(new JPanel());
			}
			
			ConsolePanel = new ConsolePanelG();
			PlateauJoueurBas.add(ConsolePanel);
			
			if (nbjoueurs >= 4) {
				PlateauJoueurBas.add(joueurG.get(3));
			} else {
				PlateauJoueurBas.add(new JPanel());
			}
		// END Panel PlateauJoueurBas
		
		// Config Pre launch

		//Ajout des 3 JPanels sur le JFrame
		win.add(FabriquesPanel);
		win.add(PlateauJoueursPanel);
		win.add(PlateauJoueurBas);
		
		win.pack(); 
		win.setResizable(true);
		win.setVisible(true);
		
		//Create a parallel thread to repaint the jframe in loop
		runnableViewProjet = new Runnable() {
			public void run() {
				//while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					win.repaint();
				//}
			}
		};
		(new Thread(runnableViewProjet)).start();

	}

	public void refresh(Partie p) {

		// Refresh Fabriques
		for (int i = 1; i <= nbfabriques; i++) {
			fabriquesG.get(i - 1).updateTuile(p.zone.getFabrique().get(i - 1).getTuiles());
		}
		// Refresh Centre
		PlateauCentral.updateTuile(p.zone.getCentre().getTuiles());

		// Refresh Joueurs
		for (int i = 1; i <= nbjoueurs; i++) {
			joueurG.get(i - 1).updateTuile(p.getJoueurs().get(i - 1).getP().getLigneMotifTab());
			joueurG.get(i - 1).updateScore(p.getJoueurs().get(i - 1).getScore());
		}
		getWin().repaint();
	}

	public void updateCurrentPlayer(String currentPlayer) {
		for (int i = 1; i <= nbjoueurs; i++) {
			joueurG.get(i - 1).updateCurrentPlayer(currentPlayer);
		}
	}

	public JTextArea getTextArea() {
		return ConsolePanel.getTextArea();
	}

	public JTextArea getInputArea() {
		return ConsolePanel.getInputArea();
	}
	
	public void setmultiClick ( boolean multiClick) {
		this.multiClick=multiClick;
	}
	
	public boolean getmultiClick () {
		return this.multiClick;
	}

	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = e.getX();
		y2 = e.getY();
		// cutButton.setEnabled(false);
		// repaint();
		System.out.println("Mouse Pressed " + "Selection [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]");
		Component panel = (Component) e.getSource();
		System.out.println(panel.getName());
	}

	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		// System.out.println(((JPanel)mouseEvent.getSource()).getName());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		// cutButton.setEnabled(true);
		// repaint();
		System.out
				.println("Mouse Reselased " + "Selection [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + "]");
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
