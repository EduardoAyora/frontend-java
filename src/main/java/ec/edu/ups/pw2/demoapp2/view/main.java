package ec.edu.ups.pw2.demoapp2.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ec.edu.ups.pw2.demoapp2.ON.CalculadoraONRemote;
import ec.edu.ups.pw2.demoapp2.ON.PersonaONRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main {
	
	private VentanaCalculadora ventanaCalculadora;

	private JFrame frame;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Cédula");
		panel_1.add(lblNewLabel);
		
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
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_4.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Crear Persona");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText();
				String nombre = txtNombre.getText();
				String direccion = txtDireccion.getText();
				txtCedula.setText("");
				txtNombre.setText("");
				txtDireccion.setText("");
				ventanaCalculadora.crearPersona(cedula, nombre, direccion);
			}
		});
		panel_4.add(btnNewButton);
		
		JPanel panel_factura = new JPanel();
		frame.getContentPane().add(panel_factura);
		panel_factura.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_factura.add(panel_5);
		
		JButton btnFactura = new JButton("Crear Factura");
		btnFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaCalculadora.insertarFactura();
			}
		});
		panel_5.add(btnFactura);
	}

}
