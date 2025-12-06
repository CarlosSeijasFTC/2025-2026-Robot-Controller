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
    private DcMotor belt;
    private CRServo[] servos = new CRServo[4];
    private String[] servoNames = new String[]{"bands", "backLefts", "frontRights", "backRights"};
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
        belt = hwMp.get(DcMotor.class, "belt");

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

        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        belt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        belt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);


        lens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);

        lens.initialize();



        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
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
        belt.setPower(speed);
    }
    public void setShooting(double speed){
        rightShooter.setPower(speed);
        leftShooter.setPower(speed);
    }


    public void intake(int cosa) {
        int modulo = cosa % 2;
        switch (modulo){
            case 0:
                intake.setPower(0);
                break;
            case 1:
                intake.setPower(1);
                break;
        }
    }

    public void belt(int cosa){
        int modulo = cosa % 2;
        switch (modulo){
            case 0:
                belt.setPower(0);
                servos[0].setPower(0);
                break;
            case 1:
                belt.setPower(0.8);
                servos[0].setPower(1);
                break;
        }
    }

    public void shootingTest(int on){
        int module = on % 6;
        switch (module){
            case 0:
                leftShooter.setPower(0);
                rightShooter.setPower(0);
                break;
            case 1:
                leftShooter.setPower(0.75);
                rightShooter.setPower(0.75);
                break;
            case 2:
                leftShooter.setPower(0.80);
                rightShooter.setPower(0.80);
                break;
            case 3:
                rightShooter.setPower(0.85);
                leftShooter.setPower(0.85);
                break;
            case 4:
                leftShooter.setPower(0.9);
                rightShooter.setPower(0.9);
                break;
            case 5:
                leftShooter.setPower(1);
                rightShooter.setPower(1);
                break;
        }
    }

    public void shoot(int on){
        int module = on %2;
        switch (module){
            case 0:
                leftShooter.setPower(0);
                rightShooter.setPower(0);
                break;
            case 1:
                leftShooter.setPower(1);
                rightShooter.setPower(1);
                break;
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
        return belt.getPower();
    }

    public double getLeftEncTicks(){
        return leftShooter.getCurrentPosition();
    }
    public double getRightEncTicks(){
        return -rightShooter.getCurrentPosition();
    }
    public double getNormalEncTicks(){
        return -intake.getCurrentPosition();
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode