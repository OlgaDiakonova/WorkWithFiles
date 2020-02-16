import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFile {

    public static final Path FROM_PATH = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT.dat.txt");
    public static final Path TO_PATH = Paths.get("C:\\Users\\admin\\Desktop\\Go2ITcopy.dat.txt");

    public static void main(String[] args) throws IOException {

        File myFile = FROM_PATH.toFile();
        File copyFile = TO_PATH.toFile();
        if(myFile.exists()){
            System.out.println("File exists!");
            try(FileReader fileReader = new FileReader(myFile);
                FileWriter fileWriter = new FileWriter(copyFile)) {
                int i;
                while ((i = fileReader.read()) != -1){
                    fileWriter.write(i);
                }

            }

        }else{
            System.out.println("File does not exist!");
        }
    }
}
