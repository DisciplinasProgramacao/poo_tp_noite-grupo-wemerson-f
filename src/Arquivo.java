import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Arquivo {
  private String path;
  private ArrayList<String> doc = new ArrayList<String>();

    public Arquivo(String path)
    {
      this.path = path;
      this.CsvReader();
    }

  /**
  * Lê o documento txt informado no parâmetro
  * @param path
  */
  public void CsvReader()
    {
        String file = path;
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while((line = br.readLine()) != null){
              //Remove caracter "ï»¿" 
              line = line.replace("ï»¿", "");
              if(!line.contains("#")) {
                this.doc.add(line);
              }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

  public ArrayList<String> getDoc()
  {
    return this.doc;
  }
    
}
