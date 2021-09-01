package agenda.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import agenda.entities.Contact;
import agenda.repositories.interfaces.AgendaRepository;

public class ContactRepository implements AgendaRepository<Contact> {
  private static List<Contact> contatos = new ArrayList<Contact>();
  
  @Override
  public List<Contact> select() {
    return contatos;
  }

  @Override
  public void insert(Contact entity){
    contatos.add(entity);
  }

  @Override
  public void update(Contact entity){
    Optional<Contact> original = contatos.stream().filter(contato -> contato.getNome().equals(entity.getNome())).findFirst();
    
    if(original.isPresent()){
      original.get().setIdade(entity.getIdade());
      original.get().setTel(entity.getTel());
    }
  }

  @Override
  public void delete(Contact entity){
    contatos.remove(entity);
  }
}
