import java.util.ArrayList;
public abstract class Usuario {

    protected ArrayList<Integer> paraAssistir = new ArrayList<Integer>();
    protected ArrayList<Integer> assistidas = new ArrayList<Integer>();
    protected ArrayList<Midia> midiasAvaliadas = new ArrayList<Midia>();
    protected String nome;
    protected String login;
    protected String password;
    
    public abstract String getNome();

    public abstract String getLogin();

    public abstract String getPassword();

    public abstract void addParaAssistir(int id);

    public abstract void addAssistidas(int id);

    public abstract void mostrarListaDeSeriesAssistidas();

    public abstract boolean verificaSeJaAvaliou(int id);

    public abstract void addAvaliada(Midia serie);

    public abstract void mostrarListaDeSeriesParaAssistir();

}
