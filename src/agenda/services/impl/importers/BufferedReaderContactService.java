package agenda.services.impl.importers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import agenda.entities.Contact;
import agenda.repositories.interfaces.AgendaRepository;
import agenda.services.interfaces.ContactImporterService;

public class BufferedReaderContactService implements ContactImporterService {
  @Override
  public void importar(String fileName, AgendaRepository<Contact> agendaRepo) throws IOException, SQLException {
    try(FileReader fr = new FileReader(fileName)){
      try(BufferedReader reader = new BufferedReader(fr)){
        //modo 1
        while(reader.read() != -1){
          String data = reader.readLine();
          String[] info = data.split(";");
          Contact contato = new Contact();
          contato.setNome(info[1]);
          contato.setIdade(Integer.parseInt(info[2]));
          contato.setTel(info[3]);
          agendaRepo.insert(contato);
        }

        //modo 2
        /*String data = "";
        while((data = reader.readLine()) != null){
          String data = reader.readLine();
          String[] info = data.split(";");
          Contact contato = new Contact(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]), info[3]);
          agendaRepo.insert(contato);
        }*/
      }
    }
  }
}
