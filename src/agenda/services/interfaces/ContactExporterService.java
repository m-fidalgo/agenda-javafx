package agenda.services.interfaces;

import java.io.IOException;
import java.util.List;

import agenda.entities.Contact;

public interface ContactExporterService {
  void export(List<Contact> contatos, String fileName) throws IOException;
}
