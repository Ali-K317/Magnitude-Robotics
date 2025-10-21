package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Keeping Button Pressed", group = "test")
public class pressedButtons extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        /*
        These are what our if statements will read.
        They are set to false since we have yet to press them.
         */
        boolean lastXPressed = false;
        boolean lastYPressed = false;

        /*
        These detect whether the buttons are pressed.
        They are set to false since we have yet to press them.
         */
        boolean previousXState = false;
        boolean previousYState = false;

        waitForStart();
        while(opModeIsActive()){
            String telemetryNumber;

            if(gamepad1.x && !previousXState){
                lastXPressed = !lastXPressed;
            }else if(gamepad1.y && !previousYState){
                lastYPressed = !lastYPressed;
            }

            /*
            These are called after the first if-loop because if not it will always be false.
            The variable is set to false at first, so if it is read first it will always be false.
            Keep this in mind anytime you have to check for variables.
             */
            previousXState = gamepad1.x;
            previousYState = gamepad1.y;

            if(lastXPressed){
                telemetryNumber = "one";
            }else if(lastYPressed){
                telemetryNumber = "two";
            }else{
                telemetryNumber = "";
            }
            telemetry(telemetryNumber);
        }
    }
    //Outputs different telemetry depending on a string variable
    private void telemetry(String telemetryNumber){
        switch(telemetryNumber){
            case "one":
                telemetry.addLine("Telemetry One");
                telemetry.update();
                break;
            case "two":
                telemetry.addLine("Telemetry Two");
                telemetry.update();
                break;
            case "":
                telemetry.addLine("Neither Telemetry is Running");
                telemetry.update();
                break;
        }
    }

}
