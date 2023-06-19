public class Avaliacao {
    private int nota;
    private String comentario;
    private Cliente cliente;
    private Data data;

    public Avaliacao(int nota)
    {
        this.nota = nota;
        data = new Data();
    }
    
    /**
     * Atribui o comentário à classe
     * @param comentario
     */
    public void comentar(String comentario)
    {
        this.comentario = comentario;
    }

    /**
     * Recebe o cliente que realizou esta avaliação
     * e atribui à variável da classe
     * @param cliente
     */
    public void avaliador(Cliente cliente)
    {
        this.cliente = cliente;
    }

    /**
     * Recebe a data de quando esta avaliação foi feita
     * @param data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Retorna a data da avaliação
     * @return Data
     */
    public Data getData() { // NÃO ESPECIFICADO COMO A DATA SERIA ATRIBUIDA À AVALIAÇÃO
        return data;
    }

    /**
     * Retorna o comentário
     * @return  String
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Retorna o cliente que fez o comentário
     * @return Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Retorna a nota da avaliação
     * @return int
     */
    public int getNota() {
        return nota;
    }


}

