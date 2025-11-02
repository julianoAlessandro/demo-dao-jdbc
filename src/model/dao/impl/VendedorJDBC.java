/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import model.dao.VendedorDAO;
import model.entidades.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entidades.Departamentos;
import java.sql.Statement;

/**
 *
 * @author Juliano
 */
public class VendedorJDBC implements VendedorDAO {

    private Connection conn;

    public VendedorJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insert(Vendedor vendedor) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stm.setString(1, vendedor.getNome());
            stm.setString(2, vendedor.getEmail());
            stm.setDate(3, new java.sql.Date(vendedor.getDataAniversario().getTime()));
            stm.setDouble(4, vendedor.getSalarioBase());
            stm.setInt(5, vendedor.getDepartamentos().getId());

            int linhasAfetadas = stm.executeUpdate();

            if (linhasAfetadas > 0) {
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    vendedor.setId(id);
                }
            } else {
                System.out.println("Nenhuma linha afetada!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
    }

    @Override
    public void update(Vendedor vendedor) {

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(
                    "UPDATE seller\n"
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?\n"
                    + "WHERE Id = ?"
            );

            stm.setString(1, vendedor.getNome());
            stm.setString(2, vendedor.getEmail());
            stm.setDate(3, new java.sql.Date(vendedor.getDataAniversario().getTime()));
            stm.setDouble(4, vendedor.getSalarioBase());
            stm.setInt(5, vendedor.getDepartamentos().getId());
            stm.setInt(6, vendedor.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {

            DB.closeStatement(stm);
        }
    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement stm = null;
      

        try {
            stm = conn.prepareStatement(
                    "DELETE FROM seller\n"
                    + "WHERE Id = ?"
            );

 
            stm.setInt(1,id);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {

            DB.closeStatement(stm);
        }
    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
            );
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                Departamentos dep = new Departamentos();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setNome(rs.getString("DepName"));

                Vendedor ved = new Vendedor();
                ved.setId(rs.getInt("id"));
                ved.setNome(rs.getString("Name"));
                ved.setEmail(rs.getString("Email"));
                ved.setDataAniversario(rs.getDate("BirthDate"));
                ved.setSalarioBase(rs.getDouble("BaseSalary"));
                ved.setDepartamentos(dep);

                return ved;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // DB.closeConnection();
            // DB.closeResultSet(rs);
        }

        return null;

    }

    @Override

    public List<Vendedor> findAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(
                    "SELECT seller.*, department.Name AS DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name"
            );

            rs = stm.executeQuery();

            List<Vendedor> listVendedores = new ArrayList<>();
            Map<Integer, Departamentos> map = new HashMap<>();

            while (rs.next()) {

                Departamentos dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = new Departamentos();
                    dep.setId(rs.getInt("DepartmentId"));
                    dep.setNome(rs.getString("DepName"));
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor ved = new Vendedor();
                ved.setId(rs.getInt("Id"));
                ved.setNome(rs.getString("Name"));
                ved.setEmail(rs.getString("Email"));
                ved.setDataAniversario(rs.getDate("BirthDate"));
                ved.setSalarioBase(rs.getDouble("BaseSalary"));
                ved.setDepartamentos(dep);

                listVendedores.add(ved);
            }

            return listVendedores; // <-- o retorno vai aqui, depois do loop

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(stm);
            DB.closeResultSet(rs);
            // NÃO fecha a conexão aqui!
        }
    }

    @Override
    public List<Vendedor> findByDepartment(Departamentos departamentos
    ) {
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(
                    "SELECT seller.*, department.Name AS DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name"
            );

            stm.setInt(1, departamentos.getId());
            rs = stm.executeQuery();

            List<Vendedor> listVendedores = new ArrayList<>();
            Map<Integer, Departamentos> map = new HashMap<>();

            while (rs.next()) {
                Departamentos dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = new Departamentos();
                    dep.setId(rs.getInt("DepartmentId"));
                    dep.setNome(rs.getString("DepName"));
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Vendedor ved = new Vendedor();
                ved.setId(rs.getInt("Id"));
                ved.setNome(rs.getString("Name"));
                ved.setEmail(rs.getString("Email"));
                ved.setDataAniversario(rs.getDate("BirthDate"));
                ved.setSalarioBase(rs.getDouble("BaseSalary"));
                ved.setDepartamentos(dep);

                listVendedores.add(ved);
            }

            return listVendedores;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DB.closeStatement(stm);
            DB.closeResultSet(rs);
        }
    }

}
