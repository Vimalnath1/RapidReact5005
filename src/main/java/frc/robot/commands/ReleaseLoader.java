// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Loader;

public class ReleaseLoader extends CommandBase {
  private Loader loader;
  private double direction;
  /** Creates a new ReleaseLoader. */
  public ReleaseLoader(Loader subsystem, double isInverted) {
    loader=subsystem;
    direction=isInverted;
    addRequirements(loader);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially sch eduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    loader.releaseloader(direction,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    loader.releaseloader(0, direction);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
