package org.firstinspires.ftc.teamcode.FTC_2026;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "servoTest")
public class RDLTeleop extends LinearOpMode{
    public CRServo frontRight = null;
    public CRServo frontLeft = null;
    public CRServo backRight = null;
    public CRServo backLeft = null;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException{
        while(opModeInInit()){
            initialize();
            setDirection();
            runtime.reset();
        }
        while(opModeIsActive()) {
            try {
                while (runtime.seconds() < 1.0) {
                    frontRight.setPower(1.0);
                    frontLeft.setPower(1.0);
                    backRight.setPower(1.0);
                    backLeft.setPower(1.0);
                }
                runtime.reset();
                while (runtime.seconds() < 1.0) {
                    frontRight.setPower(-1.0);
                    frontLeft.setPower(-1.0);
                    backRight.setPower(-1.0);
                    backLeft.setPower(-1.0);
                }
            } catch (Exception e){
                telemetry.addData("Error", e);
                telemetry.update();
            }
        }

    }
    private void initialize(){
        frontRight = hardwareMap.get(CRServo.class, "frontRight");
        frontLeft = hardwareMap.get(CRServo.class, "frontLeft");
        backRight = hardwareMap.get(CRServo.class, "backRight");
        backLeft = hardwareMap.get(CRServo.class, "backLeft");

    }
    private void setDirection(){
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
