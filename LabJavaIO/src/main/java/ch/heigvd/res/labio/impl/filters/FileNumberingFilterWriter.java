package ch.heigvd.res.labio.impl.filters;

import ch.heigvd.res.labio.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {


  private int count;
  private boolean flag_debut;
  private boolean flag_fin;
  private String line;

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    count = 1;
    flag_debut = true;
    flag_fin = false;
    line = count+"\t";
    count++;

  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    str=str.substring(off,off+ len);

    String[] newLines = new String[2];

    newLines = Utils.getNextLine(str);

    while(!newLines[0].equals("")){

      line = line.concat(newLines[0]+count+"\t");
      count++;

      newLines = Utils.getNextLine(newLines[1]);

    }

   line= line.concat(newLines[1]);

    super.write(line,0,line.length());
    line = "";
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    String str = new String(cbuf);
    this.write(str,off,len);

  }

  @Override
  public void write(int c) throws IOException {

    if(flag_debut){

      super.write('1');
      super.write('\t');

      flag_debut=false;
    }

    if(flag_fin){

      if(c == '\n') {

        super.write(c);
        super.write((char)(count + '0'));
        super.write('\t');
        count++;
      }
      else{
        super.write((char)(count + '0'));
        super.write('\t');
          count++;
        super.write(c);
        }
      flag_fin=false;
      return;
      }

    if(c == '\r'){
      flag_fin = true;
    }
    if(c =='\n'){

      super. write(c);
      super.write((char)(count + '0'));
      super.write('\t');
      count++;
      return;
  }
    super. write(c);
  }

}
