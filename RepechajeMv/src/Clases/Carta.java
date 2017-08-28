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
public class Carta {
    
    private int idCarta;
    private String fecha;
        
    public Carta(){
    
    }

    public Carta(String idCarta, String fecha) {
        this.idCarta=Integer.valueOf(idCarta);
        this.fecha=fecha;
    }

    public int getIdCarta() {
        return idCarta;
    }
    
    
}
