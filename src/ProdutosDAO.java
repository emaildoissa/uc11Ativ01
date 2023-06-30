/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        //conn = new conectaDAO().connectDB();
        try {
            // Estabelecer conexão com o banco de dados
            Connection conn = new conectaDAO().connectDB();

            // Preparar a instrução SQL para inserir o produto no banco de dados
            String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Definir os valores dos parâmetros na instrução SQL
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());

            // Executar a instrução SQL para inserir o produto no banco de dados
            int linhasModificadas = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (linhasModificadas > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar o produto.");
            }

            // Fechar a conexão com o banco de dados
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            // Estabelecer conexão com o banco de dados
            conn = new conectaDAO().connectDB();

            // Preparar a instrução SQL para selecionar os produtos
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);

            // Executar a consulta SQL e obter o resultado
            resultset = prep.executeQuery();

            // Limpar a lista de produtos
            listagem.clear();

            // Percorrer o resultado e criar objetos ProdutosDTO para cada linha retornada
            while (resultset.next()) {
                int id = resultset.getInt("Id");
                String nome = resultset.getString("nome");
                Integer valor = resultset.getInt("valor");
                String status = resultset.getString("Status");

                // Criar um objeto ProdutosDTO com os dados obtidos
                ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);

                // Adicionar o produto à lista
                listagem.add(produto);
            }

            // Fechar a conexão com o banco de dados
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos: " + e.getMessage());
        }

        return listagem;
    }

    public void venderProduto(int id) {
        try {

            conn = new conectaDAO().connectDB();

            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);

            prep.setInt(1, id);

            int linhasModificadas = prep.executeUpdate();

            // Verificar se a atualização foi bem-sucedida
            if (linhasModificadas > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao vender o produto.");
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + e.getMessage());
        }
    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    try {
        // Estabelecer conexão com o banco de dados
        conn = new conectaDAO().connectDB();

        // Preparar a instrução SQL para selecionar os produtos com status "Vendido"
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        prep = conn.prepareStatement(sql);

        // Executar a consulta SQL e obter o resultado
        resultset = prep.executeQuery();

        // Limpar a lista de produtos vendidos
        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

        // Percorrer o resultado e criar objetos ProdutosDTO para cada linha retornada
        while (resultset.next()) {
            int id = resultset.getInt("Id");
            String nome = resultset.getString("nome");
            Integer valor = resultset.getInt("valor");
            String status = resultset.getString("Status");

            // Criar um objeto ProdutosDTO com os dados obtidos
            ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);

            // Adicionar o produto à lista de produtos vendidos
            produtosVendidos.add(produto);
        }

        // Fechar a conexão com o banco de dados
        conn.close();

        return produtosVendidos;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar os produtos vendidos: " + e.getMessage());
    }

    return null;
}


}
