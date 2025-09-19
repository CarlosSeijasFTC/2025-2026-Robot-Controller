package org.firstinspires.ftc.teamcode.OpModes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "ColorTest", group = "Tests")
public class ColorTest extends OpMode {

    public ColorSensor colorSensor;
    public DistanceSensor distanceSensor;
    float[] hsbValues = new float[3];

    @Override
    public void init(){
        colorSensor = hardwareMap.get(ColorSensor.class, "Color/Distance");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "Color/Distance");
    }

    @Override
    public void loop(){
        telemetry.addData("RGB", colorSensor.red() + " " + colorSensor.green() + " " + colorSensor.blue());
        Color.RGBToHSV(colorSensor.red(), colorSensor.green(), colorSensor.blue(), hsbValues);
        telemetry.addData("HSB", hsbValues[0] + " " + hsbValues[1] + " " + hsbValues[2]);
        telemetry.addData("distance", distanceSensor.getDistance(DistanceUnit.CM));

    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode