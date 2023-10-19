package Sprint2.InlämningsUppgift;

import java.io.IOException;

public class MainProgram {

    public static void main(String[] args) throws IOException {
        FileManagement management = new FileManagement();
        UserInteraction userInteraction = new UserInteraction();
        GymFunctions gymFunctions = new GymFunctions();
        management = new FileManagement(management.GetFilePath());
        Menu menu = new Menu();
        boolean menuBool = true;
        while (menuBool) {
            int menuChoice = menu.MenuChoice(5, menu.GetMainMenu());
            switch (menuChoice) {
                case 1 -> {
                    String category = gymFunctions.CustomerCategory(gymFunctions.SearchCustomer(userInteraction.CustomerSearch(),
                            management), management, management.GetPtFilePath());
                    System.out.println(category);
                }
                case 2 -> {
                    gymFunctions.AddNewMember(management);
                }
                case 3 -> {
                    gymFunctions.RenewMembership(gymFunctions.SearchCustomer(userInteraction.CustomerSearch(), management),management);
                }
                case 4 -> {
                    gymFunctions.PrintMemberWorkoutHistory(gymFunctions.SearchCustomer(userInteraction.CustomerSearch(), management), management);
                }
                case 5 -> {
                    menuBool = false;
                }
            }
        }
    }
}