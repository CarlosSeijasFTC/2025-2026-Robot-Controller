package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Methods.MotorMethods;

@Autonomous(name = "PID", group = "Tests")
public class PIDTest extends LinearOpMode {

    protected DcMotor motor1;
    MotorMethods methods = new MotorMethods();
    @Override
    public void runOpMode(){
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        methods.MoveToPosition(motor1, 100, 0.1, 0, 0);

        waitForStart();
        stop();
    }
}
