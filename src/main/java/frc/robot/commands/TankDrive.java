package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.Timer;

public class TankDrive extends CommandBase {
  private final DriveTrain drivetrain;
  private final DoubleSupplier leftvalue;
  private final DoubleSupplier rightvalue;
  Timer timer;
  /** Creates a new DefaultDrive. */
  public TankDrive(DriveTrain subsystem,DoubleSupplier LeftValue,DoubleSupplier RightValue) {
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
    drivetrain.tankdrive(leftvalue.getAsDouble(),rightvalue.getAsDouble());
    //drivetrain.turnanddrive(leftvalue.getAsDouble(), rightvalue.getAsDouble(),0.75);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.turnanddrive(0,0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

