/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entidades.Departamentos;

/**
 *
 * @author Juliano
 */
public interface DepartamentoDAO {
    void insert(Departamentos departamento);
    void update(Departamentos departamento);
    void deleteById(Integer id);
    Departamentos findById(Integer id);
    List<Departamentos> findAll();
    
    
}
