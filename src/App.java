import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class App {
    final public static String PATH = "./docs/POO_Audiencia.csv";
    final public static String PATH_ESPECTADORES = "./docs/POO_Espectadores.csv";
    final public static String PATH_SERIES = "./docs/POO_Series.csv";
    final public static String PATH_FILMES = "./docs/POO_Filmes.csv";
    public static Random random = new Random();
    public static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    public static HashMap<Integer, Midia> midias = new HashMap<Integer, Midia>();
    public static Scanner key = new Scanner(System.in);
    public static int cont = 0;
    public static Usuario eu;

    /**
     * Classe que inicia os metodos fundamentais para o funcionamento do projeto
     */
    public static void init()
    {
        carregaEspectadores();
        carregaSeries();
        carregarFilmes();
        carregarAudiencia();

    }

    /**
     * Carrega a lista de audiência Audiencia.csv
     */
    public static void carregarAudiencia()
    {
        try{
            App.print("Aguarde... Carregando arquivos.");
            Arquivo arquivo = new Arquivo(PATH);
            ArrayList<String> arq = arquivo.getDoc();
            for(int i=0; i < arq.size(); i++)
            {
                cont++;
                String linha[];
                linha = arq.get(i).split(";");
                if(verificaSeExistCliente(linha[0]))
                {
                    Usuario user = usuarios.get(linha[0].toLowerCase());
                    if(linha[1].toLowerCase().equals("f")) {
                        user.addParaAssistir(Integer.parseInt(linha[2]));
                    } else {
                        user.addAssistidas(Integer.parseInt(linha[2]));
                    }
                }
                
            }
            clear();
        } catch(Exception e) {
            print("Erro ao carregar na linha: " + cont);
            print(e.getMessage());
        }
    }
    
    /**
     * Verifica se o cliente existe no hashmap cliente
     * @param id
     * @return  boolean
     */
    public static boolean verificaSeExistCliente(String id)
    {
        return usuarios.get(id.toLowerCase()) != null ? true : false;
    }

    /**
     * Carrega a lista de espectadores POO_Espectadores.csv
     */
    private static void carregaEspectadores()
    {
        print("Aguarde... Carregando arquivos.");
        Arquivo arquivo = new Arquivo(PATH_ESPECTADORES);
        ArrayList<String> arq = arquivo.getDoc();
        String linha[];
        for(int i=0; i < arq.size(); i++)
        {
            linha = arq.get(i).split(";");
            if(!verificaSeExistCliente(linha[1])) {
                Cliente cliente = new Cliente(linha[0], linha[1].toLowerCase(), linha[2]);
                usuarios.put(linha[1].toLowerCase(), cliente);
            }
        }
        clear();
    }

    /**
     * Este método mostra o usuário que assistiu mais mídias
     */
    public static void usuarioQueAssistiuMaisMidias()
    {
        Optional<Usuario> usuarioComMaisMidias = usuarios.values().stream()
                                                         .max((usuario1, usuario2) -> Integer.compare(usuario1.getTotalDeAssistidas(), usuario2.getTotalDeAssistidas()));                  
        Usuario u = usuarioComMaisMidias.get();
        System.out.println("\nO usuário(a) que assistiu mais mídias foi o(a) " + u.getNome() + " com " + u.getTotalDeAssistidas());
    }

    /**
     * Este método mostra o usuário que avaliou mais mídias
     */
    private static void usuarioQueAvaliouMaisMidias()
    {
        Optional<Usuario> usuarioComMaisMidias = usuarios.values().stream()
                                                         .max((usuario1, usuario2) -> Integer.compare(usuario1.getTotalDeAvaliacao(), usuario2.getTotalDeAvaliacao()));                  
        Usuario u = usuarioComMaisMidias.get();
        System.out.println("\nO usuário(a) que avaliou mais mídias foi o(a) " + u.getNome() + " com " + u.getTotalDeAvaliacao());
    }

    /**
     * Carrega a lista de filmes POO_Filmes.csv
     */
    private static void carregarFilmes()
    {
        print("Aguarde... Carregando arquivos.");
        Arquivo arquivo = new Arquivo(PATH_FILMES);
        ArrayList<String> arq = arquivo.getDoc();
        String linha[];
        for(int i=0; i < arq.size(); i++)
        {
            linha = arq.get(i).split(";");
            Filme filme = new Filme(Integer.parseInt(linha[0]), linha[1], new Data(linha[2]), Integer.parseInt(linha[3]));
            midias.put(Integer.parseInt(linha[0]), filme);
        }
        clear();

    }

    /**
     * Este método a porcentagem de usuários que avaliaram mais de 15 mídias
     */
    private static void porcentagemDeUsuariosCom15Avaliacoes()
    {
        System.out.println("\n" + (usuarios.values().stream()
                                            .filter(usuario -> usuario.getTotalDeAvaliacao() >= 15)
                                            .count() * 100.0) / usuarios.size()
                            + "%");
    }

    /**
     * Mostra as 10 mídias com as melhores avaliações
     */
    private static void midiaCom10MelhoresAvaliacoes()
    {
        List<Midia> midiasFiltradas = usuarios.values().stream()
                .flatMap(usuario -> usuario.getMidiasAvaliadas().stream())
                .filter(midia -> midia.getViews() >= 100)
                .collect(Collectors.toList());

        List<Midia> top10Midias = midiasFiltradas.stream()
                .sorted(Comparator.comparingDouble(Midia::getMedia).reversed())
                .limit(10)
                .collect(Collectors.toList());
        int count = 0;
        for (Midia midia : top10Midias) {
            ++count;
            System.out.println("\n" + count + "- " + midia);
        }

        if(count == 0) {
            System.out.println("Não encontrado nenhuma mídia com avaliação");
        }
    }

    /**
     * Mostra as 10 mídias com mais visualizações em ordem decrescente
     */
    private static void midiasComMaisViwesDecrescente() {
        int count = 10;
        List<Midia> todasMidias = usuarios.values().stream()
                .flatMap(usuario -> usuario.getAssistidas().stream())
                .collect(Collectors.toList());

        List<Midia> top10Midias = todasMidias.stream()
                .sorted(Comparator.comparingLong(Midia::getViews).reversed())
                .limit(10)
                .collect(Collectors.toList());

        for (Midia midia : top10Midias) {
            System.out.println(count-- + "- " + midia.getNome());
        }


    }

    /**
     * Carrega a lista de series que a plataforma irá possuir POO_Series.csv
     */
    private static void carregaSeries()
    {
        print("Aguarde... Carregando arquivos.");
        Arquivo arquivo = new Arquivo(PATH_SERIES);
        ArrayList<String> arq = arquivo.getDoc();
        String linha[];
        for(int i=0; i < arq.size(); i++)
        {
            linha = arq.get(i).split(";");
            Serie serie = new Serie(Integer.parseInt(linha[0]), linha[1], new Data(linha[2]));
            midias.put(Integer.parseInt(linha[0]), serie);
        }
        clear();
    }
    
    /**
     * Mostra todas as séries ou todos os filmes
     * quando o tipo = 1 exibe séries, quando tipo = 2
     * exibe filmes
     */
    public static void mostrarMidias(int tipo)
    {
        midias.forEach((id, midia) -> {
            if(midia.getTipo() == tipo) {
                if(!midia.getLancamento() || eu.getCategoria() == null) {
                    print(id + " - " + midia.getNome());
                }
            }
        });
    }

    /**
     * Mostra detalhes de uma mídia a partir do código
     * @param codigo String
     */
    public static void mostrarDestalhesDaMidia(int codigo)
    {
        try{
            print(midias.get(codigo).toString());

        } catch (Exception e) {
            print("A mídia " + codigo + " não existe.");
        }
    }

    /**
     * Pesquisa a mídia no Hash de mídias e se encontrar chama o método de exibir
     * os detalhes dessa mídia
     * @param nome
     */
    public static void pesquisarMidia(String nome)
    {
        try{
            midias.forEach((id, midia) -> {
                if(midia.getNome().equals(nome)) {
                    mostrarDestalhesDaMidia(midia.getId());
                }
            });
        } catch(Exception e) {
            print(e.getMessage());
        }
    }

    /**
     * Método para limpar a tela
     */
    public static void clear()
    { 
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
    }

    /**
     * Menu do programa
     * @return int
     */
    public static int menu()
    {
        try{
            System.out.print("|-----------------------------|\n");
            System.out.print("|        Bem Vindo!           |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("| 1 - Cadastro                |\n");
            System.out.print("| 2 - Login                   |\n");
            System.out.print("| 3 - Sair                    |\n");
            System.out.print("|-----------------------------|\n\n");

            System.out.print("Digite uma opção: ");
            return Integer.parseInt(key.nextLine());

        
        } catch(Exception e) {
            return -1;
        }
    }

    /**
     * Submenu após login
     * @return int
     */
    public static int menu2() 
    {
        try{
            System.out.print("|-----------------------------|\n");
            System.out.print("|    Selecione uma opção      |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("| 1 - Mídias                  |\n");
            System.out.print("| 2 - Informações do usuário  |\n");
            System.out.print("| 3 - Relarório do Sistema    |\n");
            System.out.print("| 4 - Voltar                  |\n");
            System.out.print("|-----------------------------|\n\n");

            System.out.print("Digite uma opção: ");
            return Integer.parseInt(key.nextLine());

        
        } catch(Exception e) {
            return -1;
        }
    }

    /**
     * Escreve na tela o submenu 
     */
    public static int subMenu2() 
    {
        System.out.print("|-----------------------------|\n");
        System.out.print("|    Selecione uma opção      |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("| 1 - Mídias assistidas       |\n");
        System.out.print("| 2 - Mídias para assistir    |\n");
        System.out.print("| 3 - Dados do usuário        |\n");
        System.out.print("| 4 - Voltar                  |\n");
        System.out.print("|-----------------------------|\n\n");

        System.out.print("Digite uma opção: ");
        return Integer.parseInt(key.nextLine());
    }

    /**
     * Escreve na tela o menu de mídias
     * e retorna o valor da opção que o cliente escolher
    */
    public static int subMenuMidias(){
        System.out.print("|-----------------------------|\n");
        System.out.print("|    Selecione uma opção      |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("| 1 - Mostrar todas as séries |\n");
        System.out.print("| 2 - Mostrar todos os filmes |\n");
        System.out.print("| 3 - Pesquisar por uma mídia |\n");
        System.out.print("| 4 - Voltar                  |\n");
        System.out.print("|-----------------------------|\n\n");

        System.out.print("Digite uma opção: ");
        return Integer.parseInt(key.nextLine());
    }

    /**
     * Escreve na tela o submenu de relatório
     */
    private static int subMenuRelatorio()
    {
        System.out.print("|-------------------------------------------------------|\n");
        System.out.print("|                     Selecione uma opção               |\n");
        System.out.print("|-------------------------------------------------------|\n");
        System.out.print("| 1 - Cliente que assistiu mais mídias                  |\n");
        System.out.print("| 2 - Cliente que mais avaliou                          |\n");
        System.out.print("| 3 - Porcentagem dos clientes  c/ 15 avaliações        |\n");
        System.out.print("| 4 - As 10 mídias com a melhor média                   |\n");
        System.out.print("| 5 - As 10 mídias com mais visualizações decrescente   |\n");
        System.out.print("| 6 - Voltar                                            |\n");
        System.out.print("|-------------------------------------------------------|\n\n");


        System.out.print("Digite uma opção: ");
        return Integer.parseInt(key.nextLine());
    }

    /**
     * Escreve na tela
     * @param texto String
     */
    public static void print(String texto)
    {
        System.out.println(texto);
    }

    public static void main(String[] args) throws Exception {
        init();
        clear();
        //Variáveis:
        String nome = "", password = "", login = "";
        int opcao = -1;

        do {
            opcao = menu();

            switch(opcao) {
                case 1: // CADASTRO
                    clear();
                    print("CADASTRO:\n");
                    //NOME
                    System.out.print("Nome: ");
                    nome = key.nextLine();
                    clear();
                    //LOGIN
                    System.out.print("Login: ");
                    login = key.nextLine().toLowerCase();
                    clear();

                    if(usuarios.get(login) != null)
                    {
                        print("O login " + login + " já está cadastrado no sistema.");
                        TimeUnit.SECONDS.sleep(2);
                    } else {
                        //PASSWORD
                        System.out.print("Password: ");
                        password = key.nextLine();

                        clear();
                        //Tipo
                        System.out.print("|-----------------------------|\n");
                        System.out.print("|    Selecione uma opção      |\n");
                        System.out.print("|-----------------------------|\n");
                        System.out.print("| 1 - Cliente                 |\n");
                        System.out.print("| 2 - Ator                    |\n");
                        System.out.print("| 3 - Diretor                 |\n");
                        System.out.print("| 4 - Jornalista              |\n");
                        System.out.print("|-----------------------------|\n\n");
                        
                        System.out.print("Digite uma opção: ");

                        opcao = Integer.parseInt(key.nextLine());
                        switch(opcao) { //Criando o objeto
                            case 1:
                                usuarios.put(login, new Cliente(nome, login, password));
                            break;
                            case 2:
                                usuarios.put(login, new Ator(nome, login, password));
                            break;
                            case 3:
                                 usuarios.put(login, new Diretor(nome, login, password));
                            break;
                            case 4:
                                usuarios.put(login, new Jornalista(nome, login, password));
                            break;
                        }
                    }
                    
                    clear();
                    opcao = -1;
                break;
                case 2: //                                                                       LOGIN
                    clear();
                    print("Acessar Sistema:\n");
                    System.out.print("Login: ");
                    login = key.nextLine().toLowerCase();

                    if(usuarios.get(login) == null) 
                    {
                        print("O login informado não existe.");
                        TimeUnit.SECONDS.sleep(3);
                        clear();
                    } else {
                        System.out.print("Password: ");
                        password = key.nextLine();
                        if(usuarios.get(login).getPassword().equals(password))
                        {
                            clear();
                            eu = usuarios.get(login);
                            print("Login efetuado com sucesso!\n");
                            print("Bem vindo(a) " + eu.getNome() + "!");
                            TimeUnit.SECONDS.sleep(2);
                            clear();
                            do {
                                opcao = menu2();
                                switch(opcao) {
                                    case 1://                                                    MIDIAS
                                        clear();
                                        opcao = subMenuMidias();
                                        switch(opcao) {
                                            case 1: //Series
                                                clear();
                                                mostrarMidias(1);
                                                print("\n\nDeseja ver os detalhes de uma série?(S/N)");
                                                if(key.nextLine().toLowerCase().equals("s")){
                                                    System.out.print("\nInforme o código da série: ");
                                                    mostrarDestalhesDaMidia(Integer.parseInt(key.nextLine()));
                                                }
                                                opcao = voltar();
                                                break;
                                            case 2: //Filmes
                                                clear();
                                                mostrarMidias(2);
                                                print("\n\nDeseja ver os detalhes de um filme?(S/N)");
                                                if(key.nextLine().toLowerCase().equals("s")){
                                                    System.out.print("\nInforme o código do filme: ");
                                                    mostrarDestalhesDaMidia(Integer.parseInt(key.nextLine()));
                                                }
                                                opcao = voltar();
                                                break;
                                            case 3: //PESQUISAR UMA MÍDIA
                                                clear();
                                                print("Informe o nome da mídia desejada:");
                                                System.out.print("\nNome: ");
                                                String nomeMidia = key.nextLine();
                                                pesquisarMidia(nomeMidia);
                                                opcao = voltar();
                                            break;
                                        }
                                    break;
                                    case 2:     //DADOS DO USUÁRIO
                                        do {
                                            clear();
                                            opcao = subMenu2();
                                            switch(opcao){
                                                case 1: //Midias assistidas
                                                    eu.mostrarListaDeMidiasAssistidas();
                                                    print("\n\nDeseja avaliar uma mídia?(S/N)");        //Nesta lógica só é possível avaliar uma mídia que já foi assistida
                                                    if(key.nextLine().toLowerCase().equals("s")){
                                                        System.out.print("\nInforme o código da mídia que deseja avaliar: ");
                                                        int opt = Integer.parseInt(key.nextLine());
                                                        if(!eu.verificaSeJaAvaliou(opt)) { 
                                                            Midia esta = midias.get(opt);
                                                            if(esta == null || (esta.getLancamento() && eu.getCategoria() != null)) {
                                                                print("Erro... Não foi encontrada a mídia informada!");
                                                            } else {
                                                                System.out.print("Avalie a mídia: '" + esta.getNome() + "' com uma nota de 0 à 5: ");
                                                                int nota = Integer.parseInt(key.nextLine());
                                                                clear();
                                                                System.out.print("Informe a data desta avaliação no padrão 01/01/1001: ");
                                                                String dataTemp = key.nextLine();
                                                                while(nota < 0 && nota <= 5){
                                                                    clear();
                                                                    print("Erro... Informe uma nota de 0 à 5!");
                                                                    TimeUnit.SECONDS.sleep(1);
                                                                    clear();
                                                                    System.out.print("Avalie a mídia: '" + esta.getNome() + "' com uma nota de 0 à 5: ");
                                                                    nota = Integer.parseInt(key.nextLine());
                                                                }

                                                                if(nota >= 0 && nota <= 5) {
                                                                    eu.addAvaliada(midias.get(opt));
                                                                    esta.avaliar(nota, dataTemp);
                                                                    if(!eu.categoria.getDescricao().equals("Regular")) {
                                                                        print("Escreva um comentário: ");
                                                                        esta.comentar(key.nextLine());
                                                                        eu.addMidiasAvaliadas(esta);
                                                                    }
                                                                } else {
                                                                    clear();
                                                                    print("Erro... Só é possível avaliar de  à 5!");
                                                                    TimeUnit.SECONDS.sleep(2);
                                                                    clear();
                                                                    opcao = -1;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            clear();
                                                            print("Erro... Esta mídia já foi avaliada por você!");
                                                            TimeUnit.SECONDS.sleep(2);
                                                            clear();
                                                            opcao = -1;
                                                            break;
                                                        }
                                                    }
                                                    opcao = voltar();
                                                break;
                                                case 2: //Midias para assistir
                                                    eu.mostrarListaDeMidiasParaAssistir();
                                                    opcao = voltar();
                                                break;
                                                case 3: //Dados do cliente
                                                    print(eu.toString());
                                                    opcao = voltar();
                                                break;
                                                case 4:// Voltar
                                                    clear();
                                                    opcao = 1;
                                                    break;
                                            }
                                        } while(opcao < 0);
                                        opcao = -1;
                                        break;
                                    case 3://                                               RELATÓRIO DO SISTAMA
                                        clear();
                                        do{
                                            opcao = subMenuRelatorio();
                                            switch(opcao) {
                                                case 1:
                                                    clear();
                                                    usuarioQueAssistiuMaisMidias();
                                                    
                                                    opcao = voltar();
                                                break;
                                                case 2:
                                                    clear();
                                                    usuarioQueAvaliouMaisMidias();

                                                    opcao = voltar();
                                                break;
                                                case 3:
                                                    clear();
                                                    porcentagemDeUsuariosCom15Avaliacoes();

                                                    opcao = voltar();
                                                break;
                                                case 4:
                                                    clear();
                                                    midiaCom10MelhoresAvaliacoes();

                                                    opcao = voltar();
                                                break;
                                                case 5:
                                                    clear();
                                                    midiasComMaisViwesDecrescente();

                                                    opcao = voltar();
                                                break;
                                                case 6:// Voltar
                                                    clear();
                                                    opcao = 1;
                                                break;
                                            }
                                        }while(opcao < 0);
                                        opcao = -1;
                                    break;
                                    case 4: //                                                        VOLTAR
                                        clear();
                                        opcao = 1;
                                    break;
                                    default:
                                        clear();
                                        print("Ops! Não existe o valor " + opcao);
                                        TimeUnit.SECONDS.sleep(1); // Timer de 1 segundo para limpar a tela
                                        clear();
                                        opcao = -1;
                                    break;
                                }
                            } while(opcao < 0);
                        }
                        opcao = -1;
                    }
                break;
                case 3: // SAIR
                    clear();
                    print("Obrigado... Volte sempre!");
                    TimeUnit.SECONDS.sleep(2);                    
                    clear();
                    opcao = 1;
                break;
                default:
                    clear();
                    print("Ops! Selecione uma opção válida.");
                    TimeUnit.SECONDS.sleep(1); // Timer de 1 segundo para limpar a tela
                    clear();
                    opcao = -1;
                break;
                
            }
        } while(opcao < 0);

        key.close();
    }

    private static int voltar() {
        print("\n\nPressione qualquer tecla para continua...");
        key.nextLine();
        clear();
        return -1;
    }
}
