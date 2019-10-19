package br.com.zup.backend.primary.domain.enums;

public enum Role {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int cod;
    private String descricao;

    private Role(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Role toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (Role x : Role.values()) {
            if (id.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid Id " + id);
    }
}

