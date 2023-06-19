public class Diretor extends Usuario {

    public Diretor(String nome, String login, String password)
    {
        this.nome = nome;
        this.login = login.toLowerCase();
        this.password = password;
    }


    @Override
    public String toString() {
        return "\nNome: " + this.nome + ","
            + "\nProfissão: Diretor";
    }

}

