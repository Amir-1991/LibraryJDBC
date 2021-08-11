package Articles;

import java.time.LocalDate;

public class Articles {
    private LocalDate creatDate;
    private int isPublished;
    private String content;
    static int categoryId;
    private String title;
    private String brief;
    private Long id;

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    public void setPublished(int published) {
        isPublished = published;
    }


    public static void setCategoryId(int categoryId) {
        Articles.categoryId = categoryId;
    }
}
