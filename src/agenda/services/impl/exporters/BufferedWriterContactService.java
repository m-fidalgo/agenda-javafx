package agenda.services.impl.exporters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import agenda.entities.Contact;
import agenda.services.interfaces.ContactExporterService;

public class BufferedWriterContactService implements ContactExporterService {
  @Override
  public void export(List<Contact> contatos, String fileName) throws IOException{
    try(FileWriter fw = new FileWriter(fileName)){
      try(BufferedWriter writer = new BufferedWriter(fw)){
        List<String> exportData = contatos.stream()
          .map(contato -> String.format("%d;%s;%d;%s", contato.getId(), contato.getNome(), contato.getIdade(), contato.getTel()))
          .collect(Collectors.toList());
        
        for(String line : exportData){
          writer.write(line);
          writer.newLine();
        }
        
        writer.flush();
      }
    }
  }
}
