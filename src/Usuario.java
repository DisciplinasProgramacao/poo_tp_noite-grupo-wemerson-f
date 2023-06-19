import java.util.ArrayList;

public abstract class Usuario {
    protected Categoria categoria = null;
    protected ArrayList<Midia> paraAssistir = new ArrayList<Midia>();
    protected ArrayList<Midia> assistidas = new ArrayList<Midia>();
    protected ArrayList<Midia> midiasAvaliadas = new ArrayList<Midia>();
    protected String nome;
    protected String login;
    protected String password;

    /**
     * Adiciona ao ArrayList de series para assistir
     * @param serie
     */
    public void addParaAssistir(int id)
    {
        paraAssistir.add(App.midias.get(id));
    }

    /**
     * Adiciona ao ArrayList de series assistidas
     * @param serie
     */
    public void addAssistidas(int id)
    {
        assistidas.add(App.midias.get(id));
        App.midias.get(id).atualizaViews();
    }

    /**
     * Exibe a lista de mídias para assistir
     */
    public void mostrarListaDeMidiasParaAssistir()
    {
        App.clear();
        if(this.paraAssistir.isEmpty()) {
            System.out.println("Nenhuma série adicionada até o momento");
            return;
        }  

        App.clear();
        System.out.println("Séries para assistir: \n");

        int count = 0;
        for (Midia midia : paraAssistir) {
            ++count;

            System.out.println(count + " - " + midia.getNome());
        }
    }

     /**
     * Exibe a lista de series assistidas
     */
    public void mostrarListaDeMidiasAssistidas()
    {
        App.clear();
        if(this.assistidas.isEmpty()) {
            System.out.println("Nenhuma mídia assistida até o momento");
            return;
        }  

        App.clear();
        System.out.println("Midias assistidas: \n");
        for (Midia midia : assistidas) {
            System.out.println(midia.getId() + " - " + midia.getNome());
        }
    }

    /**
     * Verifica se há mais de 5 avaliações do ultimo mês 
     * @return
     */
    protected Boolean verificaAvaliacoes()
    {
        return this.midiasAvaliadas.stream()
                                .map(
                                    M -> M.getAvaliacoes().stream()
                                            .filter(A -> A.getData().verificaSeEstaNoUltimoMes())
                                            
                                ).count() >= 5 ? true : false;
    }

    /**
     * Verifica se a serie informada no parâmetro está no ArrayList de series assistidas
     * @param nome
     * @return bool
     */
    public boolean verificaSeJaAssistiu(int id)
    {
        return assistidas.contains(id) ? true : false;
    }

    /**
     * Verifica se o usuário já avaliou a série do id foi informado
     * @param id
     * @return
     */
    public boolean verificaSeJaAvaliou(int id) 
    {
        for (Midia midia : this.midiasAvaliadas) {
            return midia.getId() == id ? true : false;
        }
        return false;
    }

    /**
     * Recebe o id da série que avaliou
     * @param id
     */
    public void addAvaliada(Midia midia)
    {   
        this.midiasAvaliadas.add(midia);
    }

    /**
     * Adciona a mídia informada no parâmetro à lista de mídias avaliadas do usuário
     * @param midia
     */
    public void addMidiasAvaliadas(Midia midia)
    {
        this.midiasAvaliadas.add(midia);
    }
    
    /**
     * Retorna o nome do usuário
     * @return String
     */
    public String getNome()
    {
        return this.nome;
    }

    /**
     * Retorna a senha do usuário
     * @return String
     */
    public String getPassword()
    {
        return this.password;
    }
    
    /**
     * Retorna qual o login do usuário
     * @return String
     */
    public String getLogin()
    {
        return this.login;
    }

    /**
     * Retorna a categoria do
     * @return Categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }
    /**
     * Retorna o total de midias assistidas pelo usuário
     * @return int
     */
    public int getTotalDeAssistidas() {
        return assistidas.size();
    }
    /**
     * Retorna o total de avaliações de mídias pelo usuário
     * @return int
     */
    public int getTotalDeAvaliacao() {
        return midiasAvaliadas.size();
    }   

    /**
     * Retorna o array de mídias que foram avaliadas
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> getMidiasAvaliadas()
    {
        return this.midiasAvaliadas;
    }

    /**
     * Retorna as mídias assistidas
     * @return ArrayList<Midia>
     */
    public ArrayList<Midia> getAssistidas() {
        return assistidas;
    }
}

