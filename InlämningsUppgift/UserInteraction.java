package Sprint2.InlämningsUppgift;

import java.util.Scanner;

public class UserInteraction {

    public String CustomerSearch(){
        System.out.println("Type the customers name or social security number:");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
