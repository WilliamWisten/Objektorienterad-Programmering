package Sprint2.InlämningsUppgift;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileManagement {

    private ArrayList<String> personStringList;

    private ArrayList<Person> personArrayList = new ArrayList<>();

    private final String filePath = "src/Sprint2/InlämningsUppgift/MemberFile.tmp";

    private final String ptFilePath = "src/Sprint2/InlämningsUppgift/PtFile.tmp";

    FileManagement(String path) {
        SetPersonStringList(ReadFileTwoLinesToArrayList(path));
        TextListToPersonList();
    }

    FileManagement() {
    }

    public ArrayList<String> ReadFileTwoLinesToArrayList(String path) {
        ArrayList<String> tempList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String fileInput;
            while (!((fileInput = reader.readLine()) == null)) {
                tempList.add(fileInput + reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error.");
            throw new RuntimeException(e);
        }
        return tempList;
    }

    public ArrayList<String> ReadFileToArrayList(String path) {
        ArrayList<String> tempList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String fileInput;
            while (!((fileInput = reader.readLine()) == null)) {
                tempList.add(fileInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error.");
            throw new RuntimeException(e);
        }
        return tempList;
    }

    public void TextListToPersonList() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        for (String s : personStringList) {
            LocalDate purchaseDate = LocalDate.parse(s.substring(s.indexOf('-') - 4));
            if (purchaseDate.isBefore(oneYearAgo)) {
                PreviousMember previousMember = new PreviousMember(s);
                personArrayList.add(previousMember);
            } else if (purchaseDate.isAfter(oneYearAgo)) {
                Member member = new Member(s);
                personArrayList.add(member);
            }
        }
    }

    public String ReadFileLine(String path, int lineNr) {
        String fileInput = "";
        int lineNrAmount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < lineNr - 1; i++) {
                reader.readLine();
            }
            fileInput = reader.readLine();
        } catch (IOException e) {
            System.out.println("There are only " + lineNrAmount + " lines.");
            throw new RuntimeException(e);
        }
        return fileInput;
    }

    public void WriteStringArrayListToFile(String path, ArrayList<String> arraylist) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String s : arraylist) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public ArrayList<String> GetPersonStringList() {
        return personStringList;
    }

    public ArrayList<Person> GetPersonArrayList() {
        return personArrayList;
    }

    public void SetPersonStringList(ArrayList<String> tempList) {
        personStringList = tempList;
    }

    public String GetFilePath(){
        return filePath;
    }

    public String GetPtFilePath(){
        return ptFilePath;
    }
}