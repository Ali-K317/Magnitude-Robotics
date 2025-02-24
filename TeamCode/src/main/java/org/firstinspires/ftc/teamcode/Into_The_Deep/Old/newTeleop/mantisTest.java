package org.firstinspires.ftc.teamcode.Into_The_Deep.Old.newTeleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Into_The_Deep.hardwareIntoTheDeep;

//@TeleOp(name = "Mantis Test", group = "Teleop")
public class mantisTest extends LinearOpMode {
    hardwareIntoTheDeep hardwareIntoTheDeep = new hardwareIntoTheDeep();
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while(opModeIsActive()){
            mantis();
//            while(hardware.mantis.isBusy()){
//                telemetry.addData("Posiiton", hardware.mantis.getCurrentPosition());
//                telemetry.update();
//            }
        }
    }

    private void initialize(){
        hardwareIntoTheDeep.mantis = hardwareMap.get(DcMotor.class, "mantis");
        hardwareIntoTheDeep.mantis.setDirection(DcMotor.Direction.REVERSE);
        hardwareIntoTheDeep.mantis.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //hardware.mantis.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    private void mantis(){
        double driveSpeed = gamepad1.right_stick_y;
        //boolean ballCrusher = gamepad1.right_b
        if(gamepad1.right_stick_y > 0) {
            hardwareIntoTheDeep.mantis.setPower(driveSpeed);

        }else if(gamepad1.right_stick_y < 0){
            hardwareIntoTheDeep.mantis.setPower(0.2 * driveSpeed);
        }else{
            hardwareIntoTheDeep.mantis.setPower(0.1);
        }
    }
}
