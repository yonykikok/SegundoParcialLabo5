package com.example.ultimotema.clases;

import java.util.Arrays;
import java.util.Objects;

public class Pais {
    private Float area;
    private String name;
    private String region;
    private Float population;
    private String flag;
    private byte[] imagen;


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Pais(){

    }
    public Pais(Float area, String name, String region, Float population) {
        this.area = area;
        this.name = name;
        this.region = region;
        this.population = population;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getPopulation() {
        return population;
    }

    public void setPopulation(Float population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "area=" + area +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", flag='" + flag + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais pais = (Pais) o;
        return getArea().equals(pais.getArea()) &&
                getName().equals(pais.getName()) &&
                getRegion().equals(pais.getRegion()) &&
                getPopulation().equals(pais.getPopulation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArea(), getName(), getRegion(), getPopulation());
    }
}
