import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class DirectoryLister extends JFrame {

    private JButton startButton;
    private JButton quitButton;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public DirectoryLister() {
        setTitle("Directory Lister");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        titleLabel = new JLabel("Directory Lister", SwingConstants.CENTER);

        // Set layout and add components
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseDirectoryAndListFiles();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set professional look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void chooseDirectoryAndListFiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            listFiles(selectedDirectory);
        }
    }

    private void listFiles(File directory) {
        textArea.setText("");
        listFilesRecursive(directory, "");
    }

    private void listFilesRecursive(File file, String indent) {
        if (file.isDirectory()) {
            textArea.append(indent + "Directory: " + file.getName() + "\n");
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    listFilesRecursive(f, indent + "    ");
                }
            }
        } else {
            textArea.append(indent + "File: " + file.getName() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DirectoryLister().setVisible(true);
            }
        });
    }
}
