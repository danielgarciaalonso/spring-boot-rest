package formacion.cmc.springbootrest.api.v1.model;

public class BookDTO {

    private Long id;

    private String title;


    public BookDTO() {
        super();
    }

    public BookDTO(Long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

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

}
