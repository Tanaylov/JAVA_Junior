package HW5.Connection;

public interface ConnectionAction {
    public void ready(SingleConnection connection);
    public void receiveString(SingleConnection connection, String message);
    public void disconnect(SingleConnection connection);
    public void exception(SingleConnection connection, Exception e);
}
