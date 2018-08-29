package com.adanfm.cursomc.domain.enums;

public enum TypeClient {

    PESSOA_FISICA(1, "Pessoa Fisica"),
    PESSOA_JURIDICA(2, "Pessoa Juridica");

    private int id;
    private String description;

    private TypeClient(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static TypeClient toEnum(Integer id) {
        if (id == null) {
            return null;
        }

        for (TypeClient x: TypeClient.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalid " + id);
    }
}
