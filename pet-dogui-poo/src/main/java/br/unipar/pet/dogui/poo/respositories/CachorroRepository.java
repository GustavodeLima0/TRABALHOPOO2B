package br.unipar.pet.dogui.poo.respositories;


import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CachorroRepository {

    private static final String INSERT = "INSERT INTO cachorro (nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cachorro SET nome = ?, vl_tamanho = ?, st_perfume = ?, dt_nascimento = ?, id_raca = ?, id_pelagem = ?, id_cor = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM cachorro WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor FROM cachorro WHERE id = ?";
    private static final String FIND_ALL = "SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor FROM cachorro";

    public Cachorro insert(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cachorro.getNome());
            pstmt.setDouble(2, cachorro.getTamanho());
            pstmt.setBoolean(3, cachorro.isStPerfume());
            pstmt.setDate(4, new java.sql.Date(cachorro.getDtNascimento().getTime()));
            pstmt.setInt(5, cachorro.getRaca().getId());
            pstmt.setInt(6, cachorro.getPelagem().getId());
            pstmt.setInt(7, cachorro.getCor().getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                cachorro.setId(rs.getInt(1));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cachorro;
    }

    public Cachorro update(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, cachorro.getNome());
            pstmt.setDouble(2, cachorro.getTamanho());
            pstmt.setBoolean(3, cachorro.isStPerfume());
            pstmt.setDate(4, new java.sql.Date(cachorro.getDtNascimento().getTime()));
            pstmt.setInt(5, cachorro.getRaca().getId());
            pstmt.setInt(6, cachorro.getPelagem().getId());
            pstmt.setInt(7, cachorro.getCor().getId());
            pstmt.setInt(8, cachorro.getId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cachorro;
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

    public Cachorro findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cachorro cachorro = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                cachorro.setTamanho(rs.getDouble("vl_tamanho"));
                cachorro.setStPerfume(rs.getBoolean("st_perfume"));
                cachorro.setDtNascimento(rs.getDate("dt_nascimento"));
                
                Raca raca = new Raca();
                raca.setId(rs.getInt("id_raca"));
                cachorro.setRaca(raca);
                
                Pelagem pelagem = new Pelagem();
                pelagem.setId(rs.getInt("id_pelagem"));
                cachorro.setPelagem(pelagem);
                
                Cor cor = new Cor();
                cor.setId(rs.getInt("id_cor"));
                cachorro.setCor(cor);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cachorro;
    }

    public ArrayList<Cachorro> findAll() throws SQLException {
        ArrayList<Cachorro> cachorros = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("id"));
                cachorro.setNome(rs.getString("nome"));
                cachorro.setTamanho(rs.getDouble("vl_tamanho"));
                cachorro.setStPerfume(rs.getBoolean("st_perfume"));
                cachorro.setDtNascimento(rs.getDate("dt_nascimento"));
                
                Raca raca = new Raca();
                raca.setId(rs.getInt("id_raca"));
                cachorro.setRaca(raca);
                
                Pelagem pelagem = new Pelagem();
                pelagem.setId(rs.getInt("id_pelagem"));
                cachorro.setPelagem(pelagem);
                
                Cor cor = new Cor();
                cor.setId(rs.getInt("id_cor"));
                cachorro.setCor(cor);

                cachorros.add(cachorro);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return cachorros;
    }
}
