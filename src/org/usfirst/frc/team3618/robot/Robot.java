
package org.usfirst.frc.team3618.robot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3618.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.BallIntakeSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.GearLiftSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team3618.robot.vision.Pipeline;
import org.usfirst.frc.team3618.sensorlib.ADIS16448_IMU;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

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
	public static final GearLiftSubsystem gearLiftSubsystem = new GearLiftSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final BallIntakeSubsystem ballIntakeSubsystem = new BallIntakeSubsystem();
	public static final AgitatorSubsystem agitatorSubsystem = new AgitatorSubsystem();

    Command autonomousCommand;
    private ADIS16448_IMU gyro;
    
    VisionThread visionThread;
	private final Object imgLock = new Object();
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240; 
	public static double dCx;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
//        chooser.addObject("My Auto", new MyAutoCommand());
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setExposureManual(100);
        CvSink cvSink = CameraServer.getInstance().getVideo();
        CvSource blobStream = CameraServer.getInstance().putVideo("blobPoints", 640, 480); 
        CvSource thresholdStream = CameraServer.getInstance().putVideo("Threshold", 640, 480);
	    final int IMG_CENTER = IMG_WIDTH / 2;
		final Object imgLock = new Object();
	    
	    visionThread = new VisionThread(camera, new Pipeline(), pipeline -> {
	    	Mat blobPoints = new Mat();
	    	Mat thresholdImg = new Mat();
	    	synchronized (imgLock) {
				List<KeyPoint> blobs = pipeline.findBlobsOutput().toList();
				System.out.println("Blobs: " + blobs.size());
				cvSink.grabFrame(blobPoints);
				for (KeyPoint blob : blobs) {
					Imgproc.drawMarker(blobPoints, blob.pt, new Scalar(255,0,0));
				}
				blobStream.putFrame(blobPoints);
				thresholdImg = pipeline.hsvThresholdOutput();
				thresholdStream.putFrame(thresholdImg);
				if (blobs.size() > 1) {
					Collections.sort(blobs, new Comparator<KeyPoint>() {
						@Override
						public int compare(KeyPoint arg0, KeyPoint arg1) {
							// TODO Auto-generated method stub
							return (arg0.size > arg1.size) ? -1 : (arg0.size < arg1.size) ? 1 : 0;
						}
					});
					dCx = ((blobs.get(0).pt.x + blobs.get(1).pt.x)/2) - IMG_CENTER;
					System.out.println(dCx);
				}
	    	}
	    });
	    visionThread.start();
		gyro = new ADIS16448_IMU();
		gyro.calibrate();
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
    
    public void robotPeriodic() {
    	SmartDashboard.putNumber("Robot Angle", gyro.getAngleZ());
    }
}
