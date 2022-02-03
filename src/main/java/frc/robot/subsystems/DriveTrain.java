// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
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
    leftback=new Spark(Constants.leftbacknumber);
    rightback=new Spark(Constants.rightbacknumber);
  }
  public void tankdrive(double leftamount,double rightamount){
    leftback.set(leftamount);
    rightback.set(rightamount);
    //System.out.println(leftamount);
    //System.out.println(rightamount);
  }
  public void turnanddrive(double xAxis,double yAxis){
    if (xAxis<0 && yAxis>0 || xAxis>0 && yAxis<0){
      leftback.set(yAxis);
      rightback.set(yAxis-xAxis);
    }
    else if(xAxis>0 && yAxis>0||xAxis<0 && yAxis<0){
      leftback.set(yAxis+xAxis);
      rightback.set(yAxis);
    }
    else{
      leftback.set(yAxis);
      rightback.set(yAxis);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
