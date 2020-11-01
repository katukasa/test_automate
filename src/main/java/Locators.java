public class Locators {

    public String enterButton = "//*[@href='/login' and contains(text(), 'Войти')]";

    private String loginEmail = "//*[@name='user[email]']";
    private String loginPassword = "//*[@name='user[password]']";
    private String loginEnterButton = "//*[@id='registration-form-button' and contains (text(), 'Начать изменения')]";

    private String coursesLink = "//*[@id='nav']//*[@href='/courses']";

}
