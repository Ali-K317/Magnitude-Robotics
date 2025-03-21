package org.firstinspires.ftc.teamcode.Kerollo_Senior_Thesis;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Solarsteam", group = "Senior")
public class kerrolos extends LinearOpMode {
    private Servo gearServo, mirrorServo;
    private final ElapsedTime timer = new ElapsedTime();
    boolean hasMovedLeft = false;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        while(opModeInInit()){
            timer.reset();
        }
        waitForStart();
        while (opModeIsActive()) {
            if (timer.seconds() > 2) { // Toggle every 2 seconds
                hasMovedLeft = !hasMovedLeft;
                telemetry.addData("hasMovedLeft: ", hasMovedLeft);
                telemetry.update();
                if (hasMovedLeft) {
                    setServoPos(gearServo, 0.53);
                    setServoPos(mirrorServo, 0.55);
                } else {
                    setServoPos(gearServo, 0.47);
                    setServoPos(mirrorServo, 0.45);
                }
                timer.reset();// Reset the timer after changing position
                telemetry.addLine("Timer reset");
                telemetry.update();
            }
        }
    }
    private void initialize() {
        gearServo = hardwareMap.get(Servo.class, "gearServo");
        mirrorServo = hardwareMap.get(Servo.class, "mirrorServo");
    }
    private void setServoPos(Servo servo, double position){
        servo.setPosition(position);
    }
}

