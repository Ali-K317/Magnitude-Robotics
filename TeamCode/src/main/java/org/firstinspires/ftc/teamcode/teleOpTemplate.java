package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DO NOT PICK THIS", group = "Template")
public class teleOpTemplate extends LinearOpMode {

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
            finalWheelMovement();
            finalArmMovement();
            finalClawMovement();
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
    //Wheels
    private void moveWheels(double vertical, double strafe, double turn){

    }
    private void finalWheelMovement(){

    }

    //Arms
    private void moveArms(String state, double speed){

    }
    private void finalArmMovement(){

    }

    //Claws
    private void moveClaws(String state, double speed){

    }
    private void finalClawMovement(){

    }

}
