import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
//Martinez Dimas Mahonri Matricula:1796763
public class conexion {
private static String Url="jdbc:mysql://localhost:3306/articulos?useTimeZone=true&serverTimezone=UTC";
private static String usuario = "root";
private static String password = "canelin2020";
public static Connection conectar() {
try {
	Connection con= DriverManager.getConnection(Url,usuario,password);
	return con;
	
}
catch(SQLException ex) {
	System.out.print("Ha ocurrido un error");
	return null;
}
}
}
