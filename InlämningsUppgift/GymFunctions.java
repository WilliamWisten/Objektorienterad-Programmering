package Sprint2.InlämningsUppgift;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GymFunctions {

    public void AddNewMember(FileManagement management) throws IOException {
        System.out.println("Type the customers social security number with 10 digits:");
        Scanner scan = new Scanner(System.in);
        String socialSecurityNumber;
        while (true) {
            socialSecurityNumber = scan.nextLine();
            for (Person person : management.GetPersonArrayList()) {
                if (socialSecurityNumber.trim().equals(person.GetSocialSecurityNumber())) {
                    System.out.println("There is already a member with that social security number.\n" +
                            "Check if the membership is active or use the \"Renew membership\" function if it is not.");
                    return;
                }
            }
            boolean wrongFormat = false;
            try {
                Long.parseLong(socialSecurityNumber.trim());
            } catch (NumberFormatException e) {
                wrongFormat = true;
            }
            if (!wrongFormat) {
                System.out.println("Input may only contain digits.");
            } else if (socialSecurityNumber.trim().length() != 10) {
                System.out.println("Input has to be 10 digits.");
            } else {
                break;
            }
        }
        System.out.println("Type the customers name:");
        String name = scan.nextLine().trim();
        String purchaseDate = LocalDate.now().toString();
        String memberInfo = socialSecurityNumber + ", " + name.trim() + purchaseDate;
        Member member = new Member(memberInfo);
        ArrayList<String> tempList = management.ReadFileToArrayList(management.GetFilePath());
        tempList.add(member.GetSocialSecurityNumber() + ", " + member.GetName());
        tempList.add(purchaseDate);
        management.WriteStringArrayListToFile(management.GetFilePath(), tempList);
    }

    public Person SearchCustomer(String input, FileManagement management) throws IndexOutOfBoundsException {
        ArrayList<Person> tempArrayList = new ArrayList<>();
        for (Person person : management.GetPersonArrayList()) {
            if (person.GetSocialSecurityNumber().trim().equals(input.trim())) {
                tempArrayList.add(person);
            } else if (person.GetName().trim().equalsIgnoreCase(input.trim())) {
                tempArrayList.add(person);
            }
        }
        if (tempArrayList.isEmpty()) {
            return null;
        }
        return tempArrayList.get(0);
    }

    public void AddTrainingSession(Person member, FileManagement management, String filePath) throws IOException {
        ArrayList<String> tempList = management.ReadFileToArrayList(filePath);
        boolean nameFound = false;
        for (int i = 0; i < tempList.size(); i++) {
            String tempString = tempList.get(i);
            if (!tempString.isEmpty()) {
                boolean tempBool = true;
                if(tempString.indexOf(',') == -1){
                    tempBool = false;
                }
                if (tempBool && member.GetSocialSecurityNumber().equals(tempString.substring(0, tempString.indexOf(',')))) {
                    String localDate = LocalDate.now().toString();
                    tempList.set(i, tempList.get(i) + ", " + localDate);
                    nameFound = true;
                }
            }
        }
        if (!nameFound) {
            String localDate = LocalDate.now().toString();
            tempList.add(member.GetSocialSecurityNumber() + ", " + member.GetName() + ", workout dates: " + localDate);
        }
        management.WriteStringArrayListToFile(filePath, tempList);
    }
    public String CustomerCategory(Person person, FileManagement management, String filePath) throws IOException {
        String message;
        if (person instanceof Member) {
            LocalDate expiryDate = person.GetPurchaseDate().plusYears(1);
            message = "Member, membership valid until " + expiryDate + ".";
            AddTrainingSession(person, management, filePath);
        } else if (person instanceof PreviousMember) {
            LocalDate expiryDate = person.GetPurchaseDate().plusYears(1);
            message = "Previous Member, membership expired " + expiryDate + ".";
        } else {
            message = "Could not find a match in the file, check spelling or deny entrance.";
        }
        return message;
    }

    public void RenewMembership(Person person, FileManagement management) throws IOException {
        ArrayList<String> tempList = management.ReadFileToArrayList(management.GetFilePath());
        if (person instanceof Member) {
            String expireDate = person.GetPurchaseDate().plusYears(1).toString();
            System.out.println(person.GetName() + " is already a member. Membership expires " + expireDate + ".");
        } else if (person instanceof PreviousMember) {
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).substring(0, 10).equals(person.GetSocialSecurityNumber())) {
                    String todayDate = LocalDate.now().toString();
                    tempList.set(i + 1, todayDate);
                    String expireDate = LocalDate.parse(todayDate).plusYears(1).toString();
                    System.out.println("Membership renewed, expires " + expireDate + ".");
                }
            }
        } else {
            System.out.println("Customer has never been a member.\n" +
                    "Use the \"Add new member\" function instead.");
        }
        management.WriteStringArrayListToFile(management.GetFilePath(), tempList);
    }

    public void PrintMemberWorkoutHistory(Person member, FileManagement management) {
        if (member != null) {
            ArrayList<String> tempList = management.ReadFileToArrayList(management.GetPtFilePath());
            boolean nameFound = false;
            for (String tempString : tempList) {
                if (!tempString.isEmpty()) {
                    if (member.GetSocialSecurityNumber().equals(tempString.substring(0, tempString.indexOf(',')))) {
                        String workoutHistory = tempString.substring(tempString.indexOf(':'));
                        System.out.println(member.GetName() + " has worked out on the dates" + workoutHistory);
                        nameFound = true;
                    }
                }
            }
            if (!nameFound) {
                System.out.println("No workout history found.");
            }
        } else {
            System.out.println("Member not found in the system, check spelling.");
        }
    }

}
