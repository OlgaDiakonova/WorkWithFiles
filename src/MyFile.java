import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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
                writer.write(line + "\n");
            }
        }

    }

    public static void downloadPDF() throws IOException {

        URL webAddress = new URL("http://www.ukrstat.gov.ua/express/expr2019/03/38.pdf");
        ReadableByteChannel rbc = Channels.newChannel(webAddress.openStream());
        FileOutputStream fos = new FileOutputStream("src/test.pdf");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    }

    public static void createThreeCopiesOfFile() throws IOException {
        Path filePath = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT.dat.txt");
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(filePath.toFile())))
        ) {
            String line;
            reader.mark(10000);
            BufferedWriter writer = null;
            for (int i = 1; i <= 3; i++) {
                Path copyFile = Paths.get("C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT_copy"+i+".dat.txt");
                Files.deleteIfExists(copyFile);
                try {
                    writer = Files.newBufferedWriter(copyFile, Charset.forName("UTF-8"), StandardOpenOption.APPEND, StandardOpenOption.CREATE_NEW);
                    while ((line = reader.readLine()) != null) {
                        writer.write(line + "\n");
                    }
                    System.out.println("File is saved");
                } finally {
                    writer.close();
                    reader.reset();
                }
            }
        }
    }

    public static void copyThreeFiles() throws IOException {
        Path file1 = Paths.get ( "C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT1.dat.txt");
        Path file2 = Paths.get ( "C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT2.dat.txt");
        Path file3 = Paths.get ( "C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT3.dat.txt" );
        try (BufferedReader inStream = Files.newBufferedReader ( Paths.get ( "C:\\Users\\admin\\eclipse-workspace\\WorkingWithFiles\\src\\Go2IT.dat.txt" ) );
             BufferedWriter bw1 = new BufferedWriter ( new FileWriter ( file1.toFile ( ) ) );
             BufferedWriter bw2 = new BufferedWriter ( new FileWriter ( file2.toFile ( ) ) );
             BufferedWriter bw3 = new BufferedWriter ( new FileWriter ( file3.toFile ( )  ) );
        ) {
            String line;
            while (( line = inStream.readLine ( ) ) != null) {
                bw1.write ( line + "\n" );
                bw2.write ( line + "\n" );
                bw3.write ( line + "\n" );
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        copyToFile();
          readFileFromGoogle();
//        createThreeCopiesOfFile();
//        copyThreeFiles();
        //downloadPDF();

    }
}
