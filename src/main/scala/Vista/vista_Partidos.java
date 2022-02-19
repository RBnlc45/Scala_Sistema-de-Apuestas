package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class vista_Partidos extends JFrame {
	public vista_Partidos() {
		initComponents();
	}

	private void initComponents() {
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		tabbedPane2 = new JTabbedPane();
		tabFaseGrupos = new JPanel();
		btnAsignar = new JButton();
		lblGrupo = new JLabel();
		cmbGrupos = new JComboBox<>();
		scrollPane2 = new JScrollPane();
		jgdTablaPartidosGrupos = new JTable();
		jgdTablaPartidosGrupos.getTableHeader().setReorderingAllowed(false);
		lblPorDefinirse = new JLabel();
		btnDefinirFechas = new JButton();
		btnDefinirMarcadores = new JButton();
		tabOctavos = new JPanel();
		lblPorDefinirse2 = new JLabel();
		btnDefinirFechasOctavos = new JButton();
		btnDefinirFaseOctavos = new JButton();
		scrollPane3 = new JScrollPane();
		jgdTablaPartidosOctavos = new JTable();
		jgdTablaPartidosOctavos.getTableHeader().setReorderingAllowed(false);
		lblNoAplica = new JLabel();
		tabCuartos = new JPanel();
		lblNoAplica2 = new JLabel();
		scrollPane4 = new JScrollPane();
		jgdTablaPartidosCuartos = new JTable();
		jgdTablaPartidosCuartos.getTableHeader().setReorderingAllowed(false);
		btnDefinirFaseCuartos = new JButton();
		btnDefinirFechasCuartos = new JButton();
		lblPorDefinirse3 = new JLabel();
		tabSemifinal = new JPanel();
		lblNoAplica3 = new JLabel();
		scrollPane5 = new JScrollPane();
		jgdTablaPartidosSemifinales = new JTable();
		jgdTablaPartidosSemifinales.getTableHeader().setReorderingAllowed(false);
		btnDefinirFaseSemifinales = new JButton();
		btnDefinirFechasSemifinales = new JButton();
		lblPorDefinirse4 = new JLabel();
		tabFinal = new JPanel();
		lblNoAplica4 = new JLabel();
		scrollPane6 = new JScrollPane();
		jgdTablaPartidosFinales = new JTable();
		jgdTablaPartidosFinales.getTableHeader().setReorderingAllowed(false);
		btnDefinirFaseFinales = new JButton();
		btnDefinirFechasFinales = new JButton();
		lblPorDefinirse5 = new JLabel();
		panel2 = new JPanel();
		lblGrupoTablaPosiciones = new JLabel();
		cmbGruposTablaPosiciones = new JComboBox<>();
		scrollPane1 = new JScrollPane();
		jgdPosiciones = new JTable();
		jgdPosiciones.getTableHeader().setReorderingAllowed(false);
		label1 = new JLabel();

		//======== this ========
		setResizable(false);
		setLocationByPlatform(true);
		setTitle("Partidos a disputarse");
		setIconImage(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== tabbedPane1 ========
		{

			//======== panel1 ========
			{
				panel1.setLayout(null);

				//======== tabbedPane2 ========
				{

					//======== tabFaseGrupos ========
					{
						tabFaseGrupos.setLayout(null);

						//---- btnAsignar ----
						btnAsignar.setText("Generar Partidos");
						btnAsignar.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\actualizar.png"));
						tabFaseGrupos.add(btnAsignar);
						btnAsignar.setBounds(10, 10, 145, btnAsignar.getPreferredSize().height);

						//---- lblGrupo ----
						lblGrupo.setText("Grupo:");
						tabFaseGrupos.add(lblGrupo);
						lblGrupo.setBounds(300, 30, 50, 20);

						//---- cmbGrupos ----
						cmbGrupos.setModel(new DefaultComboBoxModel<>(new String[] {
							"A",
							"B",
							"C",
							"D",
							"E",
							"F",
							"G",
							"H"
						}));
						tabFaseGrupos.add(cmbGrupos);
						cmbGrupos.setBounds(370, 30, 205, cmbGrupos.getPreferredSize().height);

						//======== scrollPane2 ========
						{

							//---- jgdTablaPartidosGrupos ----
							jgdTablaPartidosGrupos.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Fecha", "Selecci\u00f3n", "Marcador", "Selecci\u00f3n"
								}
							) {
								Class<?>[] columnTypes = new Class<?>[] {
									String.class, Object.class, String.class, Object.class
								};
								boolean[] columnEditable = new boolean[] {
									false, false, false, false
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
							jgdTablaPartidosGrupos.setUpdateSelectionOnSort(false);
							jgdTablaPartidosGrupos.setRowSorter(null);
							jgdTablaPartidosGrupos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
							jgdTablaPartidosGrupos.setVerifyInputWhenFocusTarget(false);
							jgdTablaPartidosGrupos.setAutoCreateColumnsFromModel(false);
							jgdTablaPartidosGrupos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane2.setViewportView(jgdTablaPartidosGrupos);
						}
						tabFaseGrupos.add(scrollPane2);
						scrollPane2.setBounds(210, 85, 485, 205);

						//---- lblPorDefinirse ----
						lblPorDefinirse.setText("PD: Por Definirse");
						tabFaseGrupos.add(lblPorDefinirse);
						lblPorDefinirse.setBounds(205, 305, 135, lblPorDefinirse.getPreferredSize().height);

						//---- btnDefinirFechas ----
						btnDefinirFechas.setText("Definir Fechas");
						tabFaseGrupos.add(btnDefinirFechas);
						btnDefinirFechas.setBounds(275, 345, 125, btnDefinirFechas.getPreferredSize().height);

						//---- btnDefinirMarcadores ----
						btnDefinirMarcadores.setText("Definir Marcadores");
						tabFaseGrupos.add(btnDefinirMarcadores);
						btnDefinirMarcadores.setBounds(470, 345, 150, btnDefinirMarcadores.getPreferredSize().height);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < tabFaseGrupos.getComponentCount(); i++) {
								Rectangle bounds = tabFaseGrupos.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = tabFaseGrupos.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							tabFaseGrupos.setMinimumSize(preferredSize);
							tabFaseGrupos.setPreferredSize(preferredSize);
						}
					}
					tabbedPane2.addTab("Fase de Grupos", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\grupos.png"), tabFaseGrupos);

					//======== tabOctavos ========
					{
						tabOctavos.setLayout(null);

						//---- lblPorDefinirse2 ----
						lblPorDefinirse2.setText("PD: Por Definirse");
						tabOctavos.add(lblPorDefinirse2);
						lblPorDefinirse2.setBounds(170, 280, 130, 14);

						//---- btnDefinirFechasOctavos ----
						btnDefinirFechasOctavos.setText("Definir Fechas");
						tabOctavos.add(btnDefinirFechasOctavos);
						btnDefinirFechasOctavos.setBounds(265, 340, 135, 23);

						//---- btnDefinirFaseOctavos ----
						btnDefinirFaseOctavos.setText("Definir Marcadores");
						tabOctavos.add(btnDefinirFaseOctavos);
						btnDefinirFaseOctavos.setBounds(460, 340, 155, 23);

						//======== scrollPane3 ========
						{

							//---- jgdTablaPartidosOctavos ----
							jgdTablaPartidosOctavos.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Fecha", "Selecci\u00f3n", "Marcador", "Selecci\u00f3n", "Marcador Penales"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							jgdTablaPartidosOctavos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane3.setViewportView(jgdTablaPartidosOctavos);
						}
						tabOctavos.add(scrollPane3);
						scrollPane3.setBounds(170, 45, 525, 220);

						//---- lblNoAplica ----
						lblNoAplica.setText("NA: No Aplica");
						tabOctavos.add(lblNoAplica);
						lblNoAplica.setBounds(170, 300, 125, 14);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < tabOctavos.getComponentCount(); i++) {
								Rectangle bounds = tabOctavos.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = tabOctavos.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							tabOctavos.setMinimumSize(preferredSize);
							tabOctavos.setPreferredSize(preferredSize);
						}
					}
					tabbedPane2.addTab("Octavos de Final", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\equipo.png"), tabOctavos);

					//======== tabCuartos ========
					{
						tabCuartos.setLayout(null);

						//---- lblNoAplica2 ----
						lblNoAplica2.setText("NA: No Aplica");
						tabCuartos.add(lblNoAplica2);
						lblNoAplica2.setBounds(180, 300, 125, 14);

						//======== scrollPane4 ========
						{

							//---- jgdTablaPartidosCuartos ----
							jgdTablaPartidosCuartos.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Fecha", "Selecci\u00f3n", "Marcador", "Selecci\u00f3n", "Marcador Penales"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							jgdTablaPartidosCuartos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane4.setViewportView(jgdTablaPartidosCuartos);
						}
						tabCuartos.add(scrollPane4);
						scrollPane4.setBounds(175, 70, 520, 205);

						//---- btnDefinirFaseCuartos ----
						btnDefinirFaseCuartos.setText("Definir Marcadores");
						tabCuartos.add(btnDefinirFaseCuartos);
						btnDefinirFaseCuartos.setBounds(465, 340, 145, 23);

						//---- btnDefinirFechasCuartos ----
						btnDefinirFechasCuartos.setText("Definir Fechas");
						tabCuartos.add(btnDefinirFechasCuartos);
						btnDefinirFechasCuartos.setBounds(270, 340, 135, 23);

						//---- lblPorDefinirse3 ----
						lblPorDefinirse3.setText("PD: Por Definirse");
						tabCuartos.add(lblPorDefinirse3);
						lblPorDefinirse3.setBounds(180, 280, 125, 14);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < tabCuartos.getComponentCount(); i++) {
								Rectangle bounds = tabCuartos.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = tabCuartos.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							tabCuartos.setMinimumSize(preferredSize);
							tabCuartos.setPreferredSize(preferredSize);
						}
					}
					tabbedPane2.addTab("Cuartos de Final", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\equipo.png"), tabCuartos);

					//======== tabSemifinal ========
					{
						tabSemifinal.setLayout(null);

						//---- lblNoAplica3 ----
						lblNoAplica3.setText("NA: No Aplica");
						tabSemifinal.add(lblNoAplica3);
						lblNoAplica3.setBounds(215, 285, 135, 14);

						//======== scrollPane5 ========
						{

							//---- jgdTablaPartidosSemifinales ----
							jgdTablaPartidosSemifinales.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Fecha", "Selecci\u00f3n", "Marcador", "Selecci\u00f3n", "Marcador Penales"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							jgdTablaPartidosSemifinales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane5.setViewportView(jgdTablaPartidosSemifinales);
						}
						tabSemifinal.add(scrollPane5);
						scrollPane5.setBounds(210, 75, 500, 175);

						//---- btnDefinirFaseSemifinales ----
						btnDefinirFaseSemifinales.setText("Definir Marcadores");
						tabSemifinal.add(btnDefinirFaseSemifinales);
						btnDefinirFaseSemifinales.setBounds(500, 315, 145, 23);

						//---- btnDefinirFechasSemifinales ----
						btnDefinirFechasSemifinales.setText("Definir Fechas");
						tabSemifinal.add(btnDefinirFechasSemifinales);
						btnDefinirFechasSemifinales.setBounds(305, 315, 116, 23);

						//---- lblPorDefinirse4 ----
						lblPorDefinirse4.setText("PD: Por Definirse");
						tabSemifinal.add(lblPorDefinirse4);
						lblPorDefinirse4.setBounds(215, 265, 140, 14);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < tabSemifinal.getComponentCount(); i++) {
								Rectangle bounds = tabSemifinal.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = tabSemifinal.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							tabSemifinal.setMinimumSize(preferredSize);
							tabSemifinal.setPreferredSize(preferredSize);
						}
					}
					tabbedPane2.addTab("Semifinal", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\equipo.png"), tabSemifinal);

					//======== tabFinal ========
					{
						tabFinal.setLayout(null);

						//---- lblNoAplica4 ----
						lblNoAplica4.setText("NA: No Aplica");
						tabFinal.add(lblNoAplica4);
						lblNoAplica4.setBounds(215, 310, 125, 14);

						//======== scrollPane6 ========
						{

							//---- jgdTablaPartidosFinales ----
							jgdTablaPartidosFinales.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Fecha", "Selecci\u00f3n", "Marcador", "Selecci\u00f3n", "Marcador Penales"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							jgdTablaPartidosFinales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							scrollPane6.setViewportView(jgdTablaPartidosFinales);
						}
						tabFinal.add(scrollPane6);
						scrollPane6.setBounds(200, 100, 500, 150);

						//---- btnDefinirFaseFinales ----
						btnDefinirFaseFinales.setText("Definir Marcadores");
						tabFinal.add(btnDefinirFaseFinales);
						btnDefinirFaseFinales.setBounds(490, 360, 140, 23);

						//---- btnDefinirFechasFinales ----
						btnDefinirFechasFinales.setText("Definir Fechas");
						tabFinal.add(btnDefinirFechasFinales);
						btnDefinirFechasFinales.setBounds(270, 360, 136, 23);

						//---- lblPorDefinirse5 ----
						lblPorDefinirse5.setText("PD: Por Definirse");
						tabFinal.add(lblPorDefinirse5);
						lblPorDefinirse5.setBounds(215, 285, 130, 14);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < tabFinal.getComponentCount(); i++) {
								Rectangle bounds = tabFinal.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = tabFinal.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							tabFinal.setMinimumSize(preferredSize);
							tabFinal.setPreferredSize(preferredSize);
						}
					}
					tabbedPane2.addTab("Final", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\copa3.png"), tabFinal);
				}
				panel1.add(tabbedPane2);
				tabbedPane2.setBounds(0, 0, 885, 502);

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
			tabbedPane1.addTab("Calendario", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\calendario.png"), panel1);

			//======== panel2 ========
			{
				panel2.setLayout(null);

				//---- lblGrupoTablaPosiciones ----
				lblGrupoTablaPosiciones.setText("Grupo:");
				panel2.add(lblGrupoTablaPosiciones);
				lblGrupoTablaPosiciones.setBounds(340, 185, 55, 19);

				//---- cmbGruposTablaPosiciones ----
				cmbGruposTablaPosiciones.setModel(new DefaultComboBoxModel<>(new String[] {
					"A",
					"B",
					"C",
					"D",
					"E",
					"F",
					"G",
					"H"
				}));
				panel2.add(cmbGruposTablaPosiciones);
				cmbGruposTablaPosiciones.setBounds(410, 185, 160, cmbGruposTablaPosiciones.getPreferredSize().height);

				//======== scrollPane1 ========
				{

					//---- jgdPosiciones ----
					jgdPosiciones.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
						},
						new String[] {
							"Selecci\u00f3n", "Puntos", "Goles", "Goles en Contra"
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
					jgdPosiciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane1.setViewportView(jgdPosiciones);
				}
				panel2.add(scrollPane1);
				scrollPane1.setBounds(265, 235, 395, 125);

				//---- label1 ----
				label1.setIcon(new ImageIcon("src\\main\\scala\\Icons\\Iconos\\fifa.png"));
				panel2.add(label1);
				label1.setBounds(290, 25, 330, 145);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel2.getComponentCount(); i++) {
						Rectangle bounds = panel2.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel2.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel2.setMinimumSize(preferredSize);
					panel2.setPreferredSize(preferredSize);
				}
			}
			tabbedPane1.addTab("Tablas de posiciones", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\posiciones.png"), panel2);
		}
		contentPane.add(tabbedPane1);
		tabbedPane1.setBounds(0, 0, 890, 530);

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
		setSize(890, 555);
		setLocationRelativeTo(null);
	}

	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JTabbedPane tabbedPane2;
	private JPanel tabFaseGrupos;
	public JButton btnAsignar;
	private JLabel lblGrupo;
	public JComboBox<String> cmbGrupos;
	public JScrollPane scrollPane2;
	public JTable jgdTablaPartidosGrupos;
	private JLabel lblPorDefinirse;
	public JButton btnDefinirFechas;
	public JButton btnDefinirMarcadores;
	private JPanel tabOctavos;
	private JLabel lblPorDefinirse2;
	public JButton btnDefinirFechasOctavos;
	public JButton btnDefinirFaseOctavos;
	private JScrollPane scrollPane3;
	public JTable jgdTablaPartidosOctavos;
	private JLabel lblNoAplica;
	private JPanel tabCuartos;
	private JLabel lblNoAplica2;
	private JScrollPane scrollPane4;
	public JTable jgdTablaPartidosCuartos;
	public JButton btnDefinirFaseCuartos;
	public JButton btnDefinirFechasCuartos;
	private JLabel lblPorDefinirse3;
	private JPanel tabSemifinal;
	private JLabel lblNoAplica3;
	private JScrollPane scrollPane5;
	public JTable jgdTablaPartidosSemifinales;
	public JButton btnDefinirFaseSemifinales;
	public JButton btnDefinirFechasSemifinales;
	private JLabel lblPorDefinirse4;
	private JPanel tabFinal;
	private JLabel lblNoAplica4;
	private JScrollPane scrollPane6;
	public JTable jgdTablaPartidosFinales;
	public JButton btnDefinirFaseFinales;
	public JButton btnDefinirFechasFinales;
	private JLabel lblPorDefinirse5;
	private JPanel panel2;
	private JLabel lblGrupoTablaPosiciones;
	public JComboBox<String> cmbGruposTablaPosiciones;
	private JScrollPane scrollPane1;
	public JTable jgdPosiciones;
	private JLabel label1;
}
