import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MyFile {

    public static final Path FROM_PATH = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT.dat.txt");
    public static final Path TO_PATH = Paths.get("C:\\Users\\admin\\Desktop\\Go2ITcopy.dat.txt");

    public static void copyToFile() throws IOException {
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
                System.out.println("Data was copied into the file");

            }

        }else{
            System.out.println("File does not exist!");
        }
    }

    public static void readFileFromGoogle() throws IOException {
        URL url = new URL("https://www.w3schools.com/w3css/4/w3.css");

        URLConnection connection = url.openConnection();
        InputStream stream =connection.getInputStream();
        try(BufferedReader reader =
                new BufferedReader(new InputStreamReader(stream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\downloadedFromW3School.css")))){
            String line;
            while((line = reader.readLine()) != null){
                writer.write(line);
            }
        }

    }

    public static void createTreeCopiesOfFile() throws IOException {
        Path filePath = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT.dat.txt");

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(filePath.toFile())))
        ) {
            String line;
            reader.mark(10000);
            BufferedWriter writer = null;
            for (int i = 1; i <= 3; i++) {
                Path copyFile = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT_copy"+i+".dat.txt");
                try {
                    writer = Files.newBufferedWriter(copyFile, Charset.forName("UTF-8"), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                    }
                    System.out.println("File is saved");
                } finally {
                    writer.close();
                    reader.reset();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        copyToFile();
          readFileFromGoogle();
//        createTreeCopiesOfFile();

    }
}
