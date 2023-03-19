//package com.market.service.order;
//
//import com.market.entity.order.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//
//@Service
//public class InvoiceService {
//
////    public List<String> createPdfInvoice(Long orderId, Order order) throws IOException {
//
//
//
//
////        private String parseThymeleafTemplate () {
////            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
////            templateResolver.setSuffix(".html");
////            templateResolver.setTemplateMode(TemplateMode.HTML);
////
////            TemplateEngine templateEngine = new TemplateEngine();
////            templateEngine.setTemplateResolver(templateResolver);
////
////            Context context = new Context();
////            context.setVariable("to", "Baeldung");
////
////            return templateEngine.process("invoice", context);
////        }
//    }
////}
////        try {
////            PdfWriter writer = new PdfWriter(billPdf);
////            PdfDocument pdfDocument = new PdfDocument(writer);
////            // create document object
////            Document document = new Document(pdfDocument);
////
////
////            // Table
////            Table table = new Table()
////
////            document.close();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        }
////
////
////
////        List<String> list = new ArrayList<>();
////        list.add(billPdf);
////        list.add(orderId + ".pdf");
////        return list;
//////
////    }
////
////}
//


package com.market.service.order;

import com.market.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    public File generatePdf(Order order) throws Exception{
        String outputFolder = "C:/JAVA/03_DataBase_SpringBoot/SpringBoot/PDF";
        Context context = getContext(order);
        String html = loadAndFillTemplate(context);
        System.out.println(html);
        return renderPdf(html, outputFolder);
    }

    private File renderPdf(String html, String outputFolder) throws Exception {
        String outputFile = outputFolder + File.separator + "invoice.pdf";
        OutputStream outputStream = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource("static").getURL().toString());
//        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        return new File(outputFile);
    }

    private Context getContext(Order order) {
        Context context = new Context();
        context.setVariable("order", order);
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("invoice", context);
    }
}
