import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class App {
    final public static String PATH = "./doc/POO_Audiencia.csv";
    final public static String PATH_ESPECTADORES = "./doc/POO_Espectadores.csv";
    final public static String PATH_SERIES = "./doc/POO_Series.csv";
    final public static String PATH_FILMES = "./doc/POO_Filmes.csv";
    public static ArrayList<Cliente> logins = new ArrayList<Cliente>();
    public static ArrayList<Serie> series = new ArrayList<Serie>();
    public static ArrayList<Filme> filmes = new ArrayList<Filme>();
    public static Scanner key = new Scanner(System.in);

    /**
     * Classe que inicia os metodos fundamentais para o funcionamento do projeto
     */
    public static void init()
    {
        carregaEspectadores();
        carregaSeries();
        carregarFilmes();

        //Ordena os ArrayList
        logins.sort((l1, l2) -> l1.getNome().compareTo(l2.getNome()));
        series.sort((s1, s2) -> s1.getNome().compareTo(s2.getNome()));
        filmes.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));
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
            String linha[];
            for(int i=0; i < arq.size(); i++)
            {
                linha = arq.get(i).split(";");
                Cliente user = logins.get(verificaLogin(linha[0]));
            
                if(linha[1].toLowerCase().equals("f")) {
                    user.addParaAssistir(Integer.parseInt(linha[2]));
                } else {
                    user.addAssistidas(Integer.parseInt(linha[2]));
                    series.get(verificaSerie(Integer.parseInt(linha[2]))).atualizaViews();
                }
            }
            clear();
        }catch(Exception e) {

        }
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
            Cliente cliente = new Cliente(linha[0], linha[1], linha[2]);
            logins.add(cliente);
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
            Filme filme = new Filme(Integer.parseInt(linha[0]), linha[1], new Data(linha[2]), Integer.parseInt(linha[3]));
            filmes.add(filme);
        }
        clear();

    }

    /**
     * Busca a posição da série no array de séries e retorna o index. Se não existir retorna -1
     * @param id
     * @return
     */
    public static int verificaSerie(int id)
    {
        for (int i = 0; i<series.size(); i++) {
            if(series.get(i).getId() == id){
                return i;
            } 
        }
        return -1;
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
            series.add(serie);
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
            System.out.print("|        Bem Vindo!           |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("| 1 - Mostrar todas as séries |\n");
            System.out.print("| 2 - Mostrar todos os filmes |\n");
            System.out.print("| 3 - Séries assistidas       |\n");
            System.out.print("| 4 - Séries para assistir    |\n");
            System.out.print("| 5 - Voltar                  |\n");
            System.out.print("|-----------------------------|\n\n");

            System.out.print("Digite uma opção: ");
            return Integer.parseInt(key.nextLine());

        
        } catch(Exception e) {
            return -1;
        }
    }
    /**
     * Escreve na tela
     * @param texto String
     */
    public static void print(String texto)
    {
        System.out.println(texto);
    }

    /**
     * Verifica se já existe o login cadastrado no sistema
     * @param login
     * @return boolean
     */
    public static int verificaLogin(String login)
    {   
        for (int i=0; i<= logins.size(); i++) {
            try{
                if(logins.get(i).getLogin().equals(login.toLowerCase())) {
                    return i;
                }
            } catch(Exception e) { continue; }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        init();
        clear();
        //Variáveis:
        String nome = "", password = "", login = "";
        int opcao = -1, indexCliente = -1;

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
                    indexCliente = verificaLogin(login);
                    if(indexCliente != -1)
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
                    logins.add(cliente);
                    clear();
                    opcao = -1;
                break;
                case 2: // LOGIN
                    clear();
                    print("Acessar Sistema:\n");
                    System.out.print("Login: ");
                    login = key.nextLine();
                    indexCliente = verificaLogin(login);

                    if(indexCliente == -1) 
                    {
                        print("O login informado não existe.");
                        TimeUnit.SECONDS.sleep(3);
                        clear();
                    } else {
                        System.out.print("Password: ");
                        password = key.nextLine();
                        if(logins.get(indexCliente).getPassword().equals(password))
                        {
                            clear();
                            Cliente eu = logins.get(indexCliente);
                            print("Login efetuado com sucesso!\n");
                            print("Bem vindo " + eu.getNome() + "!");
                            TimeUnit.SECONDS.sleep(3);
                            clear();
                            
                            do {
                                opcao = menu2();
                                switch(opcao) {
                                    case 1: //Series
                                        clear();
                                        int count = 0;
                                        for (Serie serie : series) {
                                            System.out.println(++count + " - " + serie.getNome());
                                        }
                                        System.out.println("\n\nPressione qualquer tecla para continua...");
                                        key.nextLine();
                                        clear();
                                        opcao = -1;
                                    break;
                                    case 2: //Filmes
                                        clear();
                                        int count2 = 0;
                                        for (Filme filme : filmes) {
                                            System.out.println(++count2 + " - " + filme.getNome());
                                        }
                                        System.out.println("\n\nPressione qualquer tecla para continua...");
                                        key.nextLine();
                                        clear();
                                        opcao = -1;
                                    break;
                                    case 3: //Series assistidas
                                        eu.mostrarListaDeSeriesAssistidas();
                                        System.out.println("\n\nPressione qualquer tecla para continua...");
                                        key.nextLine();
                                        clear();
                                        opcao = -1;
                                    break;
                                    case 4: //Series para assistir
                                        eu.mostrarListaDeSeriesParaAssistir();
                                        System.out.println("\n\nPressione qualquer tecla para continua...");
                                        key.nextLine();
                                        clear();
                                        opcao = -1;
                                    break;
                                    case 5: // Voltar
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
                    print("Ops! Não existe o valor " + opcao);
                    TimeUnit.SECONDS.sleep(1); // Timer de 1 segundo para limpar a tela
                    clear();
                    opcao = -1;
                break;
                
            }
        } while(opcao < 0);


        
        key.close();
    }
}
