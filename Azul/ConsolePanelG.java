import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ConsolePanelG extends javax.swing.JPanel  {

	private static final long serialVersionUID = 1L;
	private String img = LireDonnes.getRepertoire() + "centree.png";
	private JTextArea textArea;
	private JTextArea inputArea;
	
	public ConsolePanelG() {
		super();
		textArea = new JTextArea(8, 5);
		textArea.setEditable(false);
		inputArea = new JTextArea(1, 10);
		
			// creates the GUI
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(textArea);
		scrollPane.setViewportView(textArea);
		add(scrollPane, constraints);
		constraints.gridy = 5;
		constraints.gridheight = 1;
		add(inputArea, constraints);
	}
	
	public JTextArea getTextArea() {
		return this.textArea;
	}

	public JTextArea getInputArea() {
		return this.inputArea;
	}	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon m = new ImageIcon(img);
		Image monImage = m.getImage();
		g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
	}


}