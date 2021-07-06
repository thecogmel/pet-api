package ufrn.tads.erick.petapi.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ufrn.tads.erick.petapi.DAO.ProdutoDAO;
import ufrn.tads.erick.petapi.model.Produto;


@RestController
public class ClienteController {
    List<Produto> carrinho = new ArrayList<>();

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
            response.getWriter().println("<th> <a href=\"adicionarCarrinho?id=" + pdao.listarProdutos().get(i).getId()
                    + " \" >Adicionar<a/></th>");
            response.getWriter().println("</tr>");
            i++;
        }

        response.getWriter().println("</table>");
        response.getWriter().println("<a href=\"/verCarrinho\">Ver carrinho</a>");
    }

    @GetMapping(value = "/adicionarCarrinho")
    public void getAdicionarCarrinho(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession s = request.getSession();
        if (request.getParameter("id") != null) {

            int id = Integer.parseInt(request.getParameter("id"));
            ProdutoDAO pdao = new ProdutoDAO();

            for (Produto p : pdao.listarProdutosPorId(id)) {
                carrinho.add(p);
            }
            for (Produto p : carrinho) {
                response.getWriter().println(p.getId() + " - " + p.getNome());
            }
        }
        s.setAttribute("carrinho", carrinho);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/cliente");
        encaminhar.forward(request, response);
    }

    @GetMapping(value="/verCarrinho")
    public void getVerCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession();
        List<Produto> carrinhoSession = (List<Produto>) s.getAttribute("carrinho");
        response.getWriter().println("<html>");
        response.getWriter().println("<body>");
        if (carrinhoSession != null) {
            // JSONObject myObject = new JSONObject(result);
            for (Produto p : carrinhoSession) {
                response.getWriter().println(p.getId() + " - " + p.getNome());
                response.getWriter().println("<br/>");
            }
        } else {
            response.sendRedirect("/cliente");
        }
        response.getWriter().println("<a href=\"/finalizarCompra\">Finalizar Compra</a>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
    
    @GetMapping(value="/finalizarCompra")
    public void getFinish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession(false);
        if (s != null) {
            s.invalidate();
            response.sendRedirect("index.html");
        }
    }
    
}
