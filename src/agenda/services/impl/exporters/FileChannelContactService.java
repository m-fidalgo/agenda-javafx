package agenda.services.impl.exporters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;
import java.util.stream.Collectors;

import agenda.entities.Contact;
import agenda.services.interfaces.ContactExporterService;

public class FileChannelContactService implements ContactExporterService {
  @Override
  public void export(List<Contact> contatos, String fileName) throws IOException { 
    try(FileOutputStream fStream = new FileOutputStream(fileName)){
      try(FileChannel fileChannel = fStream.getChannel()){
        FileLock lock = fileChannel.tryLock();

        List<String> exportData = contatos.stream()
            .map(contato -> String.format("%d;%s;%d;%s\n", contato.getId(), contato.getNome(), contato.getIdade(), contato.getTel()))
            .collect(Collectors.toList());
        
        for(String line : exportData){
          byte[] info = line.getBytes();
          ByteBuffer byteBuffer = ByteBuffer.allocate(info.length);
          byteBuffer.put(info);
          byteBuffer.flip(); // retorna à posição 0
          fileChannel.write(byteBuffer);
        }

        lock.release();
      }
    }
  }
}
