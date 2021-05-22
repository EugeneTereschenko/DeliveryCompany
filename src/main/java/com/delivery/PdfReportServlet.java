package com.delivery;

import com.delivery.db.CartDAO;
import com.delivery.db.UserDAO;
import com.delivery.entity.Cart;
import com.delivery.entity.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@WebServlet(name = "PdfReportServlet")
public class PdfReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();


        switch (action) {
         /*   case "/settings":
                try {
                    settings(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;*/
            case "/createpdfdoc":
                createpdfdoc(request, response);
                break;
            default:
                break;
        }

    }

    private void createpdfdoc(HttpServletRequest request, HttpServletResponse response) {


      //  String userid = request.getParameter("userid");
        String cartid = request.getParameter("cartid");

        response.setContentType("application/pdf");
        try {
            Cart cart = new Cart();
            CartDAO cartDAO = new CartDAO();
            cart = cartDAO.checkCartByStep(Integer.parseInt(cartid), "complete");

            User user = new User();
            UserDAO userDAO = new UserDAO();
            user = userDAO.checkUserbyId(cart.getUser_id());

            cart = cartDAO.checkCartById(Integer.parseInt(cartid));


            Document doc = new Document();
            PdfWriter.getInstance(doc, response.getOutputStream());
            // PdfWriter.getInstance(doc, response.set)
            doc.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph paragraph = new Paragraph(user.getEmail());

            PdfPTable table = new PdfPTable(2);
            Stream.of("CityFrom", "CityTo").forEach(table::addCell);


            PdfPCell pdfPCell5;
            PdfPCell pdfPCell6;

            pdfPCell5 = new PdfPCell(new Paragraph(cart.getCityFrom()));
            pdfPCell6 = new PdfPCell(new Paragraph(cart.getCityTo()));
            table.addCell(pdfPCell5);
            table.addCell(pdfPCell6);


            Stream.of("Distance", "Price").forEach(table::addCell);
            Stream.of(Integer.toString(cart.getDistance()) + "km", cart.getTotal_price() + "$").forEach(table::addCell);

            paragraph.add(table);
            doc.add(paragraph);

            doc.close();

        } catch (ClassNotFoundException | DocumentException | IOException e) {
            e.printStackTrace();
        }



    }
}
