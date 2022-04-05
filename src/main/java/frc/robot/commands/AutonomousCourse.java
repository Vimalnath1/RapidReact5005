// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCourse extends SequentialCommandGroup {
  /** Creates a new AutonomousCourse. */
  public AutonomousCourse(Shooter shooter,Feeder feeder, DriveTrain drivetrain,Loader loader) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootBall(shooter, 0.59).withTimeout(2),
      new GoodAutonomousCourse(shooter, feeder,0.59),
      new DefaultDrive(drivetrain, ()->0.4, ()->0.3).withTimeout(2),
      new ReleaseLoader(loader, -1).withTimeout(1),
      new LoadBall(loader).withTimeout(2.5),
      new ShootBall(shooter, 0.63).withTimeout(2),
      new GoodAutonomousCourse(shooter, feeder,0.63)
      /*new DefaultDrive(drivetrain,()->1,()->1).withTimeout(10),
      new LoadBall(loader).withTimeout(10),
      new LineUpToShoot(drivetrain,feeder,shooter)*/
    );
  }
}
