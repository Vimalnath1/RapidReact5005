// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GoodAutonomousCourse extends ParallelCommandGroup {
  /** Creates a new GoodAutonomousCourse. */
  public GoodAutonomousCourse(Shooter shooter,Feeder feeder, double speed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootBall(shooter,speed).withTimeout(1),
      new FeedBall(feeder).withTimeout(1)
    );
  }
}
