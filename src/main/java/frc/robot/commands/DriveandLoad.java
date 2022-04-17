// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveandLoad extends ParallelCommandGroup {
  /** Creates a new DriveandLoad. */
  public DriveandLoad(DriveTrain drivetrain, Loader loader) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //new DefaultDrive(drivetrain, ()->0.2, ()->0.4).withTimeout(1.5),
      new TankDrive(drivetrain, ()->-0.4, ()->0.434).withTimeout(2.25),
      new LoadBall(loader,-0.8).withTimeout(2.25)
    );
  }
}
