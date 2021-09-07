package agenda.entities;

public class Contact {
  private int id;
  private String nome;
  private int idade;
  private String tel;

  public Contact() {
    
  }

  public Contact(int id, String nome, int idade, String tel){
    this.id = id;
    this.nome = nome;
    this.idade = idade;
    this.tel = tel;
  }

  public int getId() {
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel){
    this.tel = tel;
  }

}
