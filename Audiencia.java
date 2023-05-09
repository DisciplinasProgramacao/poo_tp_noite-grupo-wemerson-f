import java.util.*;

public class Audiencia {
    private String login;
    private char fa;
    private int idSerie;

    public Audiencia(String login, char fa, int idSerie) {
        this.login = login;
        this.fa = fa;
        this.idSerie = idSerie;
    }

    public String getLogin() {
        return login;
    }

    public char getFa() {
        return fa;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public static List<Audiencia> lerArquivo(String arquivo) {
        List<Audiencia> audiencias = new ArrayList<>();
        List<String[]> linhas = CSVReader.ler("POO_Audiencia.csv");
        for (String[] linha : linhas) {
            String login = linha[0];
            char fa = linha[1].charAt(0);
            int idSerie = Integer.parseInt(linha);
        }
    }

}
