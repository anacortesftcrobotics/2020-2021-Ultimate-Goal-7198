package OpModes.utilities;

import com.qualcomm.robotcore.hardware.Blinker;

import com.qualcomm.robotcore.hardware.Blinker;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.Locale;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumChassis {
    private Blinker control_Hub;
    private DcMotor leftback;
    private DcMotor leftfront;
    private DcMotor rightback;
    private DcMotor rightfront;
    private double MECANUM_CIRCUMFERENCE = 12.4;
    private double TICKS_PER_INCH = 62;
    public  double robotAngle = 0;
    
    // Define robot reference
    UGRobot robot;
    
    // local OpMode members.
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    Telemetry telemetry;
    
    // Constructor
    public MecanumChassis(UGRobot _robot) {
        // Set robot reference
        robot = _robot;
        init();
    }

    private void resetEncoders() {
        // Resets all motor encoders
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //leftback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        // Set all motors to use encoders
        leftfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Initialize standard Hardware interfaces
    public void init() { //Telemetry tIn, HardwareMap ahwMap) {
        // Save reference to Hardware map
        //hwMap = ahwMap;
        hwMap = robot.hardwareMap;
        // Create the object specific variables from the OpMode
        //telemetry = tIn;
        telemetry = robot.telemetry;

        // Define and Initialize Motors
        leftfront = hwMap.get(DcMotor.class, "leftfront");
        rightfront = hwMap.get(DcMotor.class, "rightfront");
        leftback = hwMap.get(DcMotor.class, "leftback");
        rightback = hwMap.get(DcMotor.class, "rightback");

        // Reverses the two left motors
        
        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftback.setDirection(DcMotor.Direction.REVERSE); 
        rightfront.setDirection(DcMotor.Direction.FORWARD);
        rightback.setDirection(DcMotor.Direction.FORWARD);
        
        // Set all motors to brake at power 0
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();
        
        // Set all motors to zero power
        leftfront.setPower(0);
        rightfront.setPower(0);
        leftback.setPower(0);
        rightback.setPower(0);
    }
    
    // Returns a string with all of the motor powers for telemetry
    public String toString() {
        return String.format(Locale.getDefault(), 
            "leftfront: %s, rightfront: %s\nleftback: %s, rightback:%s", 
            leftfront.getPower(), 
            rightfront.getPower(), 
            leftback.getPower(), 
            rightback.getPower());
    }
    
    // Sets the power on the motors directly based on a left and right value
    public void setSimplePower(double left, double right) {
        leftfront.setPower(left);
        rightfront.setPower(right);
        leftback.setPower(left);
        rightback.setPower(right);
    }
    
    // Takes a type of move and a value for that move, and drives the robot to meet that value
    public void controlMecanum(String type, int distance, double power) {
        int ticks = 0;
        resetEncoders();
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addLine(String.format("Type: %s, Distance: %d", type, distance));
        telemetry.addLine(String.format("leftfront Pos: %d", leftfront.getCurrentPosition()));
        telemetry.update();
        // Set all necessary motor values
        switch (type) {
            case "forward":
                ticks = (int) Math.floor(distance * TICKS_PER_INCH);
                leftfront.setTargetPosition(ticks);
                //frontRight.setTargetPosition(distance);
                //rearLeft.setTargetPosition(distance);
                //rearRight.setTargetPosition(distance);
                setSimplePower(power, power);
                break;
            case "right":
                ticks = (int) Math.floor(distance * TICKS_PER_INCH);
                leftfront.setTargetPosition(ticks);
                //frontRight.setTargetPosition(distance);
                //rearLeft.setTargetPosition(distance);
                //rearRight.setTargetPosition(-distance);
                leftfront.setPower(power);
                rightfront.setPower(-power);
                leftback.setPower(-power);
                rightback.setPower(power);
                break;
            case "left":
                ticks = (int) Math.floor(distance * TICKS_PER_INCH);
                leftfront.setTargetPosition(-ticks);
                //frontRight.setTargetPosition(distance);
                //rearLeft.setTargetPosition(distance);
                //rearRight.setTargetPosition(-distance);
                leftfront.setPower(-power);
                rightfront.setPower(power);
                leftback.setPower(power);
                rightback.setPower(-power);
                break;
            case "diagonalright":
                ticks = (int) Math.floor(distance * TICKS_PER_INCH);
                leftfront.setTargetPosition(ticks);
                leftfront.setPower(power);
                rightback.setPower(power);
        }

        // Add telemetry specific to the current movement
        while (leftfront.isBusy()) { //((leftfront.isBusy() || rightfront.isBusy() || leftback.isBusy() || rightback.isBusy()) ) { //&& !linearOpMode.isStopRequested()) {
            telemetry.addLine(String.format("Type: %s, Distance: %d, Ticks: %d", type, distance, ticks));
            telemetry.addLine(String.format("leftfront Pos: %d", leftfront.getCurrentPosition()));
            telemetry.update();
        }
        telemetry.addLine(String.format("Type: %s, Distance: %d, Ticks: %d", type, distance, ticks));
        telemetry.addLine(String.format("leftfront Pos: %d", leftfront.getCurrentPosition()));
        telemetry.update();
        setSimplePower(0, 0);
    }
    
    //----------------------------------------------------------------------------------------------
    // Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
    // @param degrees Degrees to turn, + is left - is right
    //----------------------------------------------------------------------------------------------

    /*
    public void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0) // Z axis postive degrees is counter clockwise
        {   // turn counter clockwise.
            leftPower = power;
            rightPower = -power;
        }
        else if (degrees > 0) // Z axis negative degrees is clockwise
        {   // turn clockwise.
            leftPower = -power;
            rightPower = power;
        }
        else return;

        robot.setSimplePower(leftPower, rightPower);
        rotate_status = true;
        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On counter clockwise turn we have to get off zero first.
            //while (opModeIsActive() && getAngle() == 0) { telemetry.update(); }
            while (opModeIsActive() && getAngle() > degrees) { telemetry.update(); }
        }
        else 
        {   // clockwise turn.
            while (opModeIsActive() && getAngle() < degrees) { telemetry.update(); }
        }
        
        rotate_status = false;
        
        // turn the motors off.
        // rightMotor.setPower(0);
        // leftMotor.setPower(0);
        robot.setSimplePower(0.0, 0.0);

        telemetry.update(); 
        // wait for rotation to stop.
        //sleep(1000);
        telemetry.update(); 
        // reset angle tracking on new heading.
        resetAngle();
        
        telemetry.update(); 
    }
    */
    
}