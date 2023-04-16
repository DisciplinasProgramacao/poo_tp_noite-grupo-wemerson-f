public class Serie {
    private String nome;
    private String genero;
    private String idioma;
    private Integer ID = 0;

    Serie(){
        this("Desconhecido","Desconhecido","Desconhecido");
    }
    Serie(String nome, String genero, String idioma){
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        ID = hashCode();
    }

    public int getID(){
        return this.ID;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
        return result;
    }
    public String getNome() {
        return this.nome;
    }
    public String getGenero() {
        return this.genero;
    }
    public String getIdioma() {
        return this.idioma;
    }  
    
    
}