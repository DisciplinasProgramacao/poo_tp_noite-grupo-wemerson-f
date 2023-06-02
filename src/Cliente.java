import java.util.ArrayList;

public class Cliente {
    
    protected String nome;
    protected String login;
    protected String password;
    protected ArrayList<Integer> paraAssistir = new ArrayList<Integer>();
    protected ArrayList<Integer> assistidas = new ArrayList<Integer>();
    protected int views = 0;

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

            System.out.println(count + " - " + App.series.get(App.verificaSerie(idSerie)).getNome());
        }
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
        int count = 0;
        for (int idSerie : this.assistidas) {
            ++count;
            System.out.println(count + " - " + App.series.get(App.verificaSerie(idSerie)).getNome());
        }
    }

    /**
     * Adiciona ao ArrayList de series assistidas
     * @param serie
     */
    public void addAssistidas(int id)
    {
        assistidas.add(id);
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
}
