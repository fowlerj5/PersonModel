import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList<String> ID = new ArrayList<>();
        ArrayList<String> FirstName = new ArrayList<>();
        ArrayList<String> LastName = new ArrayList<>();
        ArrayList<String> Title = new ArrayList<>();
        ArrayList<Integer> YearOfBirth = new ArrayList<>();
        boolean yn = true;
        ArrayList <String>recs = new ArrayList<>();
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.csv");

        do{
            ID.add(SafeInput.getNonZeroLenString(in, "ID"));
            FirstName.add(SafeInput.getNonZeroLenString(in, "First name"));
            LastName.add(SafeInput.getNonZeroLenString(in, "Last name"));
            Title.add(SafeInput.getNonZeroLenString(in, "Title"));
            YearOfBirth.add(SafeInput.getInt(in, "Birth year"));
            yn = SafeInput.getYNConfirm(in, "Are there more records to enter?");
        }while(yn);
        for(int n = 0; n < ID.size(); n++){
            recs.add(ID.get(n)+", "+FirstName.get(n)+", "+LastName.get(n)+", "+Title.get(n)+", "+YearOfBirth.get(n));
        }
        try{
            OutputStream out =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(out));
            for(String rec : recs){
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}