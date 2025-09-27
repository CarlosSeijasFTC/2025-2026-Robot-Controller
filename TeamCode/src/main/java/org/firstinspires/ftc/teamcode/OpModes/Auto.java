package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetition;
import org.firstinspires.ftc.teamcode.mechanisms.Odometry;


@Autonomous(name = "26725 Super Duper Fascinating Autonomous Code, Ohh Yeah")
public class Auto extends OpMode {
    Odometry localization = new Odometry();
    DevicesForCompetition hw = new DevicesForCompetition();
    MecanumDrive drive = new MecanumDrive();
    String state = "START";

    @Override
    public void init() {
        hw.init(hardwareMap);
    }

    @Override
    public void start(){
        state = "LOADED_START";
    }

    @Override
    public void loop() {
        telemetry.addData("State", state);
        switch (state){
            case "LOADED_START":

        }
    }

    /**
     * Moves the Robot to a desired position and yaw pitch. Parameters in cm and degrees. Uses cartesian coordinates based on Robot's odometry. PID control
     * @param targetX in cm
     * @param targetY in cm
     * @param targetAngle in degrees
     * @param MOE Margin of Error x and y
     * @param angleMOE margin of Error in degrees
     * @param maxTime I case there is an object obstructing
     */
    public void moveToPosition(double targetX, double targetY, double targetAngle, double MOE, double angleMOE, double maxTime){
        ElapsedTime timer = new ElapsedTime();
        double xError = 200, yError = 200, angleError = 200;
        double previousTime = 0;
        double previousX = 0;
        double previousY = 0;
        double previousTheta = 0;
        while(timer.milliseconds() <= maxTime && (Math.abs(xError) >= MOE || Math.abs(yError) >= MOE || Math.abs(angleError) >= angleMOE)){
            localization.updatePosition(hw.getLeftEncTicks(), hw.getRightEncTicks(), hw.getNormalEncTicks(), hw.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            double k_P = 0.05; //Tune this coefficient
            double currentTime = timer.milliseconds();
            xError = targetX - localization.getX();
            yError = targetY - localization.getY();
            angleError = normalizeAngle(targetAngle - localization.getAngle());

            double xP = k_P * xError;
            double yP = k_P * yError;
            double thetaP = k_P * angleError;

            double xD = calculateD(xError, previousX, currentTime, previousTime);
            double yD = calculateD(yError, previousY, currentTime, previousTime);
            double thetaD = calculateD(angleError, previousTheta, currentTime, previousTime);

            double xPow = xP + xD;
            double yPow = yP + yD;
            double anglePow = thetaP + thetaD;

            drive.driveFieldPerspective(xPow, yPow, anglePow);

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

    /**
     * Normalizes angles
     * @param rawAngle in degrees
     * @return angle in degrees -180<return<180
     */
    public double normalizeAngle(double rawAngle) {
        double scaledAngle = rawAngle % 360;
        if (scaledAngle < 0) {
            scaledAngle += 360;
        }

        if (scaledAngle > 180) {
            scaledAngle -= 360;
        }

        return scaledAngle;
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode