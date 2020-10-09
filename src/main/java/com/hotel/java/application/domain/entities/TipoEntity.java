package com.hotel.java.application.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="tipo")
public class TipoEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy="tipo", fetch=FetchType.EAGER)
    private List<HabitacionEntity> habitaciones;

    public TipoEntity(long id, String nombre, String descripcion) {
        setId (id);
        setNombre (nombre);
        setDescripcion (descripcion);
    }

    public TipoEntity(String nombre, String descripcion) {
        setNombre (nombre);
        setDescripcion (descripcion);
    }

    public TipoEntity() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<HabitacionEntity> getHabitacionEntityList() {
        return habitaciones;
    }

    public void setHabitacionEntityList(List<HabitacionEntity> habitacionEntityList) {
        this.habitaciones = habitacionEntityList;
    }

    @Override
    public String toString() {
        return "TipoEntity{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", habitacionEntityList=" + habitaciones +
                '}';
    }
}
