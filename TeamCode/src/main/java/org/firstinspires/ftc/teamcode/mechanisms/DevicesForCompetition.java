package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;


public class DevicesForCompetition {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor intake;
    private DcMotor rightShooter;
    private DcMotor leftShooter;
    private DcMotor motor4;
    private CRServo[] servos = new CRServo[4];
    private String[] servoNames = new String[]{"frontLefts", "backLefts", "frontRights", "backRights"};
    public IMU imu;


    public HuskyLens lens;



    public void init( HardwareMap hwMp){
        frontLeft = hwMp.get(DcMotor.class, "frontLeft");
        frontRight = hwMp.get(DcMotor.class, "frontRight");
        backLeft = hwMp.get(DcMotor.class, "backLeft");
        backRight = hwMp.get(DcMotor.class, "backRight");

        intake = hwMp.get(DcMotor.class, "intakeNormalEnc");
        rightShooter = hwMp.get(DcMotor.class, "rightShooterRightEnc");
        leftShooter = hwMp.get(DcMotor.class, "leftShooterLeftEnc");
        motor4 = hwMp.get(DcMotor.class, "motor4");

        for(int i=0; i <= 3; i++) {
        servos[i] = hwMp.get(CRServo.class, servoNames[i]);
        }

        imu = hwMp.get(IMU.class, "imu");

        lens = hwMp.get(HuskyLens.class, "husky");

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        for(int i=0; i <=1; i++){
            servos[i].setDirection(DcMotorSimple.Direction.REVERSE);
        }

        lens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);

        lens.initialize();



        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD, RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(RevOrientation));
        imu.resetYaw();


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
    public void setMotor4Speed (double speed){
        motor4.setPower(speed);
    }


    public void intake(boolean on) {
        if(on){
            intake.setPower(1);
            for(int i=0; i <= 3; i++){
                servos[i].setPower(1);
            }
        }
        if(!on){
            intake.setPower(0);
            for(int i=0; i <= 3; i++){
                servos[i].setPower(0);
            }
        }
    }

    public void shooting(boolean on){
        if(on){
            leftShooter.setPower(1);
            rightShooter.setPower(1);
        }
        if(!on){
            leftShooter.setPower(0);
            rightShooter.setPower(0);
        }
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
    public double getMotor4Speed(){
        return motor4.getPower();
    }

    public double getLeftEncTicks(){
        return -leftShooter.getCurrentPosition();
    }
    public double getRightEncTicks(){
        return -rightShooter.getCurrentPosition();
    }
    public double getNormalEncTicks(){
        return -intake.getCurrentPosition();
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode