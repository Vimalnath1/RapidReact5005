// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootBall extends CommandBase {
  Shooter shooter;
  String ballcolor;
  double shootingspeed;
  Timer timer;
  /** Creates a new ShootBall. */
  public ShootBall(Shooter subsystem, double speed) {
    shooter=subsystem;
    shootingspeed=speed;
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ballcolor=shooter.getColor();
    /*if (Robot.color!="None"){
      if (ballcolor==Robot.color){
        shooter.shootball(0.58);
      }
      else{
        shooter.shootball(0.2);
      }
    }
    else{
      shooter.shootball(0.58);
    }*/
    shooter.shootball(shootingspeed);
    //Actual:116 Robot Read:113.445700-60%
    //Actual:121 Robot Read:165.715930-68%
    //Actual:126 Robot Read:165.715930-75%
  }
    //System.out.println(ballcolor);
    //68%-19ft
    //63%-11ft
    //58%-5ft
    //40% for 1 point at 5ft 
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.shootball(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
