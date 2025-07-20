/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sintactico;

/**
 *
 * @author Luis Angel
 */

//clase que solo sirve para almacenar cada instruccion del sintactico y mostrarlo en la tabla, nada más
public class Sin {
    String RetPila;
    String RetCad2;
    String XX;
    String produccion;
    
    Sin(){
        
    }

    public Sin(String RetPila, String RetCad2, String XX, String produccion) {
        this.RetPila = RetPila;
        this.RetCad2 = RetCad2;
        this.XX = XX;
        this.produccion = produccion;
    }

    public String getRetPila() {
        return RetPila;
    }

    public void setRetPila(String RetPila) {
        this.RetPila = RetPila;
    }

    public String getRetCad2() {
        return RetCad2;
    }

    public void setRetCad2(String RetCad2) {
        this.RetCad2 = RetCad2;
    }

    public String getXX() {
        return XX;
    }

    public void setXX(String XX) {
        this.XX = XX;
    }

    public String getProduccion() {
        return produccion;
    }

    public void setProduccion(String produccion) {
        this.produccion = produccion;
    }
    
    
}
