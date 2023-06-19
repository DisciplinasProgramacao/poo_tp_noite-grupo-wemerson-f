public class Serie extends Midia {

    public Serie(int id, String nome, Data dataDeLancamento)
    {   
        this.id = id;
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.init();
        this.tipo = 1;
    }


    @Override
    public String toString()
    {
        int soma = this.avaliacoes.stream().mapToInt(i -> i.getNota()).sum();
        if(this.avaliacoes.size() != 0) {
            this.media =  soma/this.avaliacoes.size();
        }
        
        return "Serie: " + this.nome + ","
            + "\nGênero: " + this.genero.toString() + ","
            + "\nIdioma: " + this.idioma + "," 
            //+ "\nDuração: " + this.duracao + "episódios,"   //<- Não especificado onde virá essa informação
            + "\nVisualizações: " + this.views + ","
            + "\nLançamento: " + this.dataDeLancamento.dataFormatada() + ","
            + "\nAvaliação média: " + (this.avaliacoes.size() == 0 ? 0 : this.media);
    }

}
