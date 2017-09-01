/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author TOSHIBA
 */
public class Cliente {
    
    private int idCliente;
    private String nombreR;
    private String DNI_RUC;
    private String direccion;

    public Cliente(String nombre, String razonSocial, String DNI, String RUC, String direccion) {
        this.idCliente = idCliente;
        this.nombreR = nombre;
        this.DNI_RUC = DNI;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombreR() {
        return nombreR;
    }


    public String getDNI_RUC() {
        return DNI_RUC;
    }



    public String getDireccion() {
        return direccion;
    }
    
    
    
}
