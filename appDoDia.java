import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class appDoDia extends JFrame {
    private JTextField tarefaField;
    private JComboBox<String> diasSemanaComboBox;
    private JTextField dataField;
    private JTextField horaField;
    private JTextArea tarefasTextArea;

    public appDoDia() {
        super("AGENDA DO MATHEUS MENDES");

        // Configuração do frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        // Componentes
        JPanel entradaPanel = new JPanel(new GridLayout(5, 2));
        JLabel tarefaLabel = new JLabel("Tarefa:");
        tarefaField = new JTextField();
        JLabel diasSemanaLabel = new JLabel("Dia da semana:");
        diasSemanaComboBox = new JComboBox<>(new String[]{"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"});
        JLabel dataLabel = new JLabel("Data (dd/mm/yyyy):");
        dataField = new JTextField();
        JLabel horaLabel = new JLabel("Hora (HH:mm):");
        horaField = new JTextField();
        JButton adicionarButton = new JButton("ADICIONAR TAREFAS");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa();
            }
        });

        // Adiciona componentes ao painel de entrada
        entradaPanel.add(tarefaLabel);
        entradaPanel.add(tarefaField);
        entradaPanel.add(diasSemanaLabel);
        entradaPanel.add(diasSemanaComboBox);
        entradaPanel.add(dataLabel);
        entradaPanel.add(dataField);
        entradaPanel.add(horaLabel);
        entradaPanel.add(horaField);
        entradaPanel.add(adicionarButton);

        // Área para exibir tarefas
        tarefasTextArea = new JTextArea();
        tarefasTextArea.setEditable(false);

        // Adiciona componentes ao frame
        add(entradaPanel, BorderLayout.NORTH);
        add(new JScrollPane(tarefasTextArea), BorderLayout.CENTER);

        // Exibe o frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void adicionarTarefa() {
        String tarefa = tarefaField.getText();
        String diaSemana = (String) diasSemanaComboBox.getSelectedItem();
        String data = dataField.getText();
        String hora = horaField.getText();

        // Validação básica
        if (tarefa.isEmpty() || data.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Formatação da data atual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHoraAtual = dateFormat.format(new Date());

        // Construção da string da tarefa
        String novaTarefa = String.format("[%s] %s - %s em %s\n", diaSemana, tarefa, data, hora);
        novaTarefa += "Adicionado em: " + dataHoraAtual;

        // Adiciona a nova tarefa à área de exibição
        tarefasTextArea.append(novaTarefa + "\n");

        // Limpa os campos de entrada
        tarefaField.setText("");
        dataField.setText("");
        horaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new appDoDia();
            }
        });
    }
}

