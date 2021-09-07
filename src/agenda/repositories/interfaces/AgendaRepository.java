package agenda.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface AgendaRepository<T> {
  List<T> select() throws SQLException;
  void insert(T entity) throws SQLException;
  void update(T entity) throws Exception;
  void delete(T entity) throws Exception;
}
