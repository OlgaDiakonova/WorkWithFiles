import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFile {

    public static final Path ABS_PATH = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT.dat.txt");

    public static void main(String[] args) throws IOException {

        File myFile = new File(String.valueOf(ABS_PATH));
        File copyFile = new File("C:\\Users\\admin\\Desktop\\Go2ITcopy.dat.txt");
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
