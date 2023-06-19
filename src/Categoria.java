public enum Categoria {
    ESPECIALISTA("Especialista", true),
    REGULAR("Regular", false);

    String descricao;
    Boolean comentario;

    Categoria(String descricao, Boolean comentario)
    {
        this.descricao = descricao;
        this.comentario = comentario;
    }

    /**
     * Altera a categoria do cliente
     */
    public void alterarCategoria()
    {
        if(this.descricao.equals("Regular")) {
            this.descricao = "Especialista";
        } else {
            this.descricao = "Regular";
        }
    }
    /**
     * Retorna a descrição
     * @return String
     */
    public String getDescricao()
    {
        return this.descricao;
    }
}
