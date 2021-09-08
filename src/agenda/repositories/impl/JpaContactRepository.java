package agenda.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import agenda.entities.Contact;
import agenda.repositories.factories.JpaConnectionFactory;
import agenda.repositories.interfaces.AgendaRepository;

public class JpaContactRepository implements AgendaRepository<Contact> {
  @Override
  public List<Contact> select() {
    List<Contact> contatos = new ArrayList<Contact>();

    EntityManager em = null;
    try{
      em = JpaConnectionFactory.getEntityManager();
      contatos = em.createQuery("from Contact", Contact.class).getResultList();
    } finally {
      if(em != null)
        em.close();
    }

    return contatos;
  }

  @Override
  public void insert(Contact entity){
    EntityManager em = null;

    try {
      em = JpaConnectionFactory.getEntityManager();
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    } finally {
      if(em != null)
        em.close();
    }
  }

  @Override
  public void update(Contact entity) throws Exception{
    List<Contact> contatos = new ArrayList<Contact>();
    contatos = this.select();

    Optional<Contact> contact = contatos.stream().filter(contato -> contato.getNome().equals(entity.getNome())).findFirst();
    
    if(contact.isPresent()){
      EntityManager em = null;
      entity.setId(contact.get().getId());

      try {
        em = JpaConnectionFactory.getEntityManager();
        em.getTransaction().begin();
        em.merge(entity); //método da jpa, invoca um select primeiro
        //em.unwrap(Session.class).update(entity); // método do hibernate, faz o update direto
        em.getTransaction().commit();
      } finally {
        if(em != null)
          em.close();
      }
    } else throw new Exception("Não encontrado");  
  }

  @Override
  public void delete(Contact entity) throws Exception {
    List<Contact> contatos = new ArrayList<Contact>();
    contatos = this.select();

    Optional<Contact> contact = contatos.stream().filter(contato -> contato.getNome().equals(entity.getNome())).findFirst();
    
    if(contact.isPresent()){
      EntityManager em = null;

      try {
        em = JpaConnectionFactory.getEntityManager();
        em.getTransaction().begin();

        if(em.contains(contact.get()))
          em.remove(contact.get());
        else {
          Contact newContact = em.getReference(contact.get().getClass(), contact.get().getId());
          em.remove(newContact);
        }

        em.getTransaction().commit();
      } finally {
        if(em != null)
          em.close();
      }

    } else throw new Exception("Não encontrado");
  }
}
