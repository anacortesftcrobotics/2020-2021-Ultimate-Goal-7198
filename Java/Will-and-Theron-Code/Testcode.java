package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//FrontLeft, FrontRight, BackRight, BackLeft
@TeleOp(name="aRobotMovementCode", group="aaa")
public class Testcode extends OpMode {

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
        FrontLeft.setPower(1);
        BackLeft.setPower(1);
        FrontRight.setPower(1);
        BackRight.setPower(1);
    }
}
 