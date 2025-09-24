package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Methods.MotorMethods;
import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetitionMotors;

@Autonomous(name = "newPID")
public class NewPIDTest extends OpMode {
    DevicesForCompetitionMotors hw = new DevicesForCompetitionMotors();
    MotorMethods methods = new MotorMethods();

    @Override
    public void init(){
        hw.init(hardwareMap);
        hw.motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hw.motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hw.motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        methods.MoveToPosition(hw.motor1, 1000, 0.00001, 0.1, 0);
    }

    @Override
    public void loop(){
        telemetry.addData("Motor", hw.motor1.getCurrentPosition());

    }
}
