package com.example.graocafeapi.model;


import java.io.Serializable;
import java.util.List;


/*
*     "uuid":"962c0eaa-7bb5-4a54-ba7c-6d1e3cd899a4",
    "title":"Café Pilão Torrado E Moído Tradicional Almofada 500g",
    "amont":"13,99",
    "caracteristicas":[
        {
            "Marca":"Pilão"
        },
        {
            "Formato do café":"Moído"
        },
        {
            "Tipo de café":"Torrado"
        },
        {
            "Tipo de embalagem":"Saco"
        },
        {
            "Formato de venda":"Unidade"
        },
        {
            "Peso da unidade":"500 g"
        }
    ],
    "img":"https://http2.mlstatic.com/D_NQ_NP_701268-MLB45930948574_052021-O.webp",
    "img_path":"img_9be77868-f6a9-47db-acc0-dddea05122ba.jpg"
    * */
public class TiposGraosCafe implements Serializable {
    private String nome;
    private String uuid;
    private String amont;
    private String img_path;
    private List<Characteristics> characteristicsList;

    public List<Characteristics> getCharacteristicsList() {
        return characteristicsList;
    }

    public void setCharacteristicsList(List<Characteristics> characteristicsList) {
        this.characteristicsList = characteristicsList;
    }

    public TiposGraosCafe(String title, String img_path) {
        this.nome=title;
        this.img_path=img_path;

    }

    public TiposGraosCafe(String title, String img_path, String amont) {
        this.nome=title;
        this.img_path=img_path;
        this.amont=amont;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAmont() {
        return amont;
    }

    public void setAmont(String amont) {
        this.amont = amont;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;



    public TiposGraosCafe(String nome) {
        this.nome=nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
