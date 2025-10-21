package org.firstinspires.ftc.teamcode.Templates;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.List;

@Autonomous(name = "DO NOT PICK THIS", group = "Template")
public class autonomousTemplate extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        initializeHardware();
        setHardwareDirections();
        setBrakes();
        while (opModeInInit()){

        }
        waitForStart();
        while(opModeIsActive()){
            telemetry();
        }
    }
    //INITIALIZATION
    private void initializeWheels(){

    }
    private void initializeArms(){

    }
    private void initializeClaws(){

    }
    private  void initializeSensors(){

    }
    private void initializeHardware(){
        initializeWheels();
        initializeArms();
        initializeClaws();
        initializeSensors();
    }

    //DIRECTION
    private void setDirectionWheels(){

    }
    private void setDirectionArms() {

    }
    private void setDirectionClaws(){

    }
    private void setHardwareDirections(){
        setDirectionWheels();
        setDirectionArms();
        setDirectionClaws();
    }

    //BRAKES
    private void setBrakesWheels(){

    }
    private void setBrakesArms(){

    }
    private void setBrakesClaws(){

    }
    private void setBrakes(){
        setBrakesWheels();
        setBrakesArms();
        setBrakesClaws();
    }

    //TELEMETRY
    private void telemetry(){
        telemetry.update();
    }

    //MOVEMENT

}
