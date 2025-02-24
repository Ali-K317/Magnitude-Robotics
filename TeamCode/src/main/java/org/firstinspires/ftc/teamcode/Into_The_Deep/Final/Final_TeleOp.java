package org.firstinspires.ftc.teamcode.Into_The_Deep.Final;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Into_The_Deep.hardwareIntoTheDeep;
import org.firstinspires.ftc.teamcode.Into_The_Deep.mainEnum;
import org.firstinspires.ftc.teamcode.Into_The_Deep.puns;

import java.util.Random;

//@TeleOp(name = "TeleOp", group = "Final")
public class Final_TeleOp extends LinearOpMode{
    hardwareIntoTheDeep hardwareIntoTheDeep = new hardwareIntoTheDeep();
    puns puns = new puns();

    private final Random random = new Random();
    private final String randomPun = puns.puns[random.nextInt(puns.puns.length)];

    private ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() {
        // Initialize hardware
        initializeArms();
        initializeWheels();

        // Set directions and brakes
        setDirectionArms();
        setDirectionWheels();
        setBrakesArms();
        setBrakesWheels();

        ElapsedTime timer = new ElapsedTime();
        //Telemetry while initializing
        while(opModeInInit()) {
            timer.reset();
            telemetry.addLine("Robot started");
            telemetry.addLine(randomPun);
            telemetry.addLine("Press start when ready");
            telemetry.update();
        }

        waitForStart();
        while (opModeIsActive()) {
            telemetry();
            finalMovement(); // Wheel control
            finalArm(); // Arm control
            finalGrabber(); // Grabber control
        }
    }

    // Arm Initialization
    private void initializeArms() {
        try {
            hardwareIntoTheDeep.mantis = hardwareMap.get(DcMotor.class, "mantis");
            hardwareIntoTheDeep.lift = hardwareMap.get(DcMotor.class, "lift");
            hardwareIntoTheDeep.hopper = hardwareMap.get(DcMotor.class, "hopper");
            hardwareIntoTheDeep.bar = hardwareMap.get(DcMotor.class, "bar");

            hardwareIntoTheDeep.door = hardwareMap.get(Servo.class, "door");
            hardwareIntoTheDeep.topGrabber = hardwareMap.get(CRServo.class, "topGrabber");
            hardwareIntoTheDeep.bottomGrabber = hardwareMap.get(CRServo.class, "bottomGrabber");

            telemetry.addLine("Arm initialization complete");
        } catch (Exception e) {
            telemetry.addLine("Arm initialization error: " + e.getMessage());
        }
        telemetry.update();
    }

    // Wheel Initialization
    private void initializeWheels() {
        try {
            hardwareIntoTheDeep.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            hardwareIntoTheDeep.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            hardwareIntoTheDeep.backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            hardwareIntoTheDeep.backRight = hardwareMap.get(DcMotor.class, "backRight");

            telemetry.addLine("Wheel initialization complete");
        } catch (Exception e) {
            telemetry.addLine("Wheel initialization error: " + e.getMessage());
        }
        telemetry.update();
    }

