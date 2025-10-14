package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetitionMotors;

@TeleOp
public class DoubleMoptorTest extends OpMode {
    DevicesForCompetitionMotors motors = new DevicesForCompetitionMotors();

    @Override
    public void init(){
        motors.init(hardwareMap);
    }

    @Override
    public void loop(){
        motors.motor1.setPower(-gamepad1.left_stick_y);
        motors.motor2.setPower(-gamepad1.left_stick_y);
    }
}
