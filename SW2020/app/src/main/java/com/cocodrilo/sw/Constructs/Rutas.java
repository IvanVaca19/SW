package com.cocodrilo.sw.Constructs;



import android.content.Intent;

import androidx.annotation.IntegerRes;




public class Rutas {
    private int id;
    private String mall;
    private String formato;
    private int idFormatos;
    private String orden;
    private String image;
    private int idPlazas;
    private int idCadenas;
    private int idTiendas;
    private String fechaRuta;
    private String dte;
    private String plaza;
    private String promotor;


    public Rutas() {

    }

    public Rutas(int id , String mall, String formato,int idFormatos, String orden, String image  ,int idPlazas, int idCadenas, int idTiendas, String fechaRuta, String dte, String plaza, String promotor) {
        this.id = id;
        this.mall = mall;
        this.formato = formato;
        this.idFormatos = idFormatos;
        this.orden = orden;
        this.image = image;
        this.idPlazas = idPlazas;
        this.idCadenas = idCadenas;
        this.idTiendas = idTiendas;
        this.fechaRuta = fechaRuta;
        this.dte = dte;
        this.plaza = plaza;
        this.promotor = promotor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }




    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getIdPlazas() {
        return idPlazas;
    }

    public void setIdPlazas(int idPlazas) {
        this.idPlazas = idPlazas;
    }

    public int getIdCadenas() {
        return idCadenas;
    }

    public void setIdCadenas(int idCadenas) {
        this.idCadenas = idCadenas;
    }

    public int getIdTiendas() {
        return idTiendas;
    }

    public void setIdTiendas(int idTiendas) {
        this.idTiendas = idTiendas;
    }

    public int getIdFormatos() {
        return idFormatos;
    }

    public void setIdFormatos(int idFormatos) {
        this.idFormatos = idFormatos;
    }

    public String getFechaRuta() {
        return fechaRuta;
    }

    public void setFechaRuta(String fechaRuta) {
        this.fechaRuta = fechaRuta;
    }

    public String getDte() {
        return dte;
    }

    public void setDte(String dte) {
        this.dte = dte;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public String getPromotor() {
        return promotor;
    }

    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }
}