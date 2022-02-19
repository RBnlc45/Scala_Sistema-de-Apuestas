package Vista;

import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri Feb 04 12:14:23 ECT 2022
 */



/**
 * @author Berrezueta
 */
public class vista_Premios extends JFrame {
	public vista_Premios() {
		initComponents();
	}

	private void initComponents() {
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		txtNuevoPrecio = new JTextField();
		lblPrecioActualColocar = new JLabel();
		lblPrecioActual = new JLabel();
		lblNuevoPrecio = new JLabel();
		btnCambiarPrecio = new JButton();
		label1 = new JLabel();
		panel2 = new JPanel();
		lblPremiosDisponibles = new JLabel();
		lblMontoDisponible = new JLabel();
		lblImgPremio = new JLabel();
		panel3 = new JPanel();
		lblMontoAgregar = new JLabel();
		txtMontoAgregar = new JTextField();
		btnRegistrar = new JButton();
		label4 = new JLabel();
		panel4 = new JPanel();
		lblPremio2 = new JLabel();
		txtMontoQuitar = new JTextField();
		btnQuitarMonto = new JButton();
		label5 = new JLabel();

		//======== this ========
		setTitle("Premios");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== tabbedPane1 ========
		{

			//======== panel1 ========
			{

				panel1.setLayout(null);

				//---- txtNuevoPrecio ----
				txtNuevoPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel1.add(txtNuevoPrecio);
				txtNuevoPrecio.setBounds(395, 215, 85, 28);

				//---- lblPrecioActualColocar ----
				lblPrecioActualColocar.setText("0");
				lblPrecioActualColocar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel1.add(lblPrecioActualColocar);
				lblPrecioActualColocar.setBounds(400, 165, 45, 35);

				//---- lblPrecioActual ----
				lblPrecioActual.setText("Precio Actual ($):");
				lblPrecioActual.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel1.add(lblPrecioActual);
				lblPrecioActual.setBounds(225, 165, 150, 35);

				//---- lblNuevoPrecio ----
				lblNuevoPrecio.setText("Precio Nuevo ($): ");
				lblNuevoPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel1.add(lblNuevoPrecio);
				lblNuevoPrecio.setBounds(225, 210, 160, 35);

				//---- btnCambiarPrecio ----
				btnCambiarPrecio.setText("Cambiar Precio");
				btnCambiarPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel1.add(btnCambiarPrecio);
				btnCambiarPrecio.setBounds(315, 285, 140, 30);

				//---- label1 ----
				label1.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\precio2.png"));
				panel1.add(label1);
				label1.setBounds(320, 30, 115, 120);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel1.getComponentCount(); i++) {
						Rectangle bounds = panel1.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel1.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel1.setMinimumSize(preferredSize);
					panel1.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Definir Precio Participaci\u00f3n", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\precio.png"), panel1);

			//======== panel2 ========
			{
				panel2.setLayout(null);

				//---- lblPremiosDisponibles ----
				lblPremiosDisponibles.setText("Premio Disponible");
				lblPremiosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblPremiosDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
				panel2.add(lblPremiosDisponibles);
				lblPremiosDisponibles.setBounds(295, 155, 175, 45);

				//---- lblMontoDisponible ----
				lblMontoDisponible.setText("$25");
				lblMontoDisponible.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMontoDisponible.setHorizontalAlignment(SwingConstants.CENTER);
				panel2.add(lblMontoDisponible);
				lblMontoDisponible.setBounds(295, 205, 175, 45);

				//---- lblImgPremio ----
				lblImgPremio.setHorizontalAlignment(SwingConstants.CENTER);
				lblImgPremio.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\premio2.png"));
				panel2.add(lblImgPremio);
				lblImgPremio.setBounds(310, 60, 150, 80);
			}
			tabbedPane1.addTab("Monto Disponible", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\cofre.png"), panel2);

			//======== panel3 ========
			{
				panel3.setLayout(null);

				//---- lblMontoAgregar ----
				lblMontoAgregar.setText("Monto a agregar:");
				lblMontoAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMontoAgregar.setHorizontalAlignment(SwingConstants.CENTER);
				panel3.add(lblMontoAgregar);
				lblMontoAgregar.setBounds(205, 185, 175, 45);

				//---- txtMontoAgregar ----
				txtMontoAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel3.add(txtMontoAgregar);
				txtMontoAgregar.setBounds(385, 195, 135, txtMontoAgregar.getPreferredSize().height);

				//---- btnRegistrar ----
				btnRegistrar.setText("Registrar");
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel3.add(btnRegistrar);
				btnRegistrar.setBounds(305, 245, 130, 30);

				//---- label4 ----
				label4.setHorizontalAlignment(SwingConstants.CENTER);
				label4.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\premio3.png"));
				panel3.add(label4);
				label4.setBounds(320, 55, 115, 105);
			}
			tabbedPane1.addTab("Agregar Monto", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\agregar3.png"), panel3);

			//======== panel4 ========
			{
				panel4.setLayout(null);

				//---- lblPremio2 ----
				lblPremio2.setText("Monto a quitar:");
				lblPremio2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblPremio2.setHorizontalAlignment(SwingConstants.CENTER);
				panel4.add(lblPremio2);
				lblPremio2.setBounds(215, 175, 175, 45);

				//---- txtMontoQuitar ----
				txtMontoQuitar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel4.add(txtMontoQuitar);
				txtMontoQuitar.setBounds(395, 185, 135, 23);

				//---- btnQuitarMonto ----
				btnQuitarMonto.setText("Quitar Monto");
				btnQuitarMonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel4.add(btnQuitarMonto);
				btnQuitarMonto.setBounds(315, 235, 130, 30);

				//---- label5 ----
				label5.setHorizontalAlignment(SwingConstants.CENTER);
				label5.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\eliminarpremio.png"));
				panel4.add(label5);
				label5.setBounds(330, 45, 115, 105);
			}
			tabbedPane1.addTab("Quitar Monto", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\eliminar2.png"), panel4);
		}
		contentPane.add(tabbedPane1);
		tabbedPane1.setBounds(0, 0, 755, 410);

		contentPane.setPreferredSize(new Dimension(755, 440));
		setSize(755, 440);
		setLocationRelativeTo(null);
	}

	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	public JTextField txtNuevoPrecio;
	public JLabel lblPrecioActualColocar;
	private JLabel lblPrecioActual;
	private JLabel lblNuevoPrecio;
	public JButton btnCambiarPrecio;
	private JLabel label1;
	private JPanel panel2;
	private JLabel lblPremiosDisponibles;
	public JLabel lblMontoDisponible;
	private JLabel lblImgPremio;
	private JPanel panel3;
	private JLabel lblMontoAgregar;
	public JTextField txtMontoAgregar;
	public JButton btnRegistrar;
	private JLabel label4;
	private JPanel panel4;
	private JLabel lblPremio2;
	public JTextField txtMontoQuitar;
	public JButton btnQuitarMonto;
	private JLabel label5;
}
