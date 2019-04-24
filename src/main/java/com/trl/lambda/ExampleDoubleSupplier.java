package com.trl.lambda;

import java.util.function.DoubleSupplier;

public class ExampleDoubleSupplier {
    public static void main(String[] args) {
//---------------------------------------------------------------------------------------------------------------------
        DoubleSupplier doubleSupplier = () -> Math.random() * 100;
        System.out.println(doubleSupplier.getAsDouble());
//---------------------------------------------------------------------------------------------------------------------
    }

}
