import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.io.IOException;

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
                int valor = -1;
                boolean valorValido = false;

                while (!valorValido) {
                    String valorTexto = JOptionPane.showInputDialog("Digite o valor para " + categoria + " (0 a 10):");
                    
                    if (valorTexto == null) {
                        return;
                    }
                    
                    try {
                        valor = Integer.parseInt(valorTexto.trim());
                        
                        if (valor >= 0 && valor <= 10) {
                            valorValido = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor, digite um valor entre 0 e 10.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Digite apenas números inteiros.");
                    }
                }

                stmt.setString(1, nomePersonagem);
                stmt.setString(2, categoria);
                stmt.setInt(3, valor);
                stmt.executeUpdate();

                valores.append(valor).append(",");
            }

            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados: " + e.getMessage());
            e.printStackTrace();
            return; 
        }

    
        String valoresFinal = valores.substring(0, valores.length() - 1);

        String pythonCmd = System.getProperty("os.name").toLowerCase().contains("win") ? "python" : "python3";
        ProcessBuilder pb = new ProcessBuilder(pythonCmd, "grafico.py", valoresFinal);
        pb.inheritIO();
        
        try {
            Process processo = pb.start();
            int exitCode = processo.waitFor();
            
            if (exitCode != 0) {
                JOptionPane.showMessageDialog(null, "O gráfico falhou ao gerar. Verifique o console para mais detalhes.");
                return;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar executar o Python: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, "Valores coletados e gráfico gerado com sucesso!");
    }
}