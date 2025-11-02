/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacao;

import db.DB;
import java.util.Date;
import java.util.List;
import model.DAOFactory;
import model.dao.VendedorDAO;
import model.entidades.Departamentos;
import model.entidades.Vendedor;

/**
 *
 * @author Juliano
 */
public class Programa {

    public static void main(String args[]) {
        System.out.println("===========TESTE01 encontrar vendedor pelo id=====");
        VendedorDAO vendedorDao = DAOFactory.criarVendedorDAO();

        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

        System.out.println("===========TESTE02 VENDEDOR PELO ID DO DEPARTAMENTO=====");
        Departamentos departamentos = new Departamentos(2, null);
        List<Vendedor> list = vendedorDao.findByDepartment(departamentos);

        for (Vendedor vendedor02 : list) {
            System.out.println(vendedor02);
        }

        System.out.println("===========TESTE03 retornando todos=====");

        List<Vendedor> listvendedor02 = vendedorDao.findAll();

        for (Vendedor vendedor02 : listvendedor02) {
            System.out.println(vendedor02);
        }

        System.out.println("==========TESTE04 Realizando insercao de dados===========");
        Vendedor vendedor05 = new Vendedor(null, "Greg", "Gregsao@gmail.com", new Date(), 4000.0, departamentos);
        vendedorDao.insert(vendedor05);
        System.out.println("Inserção realizada com sucesso: " + vendedor05);

        System.out.println("==========TESTE05 Atualizando Dados===========");
        Vendedor vendedor06 = vendedorDao.findById(3);
        vendedor06.setNome("Juliano Alessandro dos Santos");
        vendedorDao.update(vendedor06);
        System.out.println("Dados atualizados veja o resultado abaixo:");
        System.out.println(vendedorDao.findById(3));

    }
}