    // Arm Direction
    private void setDirectionArms() {
        hardwareIntoTheDeep.lift.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.mantis.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.hopper.setDirection(DcMotor.Direction.FORWARD);
        hardwareIntoTheDeep.bar.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    // Wheel Direction
    private void setDirectionWheels() {
        hardwareIntoTheDeep.frontLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.frontRight.setDirection(DcMotor.Direction.FORWARD);
        hardwareIntoTheDeep.backLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.backRight.setDirection(DcMotor.Direction.FORWARD);
    }

    // Arm Brakes
    private void setBrakesArms() {
        hardwareIntoTheDeep.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardwareIntoTheDeep.mantis.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardwareIntoTheDeep.hopper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Wheel Brakes
    private void setBrakesWheels() {
        hardwareIntoTheDeep.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardwareIntoTheDeep.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardwareIntoTheDeep.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardwareIntoTheDeep.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Telemetry
    private void telemetry() {
        if(hardwareIntoTheDeep.lift.isBusy() || hardwareIntoTheDeep.mantis.isBusy() || hardwareIntoTheDeep.hopper.isBusy()){
            telemetry.addData("Lift position", hardwareIntoTheDeep.lift.getCurrentPosition());
            telemetry.addData("Mantis position", hardwareIntoTheDeep.mantis.getCurrentPosition());
            telemetry.addData("Hopper position", hardwareIntoTheDeep.hopper.getCurrentPosition());
            telemetry.update();
        }
        if(Math.abs(hardwareIntoTheDeep.bottomGrabber.getPower()) > 0 || Math.abs(hardwareIntoTheDeep.topGrabber.getPower()) > 0  ||Math.abs(hardwareIntoTheDeep.door.getPosition()) > 0 ){
            telemetry.addData("Bottom grabber power", hardwareIntoTheDeep.bottomGrabber.getPower());
            telemetry.addData("Top grabber power", hardwareIntoTheDeep.topGrabber.getPower());
            telemetry.addData("Door position", hardwareIntoTheDeep.door.getPosition());
            telemetry.update();
        }
    }

    // Movement
    private void movement(double vertical, double strafe, double turn) {
            hardwareIntoTheDeep.frontLeft.setPower(-vertical - strafe - turn);
            hardwareIntoTheDeep.frontRight.setPower(-vertical + strafe + turn);
            hardwareIntoTheDeep.backLeft.setPower(-vertical + strafe - turn);
            hardwareIntoTheDeep.backRight.setPower(-vertical - strafe + turn);
        }


    private boolean brakeOn = false;
    private double reverseStartTime = 0;
    private boolean reversing = false;
    // Final Movement
    private void finalMovement() {
        double reduction;
        double turnReduction;

        if (gamepad2.left_stick_button || gamepad2.right_stick_button) {
            brakeOn = !brakeOn;
            reverseStartTime = timer.seconds();
            reversing = true;
        }

        if (reversing && (timer.seconds() - reverseStartTime) <= 0.5) {
            hardwareIntoTheDeep.frontLeft.setPower(0);
            hardwareIntoTheDeep.frontRight.setPower(0);
            hardwareIntoTheDeep.backLeft.setPower(0);
            hardwareIntoTheDeep.backRight.setPower(0);
            return;
        } else {
            reversing = false;
        }

        if (brakeOn) {
            hardwareIntoTheDeep.frontLeft.setPower(0);
            hardwareIntoTheDeep.frontRight.setPower(0);
            hardwareIntoTheDeep.backLeft.setPower(0);
            hardwareIntoTheDeep.backRight.setPower(0);
            return;
        }

        reduction = 0.8;
        turnReduction = 0.55;

        double vertical = reduction * gamepad1.left_stick_y;
        double turn = -reduction * gamepad1.right_stick_x;
        double strafe = -turnReduction * gamepad1.left_stick_x;
        movement(vertical, strafe, turn);
    }


    private void arm(mainEnum state, double speed) {
        switch (state) {
            case LIFT:
                hardwareIntoTheDeep.lift.setPower(speed); // Set lift motor power
                break;
            case MANTIS:
                hardwareIntoTheDeep.mantis.setPower(speed); // Set mantis motor power
                break;
            case HOPPER:
                hardwareIntoTheDeep.hopper.setPower(speed); // Set hopper motor power
                break;
            case BAR:
                hardwareIntoTheDeep.bar.setPower(speed); // Set bar motor power
        }
    }

    // Final Arm Control
    private void finalArm() {
        //INITIALIZATION
        mainEnum state; // Initialize state
        double armSpeed = 0; // Initialize arm speed
        double threshold = 0.3; //Threshold for gamepad input

        //MANTIS
        double mantisUpReduction = 0.7;
        double mantisDownReduction = 0.6;
        double mantisUp = gamepad2.right_stick_y * mantisUpReduction;
        double mantisDown = gamepad2.right_stick_y * mantisDownReduction;
        double mantisBrake = 0;

        //HOPPER
        double hopperReduction = 0.65; // Initializes arm reduction speed, used for hopper
        double hopperHold = -0.1; //Used to keep hopper arm up

        double hopperUp = gamepad2.left_stick_y;
        double hopperDown = gamepad2.left_stick_y * hopperReduction;

        //LIFT
        double liftUp = 1; //lift up speed
        double liftDown = -1; //lift down speed
        double liftHold = 0; //Holds the lift position

        //BAR
        double barUp = 1; //bar up speed
        double barDown = -1; //bar down speed
        double barHold = 0; //Holds the bar position

        // Determine arm state and speed based on gamepad input
        //MANTIS
        if (Math.abs(gamepad2.right_stick_y) > threshold) {
            state = mainEnum.MANTIS;
            if (gamepad2.right_stick_y > threshold) {
                armSpeed = mantisUp;
            } else if (gamepad2.right_stick_y < -threshold) {
                armSpeed = mantisDown;
            }
        }else{
            state = mainEnum.MANTIS;
            armSpeed = mantisBrake;
        }
        arm(state, armSpeed); // Call the arm control


        //HOPPER
        if (Math.abs(gamepad2.left_stick_y) > threshold) {
            state = mainEnum.HOPPER;
            if (gamepad2.left_stick_y > threshold) {
                armSpeed = hopperUp;
            } else if (gamepad2.left_stick_y < -threshold) {
                armSpeed = hopperDown;
            }
        }else{
            state = mainEnum.HOPPER;
            armSpeed = hopperHold;
        }
        arm(state, armSpeed); // Call the arm control

        //LIFT
        if(gamepad2.right_bumper || gamepad2.left_bumper) {
            state = mainEnum.LIFT;
            if (gamepad2.right_bumper) {
                armSpeed = liftUp;
            } else if (gamepad2.left_bumper) {
                armSpeed = liftDown;
            }
        } else {
            state = mainEnum.LIFT;
            armSpeed = liftHold;
        }
        arm(state, armSpeed); // Call the arm control

        if(Math.abs(gamepad2.left_trigger) > threshold){
            state = mainEnum.BAR;
            armSpeed = barUp;
        }else if(Math.abs(gamepad2.right_trigger) > threshold){
            state = mainEnum.BAR;
            armSpeed = barDown;
        }else{
            state = mainEnum.BAR;
            armSpeed = barHold;
        }
        arm(state, armSpeed); // Call the arm control
    }

    // Final Grabber Control
    private void finalGrabber() {
        //GRABBER
        double bottomCollect = -1;
        double topCollect = 1;

        double bottomRelease = 1;
        double topRelease = -1;

        double grabberHold = 0;

        //DOOR
        double open = 0.25;       // Open door position
        double close = 0.6;// Close door position

        // Control gripper based on button presses
        //GRABBER
        if(gamepad2.a){
            hardwareIntoTheDeep.bottomGrabber.setPower(bottomCollect);
            hardwareIntoTheDeep.topGrabber.setPower(topCollect);
        }else if(gamepad2.b){
            hardwareIntoTheDeep.bottomGrabber.setPower(bottomRelease);
            hardwareIntoTheDeep.topGrabber.setPower(topRelease);
        }else{
            hardwareIntoTheDeep.bottomGrabber.setPower(grabberHold);
            hardwareIntoTheDeep.topGrabber.setPower(grabberHold);
        }

        //DOOR
        if(gamepad2.dpad_up){
            hardwareIntoTheDeep.door.setPosition(open);
        }else if (gamepad2.dpad_down){
            hardwareIntoTheDeep.door.setPosition(close);
        }
    }


}