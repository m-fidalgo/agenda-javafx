package agenda.services.impl.importers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import agenda.entities.Contact;
import agenda.repositories.interfaces.AgendaRepository;
import agenda.services.interfaces.ContactImporterService;

public class FileAndPathsImporterContactService implements ContactImporterService {
  @Override
  public void importar(String fileName, AgendaRepository<Contact> agendaRepo) throws IOException, SQLException {
    Path path = Paths.get(fileName);
    List<String> data = Files.readAllLines(path);

    for(String line : data){
      String[] info = line.split(";");
      Contact contato = new Contact();
      contato.setNome(info[1]);
      contato.setIdade(Integer.parseInt(info[2]));
      contato.setTel(info[3]);
      agendaRepo.insert(contato);
    }
  }
}
