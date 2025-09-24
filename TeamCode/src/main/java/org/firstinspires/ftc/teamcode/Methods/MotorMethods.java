package org.firstinspires.ftc.teamcode.Methods;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class MotorMethods {
    public void MoveToPosition(DcMotor motor, int targetPos, double k_p, double k_i, double k_d){
        ElapsedTime time = new ElapsedTime();
        int accuracy = 2;
        double previousTime = 0, previousError = 0;
        double p = 0, i = 0, d = 0;
        double max_i = 0.2, min_i = -0.2;
        double power;
        while (Math.abs(motor.getCurrentPosition()-targetPos) > accuracy){
            double currentTime = time.milliseconds();
            double error = targetPos - motor.getCurrentPosition();

            p = k_p*error;
            i += k_i * (error * (currentTime - previousTime));
            Range.clip(i, min_i, max_i);
            d= k_d * ((error - previousError)/(currentTime - previousTime));

            power = p + i + d;
            motor.setPower(power);
            previousError = error;
            previousTime = currentTime;
        }
        motor.setPower(0);
    }
}
