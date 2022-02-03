// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LineUpToShoot extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private final DriveTrain drivetrain;
  double targetVisible;
  double minimumChange;// from 0 to 1 is motor speed
  double velocity;  //use rpm but change to tangential velocity in inches
  double limelightangle;  //in degrees
  double limelightheight; //in inches
  double desiredDistance; //in inches
  NetworkTableEntry tv;
  /** Creates a new LineUpToShoot. */
  public LineUpToShoot(DriveTrain subsystem) {
    drivetrain=subsystem;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targetVisible=NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    System.out.println(targetVisible);
    while (targetVisible==0.0){
      drivetrain.tankdrive(0.5,0);
      targetVisible=NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    }
    if (targetVisible==1.0){
      drivetrain.tankdrive(0,0);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (targetVisible==1.0){
      NetworkTableEntry tx = table.getEntry("tx");
      NetworkTableEntry ty = table.getEntry("ty");
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightY", y);
      double distanceFromGoal=getDistanceFromGoal(limelightheight, 96, limelightangle, y);
      if (x>0.0/*if the crosshair is too right (change value if wrong)*/){
        drivetrain.tankdrive(0,minimumChange);      
      }
      else if (x<0.0/*if the crosshair is too left (change value if wrong)*/){
       drivetrain.tankdrive(minimumChange,0);
      }
      else{
        drivetrain.tankdrive(0,0);
      }
      /*Kind of like the stuff above except instead of turning, it moves forward or backwards */
      /*if (distanceFromGoal-desiredDistance>0.0){
        drivetrain.driveDistance(distanceFromGoal-desiredDistance);
        Make new command. This command takes a distance in inches and 
        using encoders or something, it will go that ammount.
      }
      else if (distanceFromGoal-desiredDistance<0.0){
        drivetrain.driveDistance(distanceFromGoal-desiredDistance);
      }*/
   }
    else if (targetVisible==0){
      while (targetVisible==0.0){
        drivetrain.tankdrive(minimumChange,0);
        targetVisible= NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      }
    }
  }                                                                                                        //This is y
    public double getDistanceFromGoal(double heightOfLimelight, double heightOfGoal, double angleOfLimelight,double Angle){
      Angle+=angleOfLimelight;
      Angle=Math.toRadians(Angle);
      return (heightOfGoal-heightOfLimelight)/Math.tan(Angle);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }
  