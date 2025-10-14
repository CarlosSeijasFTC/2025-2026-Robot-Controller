package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
    boolean wasOptions1;
    boolean wasA2;
    boolean wasB2;
    boolean wasRB2;
    boolean wasLB2;
    boolean shoot = false;
    public double maxPower = 1.0;
    public double maxSpeed = 1.0;

    boolean intake = false;

    @Override
    public void init(){
        hw.init(hardwareMap);
        telemetry.addData("status", "init done! Hardware Mapped");
    }

    @Override
    public void start(){
        telemetry.clearAll();
        resetRuntime();
    }

    @Override
    public void loop(){
        odo.updatePosition(hw.getLeftEncTicks(), hw.getRightEncTicks(), hw.getNormalEncTicks(), hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
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
        if (gamepad1.options && !wasOptions1){
            hw.imu.resetYaw();
        }

        if(gamepad1.left_bumper){
            maxSpeed = 0.5;
            telemetry.addData("Speed", "slow");
        }else {
            maxSpeed = 1;
            telemetry.addData("Speed", "normal");
        }

        if (!intake){
            hw.intake(false);
            telemetry.addData("intake", "off");
            if (gamepad2.a && !wasA2){
                intake = true;
            }
        }

        if(intake){
            hw.intake(true);
            telemetry.addData("intake", "on");
            if(gamepad2.b && !wasB2){
                intake = false;
            }
        }

        if (!shoot){
            hw.shooting(false);
            telemetry.addData("shooting", "off");
            if (gamepad2.right_bumper && !wasRB2){
                shoot = true;
            }
        }

        if(shoot){
            hw.intake(true);
            telemetry.addData("shooting", "on");
            if(gamepad2.left_bumper && !wasLB2){
                shoot = false;
            }
        }

        telemetry.addData("x", odo.getX());
        telemetry.addData("y", odo.getY());
        telemetry.addData("odo angle", odo.getAngle());
        telemetry.addData("imu angle", hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));


        /* if(getRuntime() > 85 && !endRumble){
            gamepad1.rumbleBlips(3);
            gamepad2.rumbleBlips(3);
            endRumble = true;
            telemetry.addLine("Game About to Finish!");
        }*/
        wasRB1 = gamepad1.right_bumper;
        wasLB1 = gamepad1.left_bumper;
        wasOptions1 = gamepad1.options;
        wasA2 = gamepad2.a;
        wasB2 = gamepad2.b;
        wasRB2 = gamepad2.right_bumper;
        wasLB2 = gamepad2.left_bumper;
    }

    @Override
    public void stop(){
        telemetry.clearAll();
        telemetry.addData("status", "stopped");
    }
    public void drive(double x, double y, double r){
        x = x*1.1;
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
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode