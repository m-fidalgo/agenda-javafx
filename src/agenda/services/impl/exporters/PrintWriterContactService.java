package agenda.services.impl.exporters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import agenda.entities.Contact;
import agenda.services.interfaces.ContactExporterService;

public class PrintWriterContactService implements ContactExporterService {
  @Override
  public void export(List<Contact> contatos, String fileName) throws IOException {
    try(FileWriter fw = new FileWriter(fileName)){
      try(PrintWriter printWriter = new PrintWriter(fw)){
        for(Contact contato: contatos){
          printWriter.println(String.format("%d;%s;%d;%s", contato.getId(), contato.getNome(), contato.getIdade(), contato.getTel()));
          //ou 
          //printWriter.printf(("%d;%s;%d;%s\n", contato.getId(), contato.getNome(), contato.getIdade(), contato.getTel()));
        }        
      }
    }
  }
}
