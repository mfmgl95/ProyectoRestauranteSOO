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
public class Mesa {
    
    private int nro;
    private int estado;


    public Mesa(String nro, String estado) {
        this.nro = Integer.parseInt(nro);
        this.estado=Integer.parseInt(estado);
 
    }

    public int getNro() {
        return nro;
    }

    public int getEstado() {
        return estado;
    }
    
    
}
