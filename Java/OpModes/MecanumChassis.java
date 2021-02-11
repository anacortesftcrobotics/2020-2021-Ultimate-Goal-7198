package OpModes;

import com.qualcomm.robotcore.hardware.Blinker;
import java.util.Locale;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumChassis {
    private Blinker control_Hub;
    private Gyroscope imu;
    private DcMotor leftback;
    private DcMotor leftfront;
    private DcMotor rightback;
    private DcMotor rightfront;

    
    // local OpMode members.
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    // Constructor
    public MecanumChassis(){

    }

    // Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftfront = hwMap.get(DcMotor.class, "leftfront");
        rightfront = hwMap.get(DcMotor.class, "leftback");
        leftback = hwMap.get(DcMotor.class, "rightfront");
        rightback = hwMap.get(DcMotor.class, "rightback");

        // Reverses the two left motors
        leftfront.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        leftback.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rightfront.setDirection(DcMotor.Direction.FORWARD);
        rightback.setDirection(DcMotor.Direction.FORWARD);
        
        /*
        // Resets all motor encoders
        leftfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightfront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set all motors to use encoders
        leftfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        */
        // Set all motors to brake at power 0
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightback.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
        // return "leftfront: " + leftfront.getPower() +
        //       "\nrightfront: " + rightfront.getPower() +
        //       "\nleftback: " + leftback.getPower() +
        //       "\nrightback: " + rightback.getPower();
    }
    
    // Sets the power on the motors directly based on a left and right value
    public void setSimplePower(double left, double right) {
        leftfront.setPower(left);
        rightfront.setPower(right);
        leftback.setPower(left);
        rightback.setPower(right);
        //return "Left: " + left + " Right: " + right;
    }
}