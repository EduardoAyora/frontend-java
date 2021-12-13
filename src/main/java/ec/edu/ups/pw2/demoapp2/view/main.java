package ec.edu.ups.pw2.demoapp2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ec.edu.ups.pw2.demoapp2.ON.CalculadoraONRemote;
import ec.edu.ups.pw2.demoapp2.ON.PersonaONRemote;
import ec.edu.ups.pw2.demoapp2.model.DetalleFactura;
import ec.edu.ups.pw2.demoapp2.model.Factura;
import ec.edu.ups.pw2.demoapp2.model.Persona;
import ec.edu.ups.pw2.demoapp2.model.Producto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;

public class main {
	
	private VentanaCalculadora ventanaCalculadora;
	private DefaultListModel<String> facturasEnLista;
	private DefaultListModel<String> detallesEnLista;
	private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

	private JFrame frame;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtPrecioProducto;
	private JTextField txtCantidadProducto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		try {
			ventanaCalculadora = new VentanaCalculadora();
			ventanaCalculadora.conectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
		actualizarJListFacturas();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		facturasEnLista = new DefaultListModel<String>();
		detallesEnLista = new DefaultListModel<String>();
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Cédula");
		panel_11.add(lblNewLabel);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText();
				Persona persona = ventanaCalculadora.buscarCliente(cedula);
				if (persona != null) {
					txtNombre.setText(persona.getNombre());
					txtDireccion.setText(persona.getDireccion());
				}
			}
		});
		panel_11.add(btnBuscar);
		
		txtCedula = new JTextField();
		panel_1.add(txtCedula);
		txtCedula.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		panel_2.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		panel_2.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Dirección");
		panel_3.add(lblNewLabel_2);
		
		txtDireccion = new JTextField();
		panel_3.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Detalles:");
		panel.add(lblNewLabel_3);
		
		JPanel panel_factura = new JPanel();
		frame.getContentPane().add(panel_factura);
		panel_factura.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_factura.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JList<String> listDetalles = new JList<String>(detallesEnLista);
		panel_5.add(listDetalles);
		
		JPanel panel_4 = new JPanel();
		panel_factura.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		panel_8.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Precio Producto");
		panel_8.add(lblNewLabel_4);
		
		txtPrecioProducto = new JTextField();
		panel_8.add(txtPrecioProducto);
		txtPrecioProducto.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad");
		panel_9.add(lblNewLabel_5);
		
		txtCantidadProducto = new JTextField();
		panel_9.add(txtCantidadProducto);
		txtCantidadProducto.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double precioProducto = Double.parseDouble(txtPrecioProducto.getText()); 
				int cantidadProducto = Integer.parseInt(txtCantidadProducto.getText());
				txtPrecioProducto.setText("");
				txtCantidadProducto.setText("");
				Producto productoNuevo = new Producto();
				productoNuevo.setPrecio(precioProducto);
				productoNuevo.setStock(10);
				DetalleFactura detalleNuevo = new DetalleFactura();
				detalleNuevo.setCantidad(cantidadProducto);
				detalleNuevo.setProducto(productoNuevo);
				detalleNuevo.setPrecio(precioProducto * cantidadProducto);
				detalles.add(detalleNuevo);
				actualizarJListDetalles();
			}
		});
		panel_10.add(btnAgregarProducto);
		
		JButton btnFactura = new JButton("Crear Factura");
		panel_4.add(btnFactura);
		btnFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText();
				String nombre = txtNombre.getText();
				String direccion = txtDireccion.getText();
				txtCedula.setText("");
				txtNombre.setText("");
				txtDireccion.setText("");
				Persona persona = new Persona();
				persona.setCedula(cedula);
				persona.setDireccion(direccion);
				persona.setNombre(nombre);
				
				Factura facturaNueva = new Factura();
				facturaNueva.setCliente(persona);
				facturaNueva.setDetalles(detalles);
				facturaNueva.setFecha(new Date());
				
				ventanaCalculadora.insertarFactura(facturaNueva);
				detalles.clear();
				actualizarJListFacturas();
				actualizarJListDetalles();
			}
		});
		
		JPanel panel_facturas = new JPanel();
		frame.getContentPane().add(panel_facturas);
		panel_facturas.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_facturas.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JList<String> facturasList = new JList<String>(facturasEnLista);
		panel_6.add(facturasList);
	}
	
	private void actualizarJListFacturas() {
		List<String> facturas = ventanaCalculadora.listarFacturas();
		facturasEnLista.removeAllElements();
		for (String factura : facturas) {
			facturasEnLista.addElement(factura);
		}
	}
	
	private void actualizarJListDetalles() {
		List<String> detallesEnTexto = new ArrayList<String>();
		for (DetalleFactura detalle : detalles) {
			String detalleEnTexto = "Precio del producto: " + detalle.getProducto().getPrecio() + ", Cantidad: " + detalle.getCantidad() + ", Total: " + detalle.getPrecio();
			detallesEnTexto.add(detalleEnTexto);
        }
		detallesEnLista.removeAllElements();
		for (String detalle : detallesEnTexto) {
			detallesEnLista.addElement(detalle);
		}
	}

}
