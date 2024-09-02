package model.entities;

public class Category {
    protected Integer codeCategory;
    private String nameCategory;

    public Category() {
    }

    public Category(Integer codeCategory, String nameCategory) {
        this.codeCategory = codeCategory;
        this.nameCategory = nameCategory;
    }

    public Integer getCodeCategory() {
        return codeCategory;
    }

    public void setCodeCategory(Integer codeCategory) {
        this.codeCategory = codeCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "codeCategory=" + codeCategory +
                ", nameCategory='" + nameCategory + '\'' +
                '}';
    }
}
