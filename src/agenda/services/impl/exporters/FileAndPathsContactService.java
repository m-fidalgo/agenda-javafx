package agenda.services.impl.exporters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import agenda.entities.Contact;
import agenda.services.interfaces.ContactExporterService;

public class FileAndPathsContactService implements ContactExporterService {
  @Override
  public void export(List<Contact> contatos, String fileName) throws IOException {
    String data = contatos.stream()
      .map(contato -> String.format("%d;%s;%d;%s\n", contato.getId(), contato.getNome(), contato.getIdade(), contato.getTel()))
      .collect(Collectors.joining());

    Path path = Paths.get(fileName);
    byte[] info = data.getBytes();
    Files.write(path, info);    
  }
}