package Vista;

import java.awt.*;
import javax.swing.*;

public class vista_Selecciones extends JFrame {
	public vista_Selecciones() {
		initComponents();
	}

	private void initComponents() {

		grbGrupos2 = new JTabbedPane();
		jtbGrupos = new JPanel();
		grbGrupos = new JPanel();
		grbGrupoA = new JPanel();
		jScrollPane1 = new JScrollPane();
		lstGrupoA = new JList<>();
		lblGrupoA = new JLabel();
		grbGrupoB = new JPanel();
		lblGrupoB = new JLabel();
		jScrollPane2 = new JScrollPane();
		lstGrupoB = new JList<>();
		grbGrupoC = new JPanel();
		lblGrupoC = new JLabel();
		jScrollPane3 = new JScrollPane();
		lstGrupoC = new JList<>();
		grbGrupoD = new JPanel();
		lblGrupoD = new JLabel();
		jScrollPane4 = new JScrollPane();
		lstGrupoD = new JList<>();
		grbGrupoE = new JPanel();
		lblGrupoE = new JLabel();
		jScrollPane5 = new JScrollPane();
		lstGrupoE = new JList<>();
		grbGrupoF = new JPanel();
		lblGrupoF = new JLabel();
		jScrollPane6 = new JScrollPane();
		lstGrupoF = new JList<>();
		grbGrupoG = new JPanel();
		lblGrupoG = new JLabel();
		jScrollPane7 = new JScrollPane();
		lstGrupoG = new JList<>();
		grbGrupoH = new JPanel();
		lblGrupoH = new JLabel();
		jScrollPane8 = new JScrollPane();
		lstGrupoH = new JList<>();
		jtbPaises = new JPanel();
		jScrollPane9 = new JScrollPane();
		lstPaises = new JList<>();
		lblCantidadPaises = new JLabel();
		lblCantidadPaisesValor = new JLabel();
		jtbAgregar = new JPanel();
		lblNombrePais = new JLabel();
		txtNombrePais = new JTextField();
		lblGrupo = new JLabel();
		cmbGrupos_2 = new JComboBox<>();
		btnAgregar = new JButton();
		jtbEditarPais = new JPanel();
		lblNombrePais1 = new JLabel();
		lblGrupo1 = new JLabel();
		cmbPaises = new JComboBox<>();
		btnEditar = new JButton();
		cmbGruposEditar = new JComboBox<>();
		jtbEliminarPais = new JPanel();
		cmbPaisesEliminar = new JComboBox<>();
		lblSeleccionePaisEliminar = new JLabel();
		btnEliminar = new JButton();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
		setResizable(false);
		setTitle("Selecciones");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== grbGrupos2 ========
		{

			//======== jtbGrupos ========
			{
				jtbGrupos.setLayout(null);

				//======== grbGrupos ========
				{
					grbGrupos.setLayout(new GridLayout(2, 4));

					//======== grbGrupoA ========
					{
						grbGrupoA.setLayout(null);

						//======== jScrollPane1 ========
						{

							//---- lstGrupoA ----
							lstGrupoA.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoA.setToolTipText("");
							jScrollPane1.setViewportView(lstGrupoA);
						}
						grbGrupoA.add(jScrollPane1);
						jScrollPane1.setBounds(10, 32, 140, 190);

						//---- lblGrupoA ----
						lblGrupoA.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoA.setText("GRUPO A");
						grbGrupoA.add(lblGrupoA);
						lblGrupoA.setBounds(0, 0, 160, 30);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoA.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoA.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoA.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoA.setMinimumSize(preferredSize);
							grbGrupoA.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoA);

					//======== grbGrupoB ========
					{
						grbGrupoB.setLayout(null);

						//---- lblGrupoB ----
						lblGrupoB.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoB.setText("GRUPO B");
						grbGrupoB.add(lblGrupoB);
						lblGrupoB.setBounds(0, 4, 160, 30);

						//======== jScrollPane2 ========
						{

							//---- lstGrupoB ----
							lstGrupoB.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoB.setToolTipText("");
							jScrollPane2.setViewportView(lstGrupoB);
						}
						grbGrupoB.add(jScrollPane2);
						jScrollPane2.setBounds(10, 32, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoB.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoB.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoB.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoB.setMinimumSize(preferredSize);
							grbGrupoB.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoB);

					//======== grbGrupoC ========
					{
						grbGrupoC.setLayout(null);

						//---- lblGrupoC ----
						lblGrupoC.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoC.setText("GRUPO C");
						grbGrupoC.add(lblGrupoC);
						lblGrupoC.setBounds(0, 0, 160, 30);

						//======== jScrollPane3 ========
						{

							//---- lstGrupoC ----
							lstGrupoC.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoC.setToolTipText("");
							jScrollPane3.setViewportView(lstGrupoC);
						}
						grbGrupoC.add(jScrollPane3);
						jScrollPane3.setBounds(10, 32, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoC.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoC.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoC.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoC.setMinimumSize(preferredSize);
							grbGrupoC.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoC);

					//======== grbGrupoD ========
					{
						grbGrupoD.setLayout(null);

						//---- lblGrupoD ----
						lblGrupoD.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoD.setText("GRUPO D");
						grbGrupoD.add(lblGrupoD);
						lblGrupoD.setBounds(0, 0, 160, 30);

						//======== jScrollPane4 ========
						{

							//---- lstGrupoD ----
							lstGrupoD.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoD.setToolTipText("");
							jScrollPane4.setViewportView(lstGrupoD);
						}
						grbGrupoD.add(jScrollPane4);
						jScrollPane4.setBounds(10, 32, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoD.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoD.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoD.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoD.setMinimumSize(preferredSize);
							grbGrupoD.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoD);

					//======== grbGrupoE ========
					{
						grbGrupoE.setLayout(null);

						//---- lblGrupoE ----
						lblGrupoE.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoE.setText("GRUPO E");
						grbGrupoE.add(lblGrupoE);
						lblGrupoE.setBounds(0, 0, 160, 30);

						//======== jScrollPane5 ========
						{

							//---- lstGrupoE ----
							lstGrupoE.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoE.setToolTipText("");
							jScrollPane5.setViewportView(lstGrupoE);
						}
						grbGrupoE.add(jScrollPane5);
						jScrollPane5.setBounds(10, 30, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoE.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoE.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoE.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoE.setMinimumSize(preferredSize);
							grbGrupoE.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoE);

					//======== grbGrupoF ========
					{
						grbGrupoF.setLayout(null);

						//---- lblGrupoF ----
						lblGrupoF.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoF.setText("GRUPO F");
						grbGrupoF.add(lblGrupoF);
						lblGrupoF.setBounds(0, 0, 160, 30);

						//======== jScrollPane6 ========
						{

							//---- lstGrupoF ----
							lstGrupoF.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoF.setToolTipText("");
							jScrollPane6.setViewportView(lstGrupoF);
						}
						grbGrupoF.add(jScrollPane6);
						jScrollPane6.setBounds(10, 32, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoF.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoF.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoF.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoF.setMinimumSize(preferredSize);
							grbGrupoF.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoF);

					//======== grbGrupoG ========
					{
						grbGrupoG.setLayout(null);

						//---- lblGrupoG ----
						lblGrupoG.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoG.setText("GRUPO G");
						grbGrupoG.add(lblGrupoG);
						lblGrupoG.setBounds(0, 0, 160, 30);

						//======== jScrollPane7 ========
						{

							//---- lstGrupoG ----
							lstGrupoG.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoG.setToolTipText("");
							jScrollPane7.setViewportView(lstGrupoG);
						}
						grbGrupoG.add(jScrollPane7);
						jScrollPane7.setBounds(10, 30, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoG.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoG.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoG.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoG.setMinimumSize(preferredSize);
							grbGrupoG.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoG);

					//======== grbGrupoH ========
					{
						grbGrupoH.setLayout(null);

						//---- lblGrupoH ----
						lblGrupoH.setHorizontalAlignment(SwingConstants.CENTER);
						lblGrupoH.setText("GRUPO H");
						grbGrupoH.add(lblGrupoH);
						lblGrupoH.setBounds(0, 0, 160, 30);

						//======== jScrollPane8 ========
						{

							//---- lstGrupoH ----
							lstGrupoH.setModel(new AbstractListModel<String>() {
								String[] values = {

								};
								@Override
								public int getSize() { return values.length; }
								@Override
								public String getElementAt(int i) { return values[i]; }
							});
							lstGrupoH.setToolTipText("");
							jScrollPane8.setViewportView(lstGrupoH);
						}
						grbGrupoH.add(jScrollPane8);
						jScrollPane8.setBounds(10, 30, 140, 190);

						{
							// compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < grbGrupoH.getComponentCount(); i++) {
								Rectangle bounds = grbGrupoH.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = grbGrupoH.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							grbGrupoH.setMinimumSize(preferredSize);
							grbGrupoH.setPreferredSize(preferredSize);
						}
					}
					grbGrupos.add(grbGrupoH);
				}
				jtbGrupos.add(grbGrupos);
				grbGrupos.setBounds(90, 30, 620, 450);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < jtbGrupos.getComponentCount(); i++) {
						Rectangle bounds = jtbGrupos.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = jtbGrupos.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					jtbGrupos.setMinimumSize(preferredSize);
					jtbGrupos.setPreferredSize(preferredSize);
				}
			}
			grbGrupos2.addTab("Grupo de Selecciones", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\mundo.png"), jtbGrupos);

			//======== jtbPaises ========
			{
				jtbPaises.setLayout(null);

				//======== jScrollPane9 ========
				{

					//---- lstPaises ----
					lstPaises.setModel(new AbstractListModel<String>() {
						String[] values = {

						};
						@Override
						public int getSize() { return values.length; }
						@Override
						public String getElementAt(int i) { return values[i]; }
					});
					jScrollPane9.setViewportView(lstPaises);
				}
				jtbPaises.add(jScrollPane9);
				jScrollPane9.setBounds(265, 30, 300, 410);

				//---- lblCantidadPaises ----
				lblCantidadPaises.setText("Cantidad de pa\u00edses: ");
				jtbPaises.add(lblCantidadPaises);
				lblCantidadPaises.setBounds(new Rectangle(new Point(260, 455), lblCantidadPaises.getPreferredSize()));

				//---- lblCantidadPaisesValor ----
				lblCantidadPaisesValor.setText("0");
				jtbPaises.add(lblCantidadPaisesValor);
				lblCantidadPaisesValor.setBounds(380, 455, 20, lblCantidadPaisesValor.getPreferredSize().height);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < jtbPaises.getComponentCount(); i++) {
						Rectangle bounds = jtbPaises.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = jtbPaises.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					jtbPaises.setMinimumSize(preferredSize);
					jtbPaises.setPreferredSize(preferredSize);
				}
			}
			grbGrupos2.addTab("Selecciones", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\bandera.png"), jtbPaises);

			//======== jtbAgregar ========
			{
				jtbAgregar.setLayout(null);

				//---- lblNombrePais ----
				lblNombrePais.setText("Nombre del pa\u00eds: ");
				jtbAgregar.add(lblNombrePais);
				lblNombrePais.setBounds(265, 155, lblNombrePais.getPreferredSize().width, 20);
				jtbAgregar.add(txtNombrePais);
				txtNombrePais.setBounds(370, 155, 173, txtNombrePais.getPreferredSize().height);

				//---- lblGrupo ----
				lblGrupo.setText("Grupo:");
				jtbAgregar.add(lblGrupo);
				lblGrupo.setBounds(310, 195, lblGrupo.getPreferredSize().width, 40);

				//---- cmbGrupos_2 ----
				cmbGrupos_2.setModel(new DefaultComboBoxModel<>(new String[] {

				}));
				jtbAgregar.add(cmbGrupos_2);
				cmbGrupos_2.setBounds(370, 205, 173, cmbGrupos_2.getPreferredSize().height);

				//---- btnAgregar ----
				btnAgregar.setText("Agregar");
				jtbAgregar.add(btnAgregar);
				btnAgregar.setBounds(370, 245, 173, btnAgregar.getPreferredSize().height);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < jtbAgregar.getComponentCount(); i++) {
						Rectangle bounds = jtbAgregar.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = jtbAgregar.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					jtbAgregar.setMinimumSize(preferredSize);
					jtbAgregar.setPreferredSize(preferredSize);
				}
			}
			grbGrupos2.addTab("Agregar Selecci\u00f3n", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\agregar.png"), jtbAgregar);

			//======== jtbEditarPais ========
			{
				jtbEditarPais.setLayout(null);

				//---- lblNombrePais1 ----
				lblNombrePais1.setText("Selecciones el pa\u00eds: ");
				jtbEditarPais.add(lblNombrePais1);
				lblNombrePais1.setBounds(250, 150, lblNombrePais1.getPreferredSize().width, 20);

				//---- lblGrupo1 ----
				lblGrupo1.setText("Grupo:");
				jtbEditarPais.add(lblGrupo1);
				lblGrupo1.setBounds(305, 190, lblGrupo1.getPreferredSize().width, 40);

				//---- cmbPaises ----
				cmbPaises.setModel(new DefaultComboBoxModel<>(new String[] {

				}));
				jtbEditarPais.add(cmbPaises);
				cmbPaises.setBounds(380, 150, 173, cmbPaises.getPreferredSize().height);

				//---- btnEditar ----
				btnEditar.setText("Confirmar");
				jtbEditarPais.add(btnEditar);
				btnEditar.setBounds(380, 240, 173, btnEditar.getPreferredSize().height);

				//---- cmbGruposEditar ----
				cmbGruposEditar.setModel(new DefaultComboBoxModel<>(new String[] {

				}));
				jtbEditarPais.add(cmbGruposEditar);
				cmbGruposEditar.setBounds(380, 200, 173, cmbGruposEditar.getPreferredSize().height);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < jtbEditarPais.getComponentCount(); i++) {
						Rectangle bounds = jtbEditarPais.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = jtbEditarPais.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					jtbEditarPais.setMinimumSize(preferredSize);
					jtbEditarPais.setPreferredSize(preferredSize);
				}
			}
			grbGrupos2.addTab("Editar Selecci\u00f3n", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\buscar.png"), jtbEditarPais);

			//======== jtbEliminarPais ========
			{
				jtbEliminarPais.setLayout(null);

				//---- cmbPaisesEliminar ----
				cmbPaisesEliminar.setModel(new DefaultComboBoxModel<>(new String[] {

				}));
				jtbEliminarPais.add(cmbPaisesEliminar);
				cmbPaisesEliminar.setBounds(370, 160, 173, cmbPaisesEliminar.getPreferredSize().height);

				//---- lblSeleccionePaisEliminar ----
				lblSeleccionePaisEliminar.setText("Selecciones el pa\u00eds: ");
				jtbEliminarPais.add(lblSeleccionePaisEliminar);
				lblSeleccionePaisEliminar.setBounds(235, 160, lblSeleccionePaisEliminar.getPreferredSize().width, 20);

				//---- btnEliminar ----
				btnEliminar.setText("Confirmar");
				jtbEliminarPais.add(btnEliminar);
				btnEliminar.setBounds(370, 230, 173, btnEliminar.getPreferredSize().height);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < jtbEliminarPais.getComponentCount(); i++) {
						Rectangle bounds = jtbEliminarPais.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = jtbEliminarPais.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					jtbEliminarPais.setMinimumSize(preferredSize);
					jtbEliminarPais.setPreferredSize(preferredSize);
				}
			}
			grbGrupos2.addTab("Eliminar Selecci\u00f3n", new ImageIcon("src\\main\\scala\\Icons\\Iconos\\eliminar.png"), jtbEliminarPais);
		}
		contentPane.add(grbGrupos2);
		grbGrupos2.setBounds(0, 0, 820, 530);

		contentPane.setPreferredSize(new Dimension(820, 560));
		setSize(820, 560);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Berrezueta
	private JTabbedPane grbGrupos2;
	private JPanel jtbGrupos;
	private JPanel grbGrupos;
	private JPanel grbGrupoA;
	private JScrollPane jScrollPane1;
	public JList<String> lstGrupoA;
	private JLabel lblGrupoA;
	private JPanel grbGrupoB;
	private JLabel lblGrupoB;
	private JScrollPane jScrollPane2;
	public JList<String> lstGrupoB;
	private JPanel grbGrupoC;
	private JLabel lblGrupoC;
	private JScrollPane jScrollPane3;
	public JList<String> lstGrupoC;
	private JPanel grbGrupoD;
	private JLabel lblGrupoD;
	private JScrollPane jScrollPane4;
	public JList<String> lstGrupoD;
	private JPanel grbGrupoE;
	private JLabel lblGrupoE;
	private JScrollPane jScrollPane5;
	public JList<String> lstGrupoE;
	private JPanel grbGrupoF;
	private JLabel lblGrupoF;
	private JScrollPane jScrollPane6;
	public JList<String> lstGrupoF;
	private JPanel grbGrupoG;
	private JLabel lblGrupoG;
	private JScrollPane jScrollPane7;
	public JList<String> lstGrupoG;
	private JPanel grbGrupoH;
	private JLabel lblGrupoH;
	private JScrollPane jScrollPane8;
	public JList<String> lstGrupoH;
	private JPanel jtbPaises;
	private JScrollPane jScrollPane9;
	public JList<String> lstPaises;
	private JLabel lblCantidadPaises;
	public JLabel lblCantidadPaisesValor;
	private JPanel jtbAgregar;
	private JLabel lblNombrePais;
	public JTextField txtNombrePais;
	private JLabel lblGrupo;
	public JComboBox<String> cmbGrupos_2;
	public JButton btnAgregar;
	private JPanel jtbEditarPais;
	private JLabel lblNombrePais1;
	private JLabel lblGrupo1;
	public JComboBox<String> cmbPaises;
	public JButton btnEditar;
	public JComboBox<String> cmbGruposEditar;
	private JPanel jtbEliminarPais;
	public JComboBox<String> cmbPaisesEliminar;
	private JLabel lblSeleccionePaisEliminar;
	public JButton btnEliminar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
