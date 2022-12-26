/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.duan1.DAO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author asus
 */
public class taogbang {

    public static void main(String args[]) {
        try {
            //Create Document instance.
            Document document = new Document();

            //Create OutputStream instance.
            OutputStream outputStream
                    = new FileOutputStream(new File("D:\\TestTableFile.pdf"));

            //Create PDFWriter instance.
            PdfWriter.getInstance(document, outputStream);

            //Open the document.
            document.open();

            //Create Table object, Here 4 specify the no. of columns
            PdfPTable pdfPTable = new PdfPTable(3);
//            PdfPTable table = new PdfPTable(3);

            //Create cells
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Cell 1"));
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Cell 2"));
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Cell 3"));
//            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Cell 4"));
            //Add cells to table
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            pdfPTable.addCell(pdfPCell3);
//            pdfPTable.addCell(pdfPCell4);

            PdfPCell data1 = new PdfPCell(new Paragraph("Data String"));
//            PdfPCell data2 = new PdfPCell(Image.getInstance("framgia.png"), false);
            PdfPCell data2 = new PdfPCell(new Paragraph("Data String"));
            PdfPTable nestedTable = new PdfPTable(2);
            nestedTable.addCell(new Paragraph("Nested Cell 1"));
            nestedTable.addCell(new Paragraph("Nested Cell 2"));
            PdfPCell data3 = new PdfPCell(nestedTable);

            //Thêm data vào bảng.
            pdfPTable.addCell(data1);
            pdfPTable.addCell(data2);
            pdfPTable.addCell(data3);
//            document.add(table);
            document.add(pdfPTable);

            //Close document and outputStream.
            document.close();
            outputStream.close();

            System.out.println("Pdf created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
