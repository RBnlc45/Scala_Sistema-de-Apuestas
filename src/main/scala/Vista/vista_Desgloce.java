package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class vista_Desgloce extends JDialog {
	public vista_Desgloce(Window owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		lblEnfrentamiento = new JLabel();
		lblPartido = new JLabel();
		lblMarcadorFinal = new JLabel();
		lblMarcadorPronosticado = new JLabel();
		lblMarcadorPronosticado2 = new JLabel();
		lblMarcadorFinal2 = new JLabel();
		lblPartidoAnulado = new JLabel();
		scrollPane1 = new JScrollPane();
		jtbDesgloce = new JTable();
		scrollPane2 = new JScrollPane();
		textArea1 = new JTextArea();

		//======== this ========
		setTitle("Desgloce de Resultados");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- lblEnfrentamiento ----
		lblEnfrentamiento.setText("Enfrentamiento");
		lblEnfrentamiento.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEnfrentamiento);
		lblEnfrentamiento.setBounds(0, 5, 1015, lblEnfrentamiento.getPreferredSize().height);

		//---- lblPartido ----
		lblPartido.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPartido);
		lblPartido.setBounds(0, 24, 1015, 21);

		//---- lblMarcadorFinal ----
		lblMarcadorFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarcadorFinal.setText("Marcador Final:");
		contentPane.add(lblMarcadorFinal);
		lblMarcadorFinal.setBounds(330, 60, 155, 21);

		//---- lblMarcadorPronosticado ----
		lblMarcadorPronosticado.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarcadorPronosticado.setText("Marcador Pronosticado:");
		contentPane.add(lblMarcadorPronosticado);
		lblMarcadorPronosticado.setBounds(310, 80, 155, 21);

		//---- lblMarcadorPronosticado2 ----
		lblMarcadorPronosticado2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMarcadorPronosticado2);
		lblMarcadorPronosticado2.setBounds(490, 80, 155, 21);

		//---- lblMarcadorFinal2 ----
		lblMarcadorFinal2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMarcadorFinal2);
		lblMarcadorFinal2.setBounds(490, 60, 155, 21);

		//---- lblPartidoAnulado ----
		lblPartidoAnulado.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartidoAnulado.setText("El partido asociado ha sido anulado!");
		contentPane.add(lblPartidoAnulado);
		lblPartidoAnulado.setBounds(30, 140, 260, 21);

		//======== scrollPane1 ========
		{

			//---- jtbDesgloce ----
			jtbDesgloce.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Acierto Resultado", "Acierto Marcador", "Acierto Pleno", "Marcador y Goles", "Acierto Pleno y Goles", "Acierto Goles Dos Equipos", "Peg\u00f3 en el palo", "Total"
				}
			) {
				Class<?>[] columnTypes = new Class<?>[] {
					Object.class, Object.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			jtbDesgloce.setFont(new Font("Tahoma", Font.PLAIN, 14));
			jtbDesgloce.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jtbDesgloce.getTableHeader().setReorderingAllowed(false);
			scrollPane1.setViewportView(jtbDesgloce);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(30, 165, 955, 45);

		//======== scrollPane2 ========
		{

			//---- textArea1 ----
			textArea1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			textArea1.setEditable(false);
			textArea1.setText("* Acierto de resultado: 2 puntos\n* Acierto de marcador: 1 puntos*\n\nBonificaciones:\n* Acierto de marcador y resultado (acierto pleno): Bono de 3 puntos*\n* Acierto de marcador, con suma de goles mayor a 3: Bono de 1 punto.\n* Acierto de marcador y resultado, con suma de goles mayor a 3: 1 punto.\n* Acierto de los goles de uno de los dos equipos: 0.5 puntos.\n* Bono \"peg\u00f3 en el palo\": Si le falt\u00f3 o sobr\u00f3 un gol para el acierto pleno: 0.5 puntos.");
			scrollPane2.setViewportView(textArea1);
		}
		contentPane.add(scrollPane2);
		scrollPane2.setBounds(275, 230, 495, 190);

		{
			// compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JLabel lblEnfrentamiento;
	public JLabel lblPartido;
	public JLabel lblMarcadorFinal;
	public JLabel lblMarcadorPronosticado;
	public JLabel lblMarcadorPronosticado2;
	public JLabel lblMarcadorFinal2;
	public JLabel lblPartidoAnulado;
	private JScrollPane scrollPane1;
	public JTable jtbDesgloce;
	private JScrollPane scrollPane2;
	private JTextArea textArea1;
}
