import java.util.*;
import java.io.*;
public class leitor {
    public void lerArquivo() {
        File f = new File("ARQUIVOS");
        File[] arquivos = f.listFiles(); //retorna um array de Files
        String[] nomes = f.list(); //retorna o nome dos arquivos em Strings
    }
}
