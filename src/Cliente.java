import java.util.ArrayList;

public class Cliente {
    protected String nome;
    protected String login;
    protected String password;
    protected ArrayList<Serie> paraAssistir = new ArrayList<Serie>();
    protected ArrayList<Serie> assistidas = new ArrayList<Serie>();

    /**
     * Construtor da classe Cliente
     * @param nome
     * @param login
     * @param password
     */
    public Cliente(String nome, String login, String password)
    {
        this.nome = nome;
        this.login = login;
        this.password = password;
    }



    /**
     * Adiciona ao ArrayList de series assistidas
     * @param serie
     */
    public void addAssistidas(Serie serie)
    {
        assistidas.add(serie);
    }

    /**
     * Adiciona ao ArrayList de series para assistir
     * @param serie
     */
    public void addParaAssistir(Serie serie)
    {
        paraAssistir.add(serie);
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
    
    public String getPassword()
    {
        return this.password;
    }
}
