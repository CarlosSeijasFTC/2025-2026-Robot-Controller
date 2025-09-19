package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetition;

import java.lang.Math;

public class MecanumDrive {
    public double maxPower = 1.0;
    public double maxSpeed = 1.0;

    DevicesForCompetition hw = new DevicesForCompetition();

    public void drive(double x, double y, double r){
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
        double angleBot = hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double theta = Math.atan2(y, x);
        double radius = Math.hypot(y, x);
        theta = AngleUnit.normalizeRadians(theta - angleBot);
        double newX = radius * Math.cos(theta);
        double newY = radius * Math.sin(theta);

        drive(newX, newY, r);
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode