package org.firstinspires.ftc.teamcode.mechanisms;



public class practica {
    int entero;
    boolean siONo;
    double numeroConDecimal;

    double cuadrado(double n){
        double resultado = Math.pow(n,2);
        return resultado;
    }

    double cuarta(double n){
        double resultado = cuadrado(n)*cuadrado(n);
        return resultado;
    }

    int sumaDesde1(int n){
        int resultado = 0;
       for(int i = 0; i<=n; i++){
           resultado += i;
       }
        return resultado;
    }

    int sumaDesde1V2(int n){
        return n*(n+1)/2;
    }

    double math = Math.pow(2,2);
}
