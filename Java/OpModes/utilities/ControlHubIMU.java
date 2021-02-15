package OpModes.utilities;

import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.stream.Collector;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


public class ControlHubIMU {
    UGRobot robot;
    HardwareMap hwMap           =  null;
    Telemetry telemetry;
    // The IMU sensor object
    BNO055IMU imu;
    //MecanumChassis robot;

    // State used for updating telemetry
    Orientation angles;
    Acceleration gravity;

    Orientation             lastAngles = new Orientation();
    double                  globalAngle, power = .30, correction;
    
    boolean rotate_status = false;

    public ControlHubIMU (UGRobot _robot/*MecanumChassis chassis, Telemetry tIn, HardwareMap ahwMap*/) {
        // Set robot reference
        robot = _robot;
        // Save reference to Hardware map
        //hwMap = ahwMap;
        hwMap = robot.hardwareMap;
        // Create the object specific variables from the OpMode
        //telemetry = tIn;
        telemetry = robot.telemetry;
        //robot = chassis;
        
        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        // make sure the imu gyro is calibrated before continuing.
        while (!robot.linearOpMode.isStopRequested() && !imu.isGyroCalibrated())
        {
            robot.linearOpMode.sleep(50);
            robot.linearOpMode.idle();
        }

    }
    
    public String getCalibrationStatus() {
        return imu.getCalibrationStatus().toString();
    }
    public void startIMUTracking() {
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }
    
    //----------------------------------------------------------------------------------------------
    // Reset cumulative angle tracking to zero
    //----------------------------------------------------------------------------------------------

    public void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        //globalAngle = 0.0;
        robot.robotAngle = 0.0;
    }

    //----------------------------------------------------------------------------------------------
    // Get current cumulative angle rotation from last reset.
    // @return Angle in degrees. + = left, - = right.
    //----------------------------------------------------------------------------------------------

    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        //globalAngle += deltaAngle;
        robot.robotAngle += deltaAngle;
        
        lastAngles = angles;

        //return globalAngle;
        return robot.robotAngle;
    }
    
}
