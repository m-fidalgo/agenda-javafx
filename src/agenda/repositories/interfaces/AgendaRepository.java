package agenda.repositories.interfaces;

import java.util.List;

public interface AgendaRepository<T> {
  List<T> select();
  void insert(T entity);
  void update(T entity);
  void delete(T entity);
}
