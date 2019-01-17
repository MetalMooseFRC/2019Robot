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
    public static double PIDErrorMargin = 0.1;

}