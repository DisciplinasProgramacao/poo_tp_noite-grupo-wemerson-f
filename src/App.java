import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *  • O serviço precisa manter dados de um número grande de clientes, cada um com seu login único.
 *  • Igualmente, os dados de séries precisam ser armazenados. Uma série terá cadastrados os dados seguintes:
 *  nome, idioma e gênero. É preciso registrar quantos clientes já assistiram uma série.
 *  • Os clientes poderão adicionar séries a duas listas em sua conta: uma contendo séries para assistir futuramente
 *  e outra para registrar e consultar suas séries já assistidas.
 *  • O cliente pode realizar buscas em suas listas, ou na lista geral de séries do sistema. A busca pode ser por nome,
 *  gênero ou idioma.
 */



public class App {
    static ArrayList<Cliente> logins = new ArrayList<Cliente>();
    public static Scanner key = new Scanner(System.in);

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
            System.out.print("| 1 - Novo Cadastro           |\n");
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
            } catch(Exception e) {}
        }
        return -1;
    }

    public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				System.out.println(linha);

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
	}

    public static void main(String[] args) throws Exception {
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
                            print("Login efetuado com sucesso!");
                            TimeUnit.SECONDS.sleep(3);
                            clear();
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
