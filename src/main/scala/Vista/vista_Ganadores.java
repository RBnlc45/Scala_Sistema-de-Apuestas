package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class vista_Ganadores extends JFrame {
	public vista_Ganadores() {
		initComponents();
	}

	private void initComponents() {
		label1 = new JLabel();
		scrollPane1 = new JScrollPane();
		jgdParticipantes = new JTable();
		jgdParticipantes.getTableHeader().setReorderingAllowed(false);
		btnDefinirGanador = new JButton();
		lblTablaClasificacion = new JLabel();

		//======== this ========
		setTitle("Ganadores");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- label1 ----
		label1.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\ganador2.png"));
		contentPane.add(label1);
		label1.setBounds(465, 0, 110, 135);

		//======== scrollPane1 ========
		{

			//---- jgdParticipantes ----
			jgdParticipantes.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00e9dula", "Nombre", "Apellido", "Puntaje", "Premios", "P. 8 Puntos", "P. 6 Puntos", "P. 3 Puntos", "P. 2.5 Puntos", "P. 2 Puntos", "P. 1 Punto", "P. 0.5 Punto", "N\u00famero Pron\u00f3sticos"
				}
			){
			  @Override
			  public boolean isCellEditable(int rowIndex, int columnIndex) {
				  return false;
			  }
			}
			);
			scrollPane1.setViewportView(jgdParticipantes);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(50, 150, 980, 327);

		//---- btnDefinirGanador ----
		btnDefinirGanador.setText("Definir  Ganador/es");
		contentPane.add(btnDefinirGanador);
		btnDefinirGanador.setBounds(445, 495, 170, 33);

		//---- lblTablaClasificacion ----
		lblTablaClasificacion.setText("Tabla de Clasificaci\u00f3n de Participantes");
		lblTablaClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaClasificacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblTablaClasificacion);
		lblTablaClasificacion.setBounds(370, 135, 275, lblTablaClasificacion.getPreferredSize().height);

		contentPane.setPreferredSize(new Dimension(1080, 580));
		setSize(1080, 580);
		setLocationRelativeTo(null);
	}
	private JLabel label1;
	private JScrollPane scrollPane1;
	public JTable jgdParticipantes;
	public JButton btnDefinirGanador;
	private JLabel lblTablaClasificacion;
}
