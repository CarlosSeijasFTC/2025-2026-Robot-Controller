package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetitionServos;

@TeleOp(name = "Test Servos Using Controller")
public class SevoTests extends OpMode {

    boolean wasA;
    boolean wasB;
    boolean wasX;
    boolean wasY;


    DevicesForCompetitionServos hw = new DevicesForCompetitionServos();

    @Override
    public void init(){
        hw.init(hardwareMap);
        telemetry.addData("status", "Init Done! Hardware Mapped");
    }

    @Override
    public void start(){
        telemetry.clearAll();
    }

    @Override
    public void loop(){
        if(gamepad1.a && !wasA){
            hw.setServo1Pos(0.0);
        }
        if(gamepad1.b && !wasB){
            hw.setServo1Pos(1.0);
        }
        if(gamepad1.x && !wasX){
            hw.setServo2Pos(0.0);
        }
        if(gamepad1.y && !wasY){
            hw.setServo2Pos(1.0);
        }
        wasA = gamepad1.a;
        wasB = gamepad1.b;
        wasX = gamepad1.x;
        wasY = gamepad1.y;

        telemetry.addData("Servo 1 Position", hw.getServo1Pos());
        telemetry.addData("Servo 2 Position", hw.getServo2Pos());
        telemetry.addData("a", gamepad1.a);
        telemetry.addData("b", gamepad1.b);
        telemetry.addData("x", gamepad1.x);
        telemetry.addData("y", gamepad1.y);


    }
}
