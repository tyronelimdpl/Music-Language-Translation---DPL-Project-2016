package audioSynthesis;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class OpenFile {

  
  public static final String readFile(String filePath) {
      File f = new File(filePath);
      if(!f.exists()) {
          System.err.println("File " + f.getName() + " not found");
          return null;
      }
      StringBuilder buffer = new StringBuilder();
      byte[] bytes = new byte[1024];
      int readBytesCount = 0;
      try {
          //@SuppressWarnings("resource")
		InputStream in = new BufferedInputStream(new FileInputStream(f));
          while( (readBytesCount = in.read(bytes)) != -1) {
              buffer.append(new String(bytes, 0, readBytesCount));     
          }
          in.close();
      } catch(Exception exc) {
          exc.printStackTrace();
          return null;
      }
      return buffer.toString();
  }
  
} 

