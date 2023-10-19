package Sprint2.InlämningsUppgift;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class GymFunctionsTest {

    final String testFilePath = "Test/Sprint2/InlämningsUppgift/TestFile.tmp";

    final String testFilePtPath = "Test/Sprint2/InlämningsUppgift/TestPtFile.tmp";

    FileManagement management = new FileManagement(testFilePath);

    GymFunctions gymFunctions = new GymFunctions();

    @Test
    public void SearchCustomerTest() {
        Person testSearch1 = gymFunctions.SearchCustomer("Per Persson", management);
        assert (testSearch1 instanceof PreviousMember);
        Person testSearch2 = gymFunctions.SearchCustomer("8505132345", management);
        assert (testSearch2 instanceof PreviousMember);
        Person testSearch3 = gymFunctions.SearchCustomer("7502031234", management);
        assert (testSearch3 instanceof Member);
        Person testSearch4 = gymFunctions.SearchCustomer("adswf", management);
        assert (testSearch4 == null);
    }

    @Test
    public void CustomerCategoryTest() throws IOException {
        assert(gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("Per Persson", management), management,testFilePtPath).equals("Previous Member, membership expired 2020-12-29."));
        assert(gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("7502031234", management), management, testFilePtPath).equals("Member, membership valid until 2024-05-03."));
        assert(!gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("8505132345", management), management, testFilePtPath).equals("Member, membership valid until 2024-05-03."));
        assert(gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("Adam Bengtsson", management),management, testFilePtPath).equals("Could not find a match in the file, check spelling or deny entrance."));
    }

    @Test
    public void AddTrainingSessionTest() throws IOException {
        gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("7502031234, Anna Andersson2023-05-03", management), management,testFilePtPath);
        ArrayList<String> list1 = management.ReadFileToArrayList(testFilePtPath);
        gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("7502031234, Anna Andersson2023-05-03", management), management,testFilePtPath);
        ArrayList<String> list2 = management.ReadFileToArrayList(testFilePtPath);
        gymFunctions.CustomerCategory(gymFunctions.SearchCustomer("7502031234, Anna Andersson2023-05-03", management), management,testFilePtPath);
        ArrayList<String> list3 = management.ReadFileToArrayList(testFilePtPath);
        assert(list1.size() == list2.size()  && list2.size() == list3.size());
    }
}
