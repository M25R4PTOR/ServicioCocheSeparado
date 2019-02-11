/**
 * Funcion_PortSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.examples.www.wsdl.WSPractica_wsdl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aytos.prueba.COCHE;

public class Funcion_PortSoapBindingImpl implements com.examples.www.wsdl.WSPractica_wsdl.Funcion_PortType {
	public aytos.prueba.COCHE funcion2(java.lang.String param1) throws java.rmi.RemoteException {

		Connection conn = conectarBaseDatos();
		COCHE res = new COCHE();
		try {
			res = traerDatos(conn, param1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private static Connection conectarBaseDatos() {
		try {
			try {
				// Se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servicios", "root", "");

			boolean valid = connection.isValid(5000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			return connection;

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
			return null;
		}
	}

	private static COCHE traerDatos(Connection conn, String matricula) throws SQLException {
		String sql = "Select * FROM coche WHERE matricula ='" + matricula + "'";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		result.next();
		COCHE res = new COCHE(result.getString(1), result.getInt(2), result.getString(3), result.getInt(4));

		return res;
	}

}
