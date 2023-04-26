import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NetworkAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the IP address or hostname to analyze: ");
        String hostname = scanner.nextLine();

        System.out.print("Enter the port number to analyze: ");
        int port = Integer.parseInt(scanner.nextLine());

        try {
            long startTime = System.nanoTime();

            InetAddress ipAddress = InetAddress.getByName(hostname);
            InetSocketAddress remoteEndpoint = new InetSocketAddress(ipAddress, port);

            Socket socket = new Socket();
            socket.connect(remoteEndpoint, 5000);

            long responseTime = System.nanoTime() - startTime;
            long responseTimeMillis = TimeUnit.NANOSECONDS.toMillis(responseTime);

            System.out.println("Connection successful!");
            System.out.println("Remote host IP address: " + ipAddress.getHostAddress());
            System.out.println("Remote host port number: " + port);
            System.out.println("Response time: " + responseTimeMillis + " milliseconds");

            Ping ping = new Ping();
            int pingTime = ping.ping(ipAddress);

            if (pingTime >= 0) {
                System.out.println("Ping successful!");
                System.out.println("Ping time: " + pingTime + " milliseconds");
            } else {
                System.out.println("Ping failed.");
            }

            InetAddress localAddress = socket.getLocalAddress();
            int localPort = socket.getLocalPort();
            String protocol = socket.getSoLinger() == -1 ? "TCP" : "UDP";

            System.out.println("Local endpoint IP address: " + localAddress.getHostAddress());
            System.out.println("Local endpoint port number: " + localPort);
            System.out.println("Socket protocol: " + protocol);

            socket.close();
        } catch (IOException e) {
            System.out.println("Failed to connect to " + hostname + ":" + port + " with error: " + e.getMessage());
        }
    }
}

class Ping {
    public int ping(InetAddress address) {
        try {
            long startTime = System.nanoTime();

            if (address.isReachable(5000)) {
                long pingTime = System.nanoTime() - startTime;
                int pingTimeMillis = (int)TimeUnit.NANOSECONDS.toMillis(pingTime);
                return pingTimeMillis;
            } else {
                return -1;
            }
        } catch (IOException e) {
            return -1;
        }
    }
}
