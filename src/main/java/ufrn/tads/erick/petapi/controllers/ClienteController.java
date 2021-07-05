package ufrn.tads.erick.petapi.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ufrn.tads.erick.petapi.DAO.ProdutoDAO;
import ufrn.tads.erick.petapi.model.Produto;

@RestController
public class ClienteController {
    @GetMapping(value = "/cliente")
    public void getCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProdutoDAO pdao = new ProdutoDAO();

        response.getWriter().println("<table>");

        response.getWriter().println("<tr>");
        response.getWriter().println("<th>Nome</th>");
        response.getWriter().println("<th>Raça</th>");
        response.getWriter().println("<th>Preço</th>");
        response.getWriter().println("<th>Espécie</th>");
        response.getWriter().println("<th>Marca</th>");
        response.getWriter().println("<th>Categoria</th>");
        response.getWriter().println("</tr>");

        int i = 0;

        for (Produto p : pdao.listarProdutos()) {
            response.getWriter().println("<tr>");
            response.getWriter().println("<th>" + p.getNome() + "</th>");
            response.getWriter().println("<th>" + p.getRaca() + "</th>");
            response.getWriter().println("<th>" + p.getPreco() + " R$</th>");
            response.getWriter().println("<th>" + p.getEspecie() + "</th>");
            response.getWriter().println("<th>" + p.getMarca() + "</th>");
            response.getWriter().println("<th>" + p.getCategoria() + "</th>");
            response.getWriter().println("<th> <a href=\"adicionarcarrinho?id=" + pdao.listarProdutos().get(i).getId()
                    + " \" >Adicionar<a/></th>");
            response.getWriter().println("</tr>");
            i++;
        }
        response.getWriter().println("</table>");
        response.getWriter().println("<a href=\"/verCarrinho\">Ver carrinho</a>");

    }

    @GetMapping(value = "/adicionarcarrinho")
    public void getAdicionarCarrinho(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            RequestDispatcher encaminhar = request.getRequestDispatcher("/");
            encaminhar.forward(request, response);
        } else {
            response.sendRedirect("/cliente");
        }
    }

}
