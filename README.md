#  Java Simple Socket Web Server

Bu proje, hiçbir üçüncü parti kütüphane veya framework (Spring Boot, Apache Tomcat vb.) bağımlılığı olmaksızın, tamamen Java'nın çekirdek (core) kütüphaneleri (`java.net` ve `java.io`) kullanılarak sıfırdan inşa edilmiş bir HTTP Web Sunucusu uygulamasıdır. 

Sistem, gelen HTTP `GET` isteklerini karşılayarak istemciye (web tarayıcısına) dinamik olarak oluşturulmuş, HTML ve CSS ile biçimlendirilmiş bir dijital profil sayfası sunar.

---

##  Öne Çıkan Özellikler

*   ** Bağımlılıksız Mimari:** Yalnızca saf Java (Pure Java) kullanılmıştır.
*   ** Çoklu İş Parçacığı (Multithreading):** Aynı anda gelen birden fazla tarayıcı isteğine yanıt verebilmek için her soket bağlantısı ayrı bir `Thread` üzerinde asenkron olarak işlenir.
*   **Özelleştirilmiş Port Dinleme:** Sunucu, standart HTTP portu (80) yerine özel olarak yapılandırılmış `1989` portu üzerinden TCP bağlantılarını dinler.
*   ** Dinamik HTML Üretimi:** HTTP 1.1 standartlarına uygun Header (Başlık) ve Body (Gövde) inşa edilerek, stilize edilmiş duyarlı bir web sayfası döndürülür.

---

## Mimari ve SOLID Prensipleri

Projenin sürdürülebilirliği ve ölçeklenebilirliği için Nesne Yönelimli Programlama (OOP) ve **SOLID** tasarım prensipleri katı bir şekilde uygulanmıştır:

*   **[S] Single Responsibility Principle (Tek Sorumluluk):** 
    *   Sistem modüllere ayrılmıştır. `SimpleWebServer` sadece bağlantıları dinlemekle, `ClientHandler` yalnızca istemci ile olan HTTP iletişimini yönetmekle, `StudentProfileGenerator` ise sadece HTML yapısını oluşturmakla sorumludur.
*   **[O] Open/Closed Principle (Açık/Kapalı Prensibi):** 
    *   Uygulama değişime kapalı, gelişime açıktır. Yeni bir HTML sayfası (örneğin İletişim Sayfası) eklenmek istendiğinde mevcut kodlar değiştirilmez, sadece `IResponseGenerator` arayüzünden yeni bir sınıf türetilir.
*   **[L] Liskov Substitution Principle (Liskov Yerine Geçme):** 
    *   `IResponseGenerator` arayüzünü uygulayan tüm alt sınıflar (`StudentProfileGenerator`), sistemin hiçbir yerinde çökmeye sebep olmadan birbirinin yerine kullanılabilir.
*   **[D] Dependency Inversion Principle (Bağımlılıkların Tersine Çevrilmesi):** 
    *   Üst seviye modül olan `ClientHandler`, alt seviye modül olan HTML üretici sınıfa doğrudan değil, `IResponseGenerator` isimli soyut bir arayüz (interface) üzerinden bağlıdır. Bu sayede modüller arası sıkı bağ (tight coupling) engellenmiştir.

---

##  Proje Dosya Yapısı

*   `SimpleWebServer.java`: Uygulamanın giriş noktasıdır (Main). Belirtilen portta (1989) bir `ServerSocket` oluşturur ve sonsuz bir döngüde istemci bağlantılarını bekler.
*   `ClientHandler.java`: `Runnable` arayüzünü uygular. Tarayıcıdan gelen HTTP başlıklarını `BufferedReader` ile okur ve `PrintWriter` kullanarak oluşturulan HTML içeriğini HTTP Response formatında ağa yazar.
*   `IResponseGenerator.java`: İçerik oluşturucu sınıfların uyguladığı sözleşmedir (Interface).
*   `StudentProfileGenerator.java`: Öğrencinin ad, soyad, numara ve biyografi verilerini alarak, uygun renk ve font ailesiyle donatılmış `<!DOCTYPE html>` formatındaki yapıyı metin (String) olarak döndürür.

---

##  Kurulum ve Çalıştırma

1. **Projeyi Bilgisayarınıza İndirin:**
   ```bash
   git clone [https://github.com/KULLANICI_ADIN/SocketWebServer.git](https://github.com/KULLANICI_ADIN/SocketWebServer.git)
