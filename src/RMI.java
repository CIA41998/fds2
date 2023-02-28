interface RemoteBuffer extends java.rmi.Remote {
    public StringBuffer buffer() throws java.rmi.RemoteException; public void append_R( RemoteBuffer r) throws java.rmi.RemoteException; public void append_S( StringBuffer s) throws java.rmi.RemoteException;
}
class StringServer extends java.rmi.server.UnicastRemoteObject implements RemoteBuffer {
    private StringBuffer buffer;
    public StringServer(String s) throws java.rmi.RemoteException { super();
        this.buffer = new StringBuffer(s); }
    // ----------------------- IMPLEMENTING THE INTERFACE:  ---------------------------
    public StringBuffer buffer() { return this.buffer;
    }
    public void append_R( RemoteBuffer r) throws java.rmi.RemoteException {
        r.append_S( new StringBuffer("R"));
        this.buffer.append(r.buffer()); }
    public void append_S( StringBuffer s) throws java.rmi.RemoteException { s.append("S");
        this.buffer.append(s);
    }
    // ----------------------------- MAIN METHOD:  ------------------------------------
    public static void main(String[] args) throws Exception { StringServer server1 = new StringServer("A"); StringServer server2 = new StringServer("B");
        java.rmi.Naming.bind("rmi://localhost:1000/MyBuffer", server1); RemoteBuffer rb
                = (RemoteBuffer) java.rmi.Naming.lookup("rmi://localhost:1000/MyBuffer");
        // How are the parameters passed for the following RMI-calls?
        rb.append_S(server2.buffer); System.out.println("1: " + server2.buffer);
        rb.append_R(server2); System.out.println("2: " + server2.buffer); System.out.println("3: " + rb.buffer());
    }
}

