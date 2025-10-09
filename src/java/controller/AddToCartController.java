///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
///**
// *
// * @author admin
// */
//@WebServlet("/add-to-cart")
//public class AddToCartController extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        int productId = Integer.parseInt(request.getParameter("id"));
//        HttpSession session = request.getSession();
//        // Lấy giỏ hàng từ session (tự implement giỏ hàng là 1 List hoặc Map)
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new Cart();
//        }
//        cart.add(productId); // Tự xử lý logic thêm sản phẩm vào giỏ
//        session.setAttribute("cart", cart);
//        response.setContentType("application/json");
//        response.getWriter().write("{\"success\":true}");
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        doPost(request, response);
//    }
//}