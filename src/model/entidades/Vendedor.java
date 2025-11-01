/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Juliano
 */
public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String email;
    private Date dataAniversario;
    private double salarioBase;
    private Departamentos departamentos;
    
    public Vendedor(){
        
    }

    public Vendedor(int id, String nome, String email, Date dataAniversario, double salarioBase, Departamentos departamentos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataAniversario = dataAniversario;
        this.salarioBase = salarioBase;
        this.departamentos = departamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(Date dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", dataAniversario=" + dataAniversario + ", salarioBase=" + salarioBase + ", departamentos=" + departamentos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.nome);
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.dataAniversario);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.salarioBase) ^ (Double.doubleToLongBits(this.salarioBase) >>> 32));
        hash = 11 * hash + Objects.hashCode(this.departamentos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendedor other = (Vendedor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.salarioBase) != Double.doubleToLongBits(other.salarioBase)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.dataAniversario, other.dataAniversario)) {
            return false;
        }
        return Objects.equals(this.departamentos, other.departamentos);
    }
    
    
    
    
}
