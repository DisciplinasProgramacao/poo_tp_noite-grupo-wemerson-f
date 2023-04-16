import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Plataforma {
    private HashMap<Integer, Cliente> listaClientes;
    private HashMap<Integer, Serie> listaSeries;
    private Cliente clienteAtual;

    void logar(String nome, String email){
        Cliente cliente = new Cliente(nome, email);
        if(listaClientes.containsKey(cliente.getID())){
            clienteAtual = listaClientes.get(cliente.getID());
        }
        else{
            cadastrarCliente(nome, email);
        }
  }

    void cadastrarCliente(String nome, String email) {
        if(nome!=null && email!=null){
            Cliente novo = new Cliente(nome, email);
            listaClientes.put(novo.getID(), novo);
        }
    }

    public String buscarSeriePorNome(String nome){
        if(clienteAtual!=null){
            List<Serie> seriesEncontradas = listaSeries.values().stream()
            .filter(serie -> serie.getNome().contains(nome))
            .collect(Collectors.toList());
    
            String todasAsSeries = seriesEncontradas.stream()
            .map(serie -> "Nome: " + serie.getNome() + ", Idioma: " + serie.getIdioma() + ", Gênero: " + serie.getGenero())
            .collect(Collectors.joining("\n"));

            return todasAsSeries;
        }
        return "Não encontrado!";
    }

    public String buscarSeriePorGenero(String genero){
        if(clienteAtual!=null){
            List<Serie> seriesEncontradas = listaSeries.values().stream()
            .filter(serie -> serie.getGenero().contains(genero))
            .collect(Collectors.toList());
    
            String todasAsSeries = seriesEncontradas.stream()
            .map(serie -> "Nome: " + serie.getNome() + ", Idioma: " + serie.getIdioma() + ", Gênero: " + serie.getGenero())
            .collect(Collectors.joining("\n"));

            return todasAsSeries;
        }
       return "Não encontrado!";
    }

    public String buscarSeriePorIdioma(String idioma){
        if(clienteAtual!=null){
            List<Serie> seriesEncontradas = listaSeries.values().stream()
            .filter(serie -> serie.getIdioma().contains(idioma))
            .collect(Collectors.toList());

            String todasAsSeries = seriesEncontradas.stream()
            .map(serie -> "Nome: " + serie.getNome() + ", Idioma: " + serie.getIdioma() + ", Gênero: " + serie.getGenero())
            .collect(Collectors.joining("\n"));

            return todasAsSeries;
        }
        return "Não encontrado!";
    }

    public String adicionarSerieAssistida(String nome){
        List<Serie> seriesEncontradas = listaSeries.values().stream()
            .filter(serie -> serie.getNome().contains(nome))
            .collect(Collectors.toList());

        if(clienteAtual!=null && seriesEncontradas.size()>0){
            if(seriesEncontradas.size()>1){
                StringBuilder series = new StringBuilder();
                int contador = 1;
                for (Serie serie : seriesEncontradas) {
                    series.append("Série ").append(contador++).append(":\n");
                    series.append("Nome: ").append(serie.getNome()).append("\n");
                    series.append("Idioma: ").append(serie.getIdioma()).append("\n");
                    series.append("Gênero: ").append(serie.getGenero()).append("\n");
                    series.append("--------------------\n");
                }
                return "Multiplas series encontradas:\n"+ series;
            }
            else{
                clienteAtual.adicionarAssistidas(seriesEncontradas.get(0));
                return "Serie adicionada a playlist Já assistida!";
            }
        }
        return "Erro!";
    }

    public String adicionarSerieAssistida(String nome, int index){
        List<Serie> seriesEncontradas = listaSeries.values().stream()
            .filter(serie -> serie.getNome().contains(nome))
            .collect(Collectors.toList());
        if(clienteAtual!= null){
            clienteAtual.adicionarAssistidas(seriesEncontradas.get(0));
            return "Serie adicionada a playlist Já assistida!";
        }
        else{
            return "Erro!";
        }
    }
}

