package HW5.Connection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SingleConnection {
    private final Socket socket;
    private final Thread thread;
    private final ConnectionAction action;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final Charset encoding = StandardCharsets.UTF_8;

    public SingleConnection(ConnectionAction action, String ipAddress, int port) throws IOException {
            this(action, new Socket(ipAddress, port));
    }
    public SingleConnection(ConnectionAction action, Socket socket) throws IOException {
        this.action = action;
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding));

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    action.ready(SingleConnection.this);
                    while (!thread.isInterrupted()) {
                        String msg = in.readLine();
                        action.receiveString(SingleConnection.this, msg);
                    }
                } catch (IOException e) {
                    action.exception(SingleConnection.this, e);
                } finally {
                    action.disconnect(SingleConnection.this);
                }
            }
        });
        thread.start();
    }

    public synchronized void sendMessage(String msg) {
        try {
            out.write(msg + "\r\n");
            out.flush();
        } catch (IOException e) {
            action.exception(SingleConnection.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect() {
        thread.isInterrupted();
        try {
            socket.close();
        } catch (IOException e) {
            action.exception(SingleConnection.this, e);
        }
    }

    @Override
    public String toString() {
        return String.format("Connection: %s %s %s", socket.getLocalAddress(), socket.getInetAddress(), socket.getLocalPort());
    }

//    public Socket getSocket() {
//        return socket;
//    }
}



