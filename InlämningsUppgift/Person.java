package Sprint2.InlämningsUppgift;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    private String name;

    private String socialSecurityNumber;

    private LocalDate purchaseDate;

    Person(String fileString) {
        name = fileString.substring(fileString.indexOf(' ') + 1, fileString.indexOf('-') - 4);
        socialSecurityNumber = fileString.substring(0, fileString.indexOf(','));
        purchaseDate = LocalDate.parse(fileString.substring(fileString.indexOf('-') - 4));
    }

    public String GetName(){
        return name;
    }

    public String GetSocialSecurityNumber(){
        return socialSecurityNumber;
    }

    public LocalDate GetPurchaseDate(){
        return purchaseDate;
    }
}

