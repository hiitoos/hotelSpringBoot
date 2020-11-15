package com.hotel.java.application.domain.entities;

import javax.persistence.*;

@Entity
@Table (name="habitacion")
public class HabitacionEntity{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String codigo;
    private String descripcion;
    private float precio;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tipo_id")
    private TipoEntity tipo;
    private int numpersonas;

    public HabitacionEntity(long id, String codigo, String descripcion, float precio, TipoEntity tipo, int numpersonas) {
        setId (id);
        setCodigo (codigo);
        setDescripcion (descripcion);
        setPrecio (precio);
        setTipo(tipo);
        setNumpersonas(numpersonas);
    }

    public HabitacionEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public TipoEntity getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntity tipo) {
        this.tipo = tipo;
    }

    public int getNumpersonas() {
        return numpersonas;
    }

    public void setNumpersonas(int numpersonas) {
        this.numpersonas = numpersonas;
    }

    @Override
    public String toString() {
        return "HabitacionEntity{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoEntity=" + tipo +
                //", reservaEntity=" + reserva +
                '}';
    }
}
