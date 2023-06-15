import java.util.ArrayList;

public class Cliente extends Usuario {

    private Categoria categoria = Categoria.REGULAR;

    /**
     * Construtor da classe Cliente
     * @param nome
     * @param login
     * @param password
     */
    public Cliente(String nome, String login, String password)
    {
        this.nome = nome;
        this.login = login.toLowerCase();
        this.password = password;
    }

    /**
     * Exibe a lista de series para assistir
     */
    public void mostrarListaDeSeriesParaAssistir()
    {
        App.clear();
        if(this.paraAssistir.isEmpty()) {
            System.out.println("Nenhuma série adicionada até o momento");
            return;
        }  

        App.clear();
        System.out.println("Séries para assistir: \n");

        int count = 0;
        for (int idSerie : paraAssistir) {
            ++count;

            System.out.println(count + " - " + App.series.get(idSerie).getNome());
        }
    }

    private Boolean verificaAvaliacoes()
    {
        return this.midiasAvaliadas.stream()
                                .map(
                                    M -> M.getAvaliacoes().stream()
                                            .filter(A -> A.getData().verificaSeEstaNoUltimoMes())
                                            
                                ).count() >= 5 ? true : false;
    }

    /**
     * Altera a categoria do usuário para ESPECIALISTA
     */
    private void alterarCategoria()
    {
        this.categoria = Categoria.ESPECIALISTA;
    }
    /**
     * Exibe a lista de series assistidas
     */
    public void mostrarListaDeSeriesAssistidas()
    {
        App.clear();
        if(this.assistidas.isEmpty()) {
            System.out.println("Nenhuma série assistida até o momento");
            return;
        }  

        App.clear();
        System.out.println("Séries assistidas: \n");
         this.assistidas.forEach(id -> {
            System.out.println(id + " - " + App.series.get(id).getNome());
        });
    }

    /**
     * Adiciona ao ArrayList de series assistidas
     * @param serie
     */
    public void addAssistidas(int id)
    {
        assistidas.add(id);
        if(verificaAvaliacoes()) {
            this.alterarCategoria();
        }
    }

    /**
     * Adiciona ao ArrayList de series para assistir
     * @param serie
     */
    public void addParaAssistir(int id)
    {
        paraAssistir.add(id);
    }

    /**
     * Verifica se a serie informada no parâmetro está no ArrayList de series assistidas
     * @param nome
     * @return bool
     */
    public boolean verificaSeJaAssistiu(String nome)
    {
        return assistidas.contains(nome) ? true : false;
    }

    /**
     * Recebe o id da série que avaliou
     * @param id
     */
    public void addAvaliada(Midia serie)
    {   
        this.midiasAvaliadas.add(serie);
    }

    /**
     * Verifica se o usuário já avaliou a série do id foi informado
     * @param id
     * @return
     */
    public boolean verificaSeJaAvaliou(int id) 
    {
        return this.midiasAvaliadas.contains(id) ? true : false;
    }

    @Override
    public String toString() {
        return "\nNome: " + this.nome + ",\n" 
        + "Categoria: " + this.categoria.descricao + ",\n";
    }

    public String getLogin()
    {
        return this.login;
    }

    public String getNome()
    {
        return this.nome;
    }
    
    public String getPassword()
    {
        return this.password;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
