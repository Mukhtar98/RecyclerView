package com.hfad.listrecyclerview;

public class Model {
    private String name;
    private String surname;
    private int image;
    private boolean isChecked = false;

    public Model(String name, String surname,int image) {
        this.name = name;
        this.surname = surname;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSurname() { return surname;
    }

    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    public boolean getIsChecked(){
        return isChecked;
    }

}
