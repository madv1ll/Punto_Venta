/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ProductosDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProducto(Productos pro) {
        String sql = "INSERT INTO productos (codigo, descripcion, proveedor, stock, precio) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setInt(5, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public void ConsultarProveedor(JComboBox proveedor) {
        String sql = "SELECT nombre FROM proveedores";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public List ListarProductos() {
        List<Productos> ListaPro = new ArrayList();
        String sql = "SELECT * FROM productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("descripcion"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getInt("precio"));
                ListaPro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPro;
    }

    public boolean EliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean ModificarProducto(Productos pro) {
        String sql = "UPDATE productos SET codigo=?, descripcion=?, proveedor=?, stock=?, precio=? WHERE id =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setInt(5, pro.getPrecio());
            ps.setInt(6, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public Productos BuscarPro(String cod) {
        Productos producto = new Productos();
        String sql = "Select * from productos WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setNombre(rs.getString("descripcion"));
                producto.setPrecio(rs.getInt("precio"));
                producto.setStock(rs.getInt("stock"));

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
    }

    public Config BuscarDatos() {
        Config conf = new Config();
        String sql = "Select * from config";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setId(rs.getInt("ID"));
                conf.setRut(rs.getInt("RUT"));
                conf.setNombre(rs.getString("Nombre"));
                conf.setTelefono(rs.getInt("Telefono"));
                conf.setDireccion(rs.getString("Direccion"));
                conf.setRazon(rs.getString("Razon"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }

    public boolean ModificarDatos(Config conf) {
        String sql = "UPDATE config SET rut=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, conf.getRut());
            ps.setString(2, conf.getNombre());
            ps.setInt(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());
            ps.setString(5, conf.getRazon());
            ps.setInt(6, conf.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

}
