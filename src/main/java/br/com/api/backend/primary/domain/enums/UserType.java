package br.com.api.backend.primary.domain.enums;

public enum UserType {
    PEOPLE(1, "Natural Person"),
    LEGALPERSON (2, "Legal Entity");

    private int cod;
    private String description;

    UserType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static UserType toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (UserType x : UserType.values()) {
            if (id.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}
