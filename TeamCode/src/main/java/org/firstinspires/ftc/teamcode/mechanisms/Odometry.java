package org.firstinspires.ftc.teamcode.mechanisms;



public class Odometry {
    private final double ENCODER_WHEEL_DIAMETER = 4.8;
    private final double ENCODER_TICKS_PER_REVOLUTION = 2000;
    private final double ENCODER_WHEEL_CIRCUMFERENCE = Math.PI * Math.sqrt(ENCODER_WHEEL_DIAMETER/2); //change this
    private final double ENCODER_WIDTH = 12; //claculate this

    private double xPos, yPos, angle;
    private double lastLeftEnc, lastRightEnc, lastNormalEnc;
    public Odometry(double xPos, double yPos, double angle){
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
    }
    public Odometry(double angle){
        this.xPos = 0;
        this.yPos = 0;
        this.angle = angle;
    }

    public Odometry(){
        this(0);
    }

    /**
     * Updates position of the class main values, x, y, and angle
     * @param l left encoder ticks
     * @param r right encoder ticks
     * @param n normal encoder ticks
     * @param ang imu or other angle orientation device in degrees. Used for more precision
     */
    public void updatePosition(double l, double r, double n, double ang) {
        double dR = r - lastRightEnc;
        double dL = l - lastLeftEnc;
        double dN = n - lastNormalEnc;

        double rightDist = dR * ENCODER_WHEEL_CIRCUMFERENCE / ENCODER_TICKS_PER_REVOLUTION;
        double leftDist = -dL * ENCODER_WHEEL_CIRCUMFERENCE / ENCODER_TICKS_PER_REVOLUTION;
        double dyR = 0.5 * (rightDist + leftDist);
        double headingChangeRadians = (rightDist - leftDist) / ENCODER_WIDTH;
        double dxR = -dN * ENCODER_WHEEL_CIRCUMFERENCE / ENCODER_TICKS_PER_REVOLUTION;
        double avgHeadingRadians = Math.toRadians(angle) + headingChangeRadians / 2.0;
        double cos = Math.cos(avgHeadingRadians);
        double sin = Math.sin(avgHeadingRadians);

        xPos += dxR * sin + dyR * cos;
        yPos += -dxR * cos + dyR * sin;
        angle = normalizeAngle(ang);
        lastNormalEnc = n;
        lastLeftEnc = l;
        lastRightEnc = r;
    }
    public double normalizeAngle(double rawAngle) {
        double scaledAngle = rawAngle % 360;
        if (scaledAngle < 0) {
            scaledAngle += 360;
        }

        if (scaledAngle > 180) {
            scaledAngle -= 360;
        }

        return scaledAngle;
    }


    public double getAngle() {
        return angle;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public void resetOdometry() {
        resetOdometry(0, 0, 0);
    }


    public void resetOdometry(double xPos, double yPos, double angle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    private String format(double num) {
        return String.format("%.3f", num);
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode
