import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JTextPane;

public class Ventana extends JFrame {
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	String escribir;
	String escribir2;
	String ruta = "./archivo.txt";
	File fichero = new File("archivo.txt");
	private JButton btnVerProductos;
	private JButton btnAlta;
	private JLabel lblResultado;
	private JLabel lblPrecio;
	private JLabel lblDescripcion;
	private JTextPane txtResultados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		lblDescripcion = new JLabel("Descripcion del articulo");
		lblDescripcion.setBounds(10, 11, 120, 14);
		getContentPane().add(lblDescripcion);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 50, 46, 14);
		getContentPane().add(lblPrecio);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(184, 9, 240, 17);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(184, 60, 240, 17);
		getContentPane().add(txtPrecio);

		lblResultado = new JLabel("Resultado");
		lblResultado.setBounds(352, 104, 72, 14);
		getContentPane().add(lblResultado);

		btnAlta = new JButton("Alta");
		btnAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lee lo introducido en los campos y lo guarda en el fichero
				escribir = txtDescripcion.getText();
				escribir2 = txtPrecio.getText();
				try {
					FileWriter escritor = new FileWriter(fichero);
					escritor.write(escribir);
					escritor.write(escribir2);
					escritor.close();
				} catch (Exception i) {
					i.printStackTrace();
				}
			}
		});
		btnAlta.setBounds(252, 100, 89, 23);
		getContentPane().add(btnAlta);

		btnVerProductos = new JButton("Visualizar Productos");
		btnVerProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lee el contenido del fichero y lo coloca en el panel
				try {
					Scanner leer = new Scanner(fichero);
					while (leer.hasNext()) {
						String datos = leer.nextLine();
						txtResultados.setText(datos);
						leer.close();
					}
				} catch (Exception o) {
				}
			}
		});
		btnVerProductos.setBounds(10, 104, 165, 23);
		getContentPane().add(btnVerProductos);

		txtResultados = new JTextPane();
		txtResultados.setEditable(false);
		txtResultados.setBounds(10, 138, 414, 112);
		getContentPane().add(txtResultados);
	}

}
