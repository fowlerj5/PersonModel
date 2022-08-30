import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args){
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                System.out.printf("%-20s","ID#");
                System.out.printf("%-20s","Firstname");
                System.out.printf("%-20s","Lastname");
                System.out.printf("%-20s","Title");
                System.out.printf("%-20s","YOB");
                System.out.println();
                for (int i = 0; i < 100; i++){
                    System.out.print("=");
                }
                System.out.println();
                while(reader.ready())
                {
                    int recn = 0;
                    rec = reader.readLine();
                    String[] arrOfRec = rec.split(", ",-1);
                    for(String a : arrOfRec) {
                        System.out.printf("%-20s", a);
                        recn++;
                        if(recn==5){
                            System.out.println();
                            recn = 0;
                        }
                    }
                }
                reader.close();
                System.out.println("\n\nData file read!");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}