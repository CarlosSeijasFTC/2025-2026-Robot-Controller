package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name = "AprilTags", group = "Tests")
public class AprilTagIds extends OpMode {

    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        WebcamName aprilTags = hardwareMap.get(WebcamName.class, "aprilTags");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(aprilTags, aprilTagProcessor);
    }
    @Override
    public void init_loop(){
        List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
        StringBuilder ids = new StringBuilder();
        for(AprilTagDetection detection : currentDetections){
            ids.append(detection.id);
            ids.append(" ");
        }
        telemetry.addData("AprilTags", ids);
    }
    @Override
    public void start(){
        visionPortal.stopStreaming();
    }
    @Override
    public void loop(){
    }
}
