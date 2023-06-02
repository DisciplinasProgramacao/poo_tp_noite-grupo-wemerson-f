public abstract class Midia {
    protected int id;
    protected int views = 0;
    protected int duracao = 0;
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
    
    public abstract String toString();
}
