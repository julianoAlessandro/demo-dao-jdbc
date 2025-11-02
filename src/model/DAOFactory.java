/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import db.DB;
import model.dao.VendedorDAO;
import model.dao.impl.VendedorJDBC;

/**
 *
 * @author Juliano
 */
public class DAOFactory {
    public static VendedorDAO criarVendedorDAO(){
       return new VendedorJDBC(DB.getConnection());

    }
    // programa não conhece a implementação apenas a interface realizar injeção de dependencia, não expoe a implementação do projeto
}
