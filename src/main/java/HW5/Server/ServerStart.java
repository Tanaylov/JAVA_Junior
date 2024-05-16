package HW5.Server;

import HW5.Connection.ConnectionAction;
import HW5.Connection.SingleConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerStart implements ConnectionAction {
    public static void main(String[] args) {
        new ServerStart();
    }

    private final ArrayList<SingleConnection> activeConnections = new ArrayList<>(1000);
    private ServerStart() {
        System.out.println("Server running...");
        try (ServerSocket ss = new ServerSocket(8888)){
            while (true) {
                try {
                    new SingleConnection(this, ss.accept());
                } catch (IOException e) {
                    System.out.println("Connection exception: " + ss.accept().getInetAddress() + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void ready(SingleConnection connection) {
        activeConnections.add(connection);
        messageToAllConnections("New connect: \n" + connection);
    }

    @Override
    public synchronized void receiveString(SingleConnection connection, String message) {
        messageToAllConnections(message);
    }

    @Override
    public synchronized void disconnect(SingleConnection connection) {
        activeConnections.remove(connection);
        messageToAllConnections("Disconnect: \n" + connection);
    }

    @Override
    public synchronized void exception(SingleConnection connection, Exception e) {
        System.out.println(connection + e.getMessage());
    }

    private void messageToAllConnections(String msg) {
        System.out.println(msg);
        activeConnections.forEach(el -> el.sendMessage(msg));

    }
}
