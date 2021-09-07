package agenda.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import agenda.entities.Contact;
import agenda.repositories.factories.JdbcConnectionFactory;
import agenda.repositories.interfaces.AgendaRepository;

public class JdbcContactRepository implements AgendaRepository<Contact> {
    
  @Override
  public List<Contact> select() throws SQLException {
    List<Contact> contatos = new ArrayList<Contact>();

    try(Connection connection = JdbcConnectionFactory.createConnection()){
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM contatos");
  
      while(rs.next()){
        contatos.add(new Contact(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("tel")));
      }

    } 

    return contatos;
  }

  @Override
  public void insert(Contact entity) throws SQLException {
    try(Connection connection = JdbcConnectionFactory.createConnection();){
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contatos (nome, idade, tel) VALUES(?, ?, ?)");
      preparedStatement.setString(1, entity.getNome());
      preparedStatement.setInt(2, entity.getIdade());
      preparedStatement.setString(3, entity.getTel());
      preparedStatement.execute();
    } 
  }

  @Override
  public void update(Contact entity) throws Exception {
    List<Contact> contatos = new ArrayList<Contact>();
    contatos = this.select();

    Optional<Contact> original = contatos.stream().filter(contato -> contato.getNome().equals(entity.getNome())).findFirst();
    
    if(original.isPresent()){
      try(Connection connection = JdbcConnectionFactory.createConnection()){
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contatos SET idade = ?, tel = ? WHERE id = ?");
        preparedStatement.setInt(1, entity.getIdade());
        preparedStatement.setString(2, entity.getTel());
        preparedStatement.setInt(3, original.get().getId());
        preparedStatement.execute();
      }
    } else throw new Exception("Não encontrado");     
  }

  @Override
  public void delete(Contact entity) throws Exception {
    List<Contact> contatos = new ArrayList<Contact>();
    contatos = this.select();

    Optional<Contact> original = contatos.stream().filter(contato -> contato.getNome().equals(entity.getNome())).findFirst();
    
    if(original.isPresent()){
      try(Connection connection = JdbcConnectionFactory.createConnection()){
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from contatos WHERE id = ?");
        preparedStatement.setInt(1, original.get().getId());
        preparedStatement.execute();
      }
    } else throw new Exception("Não encontrado");    
  }
}
