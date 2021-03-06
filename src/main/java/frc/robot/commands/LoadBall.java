// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Loader;
import edu.wpi.first.wpilibj.Timer;

public class LoadBall extends CommandBase {
  Loader loader;
  Timer timer;
  double speed;
  /** Creates a new LoadBall. */
  public LoadBall(Loader subsystem, double loadspeed) {
    loader=subsystem;
    speed=loadspeed;
    addRequirements(loader);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    loader.loadball(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    loader.loadball(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
