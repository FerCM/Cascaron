package com.fiscaliageneralags.fiscalia.ui.slideshow;

public class Model {
    String nombre, fenac, fechaecho, naci, gen, cab,ojos,señas,lugar;
    int edad;
    String img;
    double peso, esta;

    public Model(String nombre,String fenac, String fechaecho, String naci, String gen, String cab, String ojos, String señas, String lugar, int edad, double peso, double esta,String img) {
        this.fenac = fenac;
        this.fechaecho = fechaecho;
        this.naci = naci;
        this.gen = gen;
        this.cab = cab;
        this.ojos = ojos;
        this.señas = señas;
        this.lugar = lugar;
        this.edad = edad;
        this.peso = peso;
        this.esta = esta;
        this.img = img;
        this.nombre= nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFenac() {
        return fenac;
    }

    public void setFenac(String fenac) {
        this.fenac = fenac;
    }

    public String getFechaecho() {
        return fechaecho;
    }

    public void setFechaecho(String fechaecho) {
        this.fechaecho = fechaecho;
    }

    public String getNaci() {
        return naci;
    }

    public void setNaci(String naci) {
        this.naci = naci;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public String getOjos() {
        return ojos;
    }

    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    public String getSeñas() {
        return señas;
    }

    public void setSeñas(String señas) {
        this.señas = señas;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public double getEsta() {
        return esta;
    }

    public void setEsta(float esta) {
        this.esta = esta;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
