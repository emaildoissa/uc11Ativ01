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
            stmt.executeUpdate();

            // Fechar a conexão com o banco de dados
            conn.close();
        } catch (SQLException e) {

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
