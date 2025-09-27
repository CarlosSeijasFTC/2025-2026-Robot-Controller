package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.DevicesForCompetition;

@TeleOp(name = "Super Duper Fascinating TeleOp of Team 26725 (Cathedral Mechanicus)")
public class Tele extends OpMode {

    boolean endRumble = false;
    DevicesForCompetition hw = new DevicesForCompetition();
    MecanumDrive Drive = new MecanumDrive();
    boolean wasRB1;
    boolean wasLB1;
    boolean wasOptions1;
    boolean wasA2;
    boolean wasB2;
    boolean wasRB2;
    boolean wasLB2;
    boolean shoot = false;

    boolean intake = false;

    @Override
    public void init(){
        hw.init(hardwareMap);
        telemetry.addData("status", "init done! Hardware Mapped");
    }

    @Override
    public void start(){
        telemetry.clearAll();
        resetRuntime();
    }

    @Override
    public void loop(){
        telemetry.addData("status", "loop started! Good Luck");
        telemetry.addData("frontRight", hw.getFrontRightSpeed());
        telemetry.addData("frontLeft", hw.getFrontLeftSpeed());
        telemetry.addData("backRight", hw.getBackRightSpeed());
        telemetry.addData("backLeft", hw.getBackLeftSpeed());
        telemetry.addData("Time Elapsed", getRuntime());

        if (gamepad1.right_bumper && !wasRB1){
            Drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Drive Mode", "Bot Relative");
        }else {
            Drive.driveFieldPerspective(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Drive Mode", "Field Relative");
        }
        if (gamepad1.options && !wasOptions1){
            hw.imu.resetYaw();
        }

        if(gamepad1.left_bumper && !wasLB1){
            Drive.maxSpeed = 0.5;
            telemetry.addData("Speed", "slow");
        }else {
            Drive.maxSpeed = 1;
            telemetry.addData("Speed", "normal");
        }

        if (!intake){
            hw.intake(false);
            telemetry.addData("intake", "off");
            if (gamepad2.a && !wasA2){
                intake = true;
            }
        }

        if(intake){
            hw.intake(true);
            telemetry.addData("intake", "on");
            if(gamepad2.b && !wasB2){
                intake = false;
            }
        }

        if (!shoot){
            hw.shooting(false);
            telemetry.addData("shooting", "off");
            if (gamepad2.right_bumper && !wasRB2){
                shoot = true;
            }
        }

        if(shoot){
            hw.intake(true);
            telemetry.addData("shooting", "on");
            if(gamepad2.left_bumper && !wasLB2){
                shoot = false;
            }
        }


        /* if(getRuntime() > 85 && !endRumble){
            gamepad1.rumbleBlips(3);
            gamepad2.rumbleBlips(3);
            endRumble = true;
            telemetry.addLine("Game About to Finish!");
        }*/
        wasRB1 = gamepad1.right_bumper;
        wasLB1 = gamepad1.left_bumper;
        wasOptions1 = gamepad1.options;
        wasA2 = gamepad2.a;
        wasB2 = gamepad2.b;
        wasRB2 = gamepad2.right_bumper;
        wasLB2 = gamepad2.left_bumper;
    }

    @Override
    public void stop(){
        telemetry.clearAll();
        telemetry.addData("status", "stopped");
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode