/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.*;

public class Conexion {
    
   private Connection cnx = null;
   
   public Connection obtener() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/repechaje", "root", "");
         } catch (SQLException e) {
             System.out.println("Error Conexion BD"+e.getMessage());
         } 
      }
      return cnx;
   }
   public void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}