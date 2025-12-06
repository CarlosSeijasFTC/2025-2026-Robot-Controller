package org.firstinspires.ftc.teamcode.mechanisms;

interface funciones{
    double cuadrado(double a);
}
public class practica2 implements funciones{
    @Override
    public double cuadrado(double a){
        return a*a;
    }
}
