package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetition;

import java.lang.Math;

public class MecanumDrive {
    public double maxPower = 1.0;
    public double maxSpeed = 1.0;

    DevicesForCompetition hw = new DevicesForCompetition();

    public MecanumDrive(HardwareMap hardwareMap){
        hw.init(hardwareMap);
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