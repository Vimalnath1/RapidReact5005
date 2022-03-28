// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LineUpToShoot extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private final DriveTrain drivetrain;
  private final Feeder feeder;
  private final Shooter shooter;
  double targetVisible;
  double minimumChange=0.6;// from 0 to 1 is motor speed
  double limelightangle=38  ;  //in degrees
  double limelightheight=22; //in inches
  NetworkTableEntry tv;
  public static double shooterspeed;
  double rpm;
  public static boolean usingVision=false;
  String ballcolor;
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
    }
    /*if (targetVisible==1.0){
      drivetrain.turnanddrive(0.5,0.5,0.5);
    }*/
    usingVision=true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ballcolor=shooter.getColor();
    //if (Robot.color=="None" || ballcolor==Robot.color){
    if (targetVisible==1.0){
      NetworkTableEntry tx = table.getEntry("tx");
      NetworkTableEntry ty = table.getEntry("ty");
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double distanceFromGoal=getDistanceFromGoal(22, 101, 38, y);
      rpm=((7.039124597*distanceFromGoal)+2665.979323);
      shooterspeed=rpm/5310;
      /*if (x>0.0){
        drivetrain.turnanddrive(minimumChange,minimumChange,minimumChange);      
      }
      else if (x<0.0){
       drivetrain.turnanddrive(-minimumChange,-minimumChange,minimumChange);
      }
      else{
        drivetrain.turnanddrive(0,0,0);
      }
      if (x==0.0){*/
        SmartDashboard.putNumber("Distance", distanceFromGoal);
        SmartDashboard.putNumber("speed",shooterspeed);
        SmartDashboard.putNumber("rpm",rpm);   
      shooter.shootball(shooterspeed);
      if ((-1*Shooter.rightencoder.getVelocity())-500>=rpm && Shooter.leftencoder.getVelocity()>=rpm){
          feeder.feedball(1);
      }
      
   }
    else if (targetVisible==0){
      /*
      drivetrain.turnanddrive(minimumChange,minimumChange,minimumChange);
      targetVisible= NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      */
      System.out.println("Your bad");
    }
  //}
  /*else if (ballcolor!=Robot.color){
      shooter.shootball(0.2);
      feeder.feedball(1);
    }*/
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
  