public class Cliente extends Usuario {

    /**
     * Construtor da classe Cliente
     * @param nome
     * @param login
     * @param password
     */
    public Cliente(String nome, String login, String password)
    {
        this.nome = nome;
        this.login = login.toLowerCase();
        this.password = password;
        this.categoria = Categoria.REGULAR;
    } 

    /**
     * Altera a categoria do usuário para ESPECIALISTA
     */
    private void alterarCategoria()
    {
        this.categoria = Categoria.ESPECIALISTA;
    }

    @Override
    /**
     * Adiciona ao ArrayList de series assistidas
     * @param serie
     */
    public void addAssistidas(int id)
    {
        assistidas.add(App.midias.get(id));
        App.midias.get(id).atualizaViews();
        if(verificaAvaliacoes()) {
            this.alterarCategoria();
        }
    }

    @Override
    public String toString() {
        return "\nNome: " + this.nome + ",\n" 
        + "Cliente categoria: " + this.categoria.descricao + ",\n";
    }

}
