package estudoThread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
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
	
	private JButton botaoStart = new JButton("Start");
	private JButton botaoStop = new JButton("Stop");
	
	private Runnable thread1 = new Runnable() {
		
		@Override
		public void run() {
			while (true) {
				mostraTempo.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	private Thread thread1Time;
	
	private Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
			while (true) {
				mostraTempo2.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	};
	private Thread thread2Time;
	
	// Construtor
	public MinhaTelaThread() {
		setTitle("Controlando Threads");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		grid.gridwidth = 2;
		grid.anchor = GridBagConstraints.WEST;
		grid.insets = new Insets(5, 10, 0, 5);
		
		descricaoHora.setPreferredSize(new Dimension(200,25));
		jpanel.add(descricaoHora, grid);
		mostraTempo.setPreferredSize(new Dimension(200, 25));
		mostraTempo.setEditable(false);
		grid.gridy++;
		jpanel.add(mostraTempo, grid);
		
		grid.gridy++;
		descricaoHora2.setPreferredSize(new Dimension(200,25));
		jpanel.add(descricaoHora2, grid);
		mostraTempo2.setPreferredSize(new Dimension(200, 25));
		mostraTempo2.setEditable(false);
		grid.gridy++;
		jpanel.add(mostraTempo2, grid);
		
		grid.gridwidth = 1;
		botaoStart.setPreferredSize(new Dimension(92,25));
		grid.gridy++;
		jpanel.add(botaoStart, grid);
		botaoStop.setPreferredSize(new Dimension(92,25));
		grid.gridx++;
		jpanel.add(botaoStop, grid);
		
		botaoStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread1Time = new Thread(thread1);
				thread1Time.start();
				thread2Time = new Thread(thread2);
				thread2Time.start();
				botaoStop.setEnabled(true);
				botaoStart.setEnabled(false);
			}
		});
		
		botaoStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread2Time.stop();
				thread1Time.stop();
				botaoStop.setEnabled(false);
				botaoStart.setEnabled(true);

			}
		});

		botaoStop.setEnabled(false);
		add(jpanel, BorderLayout.WEST);
		// Executar sempre ao final do codigo e nunca no inicio
		setVisible(true); //mostra a tela do swing
	}

}
