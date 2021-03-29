/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.global.fsfb.fsfbapi.persistencia;

import co.global.fsfb.fsfbapi.site.PropertiesManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class Conexion {

    private static Connection cnx = null;
    
    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion();
            conexion.obtener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                
                Class.forName(PropertiesManager.CONFIG.getString("spring.datasource.driver-class-name"));
                cnx = DriverManager.getConnection("jdbc:oracle:thin:@//hisisis-scan.fundacion.loc:1537/DWHFSFB", PropertiesManager.CONFIG.getString("spring.datasource.username"), PropertiesManager.CONFIG.getString("spring.datasource.password"));
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }

}
