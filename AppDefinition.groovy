/**
 * Created by francisco on 19/4/16.
 */
package prueba

import org.openqa.selenium.By
import net.continuumsecurity.UserPassCredentials
import net.continuumsecurity.Config
import net.continuumsecurity.Credentials;
import net.continuumsecurity.web.WebApplication;

class AppDefinition extends WebApplication {

    private Binding binding;

    public AppDefinition() {
        this(new Binding());
    }

    public AppDefinition(Binding binding) {
        this.binding = binding;
    }

    public Binding getBinding() {
        return this.binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    def openLoginPage() {
        driver.get("user/login");
        findAndWaitForElement(By.id("username"));
    }


    def login(Credentials credentials) {
        def creds = new UserPassCredentials(credentials);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(creds.getUsername());
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(creds.getPassword());
        driver.findElement(By.name("_action_login")).click();

    }


    def login(String username, String password) {
        login(new UserPassCredentials(username, password));
    }


    def isLoggedIn() {
        driver.get(Config.getInstance().getBaseUrl() + "task/list");
        if (driver.getPageSource().contains("Tasks")) {
            return true;
        } else {
            return false;
        }
    }

    def viewProfile() {
        driver.findElement(By.linkText("Profile")).click();
    }

    def viewAlicesProfile() {
        viewProfile();
    }

    def viewBobsProfile() {
        viewProfile();
    }


    def logout() {
        driver.findElement(By.linkText("Logout")).click();
    }

    def search(String query) {
        findAndWaitForElement(By.linkText("Tasks")).click();
        driver.findElement(By.id("q")).clear();
        driver.findElement(By.id("q")).sendKeys(query);
        driver.findElement(By.id("search")).click();
    }

    def viewAllUsers() {
        driver.get(Config.getInstance().getBaseUrl() + "admin/list");
    }

    def run() {
        openLoginPage();
        login(Config.getInstance().getUsers().getDefaultCredentials());
        viewProfile();
        search("test");
    }


}