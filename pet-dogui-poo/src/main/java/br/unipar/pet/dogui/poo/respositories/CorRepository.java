package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CorRepository {

    private static final String INSERT = "INSERT INTO cor (ds_cor) VALUES (?)";
    private static final String UPDATE = "UPDATE cor SET ds_cor = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM cor WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT id, ds_cor FROM cor WHERE id = ?";
    private static final String FIND_ALL = "SELECT id, ds_cor FROM cor";

    public Cor insert(Cor cor) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cor.getDescricao());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                cor.setId(rs.getInt(1));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cor;
    }

    public Cor update(Cor cor) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, cor.getDescricao());
            pstmt.setInt(2, cor.getId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cor;
    }

    public void delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(DELETE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public Cor findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cor cor = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cor = new Cor();
                cor.setId(rs.getInt("id"));
                cor.setDescricao(rs.getString("ds_cor"));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cor;
    }

    public ArrayList<Cor> findAll() throws SQLException {
        ArrayList<Cor> cores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Cor cor = new Cor();
                cor.setId(rs.getInt("id"));
                cor.setDescricao(rs.getString("ds_cor"));
                cores.add(cor);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cores;
    }
}
