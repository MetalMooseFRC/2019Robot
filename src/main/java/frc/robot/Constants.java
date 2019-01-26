package frc.robot;

public class Constants {
     /**DRIVETRAIN */ 
    //Drive stick deadband, minimum axis input 
    public static double driveStickMinimumInput = 0.05; 
     
    //turning constant for arcade drive 
    public static double turnGain = 0.5; 
 
    //Drive PID 
    public static double driveP = 0.05; 
    public static double driveI = 0.00001; 
    public static double driveD = 0; 
    public static double PIDDriveErrorMargin = 0.1; 

    //Reflectance threshhold
    public static double reflectanceThreshHold = 2.5;

    //Line follow speed
    public static double lineFollowSpeed = 0.3;

    // Values and Error for the PID of gyro
    public static double drivetrainGyroP = 0.1;
    public static double drivetrainGyroI = 0.00001;
    public static double drivetrainGyroD = 0;
    public static double drivetrainGyroPIDError = 3.5;

    /**ELEVATOR */
    //Elevator PID
    public static double elevatorP = 0.01;
    public static double elevatorI = 0.00001;
    public static double elevatorD = 0;
    public static double PIDElevatorErrorMargin = 0.1;
}