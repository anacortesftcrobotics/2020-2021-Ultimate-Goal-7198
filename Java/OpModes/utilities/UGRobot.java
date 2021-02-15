package OpModes.utilities;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.stream.Collector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class UGRobot {
    // Define the Telemetry and Hardware objects
    Telemetry telemetry;
    HardwareMap hardwareMap;

    // Define the objects for the other Utilities
    public MecanumChassis chassis;
    public ControlHubIMU imu;
    public double robotAngle = 0.0;

    // Define other hardware devices that don't have separate Utility classes
    public LinearOpMode linearOpMode;
    
    public UGRobot(Telemetry tIn, HardwareMap mapIn, OpMode opModeIn) {

        // Create the object specific variables from the OpMode
        telemetry = tIn;
        hardwareMap = mapIn;
        if (opModeIn instanceof LinearOpMode) {
            linearOpMode = (LinearOpMode) opModeIn;
        }

        // Construct the field objects
        chassis = new MecanumChassis(this);
        imu = new ControlHubIMU(this);
    }
}