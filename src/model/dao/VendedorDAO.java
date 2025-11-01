package model.dao;


import java.util.List;
import model.entidades.Vendedor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Juliano
 */
public interface VendedorDAO {
     void insert(Vendedor vendedor);
    void update(Vendedor vendedor);
    void deleteById(Integer id);
    Vendedor findById(Integer id);
    List<Vendedor> findAll();
    
    
}
