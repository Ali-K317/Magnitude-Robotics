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