
import java.util.HashMap;

public class Cliente {
  private String nome;
  private Integer ID = 0;
  private String email;
  private HashMap<Integer, Serie> seriesAssistidas;
  private HashMap<Integer, Serie> assistirDepois;

  Cliente() {
    this("Desconhecido", "email@email.com");
  }

  Cliente(String nome, String email) {
    this.nome = nome;
    this.email = email;
    this.ID = hashCode();
    seriesAssistidas = new HashMap<Integer, Serie>();
  }

  @Override
  public int hashCode() {
    final int controle = 31;
    int res = 1;
    res = controle * res + ((this.nome == null) ? 0 : nome.hashCode());
    res = controle * res + ((this.email == null) ? 0 : email.hashCode());
    return res;
  }

  public void adicionarAssistidas(Serie assistida) {
    seriesAssistidas.put(assistida.getID(), assistida);
  }

  public void adicionarAssistirDepois(Serie desejada) {
    if (seriesAssistidas.get(desejada.getID()) == null) {
      assistirDepois.put(desejada.getID(), desejada);
    }
  }

  public int getID() {
    return this.ID;
  }
}
