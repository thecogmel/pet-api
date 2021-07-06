package ufrn.tads.erick.petapi.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

  @GetMapping(value = "/config")
  public String getMethodName() throws IOException, ServletException {
    Connection con = null;
    PreparedStatement stmt = null;

    try {
      con = ConectaBanco.getConnection();
      stmt = con.prepareStatement(
        "CREATE TABLE IF NOT EXISTS produto (id SERIAL PRIMARY KEY, nome VARCHAR(55), raca VARCHAR(50), preco FLOAT, especie VARCHAR(40), marca VARCHAR(45), categoria VARCHAR(55))");

      stmt.execute();

      stmt = con
        .prepareStatement("insert into produto (nome, raca, preco, especie, marca, categoria) values\n"
          + "('Biscoito de coco', 'Qualquer', '23.0', 'canino', 'Pedigree', 'alimentos'),\n"
          + "('Ração Premium', 'Qualquer', '52.0', 'canino', 'Premium Golden', 'alimentos'),\n"
          + "('Escova de dente veterinária', 'Qualquer', '10.0', 'canino', 'CleanPet', 'limpeza'),\n"
          + "('Coleira anti-carrapato', 'Dachshund', '75.0', 'canino', 'Pedigree', 'saude'),\n"
          + "('Cama para gato', 'Qualquer', '110.0', 'felino', 'Whiskas', 'acessórios'),\n"
          + "('Bolinha com chocalho', 'Qualquer', '20.0', 'felino', 'TopPet', 'acessórios')");

      stmt.execute();

      con.close();

      return new String("ok");

    } catch (SQLException | URISyntaxException ex) {
      return new String("Algo deu errado com a tabela" + ex);
    }
  }
}
