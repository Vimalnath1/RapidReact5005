// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveToGoal extends CommandBase {
  private DriveTrain drivetrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  double desiredDistance= 153;
  /** Creates a new DriveToGoal. */
  public DriveToGoal(DriveTrain subsystem) {
    drivetrain=subsystem;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distanceFromGoal=LineUpToShoot.getDistanceFromGoal(39.125,101.625, 45,table.getEntry("ty").getDouble(0.0));
    System.out.println(distanceFromGoal);
    /*while(distanceFromGoal!=desiredDistance){
    if (distanceFromGoal-desiredDistance>0.0){
      drivetrain.tankdrive(1,1);
    }
    else
    {
      drivetrain.tankdrive(-1, -1);
    }
  }*/
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
