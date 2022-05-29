package zad2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class Futil extends SimpleFileVisitor<Path>{
    private BufferedWriter wyjscie;

    public Futil(BufferedWriter wyjscie) {
        this.wyjscie = wyjscie;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            try (
                    FileInputStream fis = new FileInputStream(file.toFile());
                    InputStreamReader isr = new InputStreamReader(fis, "cp1250");
                    BufferedReader in = new BufferedReader(isr);
            ){
                String line;


                while ((line = in.readLine()) != null) {
                    wyjscie.write(line + "\n");
                }
            }
            return FileVisitResult.CONTINUE;
        }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }


    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }


    public static void processDir(String dirName, String resultFileName){
        try(
                FileOutputStream fis = new FileOutputStream(resultFileName);
                OutputStreamWriter isr = new OutputStreamWriter(fis, StandardCharsets.UTF_8);
                BufferedWriter br = new BufferedWriter(isr);

        ){
            Path startingDir = Paths.get(dirName);
            Files.walkFileTree(startingDir, new Futil(br));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
