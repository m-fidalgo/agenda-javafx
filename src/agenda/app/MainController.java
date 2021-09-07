package agenda.app;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import agenda.entities.Contact;
import agenda.repositories.impl.JdbcContactRepository;
import agenda.repositories.interfaces.AgendaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

  private Boolean isInsert;

  private Contact selectedContact;

  @Override
  public void initialize(URL location, ResourceBundle resources){
    this.tabelaContatos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    habilitarEdicao(false);

    this.tabelaContatos.getSelectionModel().selectedItemProperty().addListener((observador, contatoAntigo, contatoNovo) -> {
      if(contatoNovo != null){
        txtNome.setText(contatoNovo.getNome());
        txtIdade.setText(String.valueOf(contatoNovo.getIdade()));
        txtTel.setText(contatoNovo.getTel());
        selectedContact = contatoNovo;
      }
    });

    carregarTabelaContatos();
  }

  private void carregarTabelaContatos() {
    try {
      AgendaRepository<Contact> repContato = new JdbcContactRepository();
      List<Contact> contatos = repContato.select();
      
      //observable pq assim ele repara qd a lista muda e já atualiza
      ObservableList<Contact> contatosObservableList = FXCollections.observableArrayList(contatos);
      this.tabelaContatos.getItems().setAll(contatosObservableList);
    } catch (Exception e){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Erro ao obter os contatos: " + e.getMessage());
      alert.showAndWait();
    }
  }

  private void habilitarEdicao(Boolean isEdicaoHabilitada){
    this.txtNome.setDisable(!isEdicaoHabilitada);
    this.txtIdade.setDisable(!isEdicaoHabilitada);
    this.txtTel.setDisable(!isEdicaoHabilitada);
    this.btnCancelar.setDisable(!isEdicaoHabilitada);
    this.btnSalvar.setDisable(!isEdicaoHabilitada);
    this.btnInsert.setDisable(isEdicaoHabilitada);
    this.btnUpdate.setDisable(isEdicaoHabilitada);
    this.btnDelete.setDisable(isEdicaoHabilitada);
    this.tabelaContatos.setDisable(isEdicaoHabilitada);
  }

  private void cleanFields() {
    this.txtNome.setText("");
    this.txtIdade.setText("");
    this.txtTel.setText("");
  }

  public void btnInsert_Action() {
    this.isInsert = true;
    this.cleanFields();
    habilitarEdicao(true);
  }

  public void btnUpdate_Action() {
    this.isInsert = false;
    habilitarEdicao(true);
    this.txtNome.setDisable(true);
  }

  public void btnDelete_Action() {
    try{
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmação");
      alert.setHeaderText("Tem certeza de que deseja excluir o contato?");
      Optional<ButtonType> resp = alert.showAndWait();

      if(resp.isPresent() && resp.get() == ButtonType.OK){
        AgendaRepository<Contact> repContato = new JdbcContactRepository();
        repContato.delete(selectedContact);
        carregarTabelaContatos();
        this.tabelaContatos.getSelectionModel().selectFirst();
      }
    } catch (Exception e){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Erro ao deletar o contatos: " + e.getMessage());
      alert.showAndWait();
    }
  }

  public void btnCancelar_Action() {
    habilitarEdicao(false);
    this.cleanFields();
    this.tabelaContatos.getSelectionModel().selectFirst();
  }

  public void btnSalvar_Action() {
    try {
      AgendaRepository<Contact> repContato = new JdbcContactRepository();
      Contact contato = new Contact();
      contato.setNome(txtNome.getText());
      contato.setIdade(Integer.parseInt(txtIdade.getText()));
      contato.setTel(txtTel.getText());

      if(this.isInsert)
        repContato.insert(contato);
      else repContato.update(contato);
  
      habilitarEdicao(false);
      carregarTabelaContatos();
      this.tabelaContatos.getSelectionModel().selectFirst();
    } catch (Exception e){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Erro ao " + (this.isInsert ? "inserir" : "atualizar") + " o contato: " + e.getMessage());
      alert.showAndWait();
    }
  }
}
