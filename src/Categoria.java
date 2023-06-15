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

    public void alterarCategoria()
    {
        if(this.descricao.equals("Regular")) {
            this.descricao = "Especialista";
        } else {
            this.descricao = "Regular";
        }
    }

    public String getDescricao()
    {
        return this.descricao;
    }
}
