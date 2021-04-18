package BenPrograms;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
/*
@Autonomous

    public class BenAutonomousProgram extends LinearOpMode {
    
    BenRobot robot = new BenRobot();
    
    public void gotosqa() {
    robot.leftFront.setTargetPosition(5000);
    robot.rightFront.setTargetPosition(5000);
    robot.leftBack.setTargetPosition(5000);
    robot.rightBack.setTargetPosition(5000);
    
    robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
    robot.leftFront.setPower(0.5);
                    robot.rightFront.setPower(0.5);
                    robot.leftBack.setPower(0.5);
                    robot.rightBack.setPower(0.5);
        
                    sleep(5000);
        
                    robot.leftFront.setPower(0);
                    robot.rightFront.setPower(0);
                    robot.leftBack.setPower(0);
                    robot.rightBack.setPower(0);
        
            
                    robot.grab.setPosition(1);
                    sleep(500);
        
                    robot.move.setPower(-0.7);
                    sleep(100);
                    robot.move.setPower(0);
                    
                        robot.leftFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.leftBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightBack.setMode(DcMotor.RunMode.RESET_ENCODERS);    
            
        robot.leftFront.setTargetPosition(-500);
        robot.rightFront.setTargetPosition(-500);
        robot.leftBack.setTargetPosition(-500);
        robot.rightBack.setTargetPosition(-500);
    
        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        robot.leftFront.setPower(0.7);
        robot.rightFront.setPower(0.7);
        robot.leftBack.setPower(0.7);
        robot.rightBack.setPower(0.7);
        
        sleep(400);
        
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);
        
    }
    
    public void gotosqb() {
    robot.leftFront.setTargetPosition(6000);
    robot.rightFront.setTargetPosition(6000);
    robot.leftBack.setTargetPosition(6000);
    robot.rightBack.setTargetPosition(6000);
    
    robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
    robot.leftFront.setPower(0.5);
                robot.rightFront.setPower(0.5);
                robot.leftBack.setPower(0.5);
                robot.rightBack.setPower(0.5);
        
                sleep(5000);
        
                robot.leftFront.setPower(0);
                robot.rightFront.setPower(0);
                robot.leftBack.setPower(0);
                robot.rightBack.setPower(0);
        
                robot.move.setTargetPosition(1500);
                robot.move.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.move.setPower(0.7);
                sleep(2000);
                robot.move.setPower(0);
        
                robot.grab.setPosition(1);
                sleep(500);
                
                robot.leftFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.leftBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightBack.setMode(DcMotor.RunMode.RESET_ENCODERS);    
            
        robot.leftFront.setTargetPosition(-1500);
        robot.rightFront.setTargetPosition(-1500);
        robot.leftBack.setTargetPosition(-1500);
        robot.rightBack.setTargetPosition(-1500);
    
        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        robot.leftFront.setPower(0.7);
        robot.rightFront.setPower(0.7);
        robot.leftBack.setPower(0.7);
        robot.rightBack.setPower(0.7);
        
        sleep(1000);
        
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);
    }
    
    public void gotosqc() {
    robot.leftFront.setTargetPosition(7500);
    robot.rightFront.setTargetPosition(7500);
    robot.leftBack.setTargetPosition(7500);
    robot.rightBack.setTargetPosition(7500);
    
    robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    robot.leftFront.setPower(0.5);
            robot.rightFront.setPower(0.5);
            robot.leftBack.setPower(0.5);
            robot.rightBack.setPower(0.5);
        
            sleep(5500);
        
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);
        
            robot.grab.setPosition(1);
            sleep(500);
            
            robot.grab.setPosition(1);
                    sleep(500);
        
            robot.move.setPower(-0.7);
            sleep(300);
            robot.move.setPower(0);
            
        robot.leftFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.leftBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightBack.setMode(DcMotor.RunMode.RESET_ENCODERS);    
            
        robot.leftFront.setTargetPosition(-3000);
        robot.rightFront.setTargetPosition(-3000);
        robot.leftBack.setTargetPosition(-3000);
        robot.rightBack.setTargetPosition(-3000);
    
        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        robot.leftFront.setPower(0.7);
        robot.rightFront.setPower(0.7);
        robot.leftBack.setPower(0.7);
        robot.rightBack.setPower(0.7);
        
        sleep(2000);
        
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);
        
        
    }
    
       public void gotorings() {
           
           robot.leftFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightFront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.leftBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
        robot.rightBack.setMode(DcMotor.RunMode.RESET_ENCODERS);
           
        robot.leftFront.setTargetPosition(2500);
        robot.rightFront.setTargetPosition(2500);
        robot.leftBack.setTargetPosition(2500);
        robot.rightBack.setTargetPosition(2500);
    
        robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);    
    
        robot.leftFront.setPower(0.7);
        robot.rightFront.setPower(0.7);
        robot.leftBack.setPower(0.7);
        robot.rightBack.setPower(0.7);
        
        sleep(3000);
        
        robot.leftFront.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightBack.setPower(0);
        
        
        
        
    }
    @Override
    public void runOpMode() {
        // Put initialization code here
        Boolean fourring = false;
        Boolean onering = false;
        Boolean noring = false;
        double toucharmposition = 1.0;
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        robot.grab.setPosition(0);
        robot.toucharm.setPosition(1);
        sleep(400);
        
        
        
        gotorings();
        
        while (!robot.touch.isPressed() && (toucharmposition > 0))  {
            robot.toucharm.setPosition(toucharmposition);
            toucharmposition -= 0.02;
            sleep(100);
        }
        
        robot.toucharm.setPosition(1);
        sleep(300);    
        
        if (toucharmposition > 0.3) {
            fourring = true;
        } else if (toucharmposition > 0.02) {
            onering = true;
        } else {
            noring = true;
        }
            
        
        
        
        
        if (fourring == true) {
            gotosqc();
        } else if (onering == true) {
            gotosqb();
        } else {
            gotosqa();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   
        
        //Sleep is here to stop the program
        //sleep(25000);
        
        ***
        robot.color.enableLed(true);
        
        telemetry.addData("Red", robot.color.red());
        telemetry.addData("Green", robot.color.green());
        telemetry.addData("Blue", robot.color.blue());
        telemetry.addData("alpha", robot.color.alpha());
        telemetry.addData("argb", robot.color.argb());
        if (robot.color.alpha() > 2500) {
            telemetry.addData("Found White", 0);
        }
        else if ( (robot.color.alpha() < 8) && (robot.color.argb() < 10) && (robot.color.red() > 5)) {
            telemetry.addData("Found red", 0);
        }
        
        telemetry.update();
        //This below is for delivery. made 3/29/2021. tool for wobble goal is still 3 beams.
        // robot.motor5.setTargetPosition(0);
        // robot.motor5.setPower(0.7);
       // sleep(1400);
        //robot.motor5.setPower(0);
        
       // robot.servo1.setPosition(0.5);
        //sleep(5000);
        
        //robot.motor5.setTargetPosition(0);
        //robot.motor5.setPower(-1);
        //sleep(1400);
        //robot.motor5.setPower(0);
        
        ***
    telemetry.addData("onering = %b", onering );
    telemetry.addData("noring = %b", noring );
    telemetry.addData("fourring = %b", fourring );    
    telemetry.update();
    
    sleep(20000);
    
    
    
    }
   
}
*/ 