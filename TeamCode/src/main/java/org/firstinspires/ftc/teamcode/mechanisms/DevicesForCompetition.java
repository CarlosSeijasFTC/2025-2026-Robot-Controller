package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class DevicesForCompetition {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private Servo servo1;
    private Servo servo2;


    public void init(HardwareMap hwMp){
        frontLeft = hwMp.get(DcMotor.class, "frontLeft");
        frontRight = hwMp.get(DcMotor.class, "frontRight");
        backLeft = hwMp.get(DcMotor.class, "backLeft");
        backRight = hwMp.get(DcMotor.class, "backRight");
        motor1 = hwMp.get(DcMotor.class, "motor1");
        motor2 = hwMp.get(DcMotor.class, "motor2");
        motor3 = hwMp.get(DcMotor.class, "motor3");
        motor4 = hwMp.get(DcMotor.class, "motor4");
        servo1 = hwMp.get(Servo.class, "servo1");
        servo2 = hwMp.get(Servo.class, "servo2");
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //Set Speed Methods
    public void setFrontRightSpeed (double speed){
        frontRight.setPower(speed);
    }
    public void setFrontLeftSpeed (double speed){
        frontLeft.setPower(speed);
    }
    public void setBackRightSpeed (double speed){
        backRight.setPower(speed);
    }
    public void setBackLeftSpeed (double speed){
        backLeft.setPower(speed);
    }
    public void setMotor1Speed (double speed){
        motor1.setPower(speed);
    }
    public void setMotor2Speed (double speed){
        motor2.setPower(speed);
    }
    public void setMotor3Speed (double speed){
        motor3.setPower(speed);
    }
    public void setMotor4Speed (double speed){
        motor4.setPower(speed);
    }

    //Servos
    public void setServo1Pos(double pos){
        servo1.setPosition(pos);
    }
    public void setServo2Pos(double pos){
        servo2.setPosition(pos);
    }



    //Get Speed Methods
    public double getFrontRightSpeed(){
        return frontRight.getPower();
    }
    public double getFrontLeftSpeed(){
        return frontLeft.getPower();
    }
    public double getBackRightSpeed(){
        return backRight.getPower();
    }
    public double getBackLeftSpeed(){
        return backLeft.getPower();
    }
    public double getMotor1Speed(){
        return motor1.getPower();
    }
    public double getMotor2Speed(){
        return motor2.getPower();
    }
    public double getMotor3Speed(){
        return motor3.getPower();
    }
    public double getMotor4Speed(){
        return motor4.getPower();
    }
}
