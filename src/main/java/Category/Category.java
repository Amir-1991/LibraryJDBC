package Category;

/**
 * This Class Declare All Categories Attribute And
 * With Setter And Getter Methods Gives Values
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class Category {
    private String description;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
