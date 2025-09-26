package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;

public class OdometryPod {
    private final OctoQuad octoQuad;
    private final int channel;

    public OdometryPod(OctoQuad octoQuad, int channel, OctoQuad.EncoderDirection direction){
        this.octoQuad = octoQuad;
        this.channel = channel;
        octoQuad.setSingleEncoderDirection(channel, direction);
    }
    public double getOdometryPodTicks(){
        return octoQuad.readSinglePosition_Caching(channel);
    }
}
//Carlos Seijas, FTC Team 26725 - Cathedral Mechanicus, 2025-2026 Season Decode