package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetition;
import org.firstinspires.ftc.teamcode.mechanisms.Odometry;

@TeleOp(name = "Super Duper Fascinating TeleOp of Team 26725 (Cathedral Mechanicus)")
public class Tele extends OpMode {

    boolean endRumble = false;
    DevicesForCompetition hw = new DevicesForCompetition();
    Odometry odo = new Odometry();
    boolean wasRB1;
    boolean wasLB1;
    boolean wasRSB1;
    boolean wasA2;
    boolean wasB2;
    boolean wasRB2;
    boolean wasLB2;
    boolean wasA1;
    boolean wasRSB2;
    boolean wasLSB2;

    public double maxPower = 1.0;
    public double maxSpeed = 1.0;

    int testShooting = 0;

    int shooting = 0;

    int intake = 0;
    int belt = 0;

    @Override
    public void init(){

        hw.init(hardwareMap);
        telemetry.addData("status", "init done! Hardware Mapped");
        odo.resetOdometry();
    }

    @Override
    public void start(){
        telemetry.clearAll();
        resetRuntime();
    }

    @Override
    public void loop(){
        odo.updatePosition(hw.getLeftEncTicks(), hw.getRightEncTicks(), hw.getNormalEncTicks(), hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        maxPower = 1;
        telemetry.addData("status", "loop started! Good Luck");
        telemetry.addData("frontRight", hw.getFrontRightSpeed());
        telemetry.addData("frontLeft", hw.getFrontLeftSpeed());
        telemetry.addData("backRight", hw.getBackRightSpeed());
        telemetry.addData("backLeft", hw.getBackLeftSpeed());
        telemetry.addData("Time Elapsed", getRuntime());

        if (gamepad1.right_bumper){
            drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Drive Mode", "Bot Relative");
        }else {
            driveFieldPerspective(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Drive Mode", "Field Relative");
        }
        if (gamepad1.right_stick_button && !wasRSB1){
            hw.imu.resetYaw();
            odo.setAngle(0);
        }

        if(gamepad1.left_bumper){
            maxSpeed = 0.5;
            telemetry.addData("Speed", "slow");
        }else {
            maxSpeed = 1;
            telemetry.addData("Speed", "normal");
        }

        if(gamepad2.a && !wasA2){
            intake ++;
        }
        if(gamepad2.b && !wasB2){
            belt ++;
        }
        if(gamepad2.right_stick_button && !wasRSB2){
            testShooting ++;
        }
        if(gamepad2.left_stick_button && !wasLSB2){
            testShooting = 0;
        }



        if(gamepad2.right_bumper && !wasRB2){
            hw.setShooting(1);
        }
        if(gamepad2.left_bumper && !wasLB2){
            hw.setShooting(0);
        }

        hw.intake(intake);
           hw.belt(belt);



        telemetry.addData("x", odo.getX());
        telemetry.addData("y", odo.getY());
        telemetry.addData("odo angle", odo.getAvgAngle());
        telemetry.addData("imu angle", hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        if(gamepad1.a && !wasA1){
            moveToPosition(0,0,0,5,5,1000);
        }


         if(getRuntime() > 85 && !endRumble){
            gamepad1.rumbleBlips(3);
            gamepad2.rumbleBlips(3);
            endRumble = true;
            telemetry.addLine("Game About to Finish!");
        }
        wasRB1 = gamepad1.right_bumper;
        wasLB1 = gamepad1.left_bumper;
        wasRSB1 = gamepad1.right_stick_button;
        wasA2 = gamepad2.a;
        wasB2 = gamepad2.b;
        wasRB2 = gamepad2.right_bumper;
        wasLB2 = gamepad2.left_bumper;
        wasRSB2 = gamepad2.right_stick_button;
        wasLSB2 = gamepad2.left_stick_button;

    }

    @Override
    public void stop(){
        telemetry.clearAll();
        telemetry.addData("status", "stopped");
    }
    public void drive(double x, double y, double r){

        y = y;
        double frontRightPower = -x + y - r;
        double frontLeftPower = x + y + r;
        double backRightPower = x + y - r;
        double backLeftPower = -x + y + r;


        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));

        hw.setFrontRightSpeed(maxSpeed * (frontRightPower/maxPower));
        hw.setFrontLeftSpeed(maxSpeed * (frontLeftPower/maxPower));
        hw.setBackRightSpeed(maxSpeed * (backRightPower/maxPower));
        hw.setBackLeftSpeed(maxSpeed * (backLeftPower/maxPower));
    }



    public void driveFieldPerspective(double x, double y, double r){
        double angle = hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        angle = AngleUnit.normalizeRadians(angle);
        double newX = x*Math.cos(-angle) - y*Math.sin(-angle);
        double newY = x*Math.sin(-angle) + y*Math.cos(-angle);

        drive(newX,newY, r);
    }
    public void moveToPosition(double targetX, double targetY, double targetAngle, double MOE, double angleMOE, double maxTime){
        ElapsedTime timer = new ElapsedTime();
        double xError = 200, yError = 200, angleError = 200;
        double previousTime = 0;
        double previousX = 0;
        double previousY = 0;
        double previousTheta = 0;
        while(timer.milliseconds() <= maxTime && (Math.abs(xError) >= MOE || Math.abs(yError) >= MOE || Math.abs(angleError) >= angleMOE)){
            odo.updatePosition(hw.getLeftEncTicks(), hw.getRightEncTicks(), hw.getNormalEncTicks(), hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            double k_P = 0.05; //Tune this coefficient
            double currentTime = timer.milliseconds();
            xError = targetX - odo.getX();
            yError = targetY - odo.getY();
            angleError = odo.normalizeAngle(targetAngle - odo.getAngle());

            double xP = k_P * xError;
            double yP = k_P * yError;
            double thetaP = k_P * angleError;

            double xD = calculateD(xError, previousX, currentTime, previousTime);
            double yD = calculateD(yError, previousY, currentTime, previousTime);
            double thetaD = calculateD(angleError, previousTheta, currentTime, previousTime);

            double xPow = xP + xD;
            double yPow = yP + yD;
            double anglePow = thetaP + thetaD;

            driveFieldPerspective(xPow, yPow, anglePow);

            previousX = xError;
            previousY = yError;
            previousTheta = angleError;
            previousTime = currentTime;

        }
    }
    public double calculateD(double curr_error, double prev_error, double curr_time, double prev_time){   //Just a function for differentiation

        double k_d = 0.12;
        double error_diff = curr_error - prev_error;
        double time_diff = curr_time - prev_time;

        double errorByTime = error_diff / time_diff;
        double d = k_d * errorByTime;

        return d;
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode