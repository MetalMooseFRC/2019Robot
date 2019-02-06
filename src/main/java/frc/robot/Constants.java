package frc.robot;

public class Constants {
     /**DRIVETRAIN */ 
    //Drive stick deadband, minimum axis input 
    public static final double driveStickMinimumInput = 0.1; 
     
    //turning constant for arcade drive 
    public static double turnGain = 0.5; 
 
    //Drive PID 
    public static double driveP = 0.015; 
    public static double driveI = 0.00001; 
    public static double driveD = 0; 
    public static double PIDDriveErrorMargin = 0.1; 

    //Reflectance threshhold
    public static double reflectanceThreshHold = 2.5;

    //Line follow speed
    public static double lineFollowSpeed = 0.3;

    // Values and Error for the PID of gyro
    public static double drivetrainGyroP = 0.01;
    public static double drivetrainGyroI = 0;
    public static double drivetrainGyroD = 0;
    public static double drivetrainGyroPIDError = 3.5;


    /**ELEVATOR */
    //Minimum joystick input
    public static final double elevatorStickMinimumInput = 0.1;
    //Elevator PID
    public static double elevatorP = 0.01;
    public static double elevatorI = 0.00001;
    public static double elevatorD = 0;
    public static double PIDElevatorErrorMargin = 0.1;

    public static double elevatorXP = 0.1;
    public static double elevatorXI = 0;
    public static double elevatorXD = 0;
    //In tics
    public static double elevatorXMargin = 300;

    //Range for elevatorX limit
    public static final double elevatorXLimit = 8000;

    public static final double encoderRotationsToDistanceCoefficient = 1;
    public static final double encoderTicsToDistanceCoefficient = 1;//227.556;

}