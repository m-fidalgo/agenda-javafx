package agenda.services.interfaces;

import java.io.IOException;
import java.sql.SQLException;

import agenda.entities.Contact;
import agenda.repositories.interfaces.AgendaRepository;

public interface ContactImporterService {
  void importar(String fileName, AgendaRepository<Contact> agendaRepo) throws IOException, SQLException;
}
