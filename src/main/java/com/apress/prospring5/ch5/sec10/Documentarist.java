package com.apress.prospring5.ch5.sec10;

public class Documentarist {

    private GrammyGuitarist guitarist;

    public void execute(){
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(GrammyGuitarist guitarist){
        this.guitarist = guitarist;
    }
}
