//import tools here
import java.lang.Math;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp

public class WillsCode extends OpMode {
    
    //add variables
    public static float[] lCoords = {0,0};
    public static float[] rCoords = {0};
    public static float lMagnitude = 0;
    public static double lDirection;
    public static String thing = "";
    public static boolean Dleft = false;
    public static boolean Dright = false;
    public static boolean left_bumber = false;
    public static boolean grabbing = false;
    
    //add motors
    //public DcMotor collector;
    //public DcMotor shooter;
    public DcMotor lBack;
    public DcMotor lFront;
    public DcMotor rBack;
    public DcMotor rFront;
    //public DcMotor move;
    //public Servo grab;
    //public Servo basket;
    public void init() {
    
    //arm motors
    /*
    move = hardwareMap.get(DcMotor.class, "move");
    move.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    move.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    move.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    grab = hardwareMap.get(Servo.class, "grab");
    basket = hardwareMap.get(Servo.class, "basket");
    */
    
    //drive motors
        lBack = hardwareMap.get(DcMotor.class, "leftBack");
        lBack.setDirection(DcMotor.Direction.REVERSE);
        lFront = hardwareMap.get(DcMotor.class, "leftFront");
        lFront.setDirection(DcMotor.Direction.REVERSE);
        rBack = hardwareMap.get(DcMotor.class, "rightBack");
        rFront = hardwareMap.get(DcMotor.class, "rightFront");
        //collector = hardwareMap.get(DcMotor.class, "collector");
        //shooter = hardwareMap.get(DcMotor.class, "shooter");
    
        //motor changes 
        /*lBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        collector.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        collector.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/
        lBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        //?
        //grab.setPosition(0);
        //basket.setPosition(0);
        
    }
    
    //controller 1 controls
    public void controller1 () {
        //left stick control
        lCoords[0] = -gamepad1.left_stick_x;
        lCoords[1] = -gamepad1.left_stick_y;
        lDirection = ((float) Math.toDegrees(Math.atan(lCoords[1] / lCoords[0])));
        if (lCoords[0] > 0) {
            if (lCoords[1] > 0) {
                lDirection += 180;
            } else {
                lDirection -= 180;
            }
        }
        lDirection += 360;
        lDirection = 360 - lDirection;
        if (lDirection < 0) {
            lDirection += 360;
        }
        lMagnitude = ((float)Math.sqrt((lCoords[0]*lCoords[0])+(lCoords[1]*lCoords[1])));
        lMagnitude = lMagnitude * (1-gamepad1.right_trigger/2);
        double lBackPower = 0;
        double rBackPower = 0;
        double lFrontPower = 0;
        double rFrontPower = 0;
        thing = "";
        
        if (lCoords[0] > 0 || lCoords[0] < 0 || lCoords[1] > 0 || lCoords[1] < 0 ) {
            if (lDirection >= 22.5 && lDirection < 67.5) {
                //b
                //lFront and rBack go forward
                thing = "DiagonalForwardRight";
                lFrontPower = ((lMagnitude));
                rBackPower = ((lMagnitude));
            }
            
            if (lDirection >= 67.5 && lDirection < 112.5) {
                //a
                //all forwards
                thing = "Forwards";
                lFrontPower = ((lMagnitude));
                rBackPower = ((lMagnitude));
                lBackPower = ((lMagnitude));
                rFrontPower = ((lMagnitude));
            }
            
            if (lDirection >= 112.5 && lDirection   < 157.5) {
                //h
                //lBack and rFront forwards
                thing = "DiagonalForwardLeft";
                lBackPower = ((lMagnitude));
                rFrontPower = ((lMagnitude));
            }
            
            if (lDirection >= 157.5 && lDirection < 202.5) {
                //g
                //lFront and rBack backwards, lBack and rFront forwards
                thing = "Left";
                lFrontPower = (-(lMagnitude));
                rBackPower = (-(lMagnitude));
                lBackPower = ((lMagnitude));
                rFrontPower = ((lMagnitude));
            }
            
            if (lDirection >= 202.5 && lDirection < 247.5) {
                //f
                //lFront and rBack backwards
                thing = "DiagonalBackLeft";
                lFrontPower = (-(lMagnitude));
                rBackPower = (-(lMagnitude));
            }
            
            if (lDirection >= 247.5 && lDirection < 292.5) {
                //e
                //all motors fire backwards
                thing = "Backwards";
                lFrontPower = (-(lMagnitude));
                rBackPower = (-(lMagnitude));
                lBackPower = (-(lMagnitude));
                rFrontPower = (-(lMagnitude));
            }
            
            if (lDirection >= 292.5 && lDirection < 337.5) {
                //d
                //front left and back right backwards
                thing = "DiagonalBackRight";
                rFrontPower = (-(lMagnitude));
                lBackPower = (-(lMagnitude));
            }
            
            if ((lDirection >= 337.5 && lDirection < 0) || (lDirection < 22.5)) {
                //c
                //left front and right back forwards, others backwards
                thing = "Right";
                lFrontPower = ((lMagnitude));
                rBackPower = ((lMagnitude));
                lBackPower = (-(lMagnitude));
                rFrontPower = (-(lMagnitude));
            }
        }
        
        //right stick control
        rCoords[0] = -gamepad1.right_stick_x * (1-gamepad1.right_trigger/2);
        if (rCoords[0] > 0 || rCoords[0] < 0) {
            lFrontPower += (-rCoords[0]);
            rBackPower += (rCoords[0]);
            lBackPower += (-rCoords[0]);
            rFrontPower += (rCoords[0]);
        }
        
        double lPower = Math.max(Math.abs(lFrontPower),Math.abs(lBackPower) );
        double rPower = Math.max(Math.abs(rFrontPower),Math.abs(rBackPower) );
        double AbsMaxValue = Math.max(lPower,rPower);
        
        if (AbsMaxValue > 1) {
            lFront.setPower((lFrontPower/AbsMaxValue));
            lBack.setPower((lBackPower/AbsMaxValue));
            rFront.setPower((rFrontPower/AbsMaxValue));
            rBack.setPower((rBackPower/AbsMaxValue));
        } else {
            lFront.setPower((lFrontPower));
            lBack.setPower((lBackPower));
            rFront.setPower((rFrontPower));
            rBack.setPower((rBackPower));
        }
    }
    
    //controller 2 controls
    public void controller2 () {
        /*
        //wobble goal collector
        // move is arm, grab is hand
        double movePower = 0.0;
        //moving
        if (gamepad2.left_trigger > 0) {
            movePower = (gamepad2.left_trigger);
        } else if (gamepad2.right_trigger > 0) {
            movePower = (-gamepad2.right_trigger);
        } else {
            movePower = (0.0);
        }
        //grabber
        if (gamepad2.dpad_right) {
            grab.setPosition(0);
        }
        
        if (gamepad2.dpad_left) {
            grab.setPosition(0.78);
        }
        
        collector.setPower(-gamepad2.left_stick_y);
         
         
        if (gamepad2. left_bumper == true){
            basket.setPosition(0.2);
        }
        
        else if (gamepad2.left_bumper == false){
            basket.setPosition(-0.2);
        }
        
        if (gamepad2.dpad_up){
            shooter.setPower(0.7);
        }    
        else if (gamepad2.dpad_down){
            shooter.setPower(0.0);
        }    
        
        move.setPower(movePower);
        */
    }
    
    public void loop () {
        controller1 ();
        //controller2 ();
        telemetry();
    }
    
    //telemetry
    public void telemetry () {
    telemetry.addData("Direction", lDirection);
    telemetry.addData("Magnitude", lMagnitude);
    telemetry.addData("Way",thing); 
    telemetry.addData("Grabbing",grabbing); 
    telemetry.update();
    }
}
