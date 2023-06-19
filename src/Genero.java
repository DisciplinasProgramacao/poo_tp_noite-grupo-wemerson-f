public enum Genero {
    ACAO("Ação"),
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DOCUMENTARIO("Documentário"),
    DRAMA("Drama"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense");

    private String descricao;

    Genero( String desc)
    {
        this.descricao = desc;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}

