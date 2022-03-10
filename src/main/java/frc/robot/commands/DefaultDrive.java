// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

public class DefaultDrive extends CommandBase {
  private final DriveTrain drivetrain;
  private final DoubleSupplier leftvalue;
  private final DoubleSupplier rightvalue;
  /** Creates a new DefaultDrive. */
  public DefaultDrive(DriveTrain subsystem,DoubleSupplier LeftValue,DoubleSupplier RightValue) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain=subsystem;
    leftvalue=LeftValue;
    rightvalue=RightValue;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //drivetrain.tankdrive(leftvalue.getAsDouble(),rightvalue.getAsDouble());
    drivetrain.turnanddrive(leftvalue.getAsDouble(), rightvalue.getAsDouble(),0.75);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.tankdrive(0,0);
    drivetrain.turnanddrive(0,0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
