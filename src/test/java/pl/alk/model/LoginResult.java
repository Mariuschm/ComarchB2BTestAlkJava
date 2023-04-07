package pl.alk.model;
import pl.alk.pop.MainPage;

public class LoginResult {
    private MainPage page;
    private int result;

    public LoginResult() {
    }

    public void setPage(MainPage page) {
        this.page = page;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MainPage getPage() {
        return page;
    }

    public int getResult() {
        return result;
    }
}
