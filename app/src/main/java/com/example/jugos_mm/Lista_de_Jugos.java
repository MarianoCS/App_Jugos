package com.example.jugos_mm;

import androidx.annotation.NonNull;


public class Lista_de_Jugos
{
    String id;
    String Cuantos;

    public Lista_de_Jugos(String id, String Cuantos)
    {
        this.id = id;
        this.Cuantos = Cuantos;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCuantos()
    {
        return Cuantos;
    }

    public void setCuantos(String cuantos)
    {
        this.Cuantos = cuantos;
    }

    @NonNull
    @Override
    public String toString()
    {
        return Cuantos;
    }
}
