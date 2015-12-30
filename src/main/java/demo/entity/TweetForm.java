package demo.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TweetForm {
    @NotNull
    @Size(min = 1, max = 140)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
