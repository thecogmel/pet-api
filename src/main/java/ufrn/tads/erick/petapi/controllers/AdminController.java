package ufrn.tads.erick.petapi.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ufrn.tads.erick.petapi.DAO.ProdutoDAO;
import ufrn.tads.erick.petapi.model.Produto;

@Controller
public class AdminController {
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public void formCadastrar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.getWriter().println("<body>");
        response.getWriter().println("<form method=\"post\" action=\"/cadastra\">");
        response.getWriter().println("Nome do produto: <input type=\"text\" name=\"nome\"> <br />");
        response.getWriter()
                .println("raça: <input type=\"text\" name=\"raca\"><br />\n"
                        + "preço: <input type=\"number\" name=\"preco\" step=\"0.01\"><br />\n"
                        + "especie: <input type=\"text\" name=\"especie\"> <br/>\n"
                        + "marca: <input type=\"text\" name=\"marca\"> <br/>\n"
                        + "categoria: <input type=\"text\" name=\"categoria\"> <br/>\n"
                        + "<button type=\"submit\">Cadastrar</button>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body>");

    }

    @RequestMapping(value = "/cadastra", method = RequestMethod.POST)
    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String raca = request.getParameter("raca");
        float preco = Float.parseFloat(request.getParameter("preco"));
        String especie = request.getParameter("especie");
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");

        Produto p = new Produto();
        ProdutoDAO pdao = new ProdutoDAO();

        p.setNome(nome);
        p.setRaca(raca);
        p.setPreco(preco);
        p.setEspecie(especie);
        p.setMarca(marca);
        p.setCategoria(categoria);                

        pdao.cadastrarProduto(p);

        HttpSession session = request.getSession();

        Date data = new Date(session.getCreationTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
        String currentTime = sdf.format(data);        
        Cookie c = new Cookie("visita", currentTime);
        c.setMaxAge(86400);
        response.addCookie(c);
        response.sendRedirect("/admin");

    }
}
