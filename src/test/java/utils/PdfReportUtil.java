package utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfReportUtil {

    public static void convertHtmlToPdf(String htmlPath, String pdfPath) {
        try {
            // 1. HTML dosyasını oku
            String html = new String(Files.readAllBytes(Paths.get(htmlPath)), StandardCharsets.UTF_8);

            // 2. HTML içeriğini jsoup ile parse et
            Document doc = Jsoup.parse(html);
            doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);

            // 3. Temizlenmiş HTML’i yeni dosyaya yaz
            String cleanedHtml = doc.html();
            String tempFixedHtmlPath = "test-output/pdf-reports/fixed-report.html";
            Files.write(Paths.get(tempFixedHtmlPath), cleanedHtml.getBytes(StandardCharsets.UTF_8));

            // 4. PDF üret
            OutputStream os = new FileOutputStream(pdfPath);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri(new File(tempFixedHtmlPath).toURI().toString());
            builder.toStream(os);
            builder.run();
            os.close();

            System.out.println(" PDF raporu oluşturuldu: " + pdfPath);
        } catch (Exception e) {
            System.err.println(" PDF oluşturulurken hata: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
