// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class DriveTrain extends SubsystemBase {
  Spark leftfront;
  Spark rightfront;
  Spark leftback;
  Spark rightback;
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    //leftfront=new Spark(1);
    //rightfront=new Spark(2);
    leftback=new Spark(0);
    rightback=new Spark(1);
  }
  public void drive(double leftamount,double rightamount){
    leftback.set(leftamount);
    rightback.set(rightamount);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
