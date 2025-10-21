<<<<<<< HEAD
//package org.firstinspires.ftc.teamcode.FTC_2026;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@TeleOp(name = "TeleOp 2026", group = "2026")
//public class teleOp extends LinearOpMode {
//    private final List<String> driveMotorNames = Arrays.asList("frontLeft", "frontRight", "backLeft", "backRight");
//    private final List<String> armMotorNames = new ArrayList<>();
//    private final List<String> clawByPosMotorNames = new ArrayList<>();
//    private final List<String> clawBySpeedMotorNames = new ArrayList<>();
//
//    private final List<DcMotorEx> driveMotors = new ArrayList<>();
//    private final List<DcMotorEx> armMotors = new ArrayList<>();
//    private final List<Servo> clawByPosMotors = new ArrayList<>();
//    private final List<CRServo> clawBySpeedMotors = new ArrayList<>("");
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        initializeHardware();
//        setHardwareDirections();
//        setBrakes();
//        while (opModeInInit()){
//            telemetry.addLine("Code Initialized");
//            telemetry.addLine("Start When Ready");
//            telemetry.update();
//        }
//        waitForStart();
//        while(opModeIsActive()){
//            telemetry();
//            finalMovement();
//        }
//    }
//    //INITIALIZATION
//    private void initializeWheels(){
//            for (String driveMotorName : driveMotorNames) {
//                try {
//                    DcMotorEx driveMotor = hardwareMap.get(DcMotorEx.class, driveMotorName);
//                    driveMotors.add(driveMotor);
//                } catch (Exception e) {
//                    telemetry.addData("ERROR, ", driveMotorName, "NOT FOUND");
//                }
//        }
//    }
//    private void initializeArms(){
//            for (String armMotorName : armMotorNames) {
//                try {
//                    DcMotorEx armMotor = hardwareMap.get(DcMotorEx.class, armMotorName);
//                    armMotors.add(armMotor);
//                } catch (Exception e) {
//                    telemetry.addData("ERROR, ", armMotorName, "NOT FOUND");
//                }
//            }
//    }
//    private void initializeClaws(){
//
//            for (String clawByPosMotorName : clawByPosMotorNames) {
//                try {
//                    Servo clawByPosMotor = hardwareMap.get(Servo.class, clawByPosMotorName);
//                    clawByPosMotors.add(clawByPosMotor);
//                } catch (Exception e) {
//                    telemetry.addData("ERROR, ", clawByPosMotorName, "NOT FOUND");
//                }
//        }
//            for (String clawBySpeedMotorName : clawBySpeedMotorNames) {
//                try {
//                    CRServo clawBySpeedMotor = hardwareMap.get(CRServo.class, clawBySpeedMotorName);
//                    clawBySpeedMotors.add(clawBySpeedMotor);
//                } catch (Exception e) {
//                    telemetry.addData("ERROR, ", clawBySpeedMotorName, "NOT FOUND");
//                }
//        }
//    }
//    private  void initializeSensors(){
//    }
//    private void initializeHardware(){
//        initializeWheels();
//        initializeArms();
//        initializeClaws();
//        initializeSensors();
//    }
//
//    //DIRECTION
//    private void setDirectionWheels(){
//            driveMotors.get(0).setDirection(DcMotorEx.Direction.FORWARD);
//            driveMotors.get(1).setDirection(DcMotorEx.Direction.REVERSE);
//            driveMotors.get(2).setDirection(DcMotorEx.Direction.REVERSE);
//            driveMotors.get(3).setDirection(DcMotorEx.Direction.FORWARD);
//    }
//    private void setDirectionArms() {
//
//    }
//    private void setDirectionClaws(){
//
//    }
//    private void setHardwareDirections(){
//        setDirectionWheels();
//        setDirectionArms();
//        setDirectionClaws();
//    }
//
//    //BRAKES
//    private void setBrakesWheels(){
//            for (DcMotorEx wheelMotor : driveMotors) {
//                wheelMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//            }
//    }
//    private void setBrakesArms(){
//            for (DcMotorEx armMotor : armMotors) {
//                armMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//            }
//    }
//    private void setBrakes(){
//        setBrakesWheels();
//        setBrakesArms();
//    }
//
//    //TELEMETRY
//    private void telemetry(){
//        for(int i = 0; i < driveMotors.size(); i++){
//            telemetry.addData(driveMotorNames.get(i), driveMotors.get(i).getPower());
//        }
//        telemetry.update();
//    }
//
//    //MOVEMENT
//    //Wheels
//    private void moveWheels(double vertical, double strafe, double turn){
//        driveMotors.get(0).setPower(-vertical-strafe-turn);
//        driveMotors.get(1).setPower(-vertical+strafe+turn);
//        driveMotors.get(2).setPower(-vertical+strafe-turn);
//        driveMotors.get(3).setPower(-vertical-strafe+turn);
//    }
//    private void moveDiagonal(double speed){
//        boolean isMovingVertical;
//        double diagonalThreshold = 0.3;
//
//        isMovingVertical = (Math.abs(gamepad1.right_stick_y) > diagonalThreshold) && (Math.abs(gamepad1.right_stick_x) > diagonalThreshold);
//        if(isMovingVertical) {
//            if (gamepad1.right_stick_y > diagonalThreshold && gamepad1.right_stick_x > diagonalThreshold) {
//                driveMotors.get(0).setPower(speed);
//                driveMotors.get(3).setPower(speed);
//            } else if (gamepad1.right_stick_y > diagonalThreshold && gamepad1.right_stick_x < -diagonalThreshold) {
//                driveMotors.get(1).setPower(speed);
//                driveMotors.get(2).setPower(speed);
//            } else if (gamepad1.right_stick_y < -diagonalThreshold && gamepad1.right_stick_x > diagonalThreshold) {
//                driveMotors.get(1).setPower(-speed);
//                driveMotors.get(2).setPower(-speed);
//            } else if (gamepad1.right_stick_y < -diagonalThreshold && gamepad1.right_stick_x < -diagonalThreshold) {
//                driveMotors.get(0).setPower(-speed);
//                driveMotors.get(3).setPower(-speed);
//            }
//        }
//    }
//    private void finalWheelMovement() {
//        //Threshold for input
//        double threshold = 0.2;
//
//        double vertical = Math.abs(gamepad1.left_stick_y) > threshold ? gamepad1.left_stick_y: 0;
//        double turn = Math.abs(gamepad1.right_stick_x) > threshold ? gamepad1.right_stick_x: 0;
//        double strafe = Math.abs(gamepad1.left_stick_x) > threshold ? gamepad1.left_stick_x: 0;
//        moveWheels(vertical, strafe, turn);
//        moveDiagonal(0.5);
//    }
//    //Arms
//    private void moveArms(String state, double speed){
//
//    }
//
//    private void finalArmMovement(){
//
//    }
//
//    //Claws
//    private void moveClaws(String state, double speed){
//        switch(state){
//
//        }
//    }
//    private void finalClawMovement(){
//
//    }
//
//    private void finalMovement(){
//        finalWheelMovement();
//        finalArmMovement();
//        finalClawMovement();
//    }
//}
=======
package org.firstinspires.ftc.teamcode.FTC_2026;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class teleOp extends LinearOpMode{
    hardware hardware = new hardware();
    private final ElapsedTime time = new ElapsedTime();
    private final double threshold = 0.2;
    boolean lastPressed = false;
    boolean brakeOn = false;

    //Initialization
    public void runOpMode(){
        initWheels();
        initClaws();
        while(opModeInInit()){
            time.reset();
        }
        waitForStart();
        while(opModeIsActive()){
            driveControls();
        }
    }
    private void initClaws(){
        try {
            hardware.frontRightClaw = hardwareMap.get(CRServo.class, "frontRightClaw");
            hardware.frontLeftClaw = hardwareMap.get(CRServo.class, "frontLeftClaw");
            hardware.backRightClaw = hardwareMap.get(CRServo.class, "backRightClaw");
            hardware.backLeftClaw = hardwareMap.get(CRServo.class, "backLeftClaw");
        }catch(Exception e){
            telemetry.addData("Error occurred initializing the claw:", e);
            telemetry.update();
        }
    }
    private void initWheels(){
        try {
            hardware.frontRightWheel = hardwareMap.get(DcMotor.class, "frontRightWheel");
            hardware.frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLEftWheel");
            hardware.backRightWheel = hardwareMap.get(DcMotor.class, "backRightWheel");
            hardware.backLeftWheel = hardwareMap.get(DcMotor.class, "backLeftWheel");
        }catch(Exception e){
            telemetry.addData("Error occurred initializing the wheel:", e);
            telemetry.update();
        }
    }

    private void driveCommand(double vertical, double horizontal, double turn){
        hardware.frontRightWheel.setPower(vertical+horizontal+turn);
        hardware.frontLeftWheel.setPower(vertical+horizontal+turn);
        hardware.backRightWheel.setPower(vertical+horizontal+turn);
        hardware.backLeftWheel.setPower(vertical+horizontal+turn);
    }
    private void driveControls(){
        double verticalControls = Math.abs(gamepad1.right_stick_y) > threshold ? gamepad1.right_stick_y : 0;
        double horizontalControls = Math.abs(gamepad1.right_stick_x) > threshold ? gamepad1.right_stick_y: 0;
        double turnControls = Math.abs(gamepad1.left_stick_x) > threshold ? gamepad1.left_stick_x: 0;
        boolean brakeControl1 = gamepad1.left_stick_button;
        boolean brakeControl2 = gamepad1.right_stick_button;

        if(brakeControl1 && brakeControl2){
            lastPressed = !lastPressed;
        }
        if(lastPressed){
            driveCommand(0,0,0);
        }else{
            driveCommand(verticalControls, horizontalControls, turnControls);
        }
        telemetry.addData("The brakes have been pressed: ", lastPressed);
        telemetry.update();
    }

    private void clawControl(){
        if(gamepad1.a){
            hardware.frontRightClaw.setPower(1);
        }else if(gamepad1.b){
            hardware.frontRightClaw.setPower(-1);
        }else{
            hardware.frontRightClaw.setPower(0);
        }

        if(gamepad1.x){
            hardware.frontLeftClaw.setPower(1);
        }else if(gamepad1.y){
            hardware.frontLeftClaw.setPower(-1);
        }else{
            hardware.frontLeftClaw.setPower(0);
        }

        if(gamepad1.dpad_up){
            hardware.backRightClaw.setPower(1);
        }else if(gamepad1.dpad_down){
            hardware.backRightClaw.setPower(-1);
        }else{
            hardware.backRightClaw.setPower(0);
        }

        if(gamepad1.dpad_left){
            hardware.backLeftClaw.setPower(1);
        }else if(gamepad1.dpad_right){
            hardware.backLeftClaw.setPower(-1);
        }else{
            hardware.backLeftClaw.setPower(0);
        }
    }
}
>>>>>>> 1191f028b6d9c4a589b7ac5fcc4e40a8665bdbc6
