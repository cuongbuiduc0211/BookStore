/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author USER
 */
public class StudentDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from Student Where Username = ? and Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<StudentDTO> listAccounts;

    public List<StudentDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Select * from Student Where Lastname like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("Username"); //tên column db
                    String password = rs.getString("Password");
                    String lastname = rs.getString("Lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    StudentDTO dto = new StudentDTO(username, password, lastname, role);
                    if (listAccounts == null) {
                        listAccounts = new ArrayList<StudentDTO>();
                    }
                    listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String pk)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "delete from Student where Username = ?";
                stm = con.prepareStatement(sql); 
                stm.setString(1, pk);
                int row = stm.executeUpdate(); // thêm xóa sửa db trả về 1 con số int
                if (row > 0) { //nếu hiệu lực thì > 0, ko hiệu lực thì = 0
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean updateAccount(String username, String password, boolean role) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "update Student set Password = ?, isAdmin= ? where Username = ?";
                stm = con.prepareStatement(sql); 
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row = stm.executeUpdate(); // thêm xóa sửa db trả về 1 con số int
                if (row > 0) { //nếu hiệu lực thì > 0, ko hiệu lực thì = 0
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean createNewAccount(String username, String password, String lastname, boolean role) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "insert into Student(Username, Password, Lastname, isAdmin) "
                        + "values(?,?,?,?)";
                stm = con.prepareStatement(sql); 
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate(); // thêm xóa sửa db trả về 1 con số int
                if (row > 0) { //nếu hiệu lực thì > 0, ko hiệu lực thì = 0
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
        
    }
}
