import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private IResponseGenerator responseGenerator;

    public ClientHandler(Socket clientSocket, IResponseGenerator responseGenerator) {
        this.clientSocket = clientSocket;
        this.responseGenerator = responseGenerator;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String requestLine = in.readLine();
            if (requestLine == null) return;
            System.out.println("Yeni İstek Geldi: " + requestLine);

            String htmlContent = responseGenerator.generateHtml();

            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: " + htmlContent.getBytes("UTF-8").length + "\r\n" +
                    "Connection: close\r\n\r\n" +
                    htmlContent;

            out.print(httpResponse);
            out.flush();

        } catch (IOException e) {
            System.err.println("İstemci ile iletişimde hata: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Soket kapatılamadı: " + e.getMessage());
            }
        }
    }
}