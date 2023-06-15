import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
    final public static String PATH = "./doc/POO_Audiencia.csv";
    final public static String PATH_ESPECTADORES = "./doc/POO_Espectadores.csv";
    final public static String PATH_SERIES = "./doc/POO_Series.csv";
    final public static String PATH_FILMES = "./doc/POO_Filmes.csv";
    public static Random random = new Random();
    public static HashMap<String, Usuario> clientes = new HashMap<String, Usuario>();
    public static HashMap<Integer, Midia> series = new HashMap<Integer, Midia>();
    public static HashMap<Integer, Midia> filmes = new HashMap<Integer, Midia>();
    public static Scanner key = new Scanner(System.in);
    public static int cont = 0;

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
                    Usuario user = clientes.get(linha[0].toLowerCase());
                    if(linha[1].toLowerCase().equals("f")) {
                        user.addParaAssistir(Integer.parseInt(linha[2]));
                    } else {
                        user.addAssistidas(Integer.parseInt(linha[2]));
                        series.get(Integer.parseInt(linha[2])).atualizaViews();
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
        return clientes.get(id.toLowerCase()) != null ? true : false;
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
                clientes.put(linha[1].toLowerCase(), cliente);
            }
        }
        clear();
    }

    /**
     * Carrega a lista de filmes POO_Filmes.csv
     */
    public static void carregarFilmes()
    {
        print("Aguarde... Carregando arquivos.");
        Arquivo arquivo = new Arquivo(PATH_FILMES);
        ArrayList<String> arq = arquivo.getDoc();
        String linha[];
        for(int i=0; i < arq.size(); i++)
        {
            linha = arq.get(i).split(";");
            Filme filme = new Filme(Integer.parseInt(linha[0]), linha[1], new Data(linha[2]));
            filmes.put(Integer.parseInt(linha[0]), filme);
        }
        clear();

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
            series.put(Integer.parseInt(linha[0]), serie);
        }
        clear();
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
            System.out.print("| 1 - Mostrar todas as séries |\n");
            System.out.print("| 2 - Mostrar todos os filmes |\n");
            System.out.print("| 3 - Informações do usuário  |\n");
            System.out.print("| 4 - Voltar                  |\n");
            System.out.print("|-----------------------------|\n\n");

            System.out.print("Digite uma opção: ");
            return Integer.parseInt(key.nextLine());

        
        } catch(Exception e) {
            return -1;
        }
    }

    public static int subMenu2() 
    {
        System.out.print("|-----------------------------|\n");
        System.out.print("|    Selecione uma opção      |\n");
        System.out.print("|-----------------------------|\n");
        System.out.print("| 1 - Séries assistidas       |\n");
        System.out.print("| 2 - Séries para assistir    |\n");
        System.out.print("| 3 - Dados do usuário        |\n");
        System.out.print("| 4 - Voltar                  |\n");
        System.out.print("|-----------------------------|\n\n");

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
                    if(clientes.get(login) != null)
                    {
                        print("O login " + login + " já está cadastrado no sistema.");
                        TimeUnit.SECONDS.sleep(3);
                    } else {
                        //PASSWORD
                        System.out.print("Password: ");
                        password = key.nextLine();
                    }
                    //Criando o objeto
                    Cliente cliente = new Cliente(nome, login, password);
                    clientes.put(login,cliente);
                    clear();
                    opcao = -1;
                break;
                case 2: // LOGIN
                    clear();
                    print("Acessar Sistema:\n");
                    System.out.print("Login: ");
                    login = key.nextLine().toLowerCase();

                    if(clientes.get(login) == null) 
                    {
                        print("O login informado não existe.");
                        TimeUnit.SECONDS.sleep(3);
                        clear();
                    } else {
                        System.out.print("Password: ");
                        password = key.nextLine();
                        if(clientes.get(login).getPassword().equals(password))
                        {
                            clear();
                            Usuario eu = clientes.get(login);
                            print("Login efetuado com sucesso!\n");
                            print("Bem vindo(a) " + eu.getNome() + "!");
                            TimeUnit.SECONDS.sleep(2);
                            clear();
                            
                            do {
                                opcao = menu2();
                                switch(opcao) {
                                    case 1: //Series
                                        clear();
                                        series.forEach((id, serie) -> {
                                            print(id + " - " + serie.getNome());
                                        });
                                        print("\n\nDeseja ver detalhes de uma série?(S/N)");
                                        if(key.nextLine().toLowerCase().equals("s")){
                                            System.out.print("\nInforme o código da série: ");
                                            print(series.get(Integer.parseInt(key.nextLine())).toString());
                                        }
                                        print("\n\nPressione qualquer tecla para continua...");
                                        key.nextLine();
                                        clear();
                                        opcao = -1;
                                    break;
                                    case 2: //Filmes
                                        clear();
                                        series.forEach((id, serie) -> {
                                            print(id + " - " + serie.getNome());
                                        });
                                        print("\n\nDeseja ver detalhes do filme?(S/N)");
                                        if(key.nextLine().toLowerCase().equals("s")){
                                            System.out.print("\nInforme o código do filme: ");
                                            print(filmes.get(Integer.parseInt(key.nextLine())).toString());
                                        }
                                        print("\n\nPressione qualquer tecla para continua...");
                                        key.nextLine();
                                        clear();
                                        opcao = -1;
                                    break;
                                    case 3:
                                        do {
                                            clear();
                                            opcao = subMenu2();
                                            switch(opcao){
                                                case 1: //Series assistidas
                                                    eu.mostrarListaDeSeriesAssistidas();
                                                    print("\n\nDeseja avaliar uma série?(S/N)");
                                                    if(key.nextLine().toLowerCase().equals("s")){
                                                        System.out.print("\nInforme o código da série que deseja avaliar: ");
                                                        int opt = Integer.parseInt(key.nextLine());
                                                        if(!eu.verificaSeJaAvaliou(opt)) { 
                                                            Midia esta = series.get(opt);
                                                            if(esta == null) {
                                                                print("Erro... Esta série não existe!");
                                                            } else {
                                                                System.out.print("Avalie a série: '" + esta.getNome() + "' com uma nota de 0 à 5: ");
                                                                int nota = Integer.parseInt(key.nextLine());
                                                                if(nota >= 0 && nota <= 5){
                                                                    eu.addAvaliada(series.get(opt));
                                                                    esta.avaliar(nota);
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
                                                            print("Erro... Esta série já foi avaliada por você!");
                                                            TimeUnit.SECONDS.sleep(1);
                                                            clear();
                                                            opcao = -1;
                                                            break;
                                                        }
                                                    }
                                                    print("\n\nPressione qualquer tecla para continua...");
                                                    key.nextLine();
                                                    clear();
                                                    opcao = -1;
                                                break;
                                                case 2: //Series para assistir
                                                    eu.mostrarListaDeSeriesParaAssistir();
                                                    print("\n\nPressione qualquer tecla para continua...");
                                                    key.nextLine();
                                                    clear();
                                                    opcao = -1;
                                                break;
                                                case 3: //Dados do cliente
                                                System.out.println(eu.toString());
                                                    print("\n\nPressione qualquer tecla para continua...");
                                                    key.nextLine();
                                                    TimeUnit.SECONDS.sleep(1); // Timer de 1 segundo para limpar a tela
                                                    clear();
                                                    opcao = -1;
                                                break;
                                                case 4:// Voltar
                                                    clear();
                                                    opcao = 1;
                                                    break;
                                            }
                                        } while(opcao < 0);
                                        opcao = -1;
                                        break;
                                    case 4: // Voltar
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
}