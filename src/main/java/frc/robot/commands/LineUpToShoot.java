// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.*;
import frc.robot.commands.AutoFeed;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class LineUpToShoot extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private final DriveTrain drivetrain;
  private final Feeder feeder;
  private final Shooter shooter;
  double targetVisible;
  double minimumChange=0.35;// from 0 to 1 is motor speed
  double limelightangle=38  ;  //in degrees
  double limelightheight=22; //in inches
  NetworkTableEntry tv;
  public static double shooterspeed;
  double distanceFromGoal;
  double rpm;
  String ballcolor;
  Timer timer;
  double x;
  NetworkTableEntry tx;
  /** Creates a new LineUpToShoot. */
  public LineUpToShoot(DriveTrain subsystem, Feeder feedersubsystem, Shooter shootersubsytem) {
    drivetrain=subsystem;
    feeder=feedersubsystem;
    shooter= shootersubsytem;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targetVisible=NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    System.out.println(targetVisible);
    /*while (targetVisible==0.0){
      drivetrain.turnanddrive(0.5,0.5,0.5);
      targetVisible=NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    }*/
    if (targetVisible==1.0){
      tx = table.getEntry("tx");
      NetworkTableEntry ty = table.getEntry("ty");
      x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      distanceFromGoal=getDistanceFromGoal(22, 101, 37, y);
      shooterspeed=((0.0008333333333*distanceFromGoal)+0.58);
      rpm=shooterspeed*5310;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ballcolor=shooter.getColor();
    
    if (Robot.color=="None" || ballcolor==Robot.color){
    if (targetVisible==1.0){
      if (x>1.0){
        drivetrain.turnanddrive(minimumChange,minimumChange,1); 
        tx=table.getEntry("tx");
        x = tx.getDouble(0.0);     
      }
      else if (x<-1.0){
       drivetrain.turnanddrive(-minimumChange,-minimumChange,1);
       tx=table.getEntry("tx");
       x = tx.getDouble(0.0);   
      }
      else{
        drivetrain.turnanddrive(0,0,0);
      }
      if (x>-1.0 && x<1.0){
      SmartDashboard.putNumber("Distance", distanceFromGoal);
      SmartDashboard.putNumber("speed",shooterspeed);
      SmartDashboard.putNumber("rpm",rpm);   
      shooter.shootball(shooterspeed);
      if ((-1*Shooter.rightencoder.getVelocity())>=rpm && Shooter.leftencoder.getVelocity()>=rpm){
          feeder.feedball(1);
      }
    }
      //new AutoFeed(feeder, shooter, shooterspeed);
      
   }
  //}
    else if (targetVisible==0){
      /*
      drivetrain.turnanddrive(minimumChange,minimumChange,minimumChange);
      targetVisible= NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      */
      //new AutoFeed(feeder, shooter, 0.7);
      System.out.println("No tape");
    }
  }
  else if (ballcolor!=Robot.color){
      shooter.shootball(0.4);
      rpm=0.4*5310;
      if ((-1*Shooter.rightencoder.getVelocity())>=rpm && Shooter.leftencoder.getVelocity()>=rpm){
        feeder.feedball(1);
    }
    }
  }

                                                                                                                        //This is y
public double getDistanceFromGoal(double heightOfLimelight, double heightOfGoal, double angleOfLimelight,double Angle){
  Angle+=angleOfLimelight;
  Angle=Math.toRadians(Angle);
  return (heightOfGoal-heightOfLimelight)/Math.tan(Angle);
}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      feeder.feedball(0); 
      drivetrain.turnanddrive(0, 0, 0);
      shooter.shootball(0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }
  