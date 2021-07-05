package ufrn.tads.erick.petapi.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class HelloController {
    @GetMapping
    public void getHello(HttpServletRequest request, HttpServletResponse response) throws IOException {
         // TODO Auto-generated method stub
         String name = request.getParameter("nome");
         if (name != null) {
             response.getWriter().println("Bem vindo a pet-api, " + name + ".");   
         } else {
            response.getWriter().println("Bem vindo a pet-api, anonimo.");
         }
 
         Connection connection = null;
         try {
             connection = ConectaBanco.getConnection();
         } catch (SQLException | URISyntaxException ex) {
             response.getWriter().println("Connection Failed! Check output console");
         }
 
         if (connection != null) {
             response.getWriter().println("A conexao com o banco foi realizada!");
         } else {
             response.getWriter().println("A conexao com o banco falhou!");
         }
 
         try {
             assert connection != null;
             connection.close();
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
    }
}