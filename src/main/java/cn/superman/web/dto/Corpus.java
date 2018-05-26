package cn.superman.web.dto;

public class Corpus {
    private Integer id;
    private String path;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Corpus{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
