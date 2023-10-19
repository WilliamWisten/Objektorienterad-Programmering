package Sprint2.InlämningsUppgift;

import org.junit.Test;

public class MemberTest {

    @Test
    public void PersonTest() {
        Member memberTest = new Member("7502031234, Anna Andersson2023-05-03");
        assert(memberTest.GetName().equals("Anna Andersson"));
        assert(!memberTest.GetName().equals("Per Persson"));
        assert(memberTest.GetSocialSecurityNumber().equals("7502031234"));
        assert(memberTest.GetPurchaseDate().toString().equals("2023-05-03"));
    }
}
