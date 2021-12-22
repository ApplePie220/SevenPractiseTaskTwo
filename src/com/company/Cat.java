package com.company;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Cat implements Serializable {
    private Random random = new Random();
    private String name;
    private int age;
    private String hobby;
    private int weight;
    private String nature;
    private String[] catNames= {"Глютик","Джонлок","Шериарти","Сероволк","Драковолк","Пчелогром","Драмиона","Чимин",
            "Тэхен","Лумити","Драрри","ЧайЛюми"};
    private String[] ccatHobbys = {"Спать","Тыгыдыкать","Гадить","Существовать","Смотреть в окно","Лежать",
            "Разбрасывать корм","Царапать диван"};
    private String[] catNatures = {"красивый","толстый","непривлекательный","маленький","большой","пушистый","самый лучший",
    "мягонький","откормленный","поджарый","очень худой"};
    public Cat(){
        this.name = catNames[random.nextInt(12)];
        this.hobby = ccatHobbys[random.nextInt(8)];
        this.nature = catNatures[random.nextInt(11)];
        this.age= ThreadLocalRandom.current().nextInt(1,10);
        this.weight=ThreadLocalRandom.current().nextInt(5,100);
    }
    public String getName(){return name;}

    @Override
    public String toString(){
       return "Имя: "+name+ ", Возраст: "+age+ ", Вес: "+weight+ ", Хобби: "+hobby+ ", Внешне он "+nature+"\n";
    }

}
