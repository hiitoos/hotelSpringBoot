package com.hotel.java.application.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="cliente")
public class ClienteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String email;

    @OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
    private Set<ReservaEntity> reservas;

    public ClienteEntity(long id, String nombre, String apellido, String email) {
        setId (id);
        setNombre (nombre);
        setApellido (apellido);
        setEmail (email);
        setReservaEntities (reservas);
    }

    public ClienteEntity(String nombre, String apellido, String email) {
        setNombre (nombre);
        setApellido (apellido);
        setEmail (email);
        setReservaEntities (reservas);
    }

    public ClienteEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public Set<ReservaEntity> getReservaEntities() {
        return reservas;
    }*/

    public void setReservaEntities(Set<ReservaEntity> reservas_id) {
        this.reservas = reservas_id;
    }

    /*public LoginEntity getLoginEntity() {
        return login;
    }

    public void setLoginEntity(LoginEntity login) {
        this.login = login;
    }*/

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", reservaEntities=" + reservas +
                '}';
    }
}
