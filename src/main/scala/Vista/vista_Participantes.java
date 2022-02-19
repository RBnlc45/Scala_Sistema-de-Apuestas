package Vista;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
/*
 * Created by JFormDesigner on Fri Feb 04 12:45:30 ECT 2022
 */



/**
 * @author Berrezueta
 */
public class vista_Participantes extends JFrame {
	public vista_Participantes() {
		initComponents();
	}

	private void initComponents() {
		tabbedPane1 = new JTabbedPane();
		jtbAgregarParticipante = new JPanel();
		txtCedula = new JTextField();
		lblImgUsuario = new JLabel();
		lblCedula = new JLabel();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		txtApellido = new JTextField();
		lblApellido = new JLabel();
		lblDireccion = new JLabel();
		txtDireccion = new JTextField();
		txtTelefono = new JTextField();
		lblTelefono = new JLabel();
		btnCrearUsuario = new JButton();
		jtbEditarParticipante = new JPanel();
		lblImgEditarParticipante = new JLabel();
		cmbParticipantesEditar = new JComboBox();
		lblParticipantesEditar = new JLabel();
		grbDatosEditar = new JPanel();
		txtCedulaEditar = new JTextField();
		txtNombreEditar = new JTextField();
		txtApellidoEditar = new JTextField();
		txtDireccionEditar = new JTextField();
		txtTelefonoEditar = new JTextField();
		btnEditarUsuario = new JButton();
		lblTelefonoEditar = new JLabel();
		lblDireccionEditar = new JLabel();
		lblApellidoEditar = new JLabel();
		lblNombreEditar = new JLabel();
		lblCedulaEditar = new JLabel();
		jtbEliminarParticipante = new JPanel();
		cmbParticipantesEliminar = new JComboBox();
		lblParticipantesEliminar = new JLabel();
		lblImgEliminarUsuario = new JLabel();
		btnEliminar = new JButton();
		jtbAgregarPronostico = new JPanel();
		lblParticipantesPronostico = new JLabel();
		lblTipoEncuentro = new JLabel();
		lblFechaSimulada = new JLabel();
		btnBuscarAgregar = new JButton();
		lbl = new JLabel();
		scrollPane1 = new JScrollPane();
		jgdAgregarPronostico = new JTable();
		jgdAgregarPronostico.getTableHeader().setReorderingAllowed(false);
		cmbParticipantesPronostico = new JComboBox();
		cmbTipoEncuentro = new JComboBox<>();
		MaskFormatter format2= null;
		try {format2 = new MaskFormatter("##/##/#### ##:##");} 
		catch (ParseException e) {}
		dtpFechaSimulada = new JFormattedTextField(format2);
		lblParticipantesA = new JLabel();
		lblColocarParticipanteA = new JLabel();
		lblPuntosA = new JLabel();
		lblColocarPuntosA = new JLabel();
		panel5 = new JPanel();
		MaskFormatter format= null;
		try {format = new MaskFormatter("##/##/#### ##:##");} 
		catch (ParseException e) {}
		dtpFechaSimulada2 = new JFormattedTextField(format);
		cmbTipoEncuentro2 = new JComboBox<>();
		cmbParticipantesPronostico2 = new JComboBox();
		lblParticipantesPronostico2 = new JLabel();
		lblTipoEncuentro2 = new JLabel();
		lblFechaSimulada2 = new JLabel();
		btnBuscarEditar = new JButton();
		lbl2 = new JLabel();
		lblParticipantesA2 = new JLabel();
		lblPuntosA2 = new JLabel();
		lblColocarPuntosA2 = new JLabel();
		lblColocarParticipanteA2 = new JLabel();
		scrollPane2 = new JScrollPane();
		jgdEditarPronosticos = new JTable();
		jgdEditarPronosticos.getTableHeader().setReorderingAllowed(false);
		rbtPronosticosDefinidos = new JRadioButton();
		rbtPronosticosPorDefinir = new JRadioButton();
		label1 = new JLabel();

		//======== this ========
		setResizable(false);
		setTitle("Participantes y Pron\u00f3sticos");
		setMaximizedBounds(new Rectangle(0, 0, 865, 700));
		setMinimumSize(new Dimension(865, 700));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== tabbedPane1 ========
		{

			//======== jtbAgregarParticipante ========
			{
				jtbAgregarParticipante.setLayout(null);
				jtbAgregarParticipante.add(txtCedula);
				txtCedula.setBounds(355, 265, 245, 20);

				//---- lblImgUsuario ----
				lblImgUsuario.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\agregar_usuario.png"));
				jtbAgregarParticipante.add(lblImgUsuario);
				lblImgUsuario.setBounds(355, 35, 200, 200);

				//---- lblCedula ----
				lblCedula.setText("C\u00e9dula:");
				jtbAgregarParticipante.add(lblCedula);
				lblCedula.setBounds(270, 265, 80, 14);

				//---- lblNombre ----
				lblNombre.setText("Nombre:");
				jtbAgregarParticipante.add(lblNombre);
				lblNombre.setBounds(270, 310, 80, 14);
				jtbAgregarParticipante.add(txtNombre);
				txtNombre.setBounds(355, 305, 245, 20);
				jtbAgregarParticipante.add(txtApellido);
				txtApellido.setBounds(355, 350, 245, 20);

				//---- lblApellido ----
				lblApellido.setText("Apellido:");
				jtbAgregarParticipante.add(lblApellido);
				lblApellido.setBounds(270, 355, 80, 14);

				//---- lblDireccion ----
				lblDireccion.setText("Direcci\u00f3n:");
				jtbAgregarParticipante.add(lblDireccion);
				lblDireccion.setBounds(265, 400, 90, 14);
				jtbAgregarParticipante.add(txtDireccion);
				txtDireccion.setBounds(355, 395, 245, 20);
				jtbAgregarParticipante.add(txtTelefono);
				txtTelefono.setBounds(355, 440, 245, 20);

				//---- lblTelefono ----
				lblTelefono.setText("Tel\u00e9fono:");
				jtbAgregarParticipante.add(lblTelefono);
				lblTelefono.setBounds(265, 445, 85, 14);

				//---- btnCrearUsuario ----
				btnCrearUsuario.setText("Crear Usuario");
				jtbAgregarParticipante.add(btnCrearUsuario);
				btnCrearUsuario.setBounds(360, 490, 230, 23);
			}
			tabbedPane1.addTab("Agregar Participante", new ImageIcon("src\\Icons\\Iconos\\agregar_usuario2.png"), jtbAgregarParticipante);

			//======== jtbEditarParticipante ========
			{
				jtbEditarParticipante.setMaximumSize(new Dimension(565, 503));
				jtbEditarParticipante.setLayout(null);

				//---- lblImgEditarParticipante ----
				lblImgEditarParticipante.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\editar_usuario.png"));
				jtbEditarParticipante.add(lblImgEditarParticipante);
				lblImgEditarParticipante.setBounds(330, 20, 220, 200);
				jtbEditarParticipante.add(cmbParticipantesEditar);
				cmbParticipantesEditar.setBounds(375, 235, 230, 20);

				//---- lblParticipantesEditar ----
				lblParticipantesEditar.setText("Participantes:");
				jtbEditarParticipante.add(lblParticipantesEditar);
				lblParticipantesEditar.setBounds(280, 240, 95, 14);

				//======== grbDatosEditar ========
				{
					grbDatosEditar.setLayout(null);
					grbDatosEditar.add(txtCedulaEditar);
					txtCedulaEditar.setBounds(160, 35, 245, 20);
					grbDatosEditar.add(txtNombreEditar);
					txtNombreEditar.setBounds(160, 75, 245, 20);
					grbDatosEditar.add(txtApellidoEditar);
					txtApellidoEditar.setBounds(160, 120, 245, 20);
					grbDatosEditar.add(txtDireccionEditar);
					txtDireccionEditar.setBounds(160, 165, 245, 20);
					grbDatosEditar.add(txtTelefonoEditar);
					txtTelefonoEditar.setBounds(160, 210, 245, 20);

					//---- btnEditarUsuario ----
					btnEditarUsuario.setText("Editar Usuario");
					grbDatosEditar.add(btnEditarUsuario);
					btnEditarUsuario.setBounds(165, 260, 230, 23);

					//---- lblTelefonoEditar ----
					lblTelefonoEditar.setText("Tel\u00e9fono:");
					grbDatosEditar.add(lblTelefonoEditar);
					lblTelefonoEditar.setBounds(70, 215, 80, 14);

					//---- lblDireccionEditar ----
					lblDireccionEditar.setText("Direcci\u00f3n:");
					grbDatosEditar.add(lblDireccionEditar);
					lblDireccionEditar.setBounds(70, 170, 80, 14);

					//---- lblApellidoEditar ----
					lblApellidoEditar.setText("Apellido:");
					grbDatosEditar.add(lblApellidoEditar);
					lblApellidoEditar.setBounds(75, 125, 75, 14);

					//---- lblNombreEditar ----
					lblNombreEditar.setText("Nombre:");
					grbDatosEditar.add(lblNombreEditar);
					lblNombreEditar.setBounds(75, 80, 75, 14);

					//---- lblCedulaEditar ----
					lblCedulaEditar.setText("C\u00e9dula:");
					grbDatosEditar.add(lblCedulaEditar);
					lblCedulaEditar.setBounds(75, 35, 70, 14);

					{
						// compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < grbDatosEditar.getComponentCount(); i++) {
							Rectangle bounds = grbDatosEditar.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = grbDatosEditar.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						grbDatosEditar.setMinimumSize(preferredSize);
						grbDatosEditar.setPreferredSize(preferredSize);
					}
				}
				jtbEditarParticipante.add(grbDatosEditar);
				grbDatosEditar.setBounds(185, 275, 490, 315);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < jtbEditarParticipante.getComponentCount(); i++) {
						Rectangle bounds = jtbEditarParticipante.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = jtbEditarParticipante.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					jtbEditarParticipante.setMinimumSize(preferredSize);
					jtbEditarParticipante.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Editar Participante", new ImageIcon("src\\Icons\\Iconos\\editar_usuario2.png"), jtbEditarParticipante);

			//======== jtbEliminarParticipante ========
			{
				jtbEliminarParticipante.setLayout(null);
				jtbEliminarParticipante.add(cmbParticipantesEliminar);
				cmbParticipantesEliminar.setBounds(370, 305, 245, cmbParticipantesEliminar.getPreferredSize().height);

				//---- lblParticipantesEliminar ----
				lblParticipantesEliminar.setText("Participantes:");
				jtbEliminarParticipante.add(lblParticipantesEliminar);
				lblParticipantesEliminar.setBounds(265, 310, 90, lblParticipantesEliminar.getPreferredSize().height);

				//---- lblImgEliminarUsuario ----
				lblImgEliminarUsuario.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\eliminar_usuario.png"));
				jtbEliminarParticipante.add(lblImgEliminarUsuario);
				lblImgEliminarUsuario.setBounds(350, 50, 220, 210);

				//---- btnEliminar ----
				btnEliminar.setText("Eliminar Usuario");
				jtbEliminarParticipante.add(btnEliminar);
				btnEliminar.setBounds(new Rectangle(new Point(380, 365), btnEliminar.getPreferredSize()));
			}
			tabbedPane1.addTab("Eliminar Participante", new ImageIcon("src\\Icons\\Iconos\\eliminar_usuario2.png"), jtbEliminarParticipante);

			//======== jtbAgregarPronostico ========
			{
				jtbAgregarPronostico.setLayout(null);

				//---- lblParticipantesPronostico ----
				lblParticipantesPronostico.setText("Participantes:");
				jtbAgregarPronostico.add(lblParticipantesPronostico);
				lblParticipantesPronostico.setBounds(265, 15, 85, lblParticipantesPronostico.getPreferredSize().height);

				//---- lblTipoEncuentro ----
				lblTipoEncuentro.setText("Tipo de Encuentro:");
				jtbAgregarPronostico.add(lblTipoEncuentro);
				lblTipoEncuentro.setBounds(240, 45, 110, lblTipoEncuentro.getPreferredSize().height);

				//---- lblFechaSimulada ----
				lblFechaSimulada.setText("Fecha de Ingreso:");
				jtbAgregarPronostico.add(lblFechaSimulada);
				lblFechaSimulada.setBounds(245, 75, 105, lblFechaSimulada.getPreferredSize().height);

				//---- btnBuscarAgregar ----
				btnBuscarAgregar.setText("Buscar");
				jtbAgregarPronostico.add(btnBuscarAgregar);
				btnBuscarAgregar.setBounds(400, 110, 115, btnBuscarAgregar.getPreferredSize().height);

				//---- lbl ----
				lbl.setText("Lista de Partidos Disponibles para Pronosticar");
				jtbAgregarPronostico.add(lbl);
				lbl.setBounds(new Rectangle(new Point(325, 155), lbl.getPreferredSize()));

				//======== scrollPane1 ========
				{

					//---- jgdAgregarPronostico ----
					jgdAgregarPronostico.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Fecha", "Selecci\u00f3n", "Selecci\u00f3n"
						}
					){
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return false;
						}
					}

					);
					jgdAgregarPronostico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane1.setViewportView(jgdAgregarPronostico);
				}
				jtbAgregarPronostico.add(scrollPane1);
				scrollPane1.setBounds(225, 180, scrollPane1.getPreferredSize().width, 415);
				jtbAgregarPronostico.add(cmbParticipantesPronostico);
				cmbParticipantesPronostico.setBounds(360, 10, 240, cmbParticipantesPronostico.getPreferredSize().height);

