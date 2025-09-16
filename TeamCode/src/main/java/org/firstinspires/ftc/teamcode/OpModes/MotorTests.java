package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetitionMotors;

@TeleOp(name = "Test Motors Using Controller", group = "Tests")
public class MotorTests extends OpMode {

    private String motor1activeness;
    private String motor2activeness;

    DevicesForCompetitionMotors hw = new DevicesForCompetitionMotors();

    @Override
    public void init(){
        hw.init(hardwareMap);
        telemetry.addData("init", "done! Devices Mapped");
    }

    @Override
    public void start(){
        telemetry.clearAll();
    }

    @Override
    public void loop(){
        telemetry.addData("motors", "Try Using the Controller!");
        this.motor1activeness = "not active";
        this.motor2activeness = "not active";
        if (gamepad1.left_stick_y != 0 ){
            hw.setMotorSpeed(hw.motor1, -gamepad1.left_stick_y);
            this.motor1activeness = "active!";
        }
        if(gamepad1.right_stick_y != 0){
            hw.setMotorSpeed(hw.motor2, -gamepad1.right_stick_y);
            this.motor2activeness = "active!";
        }
        telemetry.addData("motor1", motor1activeness);
        telemetry.addData("motor2", motor2activeness);
        telemetry.addData("right stick", gamepad1.right_stick_x + " " + -gamepad1.right_stick_y);
        telemetry.addData("left stick", gamepad1.left_stick_x + " " + -gamepad1.left_stick_y);

    }


}
