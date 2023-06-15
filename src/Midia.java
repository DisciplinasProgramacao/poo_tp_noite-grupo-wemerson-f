import java.util.ArrayList;

public abstract class Midia {
    protected String[] generos = {
        "Ação", "Anime", "Aventura", "Comédia", "Documentário",
        "Drama", "Policial", "Romance", "Suspense"
    };
    protected String[] idiomas = {"Portugês", "Inglês", "Chinês", "Hindi", "Espanhol", "Francês"};
    protected int id;
    protected int views;
    protected int duracao = 0;
    protected ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    protected String nome;
    protected String idioma;
    protected String genero;
    protected Data dataDeLancamento;

    /**
     * Atualiza o número de pessoas que assistiram essa série
     */
    public abstract void atualizaViews();

    /**
     * Retorna o nome
     * @return
     */
    public abstract String getNome();
    
    /**
     * Retorna a duração da mídia. Se filme retorna os minutos e se for série retorna o número de episódios
     * @return 
     */
    public abstract int getDuracao();

    /**
     * Retorna o número de Visualizações
     * @return
     */
    public abstract int getViews();
    
    /**
     * Retorna a data de lançamento
     * @return
     */
    public abstract Data getLancamento();

    /**
     * Retorna o id
     * @return
     */
    public abstract int getId();
    
    /**
     * Recebe uma nota e avalia a mídia
     * @param nota
     */
    public abstract void avaliar(int nota);

    public abstract ArrayList<Avaliacao> getAvaliacoes();

    public abstract String toString();
}
