import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServer {
    public static void main(String[] args) {
        int port = 1989;

        String myName = "Esmanur Uzun";
        String myStudentNo = "1250505049";
        String myBio = "Merhaba, ben Esma. Kırklareli Üniversitesi Yazılım Mühendisliği 2. sınıf öğrencisiyim. " +
                "Yapay zeka, bilgisayarlı görü ve otonom sistemler üzerine çalışmalar yapıyorum. " +
                "Şu anda TEKNOFEST Havacılıkta Yapay Zeka takımı ile projeler geliştiriyor, " +
                "gelecekte savunma sanayisinde ülkemiz için kritik yazılım mimarileri inşa etmeyi hedefliyorum.";

        IResponseGenerator profileGenerator = new StudentProfileGenerator(myName, myStudentNo, myBio);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Web sunucusu başlatıldı. Tarayıcınızdan http://localhost:" + port + " adresine gidebilirsiniz.");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(clientSocket, profileGenerator);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Sunucu " + port + " portunda başlatılamadı: " + e.getMessage());
        }
    }
}