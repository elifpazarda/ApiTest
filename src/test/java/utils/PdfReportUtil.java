package utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfReportUtil {

    public static void convertHtmlToPdf(String htmlPath, String pdfPath) {
        try {
            String html = new String(Files.readAllBytes(Paths.get(htmlPath)), StandardCharsets.UTF_8);


            OutputStream os = new FileOutputStream(pdfPath);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, new File(htmlPath).getParentFile().toURI().toString());
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
