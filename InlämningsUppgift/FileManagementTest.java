package Sprint2.InlämningsUppgift;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileManagementTest {

    final String testFilePath = "Test/Sprint2/InlämningsUppgift/TestFile.tmp";

    final String testFile2Path = "Test/Sprint2/InlämningsUppgift/TestFile2.tmp";

    FileManagement management = new FileManagement(testFilePath);

    UserInteraction userInteraction = new UserInteraction();

    GymFunctions gymFunctions = new GymFunctions();

    @Test
    public void ReadFileTwoLinesToArrayListTest() {
        ArrayList<String> members = management.GetPersonStringList();
        assert (members.size() == 2);
        assert (members.get(0).equals("7502031234, Anna Andersson2023-05-03"));
        assert (!members.get(0).equals("8505132345, Per Persson2019-12-29"));
    }

    @Test
    public void ReadFileToArrayListTest(){
        ArrayList<String> testList = management.ReadFileToArrayList(testFilePath);
        assert(testList.size() == 4);
        assert(testList.size() != 3);
        assert(testList.get(1).equals("2023-05-03"));
    }

    @Test
    public void TextListToPersonListTest() {
        ArrayList<Person> personArrayList = management.GetPersonArrayList();
        assert (personArrayList.size() == 2);
        assert ((personArrayList.get(0).GetName()).equals("Anna Andersson"));
        assert ((personArrayList.get(1).GetSocialSecurityNumber()).equals("8505132345"));
        assert (personArrayList.get(0).GetPurchaseDate().isEqual(LocalDate.parse("2023-05-03")));
        assert (!(personArrayList.get(0).GetName()).equals("Per Persson"));
        assert (personArrayList.get(0) instanceof Member);
        assert (!(personArrayList.get(0) instanceof PreviousMember));
    }

    @Test
    public void ReadFileLineTest(){
        String nr1 = management.ReadFileLine(testFilePath, 1);
        String nr2 = management.ReadFileLine(testFilePath, 2);
        String nr3 = management.ReadFileLine(testFilePath, 3);
        String nr4 = management.ReadFileLine(testFilePath, 4);
        String nr5 = management.ReadFileLine(testFilePath, 5);
        assert(nr1.equals("7502031234, Anna Andersson"));
        assert(nr2.equals("2023-05-03"));
        assert(nr3.equals("8505132345, Per Persson"));
        assert(nr4.equals("2019-12-29"));
        assert(nr5 == null);
        assert(nr4 != null);
    }

    @Test
    public void WriteStringArrayListToFileTest() throws IOException {
        ArrayList<String> initialFile = management.ReadFileToArrayList(testFile2Path);
        int initialFileSize = initialFile.size();
        ArrayList<String> testList = initialFile;
        testList.add("123");
        testList.add("abc");
        testList.add("797");
        management.WriteStringArrayListToFile(testFile2Path, testList);
        ArrayList<String> finalFile = management.ReadFileToArrayList(testFile2Path);
        assert(finalFile.size() == initialFileSize + 3);
        assert(finalFile.get(finalFile.size() - 2).equals("abc"));
        assert(!finalFile.get(finalFile.size() - 1).equals("abc"));
        assert(finalFile.get(0).equals(initialFile.get(0)));
    }

}
