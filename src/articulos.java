import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class articulos extends JFrame {

	private JPanel contentPane;
	private JTextField txtarticulo;
	private JTextField txtprecio;
	PreparedStatement stm=null;
	ResultSet rst= null;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnactualizar;
	private JTextField txtcod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					articulos frame = new articulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public articulos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(36, 71, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio");
		lblNewLabel_1.setBounds(36, 116, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtarticulo = new JTextField();
		txtarticulo.setBounds(88, 68, 86, 20);
		contentPane.add(txtarticulo);
		txtarticulo.setColumns(10);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(116, 113, 86, 20);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		//Martinez Dimas Mahonri Matricula:1796763
		JButton btnadd = new JButton("Agregar");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=conexion.conectar();
				try {
				Statement st=con.createStatement();
				float precio;
				precio=Float.parseFloat(txtprecio.getText());
				st.executeUpdate("INSERT INTO articulo(descripcion,precio) values('"+txtarticulo.getText()+"',"+precio+")");
				con.close();
				txtarticulo.setText("");
				txtprecio.setText("");
				JOptionPane.showMessageDialog(null, "Registro insertado");
				
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Eres un crack");
				}
			}
		});
		btnadd.setBounds(55, 157, 89, 23);
		contentPane.add(btnadd);
		btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			//Martinez Dimas Mahonri Matricula:1796763
			public void actionPerformed(ActionEvent e) {
				Connection con =conexion.conectar();
				try {
					stm=con.prepareStatement("select descripcion,precio from articulo where codigo=?");
					stm.setString(1, txtcod.getText());
					rst=stm.executeQuery();
					if(rst.next()) {
						txtarticulo.setText(rst.getString("descripcion"));
						txtprecio.setText(rst.getString("precio"));
					}
					else {
						JOptionPane.showMessageDialog(null,"Articulo no encontrado");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error crack");
				}
		
			}
		});
		btnNewButton.setBounds(184, 22, 89, 23);
		contentPane.add(btnNewButton);
		//Martinez Dimas Mahonri Matricula:1796763
		btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = conexion.conectar();
				try {
					stm= con.prepareStatement("DELETE FROM articulo where codigo=?");
					stm.setString(1, txtcod.getText());
					int resultado=stm.executeUpdate();
					if(resultado>0) {
						JOptionPane.showMessageDialog(null, "Registro eliminado");
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "HA ocurrido un error");
				}
			}
		});
		btnNewButton_1.setBounds(265, 157, 89, 23);
		contentPane.add(btnNewButton_1);
		//Martinez Dimas Mahonri Matricula:1796763
		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = conexion.conectar();
				try {
					stm=con.prepareStatement("UPDATE articulo set descripcion=?, precio=? where codigo = ?");
					stm.setString(1,txtarticulo.getText());
					stm.setString(2,txtprecio.getText());
					stm.setString(3, txtcod.getText());
					int resultado=stm.executeUpdate();
					if(resultado>0) {
						JOptionPane.showMessageDialog(null,"Registro actualizado");
						con.close();
					}
					else {
						JOptionPane.showMessageDialog(null,"No se pudo actualizar");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error ");
				}	
			}
		});
		btnactualizar.setBounds(36, 199, 107, 23);
		contentPane.add(btnactualizar);
		
		txtcod = new JTextField();
		txtcod.setBounds(88, 23, 86, 20);
		contentPane.add(txtcod);
		txtcod.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Codigo");
		lblNewLabel_2.setBounds(10, 26, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Martinez Dimas Mahonri Matricula 1796763");
		lblNewLabel_3.setFont(new Font("Stencil", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 236, 414, 14);
		contentPane.add(lblNewLabel_3);
	}
}
