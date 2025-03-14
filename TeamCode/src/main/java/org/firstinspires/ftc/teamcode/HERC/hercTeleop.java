package org.firstinspires.ftc.teamcode.HERC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TeleOp(name = "HERC TeleOP", group = "HERC")
public class hercTeleop extends LinearOpMode {
    private final List<String> driveMotorNames = Arrays.asList("frontLeft", "frontRight", "backLeft", "backRight");
    private final List<String> drillMotorNames = Arrays.asList("drillMotor1", "drillMotor2");
    private final List<String> vacuumMotorNames = Arrays.asList("vacuumMotor1", "vacuumMotor2");
    private final List<String> doorServoNames = Arrays.asList("doorServoFront", "doorServoBack", "doorServoLeft", "doorServoRight");

    private final List<DcMotorEx> driveMotors = new ArrayList<>();
    private final List<DcMotorEx> drillMotors = new ArrayList<>();
    private final List<DcMotorEx> vacuumMotors = new ArrayList<>();
    private final List<Servo> doorServos = new ArrayList<>();

    //DRILLS
    boolean drillOneOn = false;
    boolean firstDrillLastPressed = false;

    boolean drillTwoOn = false;
    boolean secondDrillLastPressed = false;

    //VACUUMS
    boolean vacuumOneOn = false;
    boolean firstVacuumLastPressed = false;

    boolean vacuumTwoOn = false;
    boolean secondVacuumLastPressed = false;

    //DOORS
    boolean doorOneOpen = false;
    boolean lastFrontDoorPressed = false;

    boolean doorTwoOpen = false;
    boolean lastBackDoorPressed = false;

    boolean doorThreeOpen = false;
    boolean lastLeftDoorPressed = false;

