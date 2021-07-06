package ufrn.tads.erick.petapi.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ufrn.tads.erick.petapi.model.Produto;
import ufrn.tads.erick.petapi.controllers.ConectaBanco;

public class ProdutoDAO {
    public List<Produto> listarProdutos() {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> listaDeProdutos = new ArrayList<>();

        try {
            connection = ConectaBanco.getConnection();

            stmt = connection.prepareStatement("select * from produto");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setRaca(rs.getString("raca"));
                p.setPreco(rs.getFloat("preco"));
                p.setEspecie(rs.getString("especie"));
                p.setMarca(rs.getString("marca"));
                p.setCategoria(rs.getString("categoria"));

                listaDeProdutos.add(p);

            }

            connection.close();
        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }

        return listaDeProdutos;
    }

    public List<Produto> listarProdutosPorId(int id) {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> listaDeProdutos = new ArrayList<>();

        try {
            connection = ConectaBanco.getConnection();

            stmt = connection.prepareStatement("select * from produto where id=?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setRaca(rs.getString("raca"));
                p.setPreco(rs.getFloat("preco"));
                p.setEspecie(rs.getString("especie"));
                p.setMarca(rs.getString("marca"));
                p.setCategoria(rs.getString("categoria"));

                listaDeProdutos.add(p);

            }

            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            //response.getWriter().append("Connection Failed! Check output console");
        }

        return listaDeProdutos;
    }

    public void cadastrarProduto(Produto p) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ConectaBanco.getConnection();

            stmt = connection.prepareStatement(
                    "insert into produto (nome, raca, preco, especie, marca, categoria) values (?,?,?,?,?,?)");

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getRaca());
            stmt.setFloat(3, p.getPreco());
            stmt.setString(4, p.getEspecie());
            stmt.setString(5, p.getMarca());
            stmt.setString(6, p.getCategoria());

            stmt.executeUpdate();

            connection.close();

        } catch (SQLException | URISyntaxException ex) {
            // response.getWriter().append("Connection Failed! Check output console");
        }

    }
}
