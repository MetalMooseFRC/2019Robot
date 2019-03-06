package frc.robot;

public class Constants {

    /** Variables */
    public static boolean isOffenseMode = true;
    //0 is button pad, 1 is controller
    public static int operatorBoardMode = 1;
    
     /**DRIVETRAIN */ 
    //Drive stick deadband, minimum axis input 
    public static final double driveStickMinimumInput = 0.05; 
     
    //turning constant for arcade drive 
    public static double turnGain = 0.5; 
 
    //Drive PID 
    public static double driveP = 0.015; 
    public static double driveI = 0.00001; 
    public static double driveD = 0; 
    public static double PIDDriveErrorMargin = 0.1; 

    //Reflectance threshhold
    public static double reflectanceThreshHold = 2.5;

    //Line follow correction if it sees two sensors
    public static double lineFollowCorrection = 0.1;

    //Line follow corrction if it sees one sensor
    public static double lineFollowDoubleCorrection = 0.2;

    // Values and Error for the PID of gyro
    public static double drivetrainGyroP = 0.01;
    public static double drivetrainGyroI = 0;
    public static double drivetrainGyroD = 0;
    public static double drivetrainGyroPIDError = 3.5;


    /**ELEVATOR */
    //Minimum joystick input
    public static final double elevatorStickMinimumInput = 0.1;
    //Elevator PID
    public static double elevatorP = 0.09;
    public static double elevatorI = 0.000015;
    public static double elevatorD = 0;
    //in encoder rotations
    public static double PIDElevatorErrorMargin = 0.5;

    public static double elevatorXP = 0.05;
    public static double elevatorXI = 0.0001;
    public static double elevatorXD = 0;
    //In tics
    public static double elevatorXMargin = 150;

    //in tics
    public static double elevatorLimit = 47;

    //speed to hold elevator against gravity
    public static double elevatorHoldSpeed = 0.07;

    //Range for elevatorX limit
    public static final double elevatorXLimit = 10000;

    //vision PID
    public static double visionP = 0.01;
    public static double visionI = 0.00001;
    public static double visionMargin = 5;

    //Heights for ports
    public static double port1Height = 12;
    public static double port2Height = 28;
    public static double port3Height = 44;

    //Heights for hatch holes
    public static double hatch1Height = 0;
    public static double hacth2Height = 16.5;
    public static double hacth3Height = 33;


    /**COLLECTOR */
    public static double intakeSpeed = 0.35;
    public static double outtakeSpeed = -0.5;
    public static double holdSpeed = 0.2;

    //PID for arm positioning
    public static double armP = 0.01;
    public static double armI = 0;
    public static double armD = 0;
    public static double armMargin = 50;

    //In encoder tics to optimal position for intaking balls
    public static double armBallCollectingPosition = 2000;



}