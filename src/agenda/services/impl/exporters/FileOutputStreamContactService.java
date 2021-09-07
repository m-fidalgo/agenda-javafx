package agenda.services.impl.exporters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import agenda.entities.Contact;
import agenda.services.interfaces.ContactExporterService;

public class FileOutputStreamContactService implements ContactExporterService {
  @Override
  public void export(List<Contact> contatos, String fileName) throws IOException { 
    try(FileOutputStream fStream = new FileOutputStream(fileName)){
      List<String> exportData = contatos.stream()
          .map(contato -> String.format("%d;%s;%d;%s\n", contato.getId(), contato.getNome(), contato.getIdade(), contato.getTel()))
          .collect(Collectors.toList());
        
      for(String line : exportData){
        fStream.write(line.getBytes());
      }
    }
  }
}
