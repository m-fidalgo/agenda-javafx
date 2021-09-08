package agenda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contatos")
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
  private int id;

  @Column(name = "nome", nullable = false, length = 200)
  private String nome;

  @Column(name = "idade", nullable = false)
  private int idade;

  @Column(name = "tel", nullable = false, length = 12)
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
