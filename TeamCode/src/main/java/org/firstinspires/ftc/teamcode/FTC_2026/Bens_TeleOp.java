package org.firstinspires.ftc.teamcode.FTC_2026;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Random;

@TeleOp(name = "Ben's TeleOp???")
public class Bens_TeleOp extends LinearOpMode {

    private DcMotor claw1 = null;
    private DcMotor claw2 = null;

    private void Double claw1Power = claw1.setPower(0.5);
    private void Double claw2Power = claw2.setPower(0.5);

    @Override
    public void runOpMode() {

        claw1 = hardwareMap.get(DcMotor.class, "claw1");
        claw2 = hardwareMap.get(DcMotor.class, "claw2");

        claw1.setDirection(DcMotor.Direction.FORWARD);
        claw2.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {

            telemetry.addData("claw1 Position", claw1.getCurrentPosition());
            telemetry.addData("claw2 Position", claw2.getCurrentPosition());
            telemetry.update();

            gamepad1.a ? claw1Power && claw2Power : claw1Power && claw2Power;

        }
    }

}
