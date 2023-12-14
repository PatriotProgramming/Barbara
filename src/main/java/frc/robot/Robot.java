package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Robot extends TimedRobot 
{

  private CANSparkMax frontLeft, frontRight, backLeft, backRight;

  private DifferentialDrive robot;
  
  private XboxController xbox;
  
  @Override
  public void robotInit() 
  {

    frontRight = new CANSparkMax(1, MotorType.kBrushless);
    backRight = new CANSparkMax(2, MotorType.kBrushless);
    frontLeft = new CANSparkMax(4, MotorType.kBrushless);
    backLeft = new CANSparkMax(3, MotorType.kBrushless);

    backRight.follow(frontRight);

    backRight.setInverted(true);
    frontRight.setInverted(true);

    robot = new DifferentialDrive(frontLeft, frontRight);

    xbox = new XboxController(0);

    robot.setSafetyEnabled(false);

    CameraServer.startAutomaticCapture();

  }

  @Override
  public void robotPeriodic() 
  {

    if(xbox.getRightTriggerAxis() > .5)
    {
      robot.arcadeDrive(xbox.getLeftY() * .75, xbox.getRightX() * .75);
    }
    else
    {
      robot.stopMotor();
    }

  }

}
