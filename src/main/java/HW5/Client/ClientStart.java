package HW5.Client;

import HW5.Connection.ConnectionAction;
import HW5.Connection.SingleConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientStart extends JFrame implements ActionListener, ConnectionAction {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientStart::new);
    }

    private static final String IP = "localhost";
    private static final int PORT = 8888;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private final JTextArea chat = new JTextArea();
    private final JTextField nicknameField = new JTextField("Ivan");
    private final JTextField messageField = new JTextField();
    private SingleConnection connection;

    private ClientStart() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        chat.setEditable(false);
        chat.setLineWrap(true);
        messageField.addActionListener(this);

        add(chat, BorderLayout.CENTER);
        add(nicknameField, BorderLayout.NORTH);
        add(messageField, BorderLayout.SOUTH);

        setVisible(true);
        try {
            connection = new SingleConnection(this, IP, PORT);
        } catch (IOException e) {
            System.out.println(connection + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = messageField.getText();
        if (message.equals("")) return;
        messageField.setText(null);
        connection.sendMessage(nicknameField.getText() + ": " + message);
    }

    @Override
    public void ready(SingleConnection connection) {
        printMessage(connection + ": ready");
    }

    @Override
    public void receiveString(SingleConnection connection, String message) {
        printMessage(message);
    }

    @Override
    public void disconnect(SingleConnection connection) {
        printMessage(connection + ": disconnect");
    }

    @Override
    public void exception(SingleConnection connection, Exception e) {
        printMessage(connection + e.getMessage());
    }

    private synchronized void printMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            chat.append(message + "\n");
            chat.setCaretPosition(chat.getDocument().getLength());
        });
    }
}
