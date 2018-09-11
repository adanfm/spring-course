package com.adanfm.cursomc.domain;

import com.adanfm.cursomc.domain.enums.TypeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String document;

    @Column(name="type_client")
    private Integer typeClient;

    @OneToMany(mappedBy="client")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="phones")
    private Set<String> phones = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy="client")
    private List<Order> orders = new ArrayList<>();

    public Client() {}

    public Client(Integer id, String name, String email, String document, TypeClient typeClient) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.typeClient = typeClient.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public TypeClient getTypeClient() {
        return TypeClient.toEnum(typeClient);
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient.getId();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @OneToMany(mappedBy="client")
    public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
