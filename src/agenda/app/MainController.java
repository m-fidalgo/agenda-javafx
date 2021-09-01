package agenda.app;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import agenda.entities.Contact;
import agenda.repositories.impl.ContactRepository;
import agenda.repositories.interfaces.AgendaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController implements Initializable{
  @FXML
  private TableView<Contact> tabelaContatos;

  @FXML
  private Button btnInsert;

  @FXML
  private Button btnUpdate;

  @FXML
  private Button btnDelete;

  @FXML
  private TextField txtNome;

  @FXML
  private TextField txtIdade;

  @FXML
  private TextField txtTel;

  @FXML
  private Button btnCancelar;

  @FXML
  private Button btnSalvar;

  @Override
  public void initialize(URL location, ResourceBundle resources){
    this.tabelaContatos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    carregarTabelaContatos();
  }

  private void carregarTabelaContatos() {
    AgendaRepository<Contact> repContato = new ContactRepository();
    List<Contact> contatos = repContato.select();

    if(contatos.isEmpty()){
      Contact contato = new Contact();
      contato.setNome("Teste");
      contato.setIdade(20);
      contato.setTel("12 12345678");
      contatos.add(contato);
    }

    //observable pq assim ele repara qd a lista muda e já atualiza
    ObservableList<Contact> contatosObservableList = FXCollections.observableArrayList(contatos);
    this.tabelaContatos.getItems().setAll(contatosObservableList);
  }
}
