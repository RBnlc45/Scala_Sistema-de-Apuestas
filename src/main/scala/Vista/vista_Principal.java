package Vista;

import java.awt.*;
import javax.swing.*;

public class vista_Principal extends JFrame {
	public vista_Principal() {
		initComponents();
	}

	private void initComponents() {
		toolBar1 = new JToolBar();
		jtbParticipantes = new JButton();
		jtbGanadores = new JButton();
		jtbPartidos = new JButton();
		jtbPaises = new JButton();
		jtbPremios = new JButton();
		jtbSalir = new JButton();
		label1 = new JLabel();
		label2 = new JLabel();

		//======== this ========
		setTitle("Sistema Polla Mundialista");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== toolBar1 ========
		{
			toolBar1.setRollover(true);
			toolBar1.setFloatable(false);

			//---- jtbParticipantes ----
			jtbParticipantes.setText("Participantes");
			jtbParticipantes.setSelectedIcon(null);
			jtbParticipantes.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\grupo.png"));
			toolBar1.add(jtbParticipantes);

			//---- jtbGanadores ----
			jtbGanadores.setText("Ganadores");
			jtbGanadores.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\ganador.png"));
			toolBar1.add(jtbGanadores);

			//---- jtbPartidos ----
			jtbPartidos.setText("Partidos");
			jtbPartidos.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\estadio.png"));
			toolBar1.add(jtbPartidos);

			//---- jtbPaises ----
			jtbPaises.setText("Paises Participantes");
			jtbPaises.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\bandera2.png"));
			toolBar1.add(jtbPaises);

			//---- jtbPremios ----
			jtbPremios.setText("Premios");
			jtbPremios.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\premio.png"));
			toolBar1.add(jtbPremios);

			//---- jtbSalir ----
			jtbSalir.setText("Salir");
			jtbSalir.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\salir.png"));
			toolBar1.add(jtbSalir);
		}
		contentPane.add(toolBar1);
		toolBar1.setBounds(0, 0, 700, 45);

		//---- label1 ----
		label1.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\copa2.png"));
		contentPane.add(label1);
		label1.setBounds(150, 200, 300, 400);

		//---- label2 ----
		label2.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\worldcup.PNG"));
		contentPane.add(label2);
		label2.setBounds(130, 55, 425, 125);

		contentPane.setPreferredSize(new Dimension(700, 645));
		setSize(700, 645);
		setLocationRelativeTo(null);
	}

	private JToolBar toolBar1;
	public JButton jtbParticipantes;
	public JButton jtbGanadores;
	public JButton jtbPartidos;
	public JButton jtbPaises;
	public JButton jtbPremios;
	public JButton jtbSalir;
	private JLabel label1;
	private JLabel label2;
}
