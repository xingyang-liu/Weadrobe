package com.xiecc.Weadrobe.modules.city.domain;
/**
 * Created by desperateMa on 2017/4/15.
 */

public class Clothes {

    public static final int COAT = 0;
    public static final int CLOTH = 1;
    public static final int PANTS = 2;
    public static final int SHOES = 3;
    public static final int ACCESSORY = 4;

    public int ClothSort;
    public static boolean[] ishave = new boolean[100];

    private String name;
//    private boolean have;
    private String intro;
    private String icon_position;
    private int kind;
    private int warm_index;
    private int formal_index;
    private boolean is_rain=false;

    public  Clothes(){}
    public  Clothes(Clothes a){
        this.name = a.name;
//        this.have = a.have;
        this.intro = a.intro;
        this.icon_position = a.icon_position;
        this.kind = a.kind;
        this.warm_index = a.warm_index;
        this.formal_index = a.formal_index;
        this.is_rain = a.is_rain;
    }

    public String getIntro(){
       return intro;
    }

    public String getName(){
        return name;
    }
    public void setName(String aname){
        this.name= aname;
    }

//    public void sethave(){
//        this.name= aname;
//    }

    public void setIntro(String Intro){
        this.intro= Intro;
    }

    public void setIconPositon(String position){
        this.icon_position= position;
    }

    public void setKind(int index){
        this.kind = index;
    }
    public int getKind() {
        return kind;
    }

    public void setWarmIndex(int index){
        this.warm_index= index;
    }

    public void setFormalIndex(int index){
        this.formal_index= index;
    }

    public void setIsRain(boolean rain){
        this.is_rain= rain;
    }

}
