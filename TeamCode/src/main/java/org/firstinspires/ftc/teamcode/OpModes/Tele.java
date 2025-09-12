package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetition;

@TeleOp(name = "Michael is P. Diddy's Son")
public class Tele extends OpMode {
    DevicesForCompetition hw = new DevicesForCompetition();
    public void drive(double x, double y, double r){
        double frontRightPower = -x + y - r;
        double frontLeftPower = x + y + r;
        double backRightPower = x + y - r;
        double backLeftPower = -x + y + r;

        double maxPower = 1.0;
        double maxSpeed = 1.0;

        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));

        hw.setMotorSpeed(hw.frontRight, maxSpeed * (frontRightPower/maxPower));
        hw.setMotorSpeed(hw.frontLeft, maxSpeed * (frontLeftPower/maxPower));
        hw.setMotorSpeed(hw.backRight, maxSpeed * (backRightPower/maxPower));
        hw.setMotorSpeed(hw.backLeft, maxSpeed * (backLeftPower/maxPower));
    }

    @Override
    public void init(){
        hw.init(hardwareMap);
        telemetry.addData("status", "init done! Hardware Mapped");
    }

    @Override
    public void start(){
        telemetry.clearAll();
    }

    @Override
    public void loop(){
        telemetry.addData("status", "loop started! Good Luck");
        drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
        telemetry.addData("frontRight", hw.frontRight.getPower());
        telemetry.addData("frontLeft", hw.frontLeft.getPower());
        telemetry.addData("backRight", hw.backRight.getPower());
        telemetry.addData("backLeft", hw.backLeft.getPower());
    }

    @Override
    public void stop(){
        telemetry.clearAll();
        telemetry.addData("status", "stopped");
    }
}
