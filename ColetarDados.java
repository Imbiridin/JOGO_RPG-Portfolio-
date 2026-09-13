
import javax.swing.JOptionPane;

public class ColetarDados {
    public static void main(String[]args) throws Exception {
        String[] ccategorias = {"Inteligencia", "Força", "Velocidade", "Resistencia", "Habilidade"};

        StringBuilder valores = new StringBuilder();

        for (String categoria : ccategorias) {
            String valor = JOptionPane.showInputDialog("Digite o valor para " + categoria + ":");
            valores.append(valor).append(",");
        }

        String valoresFinal = valores.substring(0, valores.length() - 1); // Remove the last newline character

        ProcessBuilder pb = new ProcessBuilder("python3", "grafico.py", valoresFinal);
        pb.inheritIO();
        Process processo = pb.start();
        processo.waitFor();

        JOptionPane.showMessageDialog(null, "Valores coletados e gráfico gerado com sucesso!");

    }   
}
