// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveTrain m_drivetrain=new DriveTrain();
  private final Shooter m_shooter=new Shooter();
  private  final Loader m_loader = new Loader();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Climber m_climber = new Climber();
  private final Feeder m_feeder=new Feeder();

  public static Joystick controller=new Joystick(1);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_drivetrain.setDefaultCommand(
      new DefaultDrive(m_drivetrain,()->controller.getRawAxis(0), ()->controller.getRawAxis(1))
      );
    m_climber.setDefaultCommand(
      new RobotClimb(m_climber,()->-controller.getRawAxis(2))
      );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller,1).whileHeld(new ShootBall(m_shooter));
    //new JoystickButton(controller,3).whileHeld(new LineUpToShoot(m_drivetrain));
    new JoystickButton(controller,4).whileHeld(new LoadBall(m_loader));
    new JoystickButton(controller,6).whileHeld(new ReleaseLoader(m_loader,true));
    new JoystickButton(controller,7).whileHeld(new ReleaseLoader(m_loader,false));
    //new JoystickButton(controller,5).whenHeld(new DriveToGoal(m_drivetrain));
    new JoystickButton(controller, 3).whileHeld(new Turbo(m_drivetrain));
    new JoystickButton(controller, 8).whenPressed(new StopClimber(m_climber));
    new JoystickButton(controller, 9).whenPressed(new RunClimber(m_climber));
    new JoystickButton(controller, 2).whileHeld(new FeedBall(m_feeder));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
