package org.firstinspires.ftc.teamcode.Lessons2025;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Functions", group = "Learning")
public class functions extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while(opModeIsActive()){
            /*
            These all contain the code seen bellow.
            Imagine if instead of being empty they contained motor controls or repeating code.
            This saves space, allocating memory and making the code more readable
             */
            /*
            For the builders, this means i can make little snippets of code that runs a mini
            auto with an input.
             */
            /*
            Be careful with how much you ask though, too much can lag the code.
             */
            privateFunction();
            publicFunction();
            privateBoolean();
        }
    }
    /*
    Right here is where all functions will be called.
    There are two main types, there exists a third but no one uses it.
     */

    /*
    Functions are snippets of code that can be called anywhere in the file.
    This helps shorten repetitive code, organize existing code, or return values.
     */

    /*
    Private functions can only be called in the file they are made in
     */
    private void privateFunction(){

    }

    /*
    Public functions, however, can be imported as libraries and used in other code
     */
    public void publicFunction(){

    }

    /*
    Notice how they all have void in them?
    This is what value it returns, since most functions return nothing its usually void
     */
    private boolean privateBoolean(){
        boolean AliIsTheBest = true;
        return AliIsTheBest;
    }

}
