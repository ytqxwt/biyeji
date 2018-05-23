package cn.jxustsrw.biyeji.doMain.bean;

public class UEditorReturn {
    public enum State {SUCCESS, FAIL}

    private Enum<State> state;
    private String url;
    private String title;
    private String original;

    public Enum<State> getState() {
        return state;
    }

    public void setState(Enum<State> state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
