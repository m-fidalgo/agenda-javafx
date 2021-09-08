package agenda.repositories.factories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConnectionFactory {
  private static EntityManagerFactory EM_FACTORY = null;

  public static EntityManager getEntityManager() {
    if(EM_FACTORY == null)
      EM_FACTORY = Persistence.createEntityManagerFactory("agenda");

    return EM_FACTORY.createEntityManager();
  }
}
