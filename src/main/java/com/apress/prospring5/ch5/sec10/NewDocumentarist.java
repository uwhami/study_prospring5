package com.apress.prospring5.ch5.sec10;

public class NewDocumentarist extends Documentarist{

    @Override
    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.sing(new Guitar());
        guitarist.talk();

    }
}
