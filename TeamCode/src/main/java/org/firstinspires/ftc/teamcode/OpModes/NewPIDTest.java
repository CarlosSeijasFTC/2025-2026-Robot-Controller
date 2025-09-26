package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetitionMotors;

@Autonomous(name = "newPID")
public class NewPIDTest extends OpMode {
    DevicesForCompetitionMotors hw = new DevicesForCompetitionMotors();


    @Override
    public void init(){
        hw.init(hardwareMap);
        hw.motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hw.motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hw.motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop(){
        telemetry.addData("Motor", hw.motor1.getCurrentPosition());
        MoveToPosition(hw.motor1, 10000, 0.001, 0.15, 0.12);


    }
    public void MoveToPosition(DcMotor motor, int targetPos, double k_p, double k_i, double k_d){
        ElapsedTime time = new ElapsedTime();
        int accuracy = 65;
        double previousTime = 0, previousError = 0;
        double p = 0, i = 0, d = 0;
        double max_i = 0.2, min_i = -0.2;
        double power;
        while (Math.abs(motor.getCurrentPosition()-targetPos) > accuracy){
            double currentTime = time.milliseconds();
            double error = targetPos - motor.getCurrentPosition();

            p = k_p*error;
            i += k_i * (error * (currentTime - previousTime));
            i = Range.clip(i, min_i, max_i);
            d= k_d * ((error - previousError)/(currentTime - previousTime));

            power = p + i + d;
            motor.setPower(power);
            previousError = error;
            previousTime = currentTime;
            telemetry.addData("Motor", motor.getCurrentPosition());
            telemetry.update();
        }
        motor.setPower(0);
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode