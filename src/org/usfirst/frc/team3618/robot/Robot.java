
package org.usfirst.frc.team3618.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.GearLiftSubsystem;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final GearLiftSubsystem gearSubsystem = new GearLiftSubsystem();

    Command autonomousCommand;
    


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            camera.setExposureManual(1);
            
            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource thresholdStream = CameraServer.getInstance().putVideo("Threshold", 640, 480);
            CvSource contoursStream = CameraServer.getInstance().putVideo("contours", 640, 480);
            
            int iLowH = 50;
        	int iHighH = 160;

        	int iLowS = 100;
        	int iHighS = 255;

        	int iLowV = 30;
        	int iHighV = 255;
            
            Mat source = new Mat();
            Mat imgHSV = new Mat();
            Mat imgThresholded = new Mat();
            Mat hierarchy = new Mat();
            
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, imgHSV, Imgproc.COLOR_BGR2HSV);
                Core.inRange(imgHSV, new Scalar(iLowH, iLowS, iLowV),
                		new Scalar(iHighH, iHighS, iHighV), imgThresholded); //Threshold the image
                thresholdStream.putFrame(imgThresholded);
                List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
				Imgproc.findContours(imgThresholded, contours, hierarchy, 0, 2, new Point(0, 0));
				if (contours.size() > 2) {
					Collections.sort(contours, new Comparator<MatOfPoint>() {
						@Override
						public int compare(MatOfPoint arg0, MatOfPoint arg1) {
							// TODO Auto-generated method stub
							double area0 = Imgproc.contourArea(arg0);
							double area1 = Imgproc.contourArea(arg1);
							return (area0 > area1) ? -1 : (area0 < area1) ? 1 : 0;
						}
					});

					contours = contours.subList(0, 2);
					

					
					Imgproc.drawContours(source, contours, -1, new Scalar(255,0,0));
				}
				contoursStream.putFrame(source);

            }
        }).start();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
