package com.lcaohoanq.fundamental.piedlclrm2;

import java.util.Random;
//giả định tất cả các con khỉ đều thấy mình học ổn
//nên không tham gia vào clb
public class Monkey extends Herbivore{
    //con khỉ có khả năng tiếp thu kém -> 30%
    public static final double RECEPTIVE = 70;

    public Monkey(String name, int yob, double weight) {
        super(name, yob, weight);
    }

    @Override
    public double study() {
        return new Random().nextDouble()*RECEPTIVE;
    }

    @Override
    public void showLearningOutcomes() {
        String str = String.format("Monkey       |%-15s|%4d|%6.2f|%6.2f",name,yob,weight,study());
        System.out.println(str);
    }
}
    
