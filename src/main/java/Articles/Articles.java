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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(LocalDate creatDate) {
        this.creatDate = creatDate;
    }

    public int isPublished() {
        return isPublished;
    }

    public void setPublished(int published) {
        isPublished = published;
    }

    public static int getCategoryId() {
        return categoryId;
    }

    public static void setCategoryId(int categoryId) {
        Articles.categoryId = categoryId;
    }
}
