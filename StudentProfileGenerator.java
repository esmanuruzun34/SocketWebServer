public class StudentProfileGenerator implements IResponseGenerator {
    private String name;
    private String studentNo;
    private String bio;

    public StudentProfileGenerator(String name, String studentNo, String bio) {
        this.name = name;
        this.studentNo = studentNo;
        this.bio = bio;
    }

    @Override
    public String generateHtml() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Öğrenci Profili</title>\n" +
                "</head>\n" +
                "<body style=\"background-color: #f0f2f5; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0;\">\n" +
                "    <div style=\"background-color: #ffffff; padding: 40px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); max-width: 600px; text-align: center; border-top: 5px solid #2980b9;\">\n" +
                "        <h1 style=\"color: #2c3e50; margin-bottom: 5px;\">" + this.name + "</h1>\n" +
                "        <h2 style=\"color: #7f8c8d; font-weight: 300; margin-top: 0; border-bottom: 1px solid #eee; padding-bottom: 15px;\">Öğrenci No: " + this.studentNo + "</h2>\n" +
                "        <p style=\"color: #34495e; font-size: 16px; line-height: 1.6; text-align: justify; margin-top: 20px;\">\n" +
                "            " + this.bio + "\n" +
                "        </p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}