				//---- cmbTipoEncuentro ----
				cmbTipoEncuentro.setModel(new DefaultComboBoxModel<>(new String[] {
					"Fase de Grupos",
					"Octavos de Final",
					"Cuartos de Final",
					"Semifinales",
					"Finales"
				}));
				jtbAgregarPronostico.add(cmbTipoEncuentro);
				cmbTipoEncuentro.setBounds(360, 40, 215, 20);
				jtbAgregarPronostico.add(dtpFechaSimulada);
				dtpFechaSimulada.setBounds(360, 75, 215, dtpFechaSimulada.getPreferredSize().height);

				//---- lblParticipantesA ----
				lblParticipantesA.setText("Participante:");
				jtbAgregarPronostico.add(lblParticipantesA);
				lblParticipantesA.setBounds(10, 225, 75, 20);
				jtbAgregarPronostico.add(lblColocarParticipanteA);
				lblColocarParticipanteA.setBounds(85, 225, 130, 20);

				//---- lblPuntosA ----
				lblPuntosA.setText("Puntos:");
				jtbAgregarPronostico.add(lblPuntosA);
				lblPuntosA.setBounds(10, 270, 75, 20);
				jtbAgregarPronostico.add(lblColocarPuntosA);
				lblColocarPuntosA.setBounds(70, 270, 145, 20);
			}
			tabbedPane1.addTab("Agregar Pron\u00f3stico", new ImageIcon("src\\Icons\\Iconos\\agregar3.png"), jtbAgregarPronostico);

			//======== panel5 ========
			{
				panel5.setLayout(null);
				panel5.add(dtpFechaSimulada2);
				dtpFechaSimulada2.setBounds(370, 85, 215, 20);

				//---- cmbTipoEncuentro2 ----
				cmbTipoEncuentro2.setModel(new DefaultComboBoxModel<>(new String[] {
					"Fase de Grupos",
					"Octavos de Final",
					"Cuartos de Final",
					"Semifinales",
					"Finales"
				}));
				panel5.add(cmbTipoEncuentro2);
				cmbTipoEncuentro2.setBounds(370, 50, 215, 20);
				panel5.add(cmbParticipantesPronostico2);
				cmbParticipantesPronostico2.setBounds(370, 20, 240, 20);

				//---- lblParticipantesPronostico2 ----
				lblParticipantesPronostico2.setText("Participantes:");
				panel5.add(lblParticipantesPronostico2);
				lblParticipantesPronostico2.setBounds(275, 25, 85, 14);

				//---- lblTipoEncuentro2 ----
				lblTipoEncuentro2.setText("Tipo de Encuentro:");
				panel5.add(lblTipoEncuentro2);
				lblTipoEncuentro2.setBounds(250, 55, 110, 14);

				//---- lblFechaSimulada2 ----
				lblFechaSimulada2.setText("Fecha de Ingreso:");
				panel5.add(lblFechaSimulada2);
				lblFechaSimulada2.setBounds(255, 85, 105, 14);

				//---- btnBuscarEditar ----
				btnBuscarEditar.setText("Buscar");
				panel5.add(btnBuscarEditar);
				btnBuscarEditar.setBounds(410, 120, 115, 23);

				//---- lbl2 ----
				lbl2.setText("Lista de Partidos Disponibles para Pronosticar");
				panel5.add(lbl2);
				lbl2.setBounds(335, 165, 217, 14);

				//---- lblParticipantesA2 ----
				lblParticipantesA2.setText("Participante:");
				panel5.add(lblParticipantesA2);
				lblParticipantesA2.setBounds(5, 235, 75, 20);

				//---- lblPuntosA2 ----
				lblPuntosA2.setText("Puntos:");
				panel5.add(lblPuntosA2);
				lblPuntosA2.setBounds(5, 280, 75, 20);
				panel5.add(lblColocarPuntosA2);
				lblColocarPuntosA2.setBounds(65, 280, 145, 20);
				panel5.add(lblColocarParticipanteA2);
				lblColocarParticipanteA2.setBounds(85, 235, 125, 20);

				//======== scrollPane2 ========
				{

					//---- jgdEditarPronosticos ----
					jgdEditarPronosticos.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Fecha", "Selecci\u00f3n", "Pron\u00f3stico", "Selecci\u00f3n"
						}
					) {
						boolean[] columnEditable = new boolean[] {
								false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					jgdEditarPronosticos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane2.setViewportView(jgdEditarPronosticos);
				}
				panel5.add(scrollPane2);
				scrollPane2.setBounds(215, 190, 452, 410);

				//---- rbtPronosticosDefinidos ----
				rbtPronosticosDefinidos.setText("Pron\u00f3sticos Definidos");
				rbtPronosticosDefinidos.setSelected(true);
				panel5.add(rbtPronosticosDefinidos);
				rbtPronosticosDefinidos.setBounds(675, 240, 175, rbtPronosticosDefinidos.getPreferredSize().height);

				//---- rbtPronosticosPorDefinir ----
				rbtPronosticosPorDefinir.setText("Pron\u00f3sticos Por Definirse");
				panel5.add(rbtPronosticosPorDefinir);
				rbtPronosticosPorDefinir.setBounds(675, 270, 175, 23);

				//---- label1 ----
				label1.setText("Filtros");
				panel5.add(label1);
				label1.setBounds(745, 220, 50, label1.getPreferredSize().height);
			}
			tabbedPane1.addTab("Editar/Buscar Pron\u00f3stico", new ImageIcon("src\\Icons\\Iconos\\editar3.png"), panel5);
		}
		contentPane.add(tabbedPane1);
		tabbedPane1.setBounds(0, 0, 865, 635);

		contentPane.setPreferredSize(new Dimension(865, 665));
		setSize(865, 665);
		setLocationRelativeTo(null);

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbtPronosticosDefinidos);
		buttonGroup1.add(rbtPronosticosPorDefinir);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	private JTabbedPane tabbedPane1;
	private JPanel jtbAgregarParticipante;
	public JTextField txtCedula;
	private JLabel lblImgUsuario;
	private JLabel lblCedula;
	private JLabel lblNombre;
	public JTextField txtNombre;
	public JTextField txtApellido;
	private JLabel lblApellido;
	private JLabel lblDireccion;
	public JTextField txtDireccion;
	public JTextField txtTelefono;
	private JLabel lblTelefono;
	public JButton btnCrearUsuario;
	private JPanel jtbEditarParticipante;
	private JLabel lblImgEditarParticipante;
	public JComboBox cmbParticipantesEditar;
	private JLabel lblParticipantesEditar;
	public JPanel grbDatosEditar;
	public JTextField txtCedulaEditar;
	public JTextField txtNombreEditar;
	public JTextField txtApellidoEditar;
	public JTextField txtDireccionEditar;
	public JTextField txtTelefonoEditar;
	public JButton btnEditarUsuario;
	private JLabel lblTelefonoEditar;
	private JLabel lblDireccionEditar;
	private JLabel lblApellidoEditar;
	private JLabel lblNombreEditar;
	private JLabel lblCedulaEditar;
	private JPanel jtbEliminarParticipante;
	public JComboBox cmbParticipantesEliminar;
	private JLabel lblParticipantesEliminar;
	private JLabel lblImgEliminarUsuario;
	public JButton btnEliminar;
	private JPanel jtbAgregarPronostico;
	private JLabel lblParticipantesPronostico;
	private JLabel lblTipoEncuentro;
	private JLabel lblFechaSimulada;
	public JButton btnBuscarAgregar;
	private JLabel lbl;
	private JScrollPane scrollPane1;
	public JTable jgdAgregarPronostico;
	public JComboBox cmbParticipantesPronostico;
	public JComboBox<String> cmbTipoEncuentro;
	public JFormattedTextField dtpFechaSimulada;
	private JLabel lblParticipantesA;
	public JLabel lblColocarParticipanteA;
	private JLabel lblPuntosA;
	public JLabel lblColocarPuntosA;
	private JPanel panel5;
	public JFormattedTextField dtpFechaSimulada2;
	public JComboBox<String> cmbTipoEncuentro2;
	public JComboBox cmbParticipantesPronostico2;
	private JLabel lblParticipantesPronostico2;
	private JLabel lblTipoEncuentro2;
	private JLabel lblFechaSimulada2;
	public JButton btnBuscarEditar;
	private JLabel lbl2;
	private JLabel lblParticipantesA2;
	private JLabel lblPuntosA2;
	public JLabel lblColocarPuntosA2;
	public JLabel lblColocarParticipanteA2;
	private JScrollPane scrollPane2;
	public JTable jgdEditarPronosticos;
	public JRadioButton rbtPronosticosDefinidos;
	public JRadioButton rbtPronosticosPorDefinir;
	private JLabel label1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
