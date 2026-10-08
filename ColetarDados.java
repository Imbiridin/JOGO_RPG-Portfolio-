import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ColetarDados {

    public static void main(String[] args) throws Exception {
        String nomePersonagem = JOptionPane.showInputDialog("Digite o nome do personagem:");
        if (nomePersonagem == null || nomePersonagem.trim().isEmpty()) {
            return; 
        }

        String[] categorias = {"Inteligencia", "Força", "Velocidade", "Resistencia", "Habilidade"};
        String sql = "INSERT INTO Personagem (nome, categoria, valor) VALUES (?, ?, ?)";
        StringBuilder valores = new StringBuilder();

        try (Connection conexao = Conexao.conectar("teste");
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            for (String categoria : categorias) {
                String valorTexto = JOptionPane.showInputDialog("Digite o valor para " + categoria + ":");
                if (valorTexto == null) {
                    return;
                }
                int valor = Integer.parseInt(valorTexto.trim());

                stmt.setString(1, nomePersonagem);
                stmt.setString(2, categoria);
                stmt.setInt(3, valor);
                stmt.executeUpdate();

                valores.append(valor).append(",");
            }

            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite apenas números inteiros.");
            return;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + e.getMessage());
            e.printStackTrace();
            return; 
        }

    
        String valoresFinal = valores.substring(0, valores.length() - 1);

        ProcessBuilder pb = new ProcessBuilder("python", "grafico.py", valoresFinal);
        pb.inheritIO();
        Process processo = pb.start();
        processo.waitFor();

        JOptionPane.showMessageDialog(null, "Valores coletados e gráfico gerado com sucesso!");
    }
}