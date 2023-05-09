import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Espectador extends leitor {
    private String nome;
    private String login;
    private String senha;

    public Espectador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public static List<Espectador> lerArquivo(String arquivo) {
        List<Espectador> espectadores = new ArrayList<>();
        List<String[]> linhas = CSVReader.ler("POO_Espectadores.csv");
        for (String[] linha : linhas) {
            String nome = linha[0];
            String login = linha[1];
            String senha = linha[2];
            Espectador espectador = new Espectador(nome, login, senha);
            espectadores.add(espectador);
        }
        return espectadores;
    }
     public void name() {
        
     }
}
