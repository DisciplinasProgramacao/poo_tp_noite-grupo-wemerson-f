import java.time.LocalDate;
import java.util.*;


public class Serie {
    private int idSerie;
    private String nome;
    private LocalDate dataDeLancamento;

    public Serie(int idSerie, String nome, LocalDate dataDeLancamento) {
        this.idSerie = idSerie;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public static List<Serie> lerArquivo(String arquivo) {
        Scanner sc = new Scanner(System.in);
        List<Serie> series = new ArrayList<>();
        List<String[]> linhas = CSVReader.ler("POO_Series.csv");
        for (String[] linha : linhas) {
            int idSerie = Integer.parseInt(linha[0]);
            String nome = linha[1];
            LocalDate dataDeLancamento = LocalDate.parse(linha[2]);
            Serie serie = new Serie(idSerie, nome, dataDeLancamento);
            series.add(serie);
        }
        return series;
    }
}