    boolean doorFourOpen = false;
    boolean lastRightDoorPressed = false;

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
            finalMovement();
        }
    }
    //INITIALIZATION
    private void initializeWheels(){
        for(String driveMotorName: driveMotorNames){
            try {
                DcMotorEx driveMotor = hardwareMap.get(DcMotorEx.class, driveMotorName);
                driveMotors.add(driveMotor);
            } catch(Exception e){
                telemetry.addData("ERROR, ", driveMotorName +"NOT FOUND");
                telemetry.update();
            }
        }
    }

    private void initializeDrills(){
        for(String drillMotorName: drillMotorNames){
            try {
                DcMotorEx drillMotor = hardwareMap.get(DcMotorEx.class, drillMotorName);
                drillMotors.add(drillMotor);
            } catch(Exception e){
                telemetry.addData("ERROR, ", drillMotorName+"NOT FOUND");
                telemetry.update();
            }
        }
    }

    private void initializeVacuums(){
        for(String vacuumMotorName: vacuumMotorNames){
            try {
                DcMotorEx vacuumMotor = hardwareMap.get(DcMotorEx.class, vacuumMotorName);
                vacuumMotors.add(vacuumMotor);
            } catch(Exception e){
                telemetry.addData("ERROR, ", vacuumMotorName +"NOT FOUND");
                telemetry.update();
            }
        }
    }


    private void initializeDoors(){
        for(String doorServo: doorServoNames){
            try {
                Servo door = hardwareMap.get(Servo.class, doorServo);
                doorServos.add(door);
            } catch(Exception e){
                telemetry.addData("ERROR, ", doorServoNames + "NOT FOUND");
                telemetry.update();
            }
        }
    }

    private void initializeHardware(){
        initializeWheels();
        initializeDrills();
        initializeVacuums();
        initializeDoors();
    }

    //DIRECTION
    private void setDirectionWheels(){
        driveMotors.get(0).setDirection(DcMotorEx.Direction.REVERSE);
        driveMotors.get(1).setDirection(DcMotorEx.Direction.FORWARD);
        driveMotors.get(2).setDirection(DcMotorEx.Direction.REVERSE);
        driveMotors.get(3).setDirection(DcMotorEx.Direction.FORWARD);
    }
    private void setDirectionDrills() {
        drillMotors.get(0).setDirection(DcMotorSimple.Direction.FORWARD);
        drillMotors.get(1).setDirection(DcMotorSimple.Direction.FORWARD);
    }
    private void setDirectionVacuums() {
        vacuumMotors.get(0).setDirection(DcMotorSimple.Direction.FORWARD);
        vacuumMotors.get(1).setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private void setHardwareDirections(){
        setDirectionWheels();
        setDirectionDrills();
        setDirectionVacuums();
    }

    //BRAKES
    private void setBrakesWheels(){
        for(DcMotorEx wheelMotor: driveMotors){
            wheelMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        }
    }
    private void setBrakesDrills(){
        for(DcMotorEx drillMotor: drillMotors){
            drillMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        }
    }

    private void setBrakesVacuums(){
        for(DcMotorEx vacuumMotor: vacuumMotors){
            vacuumMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        }
    }
    private void setBrakes(){
        setBrakesWheels();
        setBrakesDrills();
        setBrakesVacuums();
    }

    //TELEMETRY
    private void telemetry(){
        telemetry.addData("Dpad Up", gamepad1.dpad_up);
        telemetry.addData("Dpad Down", gamepad1.dpad_down);
        telemetry.addData("Dpad Left", gamepad1.dpad_left);
        telemetry.addData("Dpad Right", gamepad1.dpad_right);
        telemetry.addData("Button A", gamepad1.a);
        telemetry.addData("Button B", gamepad1.b);
        telemetry.addData("Button X", gamepad1.x);
        telemetry.addData("Button Y", gamepad1.y);
        telemetry.update();
    }

    //MOVEMENT
    //Wheels
    private void moveWheels(double vertical, double turn){
        driveMotors.get(0).setPower(-vertical-turn);
        driveMotors.get(1).setPower(-vertical+turn);
        driveMotors.get(2).setPower(-vertical-turn);
        driveMotors.get(3).setPower(-vertical+turn);
    }
    private void finalWheelMovement() {
        //Threshold for input
        double threshold = 0.1;

        double vertical = Math.abs(gamepad1.left_stick_y) > threshold ? gamepad1.left_stick_y: 0;
        double turn = Math.abs(gamepad1.right_stick_x) > threshold ? gamepad1.right_stick_x: 0;
        moveWheels(vertical,turn);
    }
    //Arms
    private void moveDrills(String state, double speed){
        switch(state){
            case "drill1":
                drillMotors.get(0).setPower(speed);
                break;
            case "drill2":
                drillMotors.get(1).setPower(speed);
                break;
        }
    }

    private void moveVacuums(String state, double speed){
        switch(state){
            case "vacuum1":
                vacuumMotors.get(0).setPower(speed);
                break;
            case "vacuum2":
                vacuumMotors.get(1).setPower(speed);
                break;
        }
    }

    private void finalApertureMovement(){
        boolean firstDrillButton = gamepad1.left_bumper;
        boolean secondDrillButton = gamepad1.right_bumper;
        boolean firstVacuumButton = gamepad1.x;
        boolean secondVacuumButton = gamepad1.y;

        double drillOn = 1;
        double drillOff = 0;

        double vacuumOn = 1;
        double vacuumOff = 0;


        if(firstDrillButton && !firstDrillLastPressed){
            drillOneOn = !drillOneOn;
        }
        firstDrillLastPressed = firstDrillButton;

        if(secondDrillButton && !secondDrillLastPressed){
            drillTwoOn = !drillTwoOn;
        }
        secondDrillLastPressed = secondDrillButton;

        if(firstVacuumButton && !firstVacuumLastPressed){
            vacuumOneOn = !vacuumOneOn;
        }
        firstVacuumLastPressed = firstVacuumButton;

        if(secondVacuumButton && !secondVacuumLastPressed){
            vacuumTwoOn = !vacuumTwoOn;
        }
        secondVacuumLastPressed = secondVacuumButton;

    moveDrills("drill1" , drillOneOn ? drillOn : drillOff);
    moveDrills("drill2" , drillTwoOn ? drillOn : drillOff);

    moveVacuums("vacuum1", vacuumOneOn ? vacuumOn : vacuumOff);
    moveVacuums("vacuum2", vacuumTwoOn ? vacuumOn : vacuumOff);


    }

    //Claws
    private void finalDoorMovement(){
        boolean frontDoorButton = gamepad1.dpad_up;
        boolean backDoorButton = gamepad1.dpad_down;
        boolean leftDoorButton = gamepad1.dpad_left;
        boolean rightDoorButton = gamepad1.dpad_right;

        double frontDoorOpen = 1;
        double frontDoorClose = 0;

        double backDoorOpen = 1;
        double backDoorClose = 0;

        double leftDoorOpen = 1;
        double leftDoorClose = 0;

        double rightDoorOpen = 1;
        double rightDoorClose = 0;

        if(frontDoorButton && !lastFrontDoorPressed){
            doorOneOpen = !doorOneOpen;
        }
        lastFrontDoorPressed = frontDoorButton;

        if(backDoorButton && !lastBackDoorPressed){
            doorTwoOpen = !doorTwoOpen;
        }
        lastBackDoorPressed = backDoorButton;

        if(leftDoorButton && !lastLeftDoorPressed){
            doorThreeOpen = !doorThreeOpen;
        }
        lastLeftDoorPressed = leftDoorButton;

        if(rightDoorButton && !lastRightDoorPressed){
            doorFourOpen = !doorFourOpen;
        }
            lastRightDoorPressed = rightDoorButton;

        doorServos.get(0).setPosition(doorOneOpen ? frontDoorOpen : frontDoorClose);
        doorServos.get(1).setPosition(doorTwoOpen ? backDoorOpen : backDoorClose);
        doorServos.get(2).setPosition(doorThreeOpen ? leftDoorOpen : leftDoorClose);
        doorServos.get(3).setPosition(doorFourOpen ? rightDoorOpen : rightDoorClose);
    }

    private void finalMovement(){
        finalWheelMovement();
        finalApertureMovement();
        finalDoorMovement();
    }
}
