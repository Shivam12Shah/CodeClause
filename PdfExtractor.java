import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfExtractor {

    public static void main(String[] args) throws IOException {
        String inputFilePath = "S:/unit4.pdf";
        String outputFilePath = "S:/java/Extracted.pdf";
        int startPage = 4; // starting page number
        int endPage = 8; // ending page number

        // Load the input PDF document
        PDDocument document = PDDocument.load(new File(inputFilePath));

        // Create a splitter object to split the document
        Splitter splitter = new Splitter();

        // Set the start and end page numbers for the splitter
        splitter.setStartPage(startPage);
        splitter.setEndPage(endPage);

        // Split the document using the splitter
        java.util.List<PDDocument> pages = splitter.split(document);

        // Save the extracted pages as a separate PDF document
        PDDocument outputDocument = new PDDocument();
        for (PDDocument page : pages) {
            outputDocument.addPage(page.getPage(0));
        }
        outputDocument.save(outputFilePath);
        outputDocument.close();

        // Close the input document
        document.close();

        System.out.println("PDF pages extracted successfully.");
    }
}
