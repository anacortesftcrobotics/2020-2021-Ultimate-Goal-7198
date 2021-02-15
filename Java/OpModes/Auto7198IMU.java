/* code taking from FIRST example IMU */

package OpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import java.util.Locale;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import OpModes.utilities.*;
import java.util.Locale;

@Autonomous(name = "Auto7198IMU", group = "Sensor")

public class Auto7198IMU extends LinearOpMode
    {
    // Define robot object
    private UGRobot robot;
    
    //----------------------------------------------------------------------------------------------
    // Main logic
    //----------------------------------------------------------------------------------------------

    @Override public void runOpMode() {
        
        /*
         * Initialize the standard drive system variables.
         * The init() method of the hardware class does most of the work here
         */
        robot = new UGRobot(telemetry, hardwareMap, this);

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus());
        telemetry.update();
        
        // wait for start button.

        // Set up our telemetry dashboard
        composeTelemetry();

        // Wait until we're told to go
        waitForStart();

        // Start the logging of measured acceleration
        //robot.imu.startIMUTracking(); // Liam says this isn't used/needed
        
        // Loop and update the dashboard
        while (opModeIsActive()) {
            telemetry.update();
            sleep(3000);

            robot.imu.resetAngle();
            // code tests robot can turn on around for 3 sec
            
            /*
            // this code would make a good test case, get it into it's own program
            // simple spin
            robot.chassis.setSimplePower(0.25, -0.25);
            telemetry.update();
            sleep(3000);
            robot.chassis.setSimplePower(0, 0);
            telemetry.update();
            
            // move forward simple power
            robot.chassis.setSimplePower(0.25, 0.25);
            telemetry.update();
            sleep(3000);
            robot.chassis.setSimplePower(0, 0);
            telemetry.update();
            */
            /*
            // code tests robot can turn 90 deg (counter clockwise) before stopping
            robot.chassis.rotate(90, 0.25);
            getAngle();
            telemetry.update();
            sleep(3000);

            // code tests robot can turn -90 deg (clockwise) before stopping
            robot.chassis.rotate(-90, 0.25);
            getAngle();
            telemetry.update();
            sleep(3000);
            */
            
            robot.chassis.controlMecanum("diagonalright", 12, 0.25);
            telemetry.update();
            sleep(3000);
            
            
            sleep(3000);
            robot.chassis.controlMecanum("forward", 12, 0.25);
            telemetry.update();
            
            sleep(3000);
            robot.chassis.controlMecanum("right", 12, 0.25);
            telemetry.update();
            
            sleep(3000);
            robot.chassis.controlMecanum("left", 12, 0.25);
            telemetry.update();
            sleep(3000);
            
            
        }
    }

    //----------------------------------------------------------------------------------------------
    // Telemetry Configuration
    //----------------------------------------------------------------------------------------------

    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        /*
        telemetry.addAction(new Runnable() { @Override public void run()
                {
                // Acquiring the angles is relatively expensive; we don't want
                // to do that in each of the three items that need that info, as that's
                // three times the necessary expense.
                angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity  = imu.getGravity();
                }
            });
        
        telemetry.addLine()
            .addData("status", new Func<String>() {
                @Override public String value() {
                    return imu.getSystemStatus().toShortString();
                    }
                })
            .addData("calib", new Func<String>() {
                @Override public String value() {
                    return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
            .addData("heading", new Func<String>() {
                @Override public String value() {
                    return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
            .addData("roll", new Func<String>() {
                @Override public String value() {
                    return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
            .addData("pitch", new Func<String>() {
                @Override public String value() {
                    return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
            .addData("grvty", new Func<String>() {
                @Override public String value() {
                    return gravity.toString();
                    }
                })
            .addData("mag", new Func<String>() {
                @Override public String value() {
                    return String.format(Locale.getDefault(), "%.3f",
                            Math.sqrt(gravity.xAccel*gravity.xAccel
                                    + gravity.yAccel*gravity.yAccel
                                    + gravity.zAccel*gravity.zAccel));
                    }
                });
        telemetry.addLine()
            .addData("lastAngles", new Func<String>() {
                @Override public String value() {
                    return lastAngles.toString();
                    }
                });
        
        telemetry.addLine()
            .addData("globalAngle", new Func<String>() {
                @Override public String value() {
                    return String.format(Locale.getDefault(), "%.3f", globalAngle);
                    }
                });
        telemetry.addLine()
            .addData("rotate_status", new Func<String>() {
                @Override public String value() {
                    return String.format(Locale.getDefault(), "%b", rotate_status);
                    }
                });
        */
        telemetry.addLine()
            .addData("robot", new Func<String>() {
                @Override public String value() {
                    return robot.toString();
                    }
                });

    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------
    /*
    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
    */
}
