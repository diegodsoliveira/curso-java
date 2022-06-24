package estudoThread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MinhaTelaThread extends JDialog {
	
	private JPanel jpanel = new JPanel(new GridBagLayout());
	
	private JLabel descricaoHora = new JLabel("Time thread 1:");
	private JTextField mostraTempo = new JTextField();
	
	private JLabel descricaoHora2 = new JLabel("Time thread 1:");
	private JTextField mostraTempo2 = new JTextField();
	
	// Construtor
	public MinhaTelaThread() {
		setTitle("Controlando Threads");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		
		descricaoHora.setPreferredSize(new Dimension(200,25));
		jpanel.add(descricaoHora, grid);
		mostraTempo.setPreferredSize(new Dimension(200, 25));
		grid.gridy++;
		jpanel.add(mostraTempo, grid);
		
		grid.gridy++;
		descricaoHora2.setPreferredSize(new Dimension(200,25));
		jpanel.add(descricaoHora2, grid);
		mostraTempo2.setPreferredSize(new Dimension(200, 25));
		grid.gridy++;
		jpanel.add(mostraTempo2, grid);
		
		add(jpanel, BorderLayout.WEST);
		// Executar sempre ao final do codigo e nunca no inicio
		setVisible(true); //mostra a tela do swing
	}

}
