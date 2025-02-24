package org.firstinspires.ftc.teamcode.Into_The_Deep.Old.newAuto;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Into_The_Deep.hardwareIntoTheDeep;

//@Autonomous(name = "Wheel test", group = "Test")
public class wheelTest extends LinearOpMode {
    hardwareIntoTheDeep hardwareIntoTheDeep = new hardwareIntoTheDeep();

    int tickPer10cm = 100;
    int tickPerCm = (int) tickPer10cm/10;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        setDirection();
        resetEncoders();
        waitForStart();
        while (opModeIsActive()) {
            forward(tickPer10cm, 0.2);
            break;
        }
    }
    private void initialize(){
        hardwareIntoTheDeep.frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        hardwareIntoTheDeep.frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        hardwareIntoTheDeep.backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        hardwareIntoTheDeep.backRight = hardwareMap.get(DcMotor.class, "backRight");
    }
    private void setDirection(){
        hardwareIntoTheDeep.frontLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.frontRight.setDirection(DcMotor.Direction.FORWARD);
        hardwareIntoTheDeep.backLeft.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.backRight.setDirection(DcMotor.Direction.FORWARD);
    }
    private void forward(int pos, double speed){
        setPos(pos);
        runToPos();
        while(hardwareIntoTheDeep.frontLeft.getCurrentPosition() < pos){
            setSpeed(speed);
        }
        speed = 0.0;
        setSpeed(speed);
        resetEncoders();
        sleep(250);
    }
    private void setSpeed(double speed){
        hardwareIntoTheDeep.frontLeft.setPower(speed);
        hardwareIntoTheDeep.frontRight.setPower(speed);
        hardwareIntoTheDeep.backLeft.setPower(speed);
        hardwareIntoTheDeep.backRight.setPower(speed);
    }
    private void setPos(int pos){
        hardwareIntoTheDeep.frontLeft.setTargetPosition(pos);
        hardwareIntoTheDeep.frontRight.setTargetPosition(pos);
        hardwareIntoTheDeep.backLeft.setTargetPosition(pos);
        hardwareIntoTheDeep.backRight.setTargetPosition(pos);
    }
    private void resetEncoders(){
        hardwareIntoTheDeep.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareIntoTheDeep.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareIntoTheDeep.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareIntoTheDeep.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    private void runToPos(){
        hardwareIntoTheDeep.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwareIntoTheDeep.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwareIntoTheDeep.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwareIntoTheDeep.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
