package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//FrontLeft, FrontRight, BackRight, BackLeft
  @TeleOp(name="aControllerButtonTesting", group="aaa")
    public class JoyStickTesting extends OpMode {
      
    DcMotor FrontLeft;
    DcMotor BackRight;
    DcMotor FrontRight;
    DcMotor BackLeft;
    
    @Override
    public void init() {
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");
    }
    
    @Override
    public void loop() {
      if (gamepad1.y){
        FrontLeft.setPower(-1);
        BackLeft.setPower(1);
        FrontRight.setPower(-1);
        BackRight.setPower(1);
      }
      else if (!gamepad1.y)
       FrontLeft.setPower(0.0);
        BackLeft.setPower(0.0);
        FrontRight.setPower(0.0);
        BackRight.setPower(0.0);
    
    
      if (gamepad1.a){
        FrontLeft.setPower(1);
        BackLeft.setPower(-1);
        FrontRight.setPower(1);
        BackRight.setPower(-1);
      }
      else if (!gamepad1.a){
       FrontLeft.setPower(0.0);
        BackLeft.setPower(0.0);
        FrontRight.setPower(0.0);
        BackRight.setPower(0.0);
        
        if (gamepad1.x){
         FrontLeft.setPower(1);
        BackLeft.setPower(1);
        FrontRight.setPower(1);
        BackRight.setPower(1);
        }
        else if (!gamepad1.x){
        FrontLeft.setPower(0.0);
        BackLeft.setPower(0.0);
        FrontRight.setPower(0.0);
        BackRight.setPower(0.0);
        
        
        if (gamepad1.b){
         FrontLeft.setPower(-1);
        BackLeft.setPower(-1);
        FrontRight.setPower(-1);
        BackRight.setPower(-1);
        }
        else if (!gamepad1.b){
        FrontLeft.setPower(0.0);
        BackLeft.setPower(0.0);
        FrontRight.setPower(0.0);
        BackRight.setPower(0.0);
    }
    } 
    }
    }
}