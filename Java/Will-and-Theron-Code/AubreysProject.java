package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//FrontLeft, FrontRight, BackRight, BackLeft
@TeleOp(name="AubreysProject", group="aaa")
public class AubreysProject extends OpMode {
  
  DcMotor BackRight;

  @Override
  public void init() {
      BackRight = hardwareMap.get(DcMotor.class, "BackRight");
  }

  @Override
  public void loop() {
    if (gamepad1.y) {
      BackRight.setPower(1);
    } else if (!gamepad1.y) {
      BackRight.setPower(0.0);
    }
  }
}