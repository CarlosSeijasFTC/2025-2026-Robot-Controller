package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DevicesForCompetition {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor motor1;
    public DcMotor motor2;

    public void init(HardwareMap hwMp){
        frontLeft = hwMp.get(DcMotor.class, "frontLeft");
        frontRight = hwMp.get(DcMotor.class, "frontRight");
        backLeft = hwMp.get(DcMotor.class, "backLeft");
        backRight = hwMp.get(DcMotor.class, "backRight");
        motor1 = hwMp.get(DcMotor.class, "motor1");
        motor2 = hwMp.get(DcMotor.class, "motor2");
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setMotorSpeed(DcMotor motor, double speed ){
        motor.setPower(speed);
    }
}
