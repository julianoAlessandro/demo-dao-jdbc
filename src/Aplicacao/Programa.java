/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacao;

import java.util.Date;
import model.entidades.Departamentos;
import model.entidades.Vendedor;

/**
 *
 * @author Juliano
 */
public class Programa {
    public static void main(String args[]){
        Departamentos departamento01 = new Departamentos(1,"RH");
        Vendedor  vendedor01 = new Vendedor(1,"juliano","juliano86@gmail.com",new Date(),1.500,departamento01);
        System.out.println(vendedor01);
    }
}
