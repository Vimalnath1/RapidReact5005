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
  
  /** Creates a new LineUpToShoot. */
  public LineUpToShoot(DriveTrain subsystem) {
    drivetrain=subsystem;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableEntry tv= table.getEntry("tv");
    System.out.println(tv);
    double targetVisible=0.5;
    //targetVisible.setDouble(tv);
    while (targetVisible==0.0){
      drivetrain.drive(0.05,0);
      tv= table.getEntry("tv");
      //targetVisible.setDouble(tv);
    }
    if (targetVisible==1.0){
      drivetrain.drive(0,0);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    int heightOfGoal= 8;
    int heightOfLimelight=4;
    int angleOfLimelight=45;
    double xdistance=(heightOfGoal-heightOfLimelight)/Math.tan(angleOfLimelight+y);
    double ydistance=heightOfGoal-heightOfLimelight;
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
