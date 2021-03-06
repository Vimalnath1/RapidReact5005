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
  private final AutonomousCourse m_autonomouscourse=new AutonomousCourse(m_shooter,m_feeder,m_drivetrain,m_loader);

  public static Joystick controller=new Joystick(1);
  public static Joystick newcontroller=new Joystick(0);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    if (controller.getRawAxis(0)>0 || controller.getRawAxis(1)>0 || controller.getRawAxis(0)<0 || controller.getRawAxis(1)<0){
    m_drivetrain.setDefaultCommand(
      new DefaultDrive(m_drivetrain,()->controller.getRawAxis(0), ()->controller.getRawAxis(1))
    //new DefaultDrive(m_drivetrain,()->controller.getRawAxis(0), ()->controller.getRawAxis(1))
      );
    }
    else{
      m_drivetrain.setDefaultCommand(
      new DefaultDrive(m_drivetrain,()->newcontroller.getRawAxis(0), ()->newcontroller.getRawAxis(1))
    //new DefaultDrive(m_drivetrain,()->controller.getRawAxis(0), ()->newcontroller.getRawAxis(1))
      );
    }
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller,1).whileHeld(new LineUpToShoot(m_drivetrain,m_feeder,m_shooter));
    new JoystickButton(controller, 2).whileHeld(new FeedBall(m_feeder));
    new JoystickButton(controller, 3).whileHeld(new Turbo(m_drivetrain));
    new JoystickButton(controller,4).whileHeld(new LoadBall(m_loader,0.6));
    new JoystickButton(controller,5).whileHeld(new ShootBall(m_shooter,0.75));
    new JoystickButton(controller,6).whileHeld(new ReleaseLoader(m_loader,1));
    new JoystickButton(controller,7).whileHeld(new ReleaseLoader(m_loader,-1));
    //new JoystickButton(controller, 7).whenPressed(new ReleaseLoader(m_loader,-1));
    //new JoystickButton(controller, 7).whileHeld(new LoadBall(m_loader));
    //new JoystickButton(controller, 7).whenReleased(new ReleaseLoader(m_loader, 1));
    new JoystickButton(controller, 8).whileHeld(new RobotClimb(m_climber,1));
    new JoystickButton(controller, 9).whileHeld(new RobotClimb(m_climber,-0.5));
    new JoystickButton(controller, 10).whenPressed(new AutonomousCourse(m_shooter,m_feeder,m_drivetrain,m_loader));
   

    //For the other joystick:
    new JoystickButton(newcontroller,1).whileHeld(new LineUpToShoot(m_drivetrain,m_feeder,m_shooter));
    new JoystickButton(newcontroller,9).whileHeld(new LoadBall(m_loader,-0.7));
    new JoystickButton(newcontroller,2).whileHeld(new LoadBall(m_loader,-1));
    new JoystickButton(newcontroller,5).whileHeld(new ReleaseLoader(m_loader,1));
    new JoystickButton(newcontroller,3).whileHeld(new ReleaseLoader(m_loader,-1));
    new JoystickButton(newcontroller,4).whileHeld(new RobotClimb(m_climber,1));
    new JoystickButton(newcontroller,6).whileHeld(new RobotClimb(m_climber,-0.5));
    new JoystickButton(newcontroller,11).whileHeld(new Turbo(m_drivetrain));
    new JoystickButton(newcontroller,7).whenPressed(new AutoFeed(m_feeder, m_shooter,0.6));
    new JoystickButton(newcontroller,8).whileHeld(new FeedBall(m_feeder));
    new JoystickButton(newcontroller, 10).whenPressed(new AutonomousCourse(m_shooter,m_feeder,m_drivetrain,m_loader));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonomouscourse;
  }
}
