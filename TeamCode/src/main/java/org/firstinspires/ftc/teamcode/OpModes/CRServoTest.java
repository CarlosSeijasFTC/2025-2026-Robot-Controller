package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetitionServos;

@TeleOp
public class CRServoTest extends OpMode {
    private CRServo servo1;
    private CRServo servo2;

    @Override
    public void init(){
    servo1 = hardwareMap.get(CRServo.class, "servo1");
    servo2 = hardwareMap.get(CRServo.class, "servo2");
    }

    @Override
    public void loop(){
        servo1.setPower(-gamepad1.left_stick_y);
        servo2.setPower(-gamepad1.right_stick_y);
    }
}
