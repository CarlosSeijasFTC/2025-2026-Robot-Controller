package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class   DevicesForCompetitionServos {

    private Servo servo1;
    private Servo servo2;

    public void init(HardwareMap hwMp){
        servo1 = hwMp.get(Servo.class, "servo1");
        servo2 = hwMp.get(Servo.class, "servo2");

    }

    public void setServo1Pos(double pos){
        servo1.setPosition(pos);
    }
    public void setServo2Pos(double pos){
        servo2.setPosition(pos);
    }
    public double getServo1Pos(){
        return servo1.getPosition();
    }
    public double getServo2Pos(){
        return servo2.getPosition();
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode