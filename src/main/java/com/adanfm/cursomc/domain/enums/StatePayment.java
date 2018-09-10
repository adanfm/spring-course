package com.adanfm.cursomc.domain.enums;

public enum StatePayment {

    PENDING(1, "Pendente"),
    SETTLED(2, "Quitado"),
    CANCELED(3, "Cancelado");

    private int id;
    private String description;

    private StatePayment(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static StatePayment toEnum(Integer id) {
        if (id == null) {
            return null;
        }

        for (StatePayment x: StatePayment.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalid " + id);
    }
}
