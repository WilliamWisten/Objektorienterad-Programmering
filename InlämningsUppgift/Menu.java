package Sprint2.InlämningsUppgift;

import java.util.Scanner;

public class Menu {
    private final String mainMenu = """
            [1] Customer check in
            [2] Add new member
            [3] Renew membership
            [4] Read training log of a customer
            [5] Exit program""";

    public int MenuChoice(int numberOfMenuChoices, String menu) {
        Scanner scan = new Scanner(System.in);
        int menuChoice;
        while (true) {
            try {
                System.out.println(menu);
                menuChoice = Integer.parseInt(scan.nextLine().trim());
                if (numberOfMenuChoices >= menuChoice && menuChoice >= 1) {
                    break;
                } else {
                    System.out.println("Input has to be an integer between 1 and " + numberOfMenuChoices);
                }
            } catch (NumberFormatException e) {
                System.out.println("Input has to be an integer between 1 and " + numberOfMenuChoices);
            }
        }
        return menuChoice;
    }

    public String GetMainMenu(){
        return mainMenu;
    }
}
