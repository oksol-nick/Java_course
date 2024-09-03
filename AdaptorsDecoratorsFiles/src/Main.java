import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "file.txt";
        String pathName = ".\\LessonData\\";
        String data = "Привет! Сохрани меня скорее!";

        writeRewriteUserFile(fileName, pathName, data);
        searchFileByNameAndPathAndRead(fileName, pathName);


        String fileNameNext = "file_next.txt";
        String pathNameNext = ".\\LessonData\\Files\\";
        String dataNext = "Нужно было быстрее сохранять!";

        writeRewriteUserFile(fileNameNext, pathNameNext, dataNext);
        searchFileByNameAndPathAndRead(fileNameNext, pathNameNext);

    }

    public static void writeRewriteUserFile(String fileName, String filePath, String data) throws IOException {

        if(!new File(filePath).isDirectory()) {
            new File(filePath).mkdirs();
        }

        File myFile = new File(filePath, fileName);

        try {
            if(myFile.exists()) {
                System.out.println("\nФайл " + myFile.getAbsolutePath() + " ПЕРЕЗАПИСАН.");
            }
            boolean createdFile = myFile.createNewFile();
            if (createdFile) {
                System.out.println("\nФайл " + myFile.getAbsolutePath() + " СОЗДАН.");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream(myFile));
        long startTime = System.currentTimeMillis();
        streamWriter.write(data);
        streamWriter.close();
        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime);

        System.out.println("Размер файла " + myFile.length()
                + " байт." + "\n" + "Время записи файла " + elapsedTime + " мс." );
    }



    public static void searchFileByNameAndPathAndRead (String fileName, String filePath) throws IOException {
        File myFile = new File(filePath, fileName);
        try {
            if (myFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile));
                System.out.println("\nСодержимое файла " + myFile.getAbsolutePath() + ":\n" + bufferedReader.readLine());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}